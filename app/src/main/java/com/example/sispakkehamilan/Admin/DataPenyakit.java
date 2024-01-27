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

import com.example.sispakkehamilan.Adapter.Adaptertabelpenyakit;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Datapenyakit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPenyakit extends AppCompatActivity {

    ArrayList<Datapenyakit> listpny;
    RecyclerView recy;
    Adaptertabelpenyakit adp;
    ApiInterface api;
    Button buttmbh, butedit;
    ImageButton kem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_penyakit);

        recy = findViewById(R.id.recydatapny);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recy.setLayoutManager(layoutManager1);
        buttmbh = findViewById(R.id.buttambahdatapny);
        butedit = findViewById(R.id.buteditdatapny);
        kem = findViewById(R.id.datapnykembali);
        init();
        buttmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataPenyakit.this, TambahDataPenyakit.class);
                startActivity(intent);
                finish();
            }
        });
        butedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataPenyakit.this, EditDataPenyakit.class);
                startActivity(intent);
                finish();
            }
        });
        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataPenyakit.this, AdminHome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datapenyakit>> call = api.getdatapenyakit();
        call.enqueue(new Callback<ArrayList<Datapenyakit>>() {
            @Override
            public void onResponse(Call<ArrayList<Datapenyakit>> call, Response<ArrayList<Datapenyakit>> response) {
                if(response.isSuccessful()){
                    listpny = response.body();
                    adp = new Adaptertabelpenyakit(DataPenyakit.this, listpny);
                    RecyclerView.Adapter adapter1 = new Adaptertabelpenyakit(DataPenyakit.this, listpny);
                    recy.setAdapter(adapter1);
                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datapenyakit>> call, Throwable t) {
                Toast.makeText(DataPenyakit.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}