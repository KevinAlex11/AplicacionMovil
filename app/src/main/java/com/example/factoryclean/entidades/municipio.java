package com.example.factoryclean.entidades;

import java.io.Serializable;

public class municipio implements Serializable {
    //Se crean las variables
    private int Id_Municipio;
    private String NombreMunicipio;
    //Se crea el constructor de la clase
    public municipio(){
    this.Id_Municipio=Id_Municipio;
    this.NombreMunicipio=NombreMunicipio;
}
    //Se declaran los diferentes objetos con sus Getters y Setters
    public int getId_Municipio(){return Id_Municipio;}
    public void setId_Municipio(int Id_Municipio){this.Id_Municipio=Id_Municipio;}
    public String getNombreMunicipio(){return NombreMunicipio;}
    public void setNombreMunicipio(String NombreMunicipio){this.NombreMunicipio=NombreMunicipio;}
    }
