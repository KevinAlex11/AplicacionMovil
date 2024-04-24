package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.lavadas.lavAutoC;
import com.example.factoryclean.entidades.lavadas.lavColchonC;
import com.example.factoryclean.entidades.lavadas.lavSillonC;

import org.json.JSONObject;

public class Info_Lavadas extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    ImageButton regresar,cambiar;
    int ida,idc,ids,cant,idC,idSillon,idAuto,idColchon;
    String tipoC;
    cliente cliente;
    lavAutoC lavAutoC;
    lavColchonC lavColC;
    lavSillonC lavSillonC;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__lavadas);
        regresar=(ImageButton)findViewById(R.id.btnRegInfoLav);
        cambiar=(ImageButton)findViewById(R.id.btnCambiarPed);
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
            idC=cliente.getId_Cliente();
            tipoC=cliente.getTipoUsu();
        }
        ida=getIntent().getExtras().getInt("ida");
        ids=getIntent().getExtras().getInt("ids");
        idc=getIntent().getExtras().getInt("idc");
        cant=getIntent().getExtras().getInt("cant");
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ida>0&&ids==0&&idc==0) {
                    request = Volley.newRequestQueue(getApplicationContext());
                    cargarwsA();
                    regresarn();
                    Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_SHORT).show();
                }

                if(ida==0&&ids==0&&idc>0) {
                    request = Volley.newRequestQueue(getApplicationContext());
                    Toast.makeText(getApplicationContext(),"c",Toast.LENGTH_SHORT).show();
                    cargarwsC();
                    regresarn();
                }
                if(ida==0&&ids>0&&idc==0) {
                    request = Volley.newRequestQueue(getApplicationContext());
                    Toast.makeText(getApplicationContext(),"s",Toast.LENGTH_SHORT).show();
                    cargarwsS();
                    regresarn();
                }
            }
        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipoC.equals("n")){
                    regresarn();
                }
                else{
                    regresarc();
                }

            }
        });

    }
    //Funcion para cargar el Web Service
    public void cargarwsS(){
        //Para mandar los datos hacia la base de datos
        Toast.makeText(getApplicationContext(),""+ids,Toast.LENGTH_SHORT).show();
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaClienteInfoSillon.php?Id_PedidoSillon="+ids;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion para cargar el Web Service
    public void cargarwsA(){
        //Para mandar los datos hacia la base de datos
        Toast.makeText(getApplicationContext(),""+ida,Toast.LENGTH_SHORT).show();
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONEstadoPedAuto.php?IdPedidoAuto="+ida;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion para cargar el Web Service
    public void cargarwsC(){
        //Para mandar los datos hacia la base de datos
        Toast.makeText(getApplicationContext(),""+idc,Toast.LENGTH_SHORT).show();
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaClienteInfoColchon.php?Id_PedidoColchon="+idc;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

    //Funcion que regresa al Layout anterior
    public void regresarc(){
        Intent regresar=new Intent(this,Lavadas_Cliente.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("cliente",cliente);
        regresar.putExtras(bundle);
        startActivity(regresar);
        finish();
    }
    public void regresarn(){
        Intent regresar=new Intent(this,LavIncompletasAuto.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("cliente",cliente);
        regresar.putExtras(bundle);
        startActivity(regresar);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}