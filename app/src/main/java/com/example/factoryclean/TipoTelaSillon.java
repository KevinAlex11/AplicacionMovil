package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.factoryclean.adaptadores.AdaptadorTipoS;
import com.example.factoryclean.adaptadores.AdaptadorTipoTS;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.tipoSill;
import com.example.factoryclean.entidades.tipoTelaSill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TipoTelaSillon extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    ImageButton regresar;
    RecyclerView recyclerView;
    ArrayList<com.example.factoryclean.entidades.tipoTelaSill> listaTiposST;
    RequestQueue request;
    String sillon;
    com.example.factoryclean.entidades.cliente cliente;
    tipoTelaSill tipoTelaSill;
    String tipoST;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tela_sillon);
        regresar=(ImageButton)findViewById(R.id.btnRegTipoTelaS);
        listaTiposST=new ArrayList<tipoTelaSill>();
        recyclerView=(RecyclerView)findViewById(R.id.listaTipoTelaSillon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        //Realiza la peticion hacia la red
        request= Volley.newRequestQueue(getApplicationContext());
        //Manda llamar la funcion de Cargar Web Service
        cargarws();
        //Se declara el click para regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
        //Crea el archivo Bundle para recibir la informacion
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
            sillon=(String) enviado.getSerializable("tipo");
        }
    }
    //Funcion para cargar el Web Service
    public void cargarws(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaTipoST.php";
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion que regresa al Layout anterior
    public void regresar(){
        Intent regresar=new Intent(this,TipoSillon.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        regresar.putExtras(bundlesuger);
        startActivity(regresar);
        finish();
    }

    //Funcion Response, devuelve un valor si encuentra datos en el Web Service
    @Override
    public void onResponse(JSONObject response) {

        JSONArray json=null;
        json=response.optJSONArray("tipoST");
        try {
            for(int i=0;i<json.length();i++){
                tipoTelaSill=new tipoTelaSill();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                tipoTelaSill.setId_TipoTelaSillon(jsonObject.optInt("Id_TipoTelaSillon"));
                tipoTelaSill.setTipoTelaSillon(jsonObject.optString("TipoTelaSillon"));
                tipoTelaSill.setDescTipoTelaSillon(jsonObject.optString("DescTipoTelaSillon"));
                tipoTelaSill.setImagenTipoTela(jsonObject.optString("ImagenTipoTela"));
                listaTiposST.add(tipoTelaSill);
            }
            AdaptadorTipoTS adaptadorTS=new AdaptadorTipoTS(listaTiposST,getApplicationContext());
            adaptadorTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Selecciono: "+listaTiposST.get(recyclerView.getChildAdapterPosition(v)).getId_TipoTelaSillon()+" "+listaTiposST.get(recyclerView.getChildAdapterPosition(v)).getTipoTelaSillon(),Toast.LENGTH_SHORT).show();
                    tipoST=listaTiposST.get(recyclerView.getChildAdapterPosition(v)).getTipoTelaSillon();
                    pedidosillon();
                }
            });
            recyclerView.setAdapter(adaptadorTS);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    //Funcion para ir hacai la ventana del pedido, mandando como datos un objeto y una variable de tipo entero
    public void pedidosillon(){
        Intent siguiente=new Intent(this, ClientePedSillon.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        bundle.putSerializable("tipotela",tipoST);
        bundle.putSerializable("tiposillon",sillon);
        siguiente.putExtras(bundle);
        startActivity(siguiente);
        finish();

    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

}