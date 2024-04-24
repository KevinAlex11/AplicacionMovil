package com.example.factoryclean.entidades;

import java.io.Serializable;

public class marcaA  implements Serializable {
    //Se crean las variables
    private int Id_MarcaAuto;
    private String Nombre;
    //Se crea el constructor de la clase
    public marcaA(){
        this.Id_MarcaAuto=Id_MarcaAuto;
        this.Nombre=Nombre;
    }
    //Se declaran los diferentes objetos con sus Getters y Setters
    public int getId_MarcaAuto(){return Id_MarcaAuto;}
    public void setId_MarcaAuto(int Id_MarcaAuto){this.Id_MarcaAuto=Id_MarcaAuto;}
    public String getNombre(){return Nombre;}
    public void setNombre(String Nombre){this.Nombre=Nombre;}
    }