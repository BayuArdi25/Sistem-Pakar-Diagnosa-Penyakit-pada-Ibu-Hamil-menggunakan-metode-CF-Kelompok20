package com.example.sispakkehamilan.modul;

import com.google.gson.annotations.SerializedName;

public class Datagejala {
    @SerializedName("nama_gejala")
    private String namaGejala;
    @SerializedName("id_gejala")
    private String idGejala;


    public void setNamaGejala(String namaGejala){
        this.namaGejala = namaGejala;
    }

    public String getNamaGejala(){
        return namaGejala;
    }

    public void setIdGejala(String idGejala){
        this.idGejala = idGejala;
    }

    public String getIdGejala(){
        return idGejala;
    }
}
