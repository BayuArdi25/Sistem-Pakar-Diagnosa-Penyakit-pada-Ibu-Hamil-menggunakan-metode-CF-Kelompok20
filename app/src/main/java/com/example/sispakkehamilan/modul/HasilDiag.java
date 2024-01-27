package com.example.sispakkehamilan.modul;

import com.google.gson.annotations.SerializedName;

public class HasilDiag{

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("nama_penyakit")
	private String namaPenyakit;

	@SerializedName("solusi")
	private String solusi;

	@SerializedName("id_penyakit")
	private String idPenyakit;

	@SerializedName("status")
	private int status;

	public void setNilai(String nilai){
		this.nilai = nilai;
	}

	public String getNilai(){
		return nilai;
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

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}