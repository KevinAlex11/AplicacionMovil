package com.example.factoryclean.entidades;

import java.io.Serializable;

public class tipoSill implements Serializable {
    private int Id_TipoSillon;
    private String TipoSillon;
    private String DescTipoSillon;
    private String ImagenTipoSillon;
    //Se crea el constructor de la clase
    public tipoSill(){
        this.Id_TipoSillon=Id_TipoSillon;
        this.TipoSillon=TipoSillon;
        this.DescTipoSillon=DescTipoSillon;
        this.ImagenTipoSillon=ImagenTipoSillon;
    }
    //Se declaran los diferentes objetos con sus Getters y Setters
    public int getId_TipoSillon(){return Id_TipoSillon;}
    public void setId_TipoSillon(int Id_TipoSillon){this.Id_TipoSillon=Id_TipoSillon;}
    public String getTipoSillon(){return TipoSillon;}
    public void setTipoSillon(String TipoSillon){this.TipoSillon=TipoSillon;}
    public String getDescTipoSillon(){return DescTipoSillon;}
    public void setDescTipoSillon(String DescTipoSillon){this.DescTipoSillon=DescTipoSillon;}
    public String getImagenTipoSillon(){return ImagenTipoSillon;}
    public void setImagenTipoSillon(String ImagenTipoSillon){this.ImagenTipoSillon=ImagenTipoSillon;}
}
