package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;

import org.json.JSONObject;

import java.io.Serializable;

public class ContraNueva extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    //Declaracion de las variables
    int may,min,i,n;
    char car;
    String cont;
    Button cambiar;
    EditText con1,con2;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    cliente cliente1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contra_nueva);
        //Referencia hacia los Items del layout
        cambiar=(Button)findViewById(R.id.btncambiar);
        con1=(EditText)findViewById(R.id.txtContraNueva1);
        con2=(EditText)findViewById(R.id.txtContraNueva2);
        //Realiza la peticion hacia la red
        request= Volley.newRequestQueue(this);
        //Crea el archivo Bundle para recibir la informacion
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente1
            cliente1=(cliente) enviado.getSerializable("cliente");
        }

        Toast.makeText(getApplicationContext(),"La nueva Contraseña debe tener :\n\n   Coloca\n\n  1 Numero\n  1 Mayuscula\n  1 Minuscula\n  Debe tener mas de 8 Caracteres ",Toast.LENGTH_LONG).show();
        //Click del boton para Cambiar la contraseña del usuario
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Carga la funcion validar contraseña
                validarContra();
                if(con1.getText().toString().isEmpty()||con2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Campos vacios, ingrese la nueva contraseña ", Toast.LENGTH_SHORT).show();
                }
                else{
                    if((con1.getText().toString().equals(con2.getText().toString()))&&(may>0||min>0||i>=8||n>0)){
                        Toast.makeText(getApplicationContext(), "Cambio correctamente", Toast.LENGTH_SHORT).show();
                        //Carga la funcion del WebService y la funcion de Inicio
                        cargarws();
                        inicio();
                    }
                }
            }
        });

    }
    //Funcion para validar la contraseña
    public void validarContra(){
        may=0;
        min=0;
        n=0;
        cont="";
        cont=con1.getText().toString();
        for( i=0;i<cont.length();i++){
            car=cont.charAt(i);
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
    //Funcion que se encarga de ejecutar la peticion al WebService
    public void cargarws(){
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONContraNClienteWhoclean.php?contra="+con1.getText().toString()+"&idcliente="+cliente1.getId_Cliente();
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion para regresar a la ventana anterior
    public void regresar2(View view){
        Intent regreso2=new Intent(this,RecuperaContra.class);
        startActivity(regreso2);
        finish();
    }
    //Funcion para regresar al inicio
    public void inicio(){
        Intent inicio=new Intent(this,Login.class);
        startActivity(inicio);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}