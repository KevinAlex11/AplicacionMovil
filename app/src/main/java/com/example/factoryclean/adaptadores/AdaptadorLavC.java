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
import com.example.factoryclean.entidades.lavadas.lavAutoC;
import com.example.factoryclean.entidades.lavadas.lavColchonC;
import com.example.factoryclean.entidades.lavadas.lavSillonC;


import java.util.ArrayList;

public class    AdaptadorLavC extends RecyclerView.Adapter<AdaptadorLavC.ViewHolderDatos> implements View.OnClickListener {
    // Declaracion de las variables
    @NonNull
    ArrayList<lavColchonC> listalavadasRC;

    private View.OnClickListener listener;
    RequestQueue request;
    Context context;

    //Funcion que declara la peticion al WebService
    public AdaptadorLavC(@NonNull ArrayList<lavColchonC> listalavadasRC, Context context) {
        this.listalavadasRC = listalavadasRC;
        this.context = context;
        request = Volley.newRequestQueue(context);
    }

    //Aqui se hace el referenciado con el Layout para implementarle los datos
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lav_col, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    //Funcion para agregar a cada uno de los Items del Layout los datos que vienen del Web Service
    @Override
    public void onBindViewHolder(@NonNull AdaptadorLavC.ViewHolderDatos holder, int position) {
        holder.etiquetaDom.setText(listalavadasRC.get(position).getDomicilio());
        holder.etiquetaNumDom.setText(listalavadasRC.get(position).getNum_Domicilio());
        holder.etiquetaTamCol.setText(listalavadasRC.get(position).getTamaño_Colchon());
        holder.etiquetaMuni.setText(listalavadasRC.get(position).getMunicipio());
        holder.etiquetaMarcaCol.setText(listalavadasRC.get(position).getMarca_colchon());
        holder.etiquetaColonia.setText(listalavadasRC.get(position).getColonia());
        holder.etiquetaFecha.setText(listalavadasRC.get(position).getFecha_Lavada());
        holder.etiquetaHora.setText(listalavadasRC.get(position).getHora_Lavada());

    }

    @Override
    public int getItemCount() {
        return listalavadasRC.size();
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
        TextView etiquetaDom,etiquetaNumDom, etiquetaColonia,etiquetaMuni, etiquetaFecha, etiquetaHora,etiquetaTamCol,etiquetaMarcaCol;


        public ViewHolderDatos(View view) {

            super(view);
            etiquetaDom = (TextView) itemView.findViewById(R.id.txtDomicilio);
            etiquetaNumDom=(TextView)itemView.findViewById(R.id.txtNumDom);
            etiquetaMuni=(TextView)itemView.findViewById(R.id.txtMunicipio);
            etiquetaTamCol=(TextView)itemView.findViewById(R.id.txtTamanoC);
            etiquetaMarcaCol=(TextView)itemView.findViewById(R.id.txtMarcaC);
            etiquetaColonia = (TextView) itemView.findViewById(R.id.txtColonia);
            etiquetaFecha = (TextView) itemView.findViewById(R.id.txtfecha);
            etiquetaHora = (TextView) itemView.findViewById(R.id.txtHora);

        }
    }
}
