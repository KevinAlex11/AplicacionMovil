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
import com.example.factoryclean.entidades.marcaCol;
import com.example.factoryclean.entidades.municipio;
import com.example.factoryclean.entidades.tamCol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClientePedColchon extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    marcaCol marcaCol;
    municipio municipio;
    cliente cliente;
    tamCol tamCol;
    ImageButton reg;
    String HC,MC,mun,textmun,marcaC,textmarcaC,tamc,texttamC;
    Long hc,mc;
    Button registrar;
    EditText domicilio, numdom, colonia,fechaLav,horaLav;
    Spinner munSC,marcaCS,tamCS;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ArrayList<String> listamunC=new ArrayList<String>();
    ArrayList<String> listamarcaC=new ArrayList<String>();
    ArrayList<String> listatamC=new ArrayList<String>();
    int idC,idN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_ped_colchon);
        domicilio=(EditText) findViewById(R.id.txtPedColchonDomicilio);
        numdom=(EditText)findViewById(R.id.txtPedColchonNumDomicilio);
        colonia=(EditText)findViewById(R.id.txtPedColchonColonia);
        fechaLav=(EditText)findViewById(R.id.txtPedColchonFechaLav);
        horaLav=(EditText)findViewById(R.id.txtPedColchonHoraLav);
        munSC=(Spinner)findViewById(R.id.txtMunicipioColchon);
        marcaCS=(Spinner)findViewById(R.id.txtMarcaColchon);
        tamCS=(Spinner)findViewById(R.id.txtTamColchon);
        reg=(ImageButton)findViewById(R.id.btnRegColchon);
        registrar=(Button)findViewById(R.id.btnPedColchonRegistrar);




        //Se declara la longitud del campo para la hora
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(5);
        horaLav.setFilters(FilterArray);

        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            cliente=(cliente) enviado.getSerializable("cliente");
            idC=cliente.getId_Cliente();
            idN=cliente.getId_Negocio();
        }



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(domicilio.getText().toString().isEmpty()||numdom.getText().toString().isEmpty()||colonia.getText().toString().isEmpty()||fechaLav.getText().toString().isEmpty()||horaLav.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Algun campo esta vacio: ",Toast.LENGTH_SHORT).show();
                }

                else{
                    String hr=horaLav.getText().toString();
                    String[] hora=hr.split(":");
                    HC=hora[0];
                    MC=hora[1];
                    hc=Long.parseLong(HC);
                    mc=Long.parseLong(MC);
                    Toast.makeText(getApplicationContext(),"Hora: "+hc+" Minutos:"+mc,Toast.LENGTH_SHORT).show();
                    if(((hc>=08.0&&hc<16.0)&&(mc>=0&&mc<=59))) {
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
        request=Volley.newRequestQueue(getApplicationContext());
        cargarwsConsulta();
    }
    //Funcion que carga el Web Service para mandar la informacion hacia la base de datos
    public void cargarwspedido(){
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONInsertClientePedColchon.php?Domicilio="+domicilio.getText().toString()+"&Num_Domicilio="+numdom.getText().toString()+"&Colonia="+colonia.getText().toString()+"&Municipio="+textmun+"&Fecha_Lavada="+fechaLav.getText().toString()+"&Hora_Lavada="+horaLav.getText().toString()+"&Tam_Colchon="+texttamC+"&Marca_Colchon="+textmarcaC+"&Estado_PedidoCol=i"+"&Id_Cliente="+idC+"&Id_Negocio="+idN;
        url=url.replace(" ","%20");
        request=Volley.newRequestQueue(getApplicationContext());
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

    //Funcion que Condulta los municipios de la tabla Municipios de la base de datos
    public void cargarwsConsulta(){
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONConsultaMunMarcaColchon.php";
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
    public void tipos(){
        Intent regresar=new Intent(this,Pedido_Cliente.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        regresar.putExtras(bundlesuger);
        startActivity(regresar);
        finish();
    }

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
                listamunC.add(municipio.getNombreMunicipio());
            }
            //Crea una lista de esos datos
            ArrayAdapter<String> adpmunc=new ArrayAdapter<>(ClientePedColchon.this,android.R.layout.simple_spinner_dropdown_item,listamunC);
            munSC.setAdapter(adpmunc);
            munSC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                //Se establecen los Items de la lista
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                    mun=(String)  munSC.getAdapter().getItem(i);
                    textmun=mun;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Se guarda la consulta de la marca del colchon
        JSONArray json1 = null;
        //Busca la palabra clave y toma los campos de esa consulta
        json1 = response.optJSONArray("marcacolchon");
        try {
            //Los guarda dentro de los objetos de Municipio
            for (int i = 0; i < json1.length(); i++) {
                marcaCol = new marcaCol();
                JSONObject jsonObject1 = null;
                jsonObject1 = json1.getJSONObject(i);
                marcaCol.setMarcaColchon(jsonObject1.optString("MarcaColchon"));
                listamarcaC.add(marcaCol.getMarcaColchon());
            }
            //Crea una lista de esos datos
            ArrayAdapter<String> adpmarca=new ArrayAdapter<>(ClientePedColchon.this,android.R.layout.simple_spinner_dropdown_item,listamarcaC);
            marcaCS.setAdapter(adpmarca);
            marcaCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                //Se establecen los Items de la lista
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                    marcaC=(String)  marcaCS.getAdapter().getItem(i);
                    textmarcaC=marcaC;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Se guarda la consulta del tamaño del colchon
        //Se guarda la consulta de la marca del colchon
        JSONArray json2 = null;
        //Busca la palabra clave y toma los campos de esa consulta
        json2 = response.optJSONArray("tamacolchon");
        try {
            //Los guarda dentro de los objetos de Municipio
            for (int i = 0; i < json2.length(); i++) {
                tamCol = new tamCol();
                JSONObject jsonObject2 = null;
                jsonObject2 = json2.getJSONObject(i);
                tamCol.setTamaColchon(jsonObject2.optString("TamaColchon"));
                listatamC.add(tamCol.getTamaColchon());
            }
            //Crea una lista de esos datos
            ArrayAdapter<String> adptam=new ArrayAdapter<>(ClientePedColchon.this,android.R.layout.simple_spinner_dropdown_item,listatamC);
            tamCS.setAdapter(adptam);
            tamCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                //Se establecen los Items de la lista
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                    tamc=(String)  tamCS.getAdapter().getItem(i);
                    texttamC=tamc;
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