package com.example.factoryclean.adaptadores;

import android.content.Context;
import android.os.TestLooperManager;
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


import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorLavR extends RecyclerView.Adapter<AdaptadorLavR.ViewHolderDatos> implements View.OnClickListener {
    // Declaracion de las variables
    @NonNull
    ArrayList<lavAutoC> listalavadasRA;

    private View.OnClickListener listener;
    RequestQueue request;
    Context context;

    //Funcion que declara la peticion al WebService
    public AdaptadorLavR(@NonNull ArrayList<lavAutoC> listalavadasRA, Context context){
        this.listalavadasRA=listalavadasRA;
        this.context=context;
        request= Volley.newRequestQueue(context);
    }

    //Aqui se hace el referenciado con el Layout para implementarle los datos
    public AdaptadorLavR.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lav, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    //Funcion para agregar a cada uno de los Items del Layout los datos que vienen del Web Service
    @Override
    public void onBindViewHolder(@NonNull AdaptadorLavR.ViewHolderDatos holder, int position){
        holder.etiquetaDom.setText(listalavadasRA.get(position).getDomicilio());
        holder.etiquetaNumDom.setText(listalavadasRA.get(position).getNum_Domicilio());
        holder.etiquetaModelo.setText(listalavadasRA.get(position).getModelo_Auto());
        holder.etiquetaTipo.setText(listalavadasRA.get(position).getTipo_Auto());
        holder.etiquetaMarca.setText(listalavadasRA.get(position).getMarca_Auto());
        holder.etiquetaMuni.setText(listalavadasRA.get(position).getMunicipio());
        holder.etiquetaTela.setText(listalavadasRA.get(position).getTipo_TelaAuto());
        holder.etiquetaColonia.setText(listalavadasRA.get(position).getColonia());
        holder.etiquetaFecha.setText(listalavadasRA.get(position).getFecha_Lavada());
        holder.etiquetaHora.setText(listalavadasRA.get(position).getHora_Lavada());

    }
    @Override
    public int getItemCount(){return listalavadasRA.size();}
    //Funcion del click para determinar la posicion del mismo que se esta seleccionando
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }



    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView etiquetaDom,etiquetaNumDom,etiquetaColonia,etiquetaMuni,etiquetaTipo,etiquetaMarca,etiquetaModelo,etiquetaTela,etiquetaFecha,etiquetaHora;


        public ViewHolderDatos(View view) {

            super(view);
            etiquetaDom=(TextView) itemView.findViewById(R.id.txtDomicilio);
            etiquetaNumDom=(TextView)itemView.findViewById(R.id.txtNumDom);
            etiquetaColonia=(TextView) itemView.findViewById(R.id.txtColonia);
            etiquetaMuni=(TextView)itemView.findViewById(R.id.txtMunicipio);
            etiquetaTipo=(TextView)itemView.findViewById(R.id.txtTipoA);
            etiquetaMarca=(TextView)itemView.findViewById(R.id.txtMarcaA);
            etiquetaModelo=(TextView)itemView.findViewById(R.id.txtModeloA);
            etiquetaTela=(TextView)itemView.findViewById(R.id.txtTipoTela);
            etiquetaFecha=(TextView) itemView.findViewById(R.id.txtfecha);
            etiquetaHora=(TextView) itemView.findViewById(R.id.txtHora);

        }
    }
}
