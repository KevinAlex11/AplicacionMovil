package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RecuperaContra extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    //Declaracion de las variables
    Button validar;
    EditText usuario, correo;
    cliente cliente=new cliente();
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_contra);
        //Referencia hacia los Items del layout
        validar=(Button)findViewById(R.id.btnValidar);
        correo=(EditText) findViewById(R.id.txtCorUsuario);
        usuario=(EditText)findViewById(R.id.txtNomUsuario);
        //Realiza la peticion hacia la red
        request= Volley.newRequestQueue(this);


        //Click del boton para validar usuario
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se carga La funcion de cargar WebService
                cargarws();

                if(usuario.getText().toString().equals(cliente.getUsuario())&&correo.getText().toString().equals(cliente.getCorreo())){
                    //Se carga La funcion de validar usuario
                    validar();
                }
                else{
                    if(correo.getText().toString().isEmpty()||usuario.getText().toString().isEmpty()){
                        Toast.makeText(RecuperaContra.this, "Ingrese los datos ", Toast.LENGTH_SHORT).show();
                    }
                   else{
                        Toast.makeText(getApplicationContext(), "Intenta presionar de nuevo el boton:\n\n                   Iniciar sesion ", Toast.LENGTH_SHORT).show();
                   }

                }
            }
        });
    }
    //Funcion del WebService
    private void cargarws(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONValidacionContra.php?usuario="+usuario.getText().toString()+"&correo="+correo.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion para regresar a la activity anterior
    public void regreso(View view){
        Intent regresar=new Intent(this,Login.class);
        startActivity(regresar);
        finish();
    }
    //Funcion para validar y avanzar a la siguiente ventana
    public void validar(){
        Intent Validar=new Intent(this,ContraNueva.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        Validar.putExtras(bundle);
        startActivity(Validar);
        finish();
    }
    /*Funcion Response, valida los datos que llegan desde el WebService
     *Se valida el JSON, toma los dato y guarda en variables nativas
     *El Objeto de cliente se llena desde esta funcion
     */
    @Override
    public void onResponse(JSONObject response) {


        JSONArray json=response.optJSONArray("cliente");
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            cliente.setId_Cliente(jsonObject.optInt("Id_Cliente"));
            cliente.setNombre(jsonObject.optString("Nombre"));
            cliente.setApellido(jsonObject.getString("Apellido"));
            cliente.setCorreo(jsonObject.getString("Correo"));
            cliente.setTelefono((jsonObject.getString("Telefono")));
            cliente.setUsuario(jsonObject.getString("Usuario"));
            cliente.setContra(jsonObject.getString("Contra"));
            cliente.setTipoUsu(jsonObject.getString("TipoUsu"));
            cliente.setId_Negocio(jsonObject.getInt("Id_Negocio"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Fallo al recibir datos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}