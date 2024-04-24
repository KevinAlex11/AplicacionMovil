package com.example.factoryclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

import com.example.factoryclean.entidades.cliente;

public class Lavadas_Cliente extends AppCompatActivity {
ImageButton realizadas, pendientes, regresar;
cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavadas__cliente);
        regresar=(ImageButton)findViewById(R.id.btnRegLavCliente);
        realizadas=(ImageButton)findViewById(R.id.btnLavFin);
        pendientes=(ImageButton)findViewById(R.id.btnLavPen);

        Bundle enviado=getIntent().getExtras();
        if(enviado!=null){
            //Busca la palabra clave y las guarda en el objeto cliente
            cliente=(cliente) enviado.getSerializable("cliente");
        }

        realizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizadas();
            }
        });
        pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pendientes();
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
    }

    public void realizadas(){
        Intent realizadas=new Intent(this, LavRealizadasAuto.class);
        Bundle reali=new Bundle();
        reali.putSerializable("cliente",cliente);
        realizadas.putExtras(reali);
        startActivity(realizadas);

    }

    public void pendientes(){
        Intent pendientes=new Intent(this, LavIncompletasAuto.class);
        Bundle pen=new Bundle();
        pen.putSerializable("cliente",cliente);
        pendientes.putExtras(pen);
        startActivity(pendientes);

    }
    public void regresar(){
        Intent regresar=new Intent(this, MainActivity.class);
        Bundle bundlesuger=new Bundle();
        bundlesuger.putSerializable("cliente", cliente);
        regresar.putExtras(bundlesuger);
        startActivity(regresar);
        finish();
    }

    }
