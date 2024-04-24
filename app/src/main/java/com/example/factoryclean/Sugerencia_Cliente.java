package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONObject;

public class Sugerencia_Cliente extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    ImageButton regresar;
    Button enviar;
    EditText asunto,mensaje;
    cliente cliente;
    int idC,idN;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencia__cliente);
        asunto=(EditText)findViewById(R.id.txtAsuntoCliente);
        mensaje=(EditText)findViewById(R.id.txtMensajeCliente);
        enviar=(Button)findViewById(R.id.btnEnviarSugerCliente);
        regresar=(ImageButton)findViewById(R.id.btnRegSugerCliente);
        request= Volley.newRequestQueue(this);
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
            idC=cliente.getId_Cliente();
            idN=cliente.getId_Negocio();
        }
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asunto.getText().toString().isEmpty()||mensaje.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Campos vacios",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(idC!=0&&idN!=0) {
                        cargarwsInsert();
                        Toast.makeText(getApplicationContext(), "Envio con exito", Toast.LENGTH_SHORT).show();
                        regresar();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Espere",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
    }

    public void cargarwsInsert(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONInsertSugerencia.php?asunto="+asunto.getText().toString()+"&mensaje="+mensaje.getText().toString()+"&idcliente="+idC+"&idnegocio="+idN;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }

    public void regresar(){
        Intent regresar=new Intent(this,MainActivity.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        regresar.putExtras(bundlesuger);
        startActivity(regresar);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Enviados correctamente",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}