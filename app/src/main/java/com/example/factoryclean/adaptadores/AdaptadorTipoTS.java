package com.example.factoryclean.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.factoryclean.R;
import com.example.factoryclean.entidades.tipoTelaSill;

import java.util.ArrayList;

public class AdaptadorTipoTS extends RecyclerView.Adapter<AdaptadorTipoTS.ViewHolderDatos> implements View.OnClickListener{
    @NonNull
    ArrayList<tipoTelaSill> listaTipoST;
    private View.OnClickListener listener;
    RequestQueue request;
    Context context;

    public AdaptadorTipoTS(@NonNull ArrayList<tipoTelaSill> listaTipoST, Context context) {
        this.listaTipoST = listaTipoST;
        this.context = context;
        request = Volley.newRequestQueue(context);
    }

    //Aqui se hace el referenciado con el Layout para implementarle los datos
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo, null, false);
        view.setOnClickListener(this);
        return new AdaptadorTipoTS.ViewHolderDatos(view);

    }

    //Funcion para agregar a cada uno de los Items del Layout los datos que vienen del Web Service
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.etiquetaTipo.setText(listaTipoST.get(position).getTipoTelaSillon());
        holder.etiquetaDesctipo.setText(listaTipoST.get(position).getDescTipoTelaSillon());
        if (listaTipoST.get(position).getImagenTipoTela() != null) {
            cargarImage(listaTipoST.get(position).getImagenTipoTela(), holder);
        } else {
            holder.fototipoA.setImageResource(R.drawable.icono);
        }
    }


    //Funcion que se encarga de mandar llamar el WebService
    private void cargarImage(String imagen, final AdaptadorTipoTS.ViewHolderDatos holder) {
        String urlImagen = "http://proyectosinformaticatnl.ceti.mx/whoclean/img/" + imagen;
        urlImagen = urlImagen.replace(" ", "%20");
        ImageRequest imageRequest = new ImageRequest(urlImagen, (new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.fototipoA.setImageBitmap(response);

            }
        }), 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo cargar la imagen ", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(imageRequest);
    }

    @Override
    public int getItemCount() {
        return listaTipoST.size();
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

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView etiquetaTipo,etiquetaDesctipo;
        ImageView fototipoA;

        public ViewHolderDatos(View view) {

            super(view);
            etiquetaTipo=(TextView) itemView.findViewById(R.id.txtTipoAuto);
            etiquetaDesctipo=(TextView) itemView.findViewById(R.id.txtDescTipo);
            fototipoA=(ImageView) itemView.findViewById(R.id.imgTipoAuto);
        }
    }
}
