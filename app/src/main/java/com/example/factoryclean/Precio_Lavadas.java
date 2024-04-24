package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.precio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Precio_Lavadas extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    Button cambiar;
    ImageButton regresar;
    precio precio=new precio();
    cliente cliente;
    Spinner tipo;
    String lav,Tusu;
    int cant;
    EditText precioS,precioC,precioA,precioCoj;
    TextView preciocliente,peso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precio__lavadas);
        regresar = (ImageButton) findViewById(R.id.btnRegPrecio);
        cambiar = (Button) findViewById(R.id.btnModificarPrecio);
        preciocliente=(TextView)findViewById(R.id.lblPrecioCliente);
        peso=(TextView)findViewById(R.id.lblprecio);
        tipo=(Spinner)findViewById(R.id.spinner);
        precioS = (EditText) findViewById(R.id.txtSillon);
        precioA = (EditText) findViewById(R.id.txtAuto);
        precioC = (EditText) findViewById(R.id.txtColchon);
        precioCoj = (EditText) findViewById(R.id.txtCojin);
        request = Volley.newRequestQueue(this);
        cargarwsP();
        cant=0;
        Bundle enviado = getIntent().getExtras();
        if (enviado != null) {
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente = (cliente) enviado.getSerializable("cliente");
            cliente.getTipoUsu();

           // Toast.makeText(getApplicationContext()," ",Toast.LENGTH_SHORT).show();
            }
        ArrayList<String> tipoLav=new ArrayList<String>();
        tipoLav.add("Seleccione el tipo de lavado");
        tipoLav.add("Auto");
        tipoLav.add("Colchon");
        tipoLav.add("Sillon");
        tipoLav.add("Cojin");
        ArrayAdapter adp=new ArrayAdapter(Precio_Lavadas.this,android.R.layout.simple_spinner_dropdown_item,tipoLav);
        tipo.setAdapter(adp);
        //Seleccion del item por la posicion
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lav=(String)tipo.getAdapter().getItem(position);

                if(lav=="Auto"){
                    cargarwsP();
                    Toast.makeText(getApplicationContext(),"Selecciono Auto ",Toast.LENGTH_SHORT).show();
                    cant = 1;
                    if(cliente!=null&&cliente.getTipoUsu().equals("n")) {
                        precioA.setVisibility(View.VISIBLE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.VISIBLE);
                    }
                    else{
                        preciocliente.setText(precio.getPrecioA());
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.GONE);
                        peso.setVisibility(View.VISIBLE);
                        preciocliente.setVisibility(View.VISIBLE);

                    }



            }
                if(lav=="Colchon"){
                    cargarwsP();
                    Toast.makeText(getApplicationContext(),"Selecciono Colchon ",Toast.LENGTH_SHORT).show();
                    cant = 2;
                    if(cliente!=null&&cliente.getTipoUsu().equals("n")) {
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.VISIBLE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.VISIBLE);
                    }
                    else{
                        preciocliente.setText(precio.getPrecioC());
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.GONE);
                        peso.setVisibility(View.VISIBLE);
                        preciocliente.setVisibility(View.VISIBLE);

                    }

                }
                if(lav=="Sillon"){
                    cargarwsP();
                        Toast.makeText(getApplicationContext(),"Selecciono Sillon ",Toast.LENGTH_SHORT).show();
                        cant = 3;
                    if(cliente!=null&&cliente.getTipoUsu().equals("n")) {
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.VISIBLE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.VISIBLE);
                    }
                    else{
                        preciocliente.setText(precio.getPrecioS());
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.GONE);
                        peso.setVisibility(View.VISIBLE);
                        preciocliente.setVisibility(View.VISIBLE);

                    }

                }
                if(lav=="Cojin"){
                    cargarwsP();
                    Toast.makeText(getApplicationContext(),"Selecciono Cojin",Toast.LENGTH_SHORT).show();
                    cant = 4;
                    if(cliente!=null&&cliente.getTipoUsu().equals("n")) {
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.VISIBLE);
                        cambiar.setVisibility(View.VISIBLE);

                    }
                         else{
                        preciocliente.setText(precio.getPrecioCoj());
                        precioA.setVisibility(View.GONE);
                        precioS.setVisibility(View.GONE);
                        precioC.setVisibility(View.GONE);
                        precioCoj.setVisibility(View.GONE);
                        cambiar.setVisibility(View.GONE);
                        peso.setVisibility(View.VISIBLE);
                        preciocliente.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"El precio es por Cojin extra",Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            cambiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request = Volley.newRequestQueue(getApplicationContext());
                    cargarwsPrecio();
                    regresarn();
                }
            });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarn();
            }
        });
    }

//Funcion para cargar el Web Service
    public void cargarwsPrecio(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONPreciosNuevos.php?precioS="+precioS.getText().toString()+"&precioC="+precioC.getText().toString()+"&precioA="+precioA.getText().toString()+"&precioCoj="+precioCoj.getText().toString()+"&num="+cant;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

    //Funcion para cargar el Web Service
    public void cargarwsP(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaPreciosN.php";
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

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
               JSONArray json = response.optJSONArray("precio");
                JSONObject jsonObject = null;

            try {
                    jsonObject = json.getJSONObject(0);
                    precio.setPrecioA(jsonObject.optString("Precio_Lav_Auto"));
                    precio.setPrecioC(jsonObject.optString("Precio_Lav_Col"));
                    precio.setPrecioS(jsonObject.optString("Precio_Lav_Sillon"));
                    precio.setPrecioCoj(jsonObject.optString("Precio_Cojin"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}