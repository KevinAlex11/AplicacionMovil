package com.example.factoryclean;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.factoryclean.entidades.cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Declaracion de las variables
TextView nombre;
    cliente cliente;


    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        //Referencia hacia los Items del layout
        nombre=(TextView)findViewById(R.id.lblUsuReg);
        //Crea el archivo Bundle para recibir la informacion
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
        }
      if(cliente!=null&&cliente.getTipoUsu().equals("n")){
          //Se carga la funcion para entrar al tipo de usuario cliente
          usuarion();
        }
      else{
          //Se carga la funcion para entrar al tipo de usuario negocio
          usuarioc();
      }


      //Codigos agregados automaticamente para configurar un Item flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
      //Codigos para poder hacer funcionar la barra de navegacion
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.pedido_Cliente,R.id.login,R.id.lavadas_Cliente,R.id.sugerencia_Cliente,R.id.informacion_Negocio,R.id.precio_Lavadas)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Codigo para mandar informacion entre activitys y fragments
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pedido_Cliente:
                        Intent pedCliente = new Intent(MainActivity.this, Pedido_Cliente.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("cliente", cliente);
                        pedCliente.putExtras(bundle);
                        startActivity(pedCliente);
                        break;
                    case R.id.login:
                        Intent login = new Intent(MainActivity.this, Login.class);
                        startActivity(login);
                        break;
                    case R.id.lavadas_Cliente:
                        Intent lavCliente = new Intent(MainActivity.this, Lavadas_Cliente.class);
                        Bundle bundlelav=new Bundle();
                        bundlelav.putSerializable("cliente", cliente);
                        lavCliente.putExtras(bundlelav);
                        startActivity(lavCliente);
                        break;
                    case R.id.sugerencia_Cliente:
                        Intent suger = new Intent(MainActivity.this, Sugerencia_Cliente.class);
                        Bundle bundlesuger=new Bundle();
                        bundlesuger.putSerializable("cliente", cliente);
                        suger.putExtras(bundlesuger);
                        startActivity(suger);
                        break;
                    case R.id.nav_home:
                        Intent pendientes = new Intent(MainActivity.this, LavIncompletasAuto.class);
                        Bundle bundpendientes=new Bundle();
                        bundpendientes.putSerializable("cliente", cliente);
                        pendientes.putExtras(bundpendientes);
                        startActivity(pendientes);
                        break;
                    case R.id.nav_gallery:
                        Intent realizadas = new Intent(MainActivity.this, LavRealizadasAuto.class);
                        Bundle bundrealizadas=new Bundle();
                        bundrealizadas.putSerializable("cliente", cliente);
                        realizadas.putExtras(bundrealizadas);
                        startActivity(realizadas);
                        break;
                    case R.id.sugerFragment:
                        Intent sugerN = new Intent(MainActivity.this, SugerenciaN.class);
                        Bundle bundlesugerN=new Bundle();
                        bundlesugerN.putSerializable("cliente", cliente);
                        sugerN.putExtras(bundlesugerN);
                        startActivity(sugerN);
                        break;
                    case R.id.precio_Lavadas:
                        Intent precio= new Intent(MainActivity.this, Precio_Lavadas.class);
                        Bundle bundleprecio=new Bundle();
                        bundleprecio.putSerializable("cliente", cliente);
                        precio.putExtras(bundleprecio);
                        startActivity(precio);
                        break;


                }
                return true;
            }
        });



    }

    //Funcion privada para ocultar los diferentes items, en usuarios administradores
    private void usuarion()
    {
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.pedido_Cliente).setVisible(false);
        menu.findItem(R.id.lavadas_Cliente).setVisible(false);
        menu.findItem(R.id.sugerencia_Cliente).setVisible(false);
        menu.findItem(R.id.precio_Lavadas).setVisible(true);
    }
    //Funcion privada para ocultar los diferentes items, en usuarios clientes
    private void usuarioc(){
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_home).setVisible(false);
        menu.findItem(R.id.nav_gallery).setVisible(false);
        menu.findItem(R.id.sugerFragment).setVisible(false);
    }

    //Funciones creadas automaticamente al crear el proyecto
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
    private void setUpActionBar() {
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}