package com.example.factoryclean.entidades;

import java.io.Serializable;

public class precio  implements Serializable {

    private String PrecioC;
    private String PrecioS;
    private String PrecioA;
    private String PrecioCoj;
    public precio(){
        this.PrecioC=PrecioC;
        this.PrecioA=PrecioA;
        this.PrecioS=PrecioS;
        this.PrecioCoj=PrecioCoj;
    }

    public String getPrecioC(){return PrecioC;}
    public void setPrecioC(String PrecioC){this.PrecioC=PrecioC;}
    public String getPrecioS(){return PrecioS;}
    public void setPrecioS(String PrecioS){this.PrecioS=PrecioS;}
    public String getPrecioA(){return PrecioA;}
    public void setPrecioA(String PrecioA){this.PrecioA=PrecioA;}
    public String getPrecioCoj(){return PrecioCoj;}
    public void setPrecioCoj(String PrecioCoj){this.PrecioCoj=PrecioCoj;}
}
