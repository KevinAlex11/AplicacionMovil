package com.example.factoryclean.entidades;

import java.io.Serializable;

public class tipoTelaSill implements Serializable {
    private int Id_TipoTelaSillon;
    private String TipoTelaSillon;
    private String DescTipoTelaSillon;
    private String ImagenTipoTela;
    //Se crea el constructor de la clase
    public tipoTelaSill(){
        this.Id_TipoTelaSillon=Id_TipoTelaSillon;
        this.TipoTelaSillon=TipoTelaSillon;
        this.DescTipoTelaSillon=DescTipoTelaSillon;
        this.ImagenTipoTela=ImagenTipoTela;
    }
    //Se declaran los diferentes objetos con sus Getters y Setters
    public int getId_TipoTelaSillon(){return Id_TipoTelaSillon;}
    public void setId_TipoTelaSillon(int Id_TipoTelaSillon){this.Id_TipoTelaSillon=Id_TipoTelaSillon;}
    public String getTipoTelaSillon(){return TipoTelaSillon;}
    public void setTipoTelaSillon(String TipoTelaSillon){this.TipoTelaSillon=TipoTelaSillon;}
    public String getDescTipoTelaSillon(){return DescTipoTelaSillon;}
    public void setDescTipoTelaSillon(String DescTipoTelaSillon){this.DescTipoTelaSillon=DescTipoTelaSillon;}
    public String getImagenTipoTela(){return ImagenTipoTela;}
    public void setImagenTipoTela(String ImagenTipoTela){this.ImagenTipoTela=ImagenTipoTela;}
    }