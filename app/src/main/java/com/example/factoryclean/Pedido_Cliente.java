package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.factoryclean.entidades.cliente;

public class Pedido_Cliente extends AppCompatActivity {
    //Declaracion de las vairbales
private ImageButton reg, auto,colchon,sillon;
cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido__cliente);
        //Referencia hacia los Items del layout
        reg=(ImageButton)findViewById(R.id.btnRegresarPedCliente);
        auto=(ImageButton)findViewById(R.id.btnLavAuto);
        sillon=(ImageButton)findViewById(R.id.btnLavSillon);
        colchon=(ImageButton)findViewById(R.id.btnLavCama);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedauto();
            }
        });
        sillon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedsillon();
            }
        });
        colchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedcol();
            }
        });
        //Crea el archivo Bundle para recibir la informacion
        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
        }
    }
    //Funcion para ir hacia el activity de Pedido Auto
    public void pedauto() {
        Intent pedAuto = new Intent(this, TipoAuto.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        pedAuto.putExtras(bundle);
        startActivity(pedAuto);
        finish();
    }
    //Funcion para ir hacia el activity de Pedido Sillon
    public void pedsillon() {
        Intent pedSill = new Intent(this, TipoSillon.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        pedSill.putExtras(bundle);
        startActivity(pedSill);
        finish();
    }
    //Funcion para ir hacia el activity de Pedido Colchon
    public void pedcol() {
        Intent pedCol = new Intent(this, ClientePedColchon.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cliente",cliente);
        pedCol.putExtras(bundle);
        startActivity(pedCol);
        finish();
    }

    //Funcion para ir hacia el activity del Menu
    public void menu() {
        Intent menu = new Intent(this, MainActivity.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        menu.putExtras(bundlesuger);
        startActivity(menu);
        finish();
    }
}