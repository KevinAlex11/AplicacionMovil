package com.example.factoryclean.entidades;

import java.io.Serializable;

public class autos implements Serializable {
    //Se crean las variables
     private int Id_Tipoauto;
     private String Tipo;
     private String DescTipo;
     private String Imagen;
    //Se crea el constructor de la clase
     public autos(){
         this.Id_Tipoauto=Id_Tipoauto;
         this.Tipo=Tipo;
         this.DescTipo=DescTipo;
         this.Imagen=Imagen;
     }
    //Se declaran los diferentes objetos con sus Getters y Setters
     public int getId_Tipoauto(){return Id_Tipoauto;}
     public void setId_Tipoauto(int Id_Tipoauto){this.Id_Tipoauto=Id_Tipoauto;}
    public String getTipo(){return Tipo;}
    public void setTipo(String Tipo){this.Tipo=Tipo;}
    public String getDescTipo(){return DescTipo;}
    public void setDescTipo(String DescTipo){this.DescTipo=DescTipo;}
    public String getImagen(){return Imagen;}
    public void setImagen(String Imagen){this.Imagen=Imagen;}

}
