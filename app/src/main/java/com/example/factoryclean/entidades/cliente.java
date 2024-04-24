package com.example.factoryclean.entidades;


import android.content.Context;

import java.io.Serializable;

public class cliente implements Serializable {
    //Se crean las variables
    private int Id_Cliente;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Telefono;
    private String Usuario;
    private String Contra;
    private String TipoUsu;
    private int Id_Negocio;

//Se crea el constructor de la clase
public cliente(){
    this.Id_Cliente=Id_Cliente;
    this.Nombre=Nombre;
    this.Apellido=Apellido;
    this.Correo=Correo;
    this.Telefono=Telefono;
    this.Usuario=Usuario;
    this.Contra=Contra;
    this.TipoUsu=TipoUsu;
    this.Id_Negocio=Id_Negocio;
}
//Se declaran los diferentes objetos con sus Getters y Setters
public int getId_Cliente(){return Id_Cliente;}
public void setId_Cliente(int Id_Cliente){this.Id_Cliente=Id_Cliente;}
public String getNombre(){return Nombre;}
public void setNombre(String Nombre){this.Nombre=Nombre;}
public String getApellido(){return Apellido;}
public void setApellido(String Apellido){this.Apellido=Apellido;}
public String getCorreo(){return Correo;}
public void setCorreo(String Correo){this.Correo=Correo;}
public String getTelefono(){return Telefono;}
public void setTelefono(String Telefono){this.Telefono=Telefono;}
public String getUsuario(){return Usuario;}
public void setUsuario(String Usuario){this.Usuario=Usuario;}
public String getContra(){return Contra;}
public void setContra(String Contra){this.Contra=Contra;}
public String getTipoUsu(){return TipoUsu;}
public void setTipoUsu(String TipoUsu){this.TipoUsu=TipoUsu;}
public int getId_Negocio(){return Id_Negocio;}
public void setId_Negocio(int Id_Negocio){this.Id_Negocio=Id_Negocio;}

}