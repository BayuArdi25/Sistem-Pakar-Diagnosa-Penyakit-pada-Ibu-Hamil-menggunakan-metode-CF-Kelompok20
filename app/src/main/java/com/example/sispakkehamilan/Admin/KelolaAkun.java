package com.example.sispakkehamilan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.Adapter.Adaptertabelakun;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Dataakun;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelolaAkun extends AppCompatActivity {

    ArrayList<Dataakun> listak;
    RecyclerView recy;
    Adaptertabelakun adp;
    ApiInterface api;
    Button buttmbh, butedit;
    ImageButton kem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_akun);
        recy = findViewById(R.id.recydataakun);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recy.setLayoutManager(layoutManager1);
        buttmbh = findViewById(R.id.buttambahdataakun);
        butedit = findViewById(R.id.buteditdataakun);
        kem = findViewById(R.id.dataakunkembali);
        init();
        buttmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KelolaAkun.this, TambahAkun.class);
                startActivity(intent);
                finish();
            }
        });
        butedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KelolaAkun.this, EditDataAkun.class);
                startActivity(intent);
                finish();
            }
        });
        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KelolaAkun.this, AdminHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Dataakun>> call = api.liatakun();
        call.enqueue(new Callback<ArrayList<Dataakun>>() {
            @Override
            public void onResponse(Call<ArrayList<Dataakun>> call, Response<ArrayList<Dataakun>> response) {
                if(response.isSuccessful()){
                    listak = response.body();
                    adp = new Adaptertabelakun(KelolaAkun.this, listak);
                    recy.setAdapter(adp);
                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dataakun>> call, Throwable t) {
                Toast.makeText(KelolaAkun.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}