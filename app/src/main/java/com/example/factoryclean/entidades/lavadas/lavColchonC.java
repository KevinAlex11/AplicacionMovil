package com.example.factoryclean.entidades.lavadas;

import java.io.Serializable;

public class lavColchonC implements Serializable {
    private int Id_PedidoColchon;
    private String Domicilio;
    private String Num_Domicilio;
    private String Colonia;
    private String Municipio;
    private String Fecha_Lavada;
    private String Hora_Lavada;
    private String Tamaño_Colchon;
    private String Marca_colchon;
    private String Estado_PedidoCol;
    private int Id_Cliente;
    private int Id_Negocio;

    public lavColchonC(){
        this.Id_PedidoColchon=Id_PedidoColchon;
        this.Domicilio=Domicilio;
        this.Num_Domicilio=Num_Domicilio;
        this.Colonia=Colonia;
        this.Municipio=Municipio;
        this.Fecha_Lavada=Fecha_Lavada;
        this.Hora_Lavada=Hora_Lavada;
        this.Tamaño_Colchon=Tamaño_Colchon;
        this.Marca_colchon=Marca_colchon;
        this.Estado_PedidoCol=Estado_PedidoCol;
        this.Id_Cliente=Id_Cliente;
        this.Id_Negocio=Id_Negocio;
    }

    public int getId_PedidoColchon(){return Id_PedidoColchon;}
    public void setId_PedidoColchon(int Id_PedidoColchon){this.Id_PedidoColchon=Id_PedidoColchon;}
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
    public String getTamaño_Colchon(){return Tamaño_Colchon;}
    public void setTamaño_Colchon(String Tamaño_Colchon){this.Tamaño_Colchon=Tamaño_Colchon;}
    public String getMarca_colchon(){return Marca_colchon;}
    public void setMarca_colchon(String Marca_colchon){this.Marca_colchon=Marca_colchon;}
    public String getEstado_PedidoCol(){return Estado_PedidoCol;}
    public void setEstado_PedidoCol(String Estado_PedidoCol){this.Estado_PedidoCol=Estado_PedidoCol;}
    public int getId_Cliente(){return Id_Cliente;}
    public void setId_Cliente(int Id_Cliente){this.Id_Cliente=Id_Cliente;}
    public int getId_Negocio(){return Id_Negocio;}
    public void setId_Negocio(int Id_Negocio){this.Id_Negocio=Id_Negocio;}
}
