package com.example.sispakkehamilan.Dokter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.Adapter.AdapterDataRiwayat;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.DataRiwayat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPasien extends AppCompatActivity {

    RecyclerView recyy;
    AdapterDataRiwayat adp;
    LinearLayoutManager linearLayoutManager;
    List<DataRiwayat> data;
    ImageButton bak;
    ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pasien);
        bak = findViewById(R.id.backriwayat);

        recyy = findViewById(R.id.recyrw);
        linearLayoutManager = new LinearLayoutManager(this);
        recyy.setLayoutManager(linearLayoutManager);
        recyy.setHasFixedSize(true);
        datarw();
        bak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(RiwayatPasien.this, DokterHome.class);
                startActivity(intet);
                finish();
            }
        });
    }

    public void datarw(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<DataRiwayat>> call = api.getdatariwayat();
        call.enqueue(new Callback<ArrayList<DataRiwayat>>(){
            @Override
            public void onResponse(Call<ArrayList<DataRiwayat>> call, Response<ArrayList<DataRiwayat>> response) {
                data = response.body();
                adp = new AdapterDataRiwayat(RiwayatPasien.this, data);
                recyy.setAdapter(adp);
                adp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<DataRiwayat>> call, Throwable t) {
                Toast.makeText(RiwayatPasien.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}