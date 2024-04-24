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
import com.example.factoryclean.entidades.lavadas.lavColchonC;
import com.example.factoryclean.entidades.lavadas.lavSillonC;


import java.util.ArrayList;

public class AdaptadorLavS extends RecyclerView.Adapter<AdaptadorLavS.ViewHolderDatos> implements View.OnClickListener {
    // Declaracion de las variables
    @NonNull
    ArrayList<lavSillonC> listalavadasRS;

    private View.OnClickListener listener;
    RequestQueue request;
    Context context;

    //Funcion que declara la peticion al WebService
    public AdaptadorLavS(@NonNull ArrayList<lavSillonC> listalavadasRS, Context context) {
        this.listalavadasRS = listalavadasRS;
        this.context = context;
        request = Volley.newRequestQueue(context);
    }

    //Aqui se hace el referenciado con el Layout para implementarle los datos
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lav_sillon, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    //Funcion para agregar a cada uno de los Items del Layout los datos que vienen del Web Service
    @Override
    public void onBindViewHolder(@NonNull AdaptadorLavS.ViewHolderDatos holder, int position) {
        holder.etiquetaDom.setText(listalavadasRS.get(position).getDomicilio());
        holder.etiquetaNumDom.setText(listalavadasRS.get(position).getNum_Domicilio());
        holder.etiquetaMuni.setText(listalavadasRS.get(position).getMunicipio());
        holder.etiquetaNumeroCojine.setText(listalavadasRS.get(position).getNumero_Cojin());
        holder.etiquetaNumeroSillon.setText(listalavadasRS.get(position).getNumero_Sillon());
        holder.etiquetaTipoSillon.setText(listalavadasRS.get(position).getTipo_Sillon());
        holder.etiquetaTipoTelaSillon.setText(listalavadasRS.get(position).getTipo_TelaSillon());
        holder.etiquetaColonia.setText(listalavadasRS.get(position).getColonia());
        holder.etiquetaFecha.setText(listalavadasRS.get(position).getFecha_Lavada());
        holder.etiquetaHora.setText(listalavadasRS.get(position).getHora_Lavada());

    }

    @Override
    public int getItemCount() {
        return listalavadasRS.size();
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
        TextView etiquetaDom,etiquetaNumDom,etiquetaMuni,etiquetaTipoSillon,etiquetaTipoTelaSillon,etiquetaNumeroSillon,etiquetaNumeroCojine, etiquetaColonia, etiquetaFecha, etiquetaHora;


        public ViewHolderDatos(View view) {

            super(view);
            etiquetaDom = (TextView) itemView.findViewById(R.id.txtDomicilio);
            etiquetaNumDom=(TextView)itemView.findViewById(R.id.txtNumDom);
            etiquetaMuni=(TextView)itemView.findViewById(R.id.txtMunicipio);
            etiquetaTipoSillon=(TextView)itemView.findViewById(R.id.txtTipoS);
            etiquetaTipoTelaSillon=(TextView)itemView.findViewById(R.id.txtTipoTelaS);
            etiquetaNumeroSillon=(TextView)itemView.findViewById(R.id.txtNumeroS);
            etiquetaNumeroCojine=(TextView)itemView.findViewById(R.id.txtNumeroCojinS);
            etiquetaColonia = (TextView) itemView.findViewById(R.id.txtColonia);
            etiquetaFecha = (TextView) itemView.findViewById(R.id.txtfecha);
            etiquetaHora = (TextView) itemView.findViewById(R.id.txtHora);

        }
    }
}
