package com.example.factoryclean.entidades;

import com.android.volley.toolbox.StringRequest;

import java.io.Serializable;

public class sugerencia implements Serializable {
    private int Id_Sugerencia;
    private String Nombre;
    private String Asunto;
    private String Mensaje;
    private int Id_Cliente;
    private int Id_Negocio;

    public sugerencia(){
    this.Id_Sugerencia=Id_Sugerencia;
    this.Nombre=Nombre;
    this.Asunto=Asunto;
    this.Mensaje=Mensaje;
    this.Id_Cliente=Id_Cliente;
    this.Id_Negocio=Id_Negocio;
    }

    public int getId_Sugerencia(){return Id_Sugerencia;}
    public void setId_Sugerencia(int Id_Sugerencia){this.Id_Sugerencia=Id_Sugerencia;}
    public String getNombre(){return Nombre;}
    public void setNombre(String Nombre){this.Nombre=Nombre;}
    public String getAsunto(){return Asunto;}
    public void setAsunto(String Asunto){this.Asunto=Asunto;}
    public String getMensaje(){return Mensaje;}
    public void setMensaje(String Mensaje){this.Mensaje=Mensaje;}
    public int getId_Cliente(){return Id_Cliente;}
    public void setId_Cliente(int Id_Cliente){this.Id_Cliente=Id_Cliente;}
    public int getId_Negocio(){return Id_Negocio;}
    public void setId_Negocio(int Id_Negocio){this.Id_Negocio=Id_Negocio;}

}
