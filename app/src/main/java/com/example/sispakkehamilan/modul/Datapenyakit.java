package com.example.sispakkehamilan.modul;

import com.google.gson.annotations.SerializedName;

public class Datapenyakit{

	@SerializedName("ciri")
	private String ciri;
	@SerializedName("keterangan")
	private String keterangan;
	@SerializedName("solusi")
	private String solusi;
	@SerializedName("nama_penyakit")
	private String namaPenyakit;
	@SerializedName("id_penyakit")
	private String idPenyakit;

	public void setCiri(String ciri){
		this.ciri = ciri;
	}
	public String getCiri(){
		return ciri;
	}
	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setNamaPenyakit(String namaPenyakit){
		this.namaPenyakit = namaPenyakit;
	}

	public String getNamaPenyakit(){
		return namaPenyakit;
	}

	public void setSolusi(String solusi){
		this.solusi = solusi;
	}

	public String getSolusi(){
		return solusi;
	}

	public void setIdPenyakit(String idPenyakit){
		this.idPenyakit = idPenyakit;
	}

	public String getIdPenyakit(){
		return idPenyakit;
	}
}