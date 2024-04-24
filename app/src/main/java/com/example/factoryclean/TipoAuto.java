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
import com.example.factoryclean.entidades.autos;
import com.example.factoryclean.entidades.cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TipoAuto extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    //Declaracion de las variables
    ImageButton regresar;
    RecyclerView recyclerView;
    ArrayList<autos> listaTiposA;
    RequestQueue request;
    cliente cliente;
    autos autos;
    String tipo;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_auto);
        //Referencia hacia los Items del layout
        regresar=(ImageButton)findViewById(R.id.btnRegTipoAuto);
        listaTiposA=new ArrayList<autos>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
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
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaTipoA.php";
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
        json=response.optJSONArray("autos");
        try {
            for(int i=0;i<json.length();i++){
                autos=new autos();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                autos.setId_Tipoauto(jsonObject.optInt("Id_Tipoauto"));
                autos.setTipo(jsonObject.optString("Tipo"));
                autos.setDescTipo(jsonObject.optString("DescTipo"));
                autos.setImagen(jsonObject.optString("Imagen"));
                listaTiposA.add(autos);
            }
            AdaptadorTipoA adaptador=new AdaptadorTipoA(listaTiposA,getApplicationContext());
            adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Selecciono: "+listaTiposA.get(recyclerView.getChildAdapterPosition(v)).getId_Tipoauto()+" "+listaTiposA.get(recyclerView.getChildAdapterPosition(v)).getTipo(),Toast.LENGTH_SHORT).show();
                    tipo=listaTiposA.get(recyclerView.getChildAdapterPosition(v)).getTipo();
                    clientePedAuto();
                }
            });
            recyclerView.setAdapter(adaptador);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    //Funcion para ir hacai la ventana del pedido, mandando como datos un objeto y una variable de tipo entero
    public void clientePedAuto(){
        Intent siguiente=new Intent(this, ClientePedAuto.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        bundle.putSerializable("tipo",tipo);
        siguiente.putExtras(bundle);
        startActivity(siguiente);
        finish();

    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}