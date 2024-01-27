package com.example.sispakkehamilan.modul;

import com.google.gson.annotations.SerializedName;

public class DataRiwayat{

	@SerializedName("username")
	private String username;
	@SerializedName("penyakit")
	private String penyakit;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nohp")
	private String nohp;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("id_riwayat")
	private String idRiwayat;

	@SerializedName("alamat")
	private String alamat;

	public void setPenyakit(String penyakit){
		this.penyakit = penyakit;
	}

	public String getPenyakit(){
		return penyakit;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNohp(String nohp){
		this.nohp = nohp;
	}

	public String getNohp(){
		return nohp;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setIdRiwayat(String idRiwayat){
		this.idRiwayat = idRiwayat;
	}

	public String getIdRiwayat(){
		return idRiwayat;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}