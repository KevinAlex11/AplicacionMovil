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
import com.example.factoryclean.adaptadores.AdaptadorTipoA;
import com.example.factoryclean.adaptadores.AdaptadorTipoS;
import com.example.factoryclean.entidades.autos;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.tipoSill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TipoSillon extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    ImageButton regresar;
    RecyclerView recyclerView;
    ArrayList<com.example.factoryclean.entidades.tipoSill> listaTiposS;
    RequestQueue request;
    cliente cliente;
    tipoSill tipoSill;
    String tipoS;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_sillon);
        regresar=(ImageButton)findViewById(R.id.btnRegTipoSillon);
        listaTiposS=new ArrayList<tipoSill>();
        recyclerView=(RecyclerView)findViewById(R.id.listaTipoSillon);
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

        }
    }

    //Funcion para cargar el Web Service
    public void cargarws(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaTipoS.php";
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion que regresa al Layout anterior
    public void regresar(){
        Intent regresar=new Intent(this,Pedido_Cliente.class);
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
        json=response.optJSONArray("tipoS");
        try {
            for(int i=0;i<json.length();i++){
                tipoSill=new tipoSill();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                tipoSill.setId_TipoSillon(jsonObject.optInt("Id_TipoSillon"));
                tipoSill.setTipoSillon(jsonObject.optString("TipoSillon"));
                tipoSill.setDescTipoSillon(jsonObject.optString("DescTipoSillon"));
                tipoSill.setImagenTipoSillon(jsonObject.optString("ImagenTipoSillon"));
                listaTiposS.add(tipoSill);
            }
            AdaptadorTipoS adaptadorS=new AdaptadorTipoS(listaTiposS,getApplicationContext());
            adaptadorS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Selecciono: "+listaTiposS.get(recyclerView.getChildAdapterPosition(v)).getId_TipoSillon()+" "+listaTiposS.get(recyclerView.getChildAdapterPosition(v)).getTipoSillon(),Toast.LENGTH_SHORT).show();
                    tipoS=listaTiposS.get(recyclerView.getChildAdapterPosition(v)).getTipoSillon();
                    tipoTelaSillon();
                }
            });
            recyclerView.setAdapter(adaptadorS);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    //Funcion para ir hacai la ventana del pedido, mandando como datos un objeto y una variable de tipo entero
    public void tipoTelaSillon(){
        Intent siguiente=new Intent(this, TipoTelaSillon.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        bundle.putSerializable("tipo",tipoS);
        siguiente.putExtras(bundle);
        startActivity(siguiente);
        finish();

    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}