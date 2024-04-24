package com.example.factoryclean.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.R;
import com.example.factoryclean.entidades.sugerencia;

import java.util.ArrayList;

public class AdaptorSuger extends RecyclerView.Adapter<AdaptorSuger.ViewHolderDatos> implements View.OnClickListener  {

    // Declaracion de las variables
    @NonNull
    ArrayList<sugerencia> listasugerencias;

    private View.OnClickListener listener;
    RequestQueue request;
    Context context;
    //Funcion que declara la peticion al WebService
    public AdaptorSuger(@NonNull ArrayList<sugerencia> listasugerencias, Context context) {
        this.listasugerencias = listasugerencias;
        this.context = context;
        request = Volley.newRequestQueue(context);
    }
    //Aqui se hace el referenciado con el Layout para implementarle los datos
    public AdaptorSuger.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sugerencia, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    //Funcion para agregar a cada uno de los Items del Layout los datos que vienen del Web Service
    @Override
    public void onBindViewHolder(@NonNull AdaptorSuger.ViewHolderDatos holder, int position) {
        holder.etiquetaNomC.setText(listasugerencias.get(position).getNombre());
        holder.etiquetaasunto.setText(listasugerencias.get(position).getAsunto());
        holder.etiquetaMensaje.setText(listasugerencias.get(position).getMensaje());

    }
    @Override
    public int getItemCount() {
        return listasugerencias.size();
    }

    //Funcion del click para determinar la posicion del mismo que se esta seleccionando
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView etiquetaNomC,etiquetaasunto,etiquetaMensaje;


        public ViewHolderDatos(View view) {

            super(view);
            etiquetaNomC = (TextView) itemView.findViewById(R.id.txtNombreCliente);
            etiquetaasunto=(TextView)itemView.findViewById(R.id.txtAsuntoSuger);
            etiquetaMensaje=(TextView)itemView.findViewById(R.id.txtMensajeSuger);

        }
    }

}
