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

import com.example.sispakkehamilan.Adapter.Adaptertabelrule;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Datarule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRules extends AppCompatActivity {

    ArrayList<Datarule> listrul;
    RecyclerView recy;
    Adaptertabelrule adp;
    ApiInterface api;
    Button buttmbh, butedit;
    ImageButton kem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_rules);
        recy = findViewById(R.id.recydatarul);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recy.setLayoutManager(layoutManager1);
        kem = findViewById(R.id.datarulkembali);
        buttmbh = findViewById(R.id.buttambahdatarul);
        butedit = findViewById(R.id.buteditdatarul);
        init();
        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataRules.this, AdminHome.class);
                startActivity(intent);
                finish();
            }
        });
        buttmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataRules.this, TambahDataRule.class);
                startActivity(intent);
                finish();
            }
        });
        butedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataRules.this, EditDataRule.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datarule>> call = api.getdatarule();
        call.enqueue(new Callback<ArrayList<Datarule>>() {
            @Override
            public void onResponse(Call<ArrayList<Datarule>> call, Response<ArrayList<Datarule>> response) {
                if(response.isSuccessful()){
                    listrul = response.body();
                    adp = new Adaptertabelrule(DataRules.this, listrul);
                    recy.setAdapter(adp);
                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datarule>> call, Throwable t) {
                Toast.makeText(DataRules.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}