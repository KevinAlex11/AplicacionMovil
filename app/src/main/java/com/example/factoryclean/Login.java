package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.entidades.cliente;
import com.android.volley.toolbox.JsonRequest;
import com.example.factoryclean.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    //Declaracion de las variables
Button inicio;
EditText usuario;
EditText contra;
cliente cliente=new cliente();
RequestQueue request;
JsonObjectRequest jsonObjectRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Referencia hacia los Items del layout
        inicio=(Button)findViewById(R.id.btnInicioSes);
        usuario=(EditText)findViewById(R.id.txtUsuario);
        contra=(EditText)findViewById(R.id.txtContra);

        //Realiza la peticion hacia la red
        request= Volley.newRequestQueue(this);

        //Click del boton Iniciar Sesion
        inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Se carga La funcion de cargar WebService
                cargarws();
                //Se valida el usuario y la contraseña, entre el editText y la base de datos
                if(usuario.getText().toString().equals(cliente.getUsuario())&&contra.getText().toString().equals(cliente.getContra())){
                    //Se carga la funcion de inicio
                        inicio();

                    Toast.makeText(getApplicationContext(),"Iniciaste Sesion con: "+cliente.getUsuario(),Toast.LENGTH_SHORT).show();
                }
                else{
                    if(usuario.getText().toString().isEmpty()&&contra.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectas ",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Intenta presionar de nuevo el boton:\n\n                   Iniciar sesion ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void cargarws(){
        //Para mandar los datos hacia la base de datos
        String url="http://proyectosinformaticatnl.ceti.mx/whoclean/wsJSONInsertClienteWhoclean.php?usuario="+usuario.getText().toString()+"&contra="+contra.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this, this);
        request.add(jsonObjectRequest);
    }
    //Funcion de inicio
    public void inicio(){
        Intent inicio=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        inicio.putExtras(bundle);
        startActivity(inicio);
        finish();
    }

    //Funcion Registrar un nuevo usuario
    public void registrar(View view){
        Intent registro=new Intent(this,Registro.class);
        startActivity(registro);
    }

    //Funcion de salir de la app
    public void salir(View view){
       Intent salida=new Intent(Intent.ACTION_MAIN);
        salida.addCategory(Intent.CATEGORY_HOME);
        salida.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(salida);
        finish();
    }

    //Funcion de recuperar la contraseña
    public void recuperar(View view){
        Intent recuperado=new Intent(this,RecuperaContra.class);
        startActivity(recuperado);
        finish();
    }

    /*Funcion Response, valida los datos que llegan desde el WebService
    *Se valida el JSON, toma los dato y guarda en variables nativas
    *El Objeto de cliente se llena desde esta funcion
     */
    @Override
    public void onResponse(JSONObject response) {

        //Variable de tipo cadena, recibe desde los ojetos salientes del WebService llamado cliente como palabra clave
        JSONArray json=response.optJSONArray("cliente");
            JSONObject jsonObject = null;
            try {
                //Aqui se hace el llenado de cada objeto creado en la clase llamada cliente
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
                    //Si no se recibe la respuesta desde el WebService
                    Toast.makeText(getApplicationContext(), "Fallo al recibir datos", Toast.LENGTH_LONG).show();
                }
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }


}