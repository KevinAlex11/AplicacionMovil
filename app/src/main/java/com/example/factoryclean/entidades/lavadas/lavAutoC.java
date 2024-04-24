package com.example.factoryclean.entidades.lavadas;

import com.example.factoryclean.MainActivity;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.io.Serializable;

public class lavAutoC implements Serializable {
    private int Id_PedidoAuto;
    private String Domicilio;
    private String Num_Domicilio;
    private String Colonia;
    private String Municipio;
    private String Fecha_Lavada;
    private String Hora_Lavada;
    private String Tipo_Auto;
    private String Marca_Auto;
    private String Modelo_Auto;
    private String Tipo_TelaAuto;
    private String Estado_PedidoAuto;
    private int Id_Cliente;
    private int Id_Negocio;

    public lavAutoC(){
        this.Id_PedidoAuto=Id_PedidoAuto;
        this.Domicilio=Domicilio;
        this.Num_Domicilio=Num_Domicilio;
        this.Colonia=Colonia;
        this.Municipio=Municipio;
        this.Fecha_Lavada=Fecha_Lavada;
        this.Hora_Lavada=Hora_Lavada;
        this.Tipo_Auto=Tipo_Auto;
        this.Marca_Auto=Marca_Auto;
        this.Modelo_Auto=Modelo_Auto;
        this.Tipo_TelaAuto=Tipo_TelaAuto;
        this.Estado_PedidoAuto=Estado_PedidoAuto;
        this.Id_Cliente=Id_Cliente;
        this.Id_Negocio=Id_Negocio;
    }

    public int getId_PedidoAuto(){return Id_PedidoAuto;}
    public void setId_PedidoAuto(int Id_PedidoAuto){this.Id_PedidoAuto=Id_PedidoAuto;}
    public String getDomicilio(){return Domicilio;}
    public void setDomicilio(String Domicilio){this.Domicilio=Domicilio;}
    public String getNum_Domicilio(){return Num_Domicilio;}
    public void setNum_Domicilio(String Num_Domicilio){this.Num_Domicilio=Num_Domicilio;}
    public String getColonia(){return Colonia;}
    public void setColonia(String Colonia){this.Colonia=Colonia;}
    public String getMunicipio(){return Municipio;}
    public void setMunicipio(String Municipio){this.Municipio=Municipio;}
    public String getFecha_Lavada(){return Fecha_Lavada;}
    public void setFecha_Lavada(String Fecha_Lavada){this.Fecha_Lavada=Fecha_Lavada;}
    public String getHora_Lavada(){return Hora_Lavada;}
    public void setHora_Lavada(String Hora_Lavada){this.Hora_Lavada=Hora_Lavada;}
    public String getTipo_Auto(){return Tipo_Auto;}
    public void setTipo_Auto(String Tipo_Auto){this.Tipo_Auto=Tipo_Auto;}
    public String getMarca_Auto(){return Marca_Auto;}
    public void setMarca_Auto(String Marca_Auto){this.Marca_Auto=Marca_Auto;}
    public String getModelo_Auto(){return Modelo_Auto;}
    public void setModelo_Auto(String Modelo_Auto){this.Modelo_Auto=Modelo_Auto;}
    public String getTipo_TelaAuto(){return Tipo_TelaAuto;}
    public void setTipo_TelaAuto(String Tipo_TelaAuto){this.Tipo_TelaAuto=Tipo_TelaAuto;}
    public String getEstado_PedidoAuto(){return Estado_PedidoAuto;}
    public void setEstado_PedidoAuto(String Estado_PedidoAuto){this.Estado_PedidoAuto=Estado_PedidoAuto;}
    public int getId_Cliente(){return Id_Cliente;}
    public void setId_Cliente(int Id_Cliente){this.Id_Cliente=Id_Cliente;}
    public int getId_Negocio(){return Id_Negocio;}
    public void setId_Negocio(int Id_Negocio){this.Id_Negocio=Id_Negocio;}


    }




