package com.example.factoryclean.entidades.lavadas;

import java.io.Serializable;

public class lavSillonC implements Serializable {
    private int Id_PedidoSillon;
    private String Domicilio;
    private String Num_Domicilio;
    private String Colonia;
    private String Municipio;
    private String Fecha_Lavada;
    private String Hora_Lavada;
    private String Tipo_TelaSillon;
    private String Tipo_Sillon;
    private String Numero_Sillon;
    private String Numero_Cojin;
    private String Estado_PedidoSillon;
    private int Id_Cliente;
    private int Id_Negocio;

    public lavSillonC(){
        this.Id_PedidoSillon=Id_PedidoSillon;
        this.Domicilio=Domicilio;
        this.Num_Domicilio=Num_Domicilio;
        this.Colonia=Colonia;
        this.Municipio=Municipio;
        this.Fecha_Lavada=Fecha_Lavada;
        this.Hora_Lavada=Hora_Lavada;
        this.Estado_PedidoSillon=Estado_PedidoSillon;
        this.Id_Cliente=Id_Cliente;
        this.Id_Negocio=Id_Negocio;
    }

    public int getId_PedidoSillon(){return Id_PedidoSillon;}
    public void setId_PedidoSillon(int Id_PedidoSillon){this.Id_PedidoSillon=Id_PedidoSillon;}
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
    public String getTipo_TelaSillon(){return Tipo_TelaSillon;}
    public void setTipo_TelaSillon(String Tipo_TelaSillon){this.Tipo_TelaSillon=Tipo_TelaSillon;}
    public String getTipo_Sillon(){return Tipo_Sillon;}
    public void setTipo_Sillon(String Tipo_Sillon){this.Tipo_Sillon=Tipo_Sillon;}
    public String getNumero_Sillon(){return Numero_Sillon;}
    public void setNumero_Sillon(String Numero_Sillon){this.Numero_Sillon=Numero_Sillon;}
    public String getNumero_Cojin(){return Numero_Cojin;}
    public void setNumero_Cojin(String Numero_Cojin){this.Numero_Cojin=Numero_Cojin;}
    public String getEstado_PedidoSillon(){return Estado_PedidoSillon;}
    public void setEstado_PedidoSillon(String Estado_PedidoSillon){this.Estado_PedidoSillon=Estado_PedidoSillon;}
    public int getId_Cliente(){return Id_Cliente;}
    public void setId_Cliente(int Id_Cliente){this.Id_Cliente=Id_Cliente;}
    public int getId_Negocio(){return Id_Negocio;}
    public void setId_Negocio(int Id_Negocio){this.Id_Negocio=Id_Negocio;}
}
