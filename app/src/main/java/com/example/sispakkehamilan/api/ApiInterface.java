package com.example.sispakkehamilan.api;

import com.example.sispakkehamilan.modul.Akun;
import com.example.sispakkehamilan.modul.DataRiwayat;
import com.example.sispakkehamilan.modul.Dataakun;
import com.example.sispakkehamilan.modul.Datagejala;
import com.example.sispakkehamilan.modul.Datapenyakit;
import com.example.sispakkehamilan.modul.Datarule;
import com.example.sispakkehamilan.modul.HasilDiag;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Akun> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("regis.php")
    Call<Akun>RegisResponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("nohp") String nohp,
            @Field("level") String level,
            @Field("tanggal") String tanggal
    );

    @FormUrlEncoded
    @POST("editdataakun.php")
    Call<Akun>editdataakun(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("nohp") String nohp,
            @Field("level") String level
    );

    @FormUrlEncoded
    @POST("ubahpass.php")
    Call<Akun> ubahpw(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("datapenyakit.php")
    Call<ArrayList<Datapenyakit>> getdatapenyakit();

    @GET("liatakun.php")
    Call<ArrayList<Dataakun>> getdataakun();

    @GET("liatakun2.php")
    Call<ArrayList<Dataakun>> liatakun();

    @GET("datariwayat.php")
    Call<ArrayList<DataRiwayat>> getdatariwayat();

    @GET("datagejala.php")
    Call<ArrayList<Datagejala>> getdatagejala();
    @GET("datarule.php")
    Call<ArrayList<Datarule>> getdatarule();

    @FormUrlEncoded
    @POST("tmbhdatapenyakit.php")
    Call<Akun>TambahdatapnyResponse(
            @Field("id_penyakit") String id_penyakit,
            @Field("nama_penyakit") String nama_penyakit,
            @Field("keterangan") String keterangan,
            @Field("solusi") String solusi,
            @Field("ciri") String ciri
    );

    @FormUrlEncoded
    @POST("tmbhdatarule.php")
    Call<Akun>tambahdatarule(
            @Field("id_rule") String id_rule,
            @Field("id_penyakit") String id_penyakit,
            @Field("id_gejala") String id_gejala,
            @Field("nilai_cf") String nilai_cf
    );

    @FormUrlEncoded
    @POST("tmbhdatagejala.php")
    Call<Akun>Tambahdatagjl(
            @Field("id_gejala") String id_gejala,
            @Field("nama_gejala") String nama_gejala
    );

    @FormUrlEncoded
    @POST("editdatapenyakit.php")
    Call<Akun>EditdatapnyResponse(
            @Field("id_penyakit") String id_penyakit,
            @Field("nama_penyakit") String nama_penyakit,
            @Field("keterangan") String keterangan,
            @Field("solusi") String solusi,
            @Field("ciri") String ciri
    );

    @FormUrlEncoded
    @POST("editdatarule.php")
    Call<Akun>editdatarule(
            @Field("id_rule") String id_rule,
            @Field("id_penyakit") String id_penyakit,
            @Field("id_gejala") String id_gejala,
            @Field("nilai_cf") String nilai_cf
    );

    @FormUrlEncoded
    @POST("editdatagejala.php")
    Call<Akun>Editdatagjl(
            @Field("id_gejala") String id_gejala,
            @Field("nama_gejala") String nama_gejala
    );

    @FormUrlEncoded
    @POST("hapusdatapenyakit.php")
    Call<Akun>HapusdatapnyResponse(
            @Field("id_penyakit") String id_penyakit);
    @FormUrlEncoded
    @POST("hapusdatarule.php")
    Call<Akun>hapusdatarule(
            @Field("id_rule") String id_rule);
    @FormUrlEncoded
    @POST("hapusdatagejala.php")
    Call<Akun>Hapusdatagjl(
            @Field("id_gejala") String id_gejala);

    @FormUrlEncoded
    @POST("hapusakun.php")
    Call<Akun>Hapusakun(
            @Field("username") String username);

    @FormUrlEncoded
    @POST("hapusdatariwayat.php")
    Call<Akun>Hapusdatariwayat(
            @Field("id_riwayat") String id_riwayat);

    @GET("rule.php")
    Call<HasilDiag> gethasilpak(
            @Query("key") String keyword,
            @Query("keyy") String keyyword
    );

    @FormUrlEncoded
    @POST("tambahriwayat.php")
    Call<Akun>Tambahriwayat(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("penyakit") String penyakit,
            @Field("alamat") String alamat,
            @Field("nohp") String nohp,
            @Field("tanggal") String tanggal
    );

}
