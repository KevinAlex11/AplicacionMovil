package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.adaptadores.AdaptadorLavC;
import com.example.factoryclean.adaptadores.AdaptadorLavR;
import com.example.factoryclean.adaptadores.AdaptadorLavS;
import com.example.factoryclean.entidades.cliente;
import com.example.factoryclean.entidades.lavadas.lavAutoC;
import com.example.factoryclean.entidades.lavadas.lavColchonC;
import com.example.factoryclean.entidades.lavadas.lavSillonC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LavIncompletasAuto extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    ImageButton regresar;
    RecyclerView Autos,Colchones,Sillones;
    ArrayList<lavAutoC> listalavadasRA;
    ArrayList<lavColchonC> listalavadasRC;
    ArrayList<lavSillonC> listalavadasRS;
    RequestQueue request;
   cliente cliente;
    lavAutoC lavAutoC;
    lavColchonC lavColC;
    lavSillonC lavSillonC;
    Spinner tipo;
    String lav,TipoUs;
    int ida,idc,ids,cant;
    int idC,idN;

    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lav_incompletas_auto);
        //Referencia hacia los Items del layout
        regresar=(ImageButton)findViewById(R.id.btnRegLavInCliente);
        listalavadasRA=new ArrayList<lavAutoC>();
        listalavadasRC=new ArrayList<lavColchonC>();
        listalavadasRS=new ArrayList<lavSillonC>();
        Autos=(RecyclerView)findViewById(R.id.ListaLavInA);
        Colchones=(RecyclerView)findViewById(R.id.ListaLavInC);
        Sillones=(RecyclerView)findViewById(R.id.ListaLavInS);
        tipo=(Spinner)findViewById(R.id.ListaTipoLav);

        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
            idC=cliente.getId_Cliente();
            idN=cliente.getId_Negocio();
            TipoUs=cliente.getTipoUsu();
            if(TipoUs.equals("c")) {
                //Realiza la peticion hacia la red
                request = Volley.newRequestQueue(this);
                cargarwsI();
            }
            if(TipoUs.equals("n")){
                //Realiza la peticion hacia la red
                request = Volley.newRequestQueue(this);
                cargarwsIN();
            }

        }

        ArrayList<String> tipoLav=new ArrayList<String>();
        tipoLav.add("Auto");
        tipoLav.add("Colchon");
        tipoLav.add("Sillon");
        ArrayAdapter adp=new ArrayAdapter(LavIncompletasAuto.this,android.R.layout.simple_spinner_dropdown_item,tipoLav);
        tipo.setAdapter(adp);
        //Seleccion del item por la posicion
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lav=(String)tipo.getAdapter().getItem(position);

                if(lav=="Auto"){
                        cant = 1;
                        Autos.setVisibility(View.VISIBLE);
                        Colchones.setVisibility(View.GONE);
                        Sillones.setVisibility(View.GONE);

                }
                if(lav=="Colchon"){
                        cant = 2;
                        Autos.setVisibility(View.GONE);
                        Colchones.setVisibility(View.VISIBLE);
                        Sillones.setVisibility(View.GONE);
                }
                if(lav=="Sillon"){
                        cant = 3;
                        Autos.setVisibility(View.GONE);
                        Colchones.setVisibility(View.GONE);
                        Sillones.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Autos.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        Autos.setHasFixedSize(true);
        Colchones.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        Colchones.setHasFixedSize(true);
        Sillones.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        Sillones.setHasFixedSize(true);

        //Se declara el click para regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TipoUs.equals("n")) {
                    regresarn();
                }
                else{
                    regresari();
                }
            }
        });
        //Manda llamar la funcion de Cargar Web Service



    }

    //Funcion para cargar el Web Service
    public void cargarwsI(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaClientePedAutoIncompleta.php?Id_Cliente="+idC+"&Id_Negocio="+idN;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

    //Funcion para cargar el Web Service
    public void cargarwsIN(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaClienteInfoIncompleta.php";
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
    //Funcion que regresa al Layout anterior
    public void regresari(){
        Intent regresar=new Intent(this,Lavadas_Cliente.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("cliente",cliente);
        regresar.putExtras(bundle);
        startActivity(regresar);
        finish();
    }

    //Funcion Response, devuelve un valor si encuentra datos en el Web Service
    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = null;
            json = response.optJSONArray("pedidoAuto");
            try {
                for (int i = 0; i < json.length(); i++) {
                    lavAutoC = new lavAutoC();
                    JSONObject jsonObject = null;
                    jsonObject = json.getJSONObject(i);
                    lavAutoC.setId_PedidoAuto(jsonObject.optInt("Id_PedidoAuto"));
                    lavAutoC.setDomicilio(jsonObject.optString("Domicilio"));
                    lavAutoC.setNum_Domicilio(jsonObject.optString("Num_Domicilio"));
                    lavAutoC.setColonia(jsonObject.optString("Colonia"));
                    lavAutoC.setMunicipio(jsonObject.optString("Municipio"));
                    lavAutoC.setFecha_Lavada(jsonObject.optString("Fecha_Lavada"));
                    lavAutoC.setHora_Lavada(jsonObject.optString("Hora_Lavada"));
                    lavAutoC.setTipo_Auto(jsonObject.optString("Tipo_Auto"));
                    lavAutoC.setMarca_Auto(jsonObject.optString("Marca_Auto"));
                    lavAutoC.setModelo_Auto(jsonObject.optString("Modelo_Auto"));
                    lavAutoC.setTipo_TelaAuto((jsonObject.optString("Tipo_TelaAuto")));
                    lavAutoC.setEstado_PedidoAuto((jsonObject.optString("Estado_PedidoAuto")));
                    lavAutoC.setId_Cliente((jsonObject.optInt("Id_Cliente")));
                    lavAutoC.setId_Negocio((jsonObject.optInt("Id_Negocio")));
                    listalavadasRA.add(lavAutoC);
                }
                AdaptadorLavR adaptador = new AdaptadorLavR(listalavadasRA, getApplicationContext());
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ida = listalavadasRA.get(Autos.getChildAdapterPosition(v)).getId_PedidoAuto();
                        if(TipoUs.equals("n")){
                            clienteLav();
                        }
                    }
                });
                Autos.setAdapter(adaptador);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        /////////////////////////////////////////////////////////////////

        JSONArray json1 = null;
            json1 = response.optJSONArray("pedidoCol");
            try {
                for (int e = 0; e < json1.length(); e++) {
                    lavColC = new lavColchonC();
                    JSONObject jsonObjectc = null;
                    jsonObjectc = json1.getJSONObject(e);
                    lavColC.setId_PedidoColchon(jsonObjectc.optInt("Id_PedidoColchon"));
                    lavColC.setDomicilio(jsonObjectc.optString("Domicilio"));
                    lavColC.setNum_Domicilio(jsonObjectc.optString("Num_Domicilio"));
                    lavColC.setColonia(jsonObjectc.optString("Colonia"));
                    lavColC.setMunicipio(jsonObjectc.optString("Municipio"));
                    lavColC.setFecha_Lavada(jsonObjectc.optString("Fecha_Lavada"));
                    lavColC.setHora_Lavada(jsonObjectc.optString("Hora_Lavada"));
                    lavColC.setTamaño_Colchon(jsonObjectc.optString("Tamaño_Colchon"));
                    lavColC.setMarca_colchon(jsonObjectc.optString("Marca_Colchon"));
                    lavColC.setId_Cliente((jsonObjectc.optInt("Id_Cliente")));
                    lavColC.setId_Negocio((jsonObjectc.optInt("Id_Negocio")));
                    lavColC.setEstado_PedidoCol((jsonObjectc.optString("Estado_PedidoCol")));
                    listalavadasRC.add(lavColC);
                }
                AdaptadorLavC adaptadorC = new AdaptadorLavC(listalavadasRC, getApplicationContext());
                adaptadorC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idc = listalavadasRC.get(Colchones.getChildAdapterPosition(v)).getId_PedidoColchon();
                        if(TipoUs.equals("n")){
                            clienteLav();
                        }
                    }
                });
                Colchones.setAdapter(adaptadorC);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        ////////////////////////////////////////////////
        JSONArray json2 = null;

            json2 = response.optJSONArray("pedidoSillon");
            try {
                for (int i = 0; i < json2.length(); i++) {
                    lavSillonC = new lavSillonC();
                    JSONObject jsonObjects = null;
                    jsonObjects = json2.getJSONObject(i);
                    lavSillonC.setId_PedidoSillon(jsonObjects.optInt("Id_PedidoSillon"));
                    lavSillonC.setDomicilio(jsonObjects.optString("Domicilio"));
                    lavSillonC.setNum_Domicilio(jsonObjects.optString("Num_Domicilio"));
                    lavSillonC.setColonia(jsonObjects.optString("Colonia"));
                    lavSillonC.setMunicipio(jsonObjects.optString("Municipio"));
                    lavSillonC.setFecha_Lavada(jsonObjects.optString("Fecha_Lavada"));
                    lavSillonC.setHora_Lavada(jsonObjects.optString("Hora_Lavada"));
                    lavSillonC.setTipo_TelaSillon(jsonObjects.optString("Tipo_TelaSillon"));
                    lavSillonC.setTipo_Sillon(jsonObjects.optString("Tipo_Sillon"));
                    lavSillonC.setNumero_Sillon(jsonObjects.optString("Numero_Sillon"));
                    lavSillonC.setNumero_Cojin(jsonObjects.optString("Numero_Cojin"));
                    lavSillonC.setId_Cliente((jsonObjects.optInt("Id_Cliente")));
                    lavSillonC.setId_Negocio((jsonObjects.optInt("Id_Negocio")));
                    lavSillonC.setEstado_PedidoSillon((jsonObjects.optString("Estado_PedidoSillon")));
                    listalavadasRS.add(lavSillonC);
                }
                AdaptadorLavS adaptadorS = new AdaptadorLavS(listalavadasRS, getApplicationContext());
                adaptadorS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ids = listalavadasRS.get(Sillones.getChildAdapterPosition(v)).getId_PedidoSillon();
                        if(TipoUs.equals("n")){
                            clienteLav();
                        }
                    }
                });
                Sillones.setAdapter(adaptadorS);
            } catch (JSONException e) {
                e.printStackTrace();
            }



    }

    //Funcion para ir hacai la ventana del pedido, mandando como datos un objeto y una variable de tipo entero
    public void clienteLav(){
        Intent siguiente=new Intent(this, Info_Lavadas.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        bundle.putSerializable("ida",ida);
        bundle.putSerializable("ids",ids);
        bundle.putSerializable("idc",idc);
        bundle.putSerializable("tipo",cant);
        siguiente.putExtras(bundle);
        startActivity(siguiente);
        finish();

    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}