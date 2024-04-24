package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.adaptadores.AdaptorSuger;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.sugerencia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SugerenciaN extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    ImageButton regresar;
    RecyclerView suge;
    ArrayList<sugerencia> listasugerencias;
    sugerencia sugerencia;
    cliente cliente;
    int ida;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencia_n);
        regresar = (ImageButton) findViewById(R.id.btnRegSuger);
        listasugerencias = new ArrayList<sugerencia>();
        suge = (RecyclerView) findViewById(R.id.ListaSuger);
        suge.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        suge.setHasFixedSize(true);

        Bundle enviado = getIntent().getExtras();
        if (enviado != null) {
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente = (cliente) enviado.getSerializable("cliente");
        }
        request = Volley.newRequestQueue(this);
        cargarwsSuger();
        //Se declara el click para regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarn();

            }
        });
    }


    //Funcion para cargar el Web Service
    public void cargarwsSuger(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaSugerenciaCliente.php";
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion que regresa al Layout anterior
    public void regresarn(){
        Intent regresar=new Intent(this,MainActivity.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("cliente",cliente);
        regresar.putExtras(bundle);
        startActivity(regresar);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = null;
        json = response.optJSONArray("sugerencia");

        try {
            for (int i = 0; i < json.length(); i++) {
                sugerencia = new sugerencia();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                sugerencia.setId_Sugerencia(jsonObject.optInt("Id_Sugerencia"));
                sugerencia.setNombre(jsonObject.optString("Nombre"));
                sugerencia.setAsunto(jsonObject.optString("Asunto"));
                sugerencia.setMensaje(jsonObject.optString("Mensaje"));
                sugerencia.setId_Cliente((jsonObject.optInt("Id_Cliente")));
                sugerencia.setId_Negocio((jsonObject.optInt("Id_Negocio")));
                listasugerencias.add(sugerencia);
            }

            AdaptorSuger adaptador = new AdaptorSuger(listasugerencias, getApplicationContext());
            adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ida = listasugerencias.get(suge.getChildAdapterPosition(v)).getId_Sugerencia();
                }
            });
            suge.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
    }


}