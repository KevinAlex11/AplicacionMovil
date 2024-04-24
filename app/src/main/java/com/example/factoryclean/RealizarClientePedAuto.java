package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;

import org.json.JSONObject;

public class RealizarClientePedAuto extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
ImageButton realizar, cancelar;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    cliente cliente;
    String domicilio,numdom,colonia,fechalav,horalav,modeloauto,tipotelaauto,tipo,textmun,textmarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_cliente_ped_auto);
        realizar=(ImageButton)findViewById(R.id.btnRealizarAuto);
        cancelar=(ImageButton)findViewById(R.id.btnCancelar);
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            cliente=(cliente) enviado.getSerializable("cliente");
            domicilio=enviado.getString("domicilio");
            numdom=enviado.getString("numdom");
            colonia=enviado.getString("colonia");
            fechalav=enviado.getString("fechalav");
            horalav=enviado.getString("horalav");
            modeloauto=enviado.getString("modeloauto");
            tipotelaauto=enviado.getString("tipotelaauto");
            tipo=enviado.getString("tipo");
            textmun=enviado.getString("textmun");
            textmarca=enviado.getString("textmarca");
        }
        realizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = Volley.newRequestQueue(getApplicationContext());
                cargarwspedido();
            }
        });
    cancelar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menu();

        }
    });
    }
    public void menu(){
        Intent inicio=new Intent(this,MainActivity.class);
        startActivity(inicio);
        finish();
    }
    public void cargarwspedido(){
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONInsertClientePedAuto.php?Domicilio="+domicilio+"&Num_Domicilio="+numdom+"&Colonia="+colonia+"&Municipio="+textmun+"&Fecha_Lavada="+fechalav+"&Hora_Lavada="+horalav+"&Tipo_Auto="+tipo+"&Marca_Auto="+textmarca+"&Modelo_Auto="+modeloauto+"&Tipo_TelaAuto="+tipotelaauto+"$Id_Cliente="+cliente.getId_Cliente()+"$Id_Negocio=1";
        //url=url.replace(" ","%20");
        request= Volley.newRequestQueue(getApplicationContext());
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Enviados correctamente",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();


    }
}