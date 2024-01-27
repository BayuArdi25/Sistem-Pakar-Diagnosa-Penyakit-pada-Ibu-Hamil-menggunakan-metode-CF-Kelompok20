package com.example.sispakkehamilan.Dokter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.Adapter.AdapterHapusAkun;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Dataakun;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HapusUser extends AppCompatActivity {

    RecyclerView recyy;
    AdapterHapusAkun adp;
    LinearLayoutManager linearLayoutManager;
    List<Dataakun> data;

    ApiInterface api;
    ImageButton beck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_user);
        beck = findViewById(R.id.backhapusakun);
        recyy = findViewById(R.id.recyakun);
        linearLayoutManager = new LinearLayoutManager(this);
        recyy.setLayoutManager(linearLayoutManager);
        recyy.setHasFixedSize(true);
        datak();
        beck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(HapusUser.this, DokterHome.class);
                startActivity(intet);
                finish();
            }
        });

    }

    public void datak(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Dataakun>> call = api.getdataakun();
        call.enqueue(new Callback<ArrayList<Dataakun>>(){
            @Override
            public void onResponse(Call<ArrayList<Dataakun>> call, Response<ArrayList<Dataakun>> response) {
                data = response.body();
                adp = new AdapterHapusAkun(HapusUser.this, data);
                recyy.setAdapter(adp);
                adp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Dataakun>> call, Throwable t) {
                Toast.makeText(HapusUser.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}