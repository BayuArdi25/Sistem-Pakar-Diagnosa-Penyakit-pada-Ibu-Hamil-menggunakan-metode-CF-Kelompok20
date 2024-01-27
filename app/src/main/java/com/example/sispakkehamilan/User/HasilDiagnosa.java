package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.SessionManager;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;
import com.example.sispakkehamilan.modul.HasilDiag;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilDiagnosa extends AppCompatActivity {

    TextView hs,sl;
    ApiInterface api;
    Button info, hm;
    String hasil, nam, alm, hp, namhasil, usrnm;
    SessionManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            hasil = extras.getString("hasil");
            namhasil = extras.getString("namhasil");
        }
        sm = new SessionManager(HasilDiagnosa.this);
        hs = findViewById(R.id.hasildiag);
        sl = findViewById(R.id.solusidiag);
        info = findViewById(R.id.butinfodiagno);
        hm = findViewById(R.id.buthomediagno);
        init(hasil,namhasil);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilDiagnosa.this, InfoPenyakit.class);
                startActivity(intent);
                finish();
            }
        });

        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilDiagnosa.this, UserHome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(String key, String keyy){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<HasilDiag> call = api.gethasilpak(key, keyy);
        call.enqueue(new Callback<HasilDiag>() {
            @Override
            public void onResponse(Call<HasilDiag> call, Response<HasilDiag> response) {
                if (response.body() != null && response.isSuccessful()) {
                    HasilDiag hsll = response.body();
                    String nm = hsll.getNamaPenyakit();
                    String nl = hsll.getNilai();
                    String sol = hsll.getSolusi();
                    String nil = nl.substring(0, nl.indexOf("."));
                    hs.setText(nm+" "+nil+"%");
                    sl.setText(sol);

                    String pny = nm + " " +nil+ "%";
                    nam = sm.getUserDetail().get(SessionManager.NAME);
                    alm = sm.getUserDetail().get(SessionManager.ALAMAT);
                    hp = sm.getUserDetail().get(SessionManager.NOHP);
                    usrnm = sm.getUserDetail().get(SessionManager.USERNAME);
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    String tnggal = date.format(calendar.getTime());
                    tambah(usrnm, nam, pny, alm, hp, tnggal);
                }else{
                }
            }

            @Override
            public void onFailure(Call<HasilDiag> call, Throwable t) {

            }
        });
    }

    private void tambah(String usernm, String na, String pen, String al, String nom, String tg) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Tambahriwayat(usernm, na, pen, al, nom, tg);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                }else{
                    Toast.makeText(HasilDiagnosa.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(HasilDiagnosa.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}