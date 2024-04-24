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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.marcaA;
import com.example.factoryclean.entidades.municipio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClientePedSillon extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    //Declaracion de las variables
    ImageButton reg;
    municipio municipio;
    cliente cliente;
    String tipoT,tipoS,mun,textmun,Tela,H,M;
    Long h,m;
    EditText domicilio, numdom, colonia,fechaLav,horaLav,numcojines,numsillones;
    Button registrar;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Spinner munST;
    ArrayList<String> listamun=new ArrayList<String>();
    int idC,idN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_ped_sillon);
        //Referencia hacia los Items del layout
        reg=(ImageButton)findViewById(R.id.btnRegSillon);
        registrar=(Button)findViewById(R.id.btnPedSillonRealizar);
        domicilio=(EditText) findViewById(R.id.txtPedSillonDomicilio);
        numdom=(EditText)findViewById(R.id.txtPedSillonNumDomicilio);
        colonia=(EditText)findViewById(R.id.txtPedSillonColonia);
        fechaLav=(EditText)findViewById(R.id.txtPedSillonFechaLav);
        horaLav=(EditText)findViewById(R.id.txtPedSillonHoraLav);
        numcojines=(EditText)findViewById(R.id.txtPedSillonNumCojines);
        numsillones=(EditText)findViewById(R.id.txtPedSillonNumeroSillones);
        munST=(Spinner)findViewById(R.id.txtMunicipioSillon);

        //Se declara la longitud del campo para la hora
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(5);
        horaLav.setFilters(FilterArray);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });


        //Se guarda la informacion que se envia por el Bundle en el objeto cliente
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            cliente=(cliente) enviado.getSerializable("cliente");
            tipoT=(String) enviado.getSerializable("tipotela");
            tipoS=(String) enviado.getSerializable("tiposillon");
            idC=cliente.getId_Cliente();
            idN=cliente.getId_Negocio();
        }


        //Se declara el click para regresar a la ventana de los tipos de pedidos
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
        //Se declara el click para realizar el registro en la base de datos
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(domicilio.getText().toString().isEmpty()||numdom.getText().toString().isEmpty()||colonia.getText().toString().isEmpty()||fechaLav.getText().toString().isEmpty()||horaLav.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Algun campo esta vacio: ",Toast.LENGTH_SHORT).show();
                }
                else{
                    String hr=horaLav.getText().toString();
                    String[] hora=hr.split(":");
                    H=hora[0];
                    M=hora[1];
                    h=Long.parseLong(H);
                    m=Long.parseLong(M);
                    Toast.makeText(getApplicationContext(),"Hora: "+h+" Minutos:"+m,Toast.LENGTH_SHORT).show();
                    if(((h>=08.0&&h<16.0)&&(m>=0&&m<=59))) {
                        Toast.makeText(getApplicationContext(), "Verificando ", Toast.LENGTH_SHORT).show();
                        //Se carga la funcion para el WebService
                        cargarwspedido();
                        tipos();
                        Toast.makeText(getApplicationContext(), "Registro con exito ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "El negocio no puede realizar su pedido a esa Hora que establecio \n\n*Abrimos de 08:00 a 16:00 ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //Se hace el pedido o se manda la señal para poser hacer uso del WebService
        request= Volley.newRequestQueue(getApplicationContext());

        cargarwsConsulta();

    }
    //Funcion que carga el Web Service para mandar la informacion hacia la base de datos
    public void cargarwspedido(){
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONInsertClientePedSillon.php?Domicilio="+domicilio.getText().toString()+"&Num_Domicilio="+numdom.getText().toString()+"&Colonia="+colonia.getText().toString()+"&Municipio="+textmun+"&Fecha_Lavada="+fechaLav.getText().toString()+"&Hora_Lavada="+horaLav.getText().toString()+"&Tipo_TelaSillon="+tipoT+"&Tipo_Sillon="+tipoS+"&Numero_Cojin="+numcojines.getText().toString()+"&Numero_Sillon="+numsillones.getText().toString()+"&Estado_PedidoSillon=i"+"&Id_Cliente="+idC+"&Id_Negocio="+idN;
        url=url.replace(" ","%20");
        request=Volley.newRequestQueue(getApplicationContext());
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion que Condulta los municipios de la tabla Municipios de la base de datos
    public void cargarwsConsulta(){
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaMunTipo.php";
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    public void regresar(){
        Intent regresar=new Intent(this,Pedido_Cliente.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        regresar.putExtras(bundlesuger);
        startActivity(regresar);
        finish();
    }
    //Funcion para regresar a la ventana
    public void tipos(){
        Intent regresar=new Intent(this,Pedido_Cliente.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        regresar.putExtras(bundlesuger);
        startActivity(regresar);
        finish();
    }

    //Funcion Response para verificar la llegada de informacion por parte del WebService
    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = null;
        //Busca la palabra clave y toma los campos de esa consulta
        json = response.optJSONArray("municipio");
        try {
            //Los guarda dentro de los objetos de Municipio
            for (int i = 0; i < json.length(); i++) {
                municipio = new municipio();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                municipio.setNombreMunicipio(jsonObject.optString("NombreMunicipio"));
                listamun.add(municipio.getNombreMunicipio());
            }
            //Crea una lista de esos datos
            ArrayAdapter<String> adpmun=new ArrayAdapter<>(ClientePedSillon.this,android.R.layout.simple_spinner_dropdown_item,listamun);
            munST.setAdapter(adpmun);
            munST.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                //Se establecen los Items de la lista
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                    mun=(String)  munST.getAdapter().getItem(i);
                    textmun=mun;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}