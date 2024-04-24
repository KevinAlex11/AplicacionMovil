package com.example.factoryclean.entidades;

import java.io.Serializable;

public class tamCol implements Serializable {
    private int Id_TamaColchon;
    private String TamaColchon;
    //Se crea el constructor de la clase
    public tamCol(){
        this.Id_TamaColchon=Id_TamaColchon;
        this.TamaColchon=TamaColchon;
    }
    //Se declaran los diferentes objetos con sus Getter y Setters
    public int getId_TamaColchon(){return Id_TamaColchon;}
    public void setId_Tamaolchon(int Id_TamaColchon){this.Id_TamaColchon=Id_TamaColchon;}
    public String getTamaColchon(){return TamaColchon;}
    public void setTamaColchon(String TamaColchon){this.TamaColchon=TamaColchon;}
}
