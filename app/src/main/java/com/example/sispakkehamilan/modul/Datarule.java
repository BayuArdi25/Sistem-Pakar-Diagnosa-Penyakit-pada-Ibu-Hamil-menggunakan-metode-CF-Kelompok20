package com.example.sispakkehamilan.modul;

import com.google.gson.annotations.SerializedName;

public class Datarule {

	@SerializedName("id_rule")
	private String idRule;

	@SerializedName("id_gejala")
	private String idGejala;

	@SerializedName("nilai_cf")
	private String nilaiCf;

	@SerializedName("id_penyakit")
	private String idPenyakit;

	public String getIdRule(){
		return idRule;
	}

	public String getIdGejala(){
		return idGejala;
	}

	public String getNilaiCf(){
		return nilaiCf;
	}

	public String getIdPenyakit(){
		return idPenyakit;
	}
}