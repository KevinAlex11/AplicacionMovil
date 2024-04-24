package com.example.factoryclean.entidades;

import java.io.Serializable;

public class marcaCol implements Serializable {
    //Se crean las variables
    private int Id_MarcaColchon;
    private String MarcaColchon;
    //Se crea el constructor de la clase
    public marcaCol(){
        this.Id_MarcaColchon=Id_MarcaColchon;
        this.MarcaColchon=MarcaColchon;
    }
    //Se declaran los diferentes objetos con sus Getter y Setters
    public int getId_MarcaColchon(){return Id_MarcaColchon;}
    public void setId_MarcaColchon(int Id_MarcaColchon){this.Id_MarcaColchon=Id_MarcaColchon;}
    public String getMarcaColchon(){return MarcaColchon;}
    public void setMarcaColchon(String MarcaColchon){this.MarcaColchon=MarcaColchon;}
}
