package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;

import org.json.JSONObject;

import java.util.ArrayList;

public class Registro extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    //Declaracion de las variables
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    String negocio;
    int numN;
    int may,min,i,n;
    char car;
    private Spinner lstnegocio;
    EditText nombre,apellido,correo,telefono,usuario,contra;
    Button registrar,regresar;
    String con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Referencia hacia los Items del layout
        nombre=(EditText)findViewById(R.id.txtNombre);
        apellido=(EditText)findViewById(R.id.txtApellido);
        correo=(EditText)findViewById(R.id.txtCorreo);
        telefono=(EditText)findViewById(R.id.txtTelefono);
        usuario=(EditText)findViewById(R.id.txtCrearUsu);
        contra=(EditText)findViewById(R.id.txtCrearContra);
        registrar=(Button)findViewById(R.id.btnRegistrar);
        regresar=(Button)findViewById(R.id.btnSalir);

        //Se declara la longitud del campo para la hora
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(10);
        telefono.setFilters(FilterArray);
        //Lista de negocios, solo se encuentran en Guadalajara
        lstnegocio=(Spinner)findViewById(R.id.lstNegocio);
        ArrayList<String> negocios=new ArrayList<String>();
        negocios.add("Guadalajara");
        ArrayAdapter adp=new ArrayAdapter(Registro.this,android.R.layout.simple_spinner_dropdown_item,negocios);
        lstnegocio.setAdapter(adp);
        //Seleccion del item por la posicion
        lstnegocio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                negocio=(String)lstnegocio.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(),"Selecciona el negocio que te queda mas cerca de ti: ",Toast.LENGTH_SHORT).show();
                if(negocio=="Guadalajara"){
                    numN=1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Boton de registrar
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().isEmpty()||apellido.getText().toString().isEmpty()||correo.getText().toString().isEmpty()||telefono.getText().toString().isEmpty()||usuario.getText().toString().isEmpty()||contra.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Algun campo esta vacio",Toast.LENGTH_SHORT).show();


                }

                else{
                    //Se carga la funcion de validacion de la contraseña
                    validarContra();

                    //Se valida la contraseña
                    if(may==0||min==0||i<8||n==0){
                        Toast.makeText(getApplicationContext(),"Contraseña invalida:\n\n   Coloca\n\n  1 Numero\n  1 Mayuscula\n  1 Minuscula\n  Debe tener mas de 8 Caracteres ",Toast.LENGTH_LONG).show();
                    }
                    else {
                        //Se hace la referencia con la conexion del WebService
                        request = Volley.newRequestQueue(getApplicationContext());
                        //Se carga la funcion de cargar el WebService
                        cargarws();
                        Toast.makeText(getApplicationContext(), "Registro con exito", Toast.LENGTH_SHORT).show();
                        regresar();
                    }

                }
            }
        });



        //Boton regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
    }

    //Funcion para validar la contraseña
    public void validarContra(){
        may=0;
        min=0;
        n=0;
        con="";
        con=contra.getText().toString();
        for( i=0;i<con.length();i++){
            car=con.charAt(i);
            String texto=String.valueOf(car);
             if(texto.matches("[A,B,C,D,E,F,G,H,I,J,K,L,M,N,Ñ,O,P,Q,R,R,S,T,U,V,W,X,Y,Z]")){
                 may++;
            }
            if(texto.matches("[a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z]")){
                 min++;
            }
            if(texto.matches("[1,2,3,4,5,6,7,8,9,0]")){
                n++;
            }

        }

    }

    //Funcion para regresar a la ventana principal
    public void regresar(){
        Intent regreso=new Intent(this,Login.class);
        startActivity(regreso);
        finish();
    }

    //Funcion para cargar el WebService
    private void cargarws(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONRegClienteWhoclean.php?Nombre="+nombre.getText().toString()+"&Apellido="+apellido.getText().toString()+"&Correo="+correo.getText().toString()+"&Telefono="+telefono.getText().toString()+"&Usuario="+usuario.getText().toString()+"&Contra="+contra.getText().toString()+"&TipoUsu=c"+"&Idnegocio="+numN;
        url=url.replace(" ","%20");
        request=Volley.newRequestQueue(this);
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);

    }


    //Funcion si recibe informacion desde el WebService
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Enviados correctamente",Toast.LENGTH_SHORT).show();

    }

    //Funcion si no recibe los datos desde el WebService
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
    }
}