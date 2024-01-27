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

import com.example.sispakkehamilan.Adapter.Adaptertabelgejala;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Datagejala;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataGejala extends AppCompatActivity {

    ArrayList<Datagejala> listgj;
    RecyclerView recy;
    Adaptertabelgejala adp;
    ApiInterface api;
    Button buttmbh, butedit;
    ImageButton kem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_gejala);
        recy = findViewById(R.id.recydatagjl);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recy.setLayoutManager(layoutManager1);
        kem = findViewById(R.id.datagejkembali);
        buttmbh = findViewById(R.id.buttambahdatagjl);
        butedit = findViewById(R.id.buteditdatagjl);
        init();
        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataGejala.this, AdminHome.class);
                startActivity(intent);
                finish();
            }
        });
        buttmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataGejala.this, TambahDataGejala.class);
                startActivity(intent);
                finish();
            }
        });
        butedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataGejala.this, EditDataGejala.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datagejala>> call = api.getdatagejala();
        call.enqueue(new Callback<ArrayList<Datagejala>>() {
            @Override
            public void onResponse(Call<ArrayList<Datagejala>> call, Response<ArrayList<Datagejala>> response) {
                if(response.isSuccessful()){
                    listgj = response.body();
                    adp = new Adaptertabelgejala(DataGejala.this, listgj);
                    recy.setAdapter(adp);
                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datagejala>> call, Throwable t) {
                Toast.makeText(DataGejala.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}