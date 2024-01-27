package com.example.sispakkehamilan.modul;

import com.google.gson.annotations.SerializedName;

public class Akun{

	@SerializedName("dataakun")
	private Dataakun dataakun;
	@SerializedName("datarule")
	private Datarule datarule;
	@SerializedName("datapenyakit")
	private Datapenyakit datapenyakit;

	@SerializedName("datariwayat")
	private DataRiwayat datariwayat;

	@SerializedName("message")
	private String message;

	@SerializedName("pesan")
	private String pesan;


	@SerializedName("status")
	private boolean status;

	public void setDataakun(Dataakun dataakun){
		this.dataakun = dataakun;
	}

	public void setDatapenyakit(Datapenyakit datapenyakit){
		this.datapenyakit = datapenyakit;
	}

	public void setDatariwayat(DataRiwayat datariwayat){
		this.datariwayat = datariwayat;
	}

	public Dataakun getDataakun(){
		return dataakun;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public String getPesan(){
		return pesan;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}