package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.Adapter.AdapterInfoPenyakit;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Datapenyakit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoPenyakit extends AppCompatActivity {

    TextView nmpnya;
    RecyclerView recy;
    AdapterInfoPenyakit adp;
    LinearLayoutManager linearLayoutManager;
    List<Datapenyakit> data;
    ApiInterface api;
    ImageButton km;
    ProgressBar barr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_penyakit);

        barr = findViewById(R.id.prograsinfopny);
        km = findViewById(R.id.infopnykembali);
        recy = findViewById(R.id.recyclerv);
        linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        recy.setHasFixedSize(true);
        dataaa();
        km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoPenyakit.this, UserHome.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void dataaa(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datapenyakit>> call = api.getdatapenyakit();
        call.enqueue(new Callback<ArrayList<Datapenyakit>>(){
            @Override
            public void onResponse(Call<ArrayList<Datapenyakit>> call, Response<ArrayList<Datapenyakit>> response) {
                barr.setVisibility(View.GONE);
                data = response.body();
                adp = new AdapterInfoPenyakit(InfoPenyakit.this, data);
                recy.setAdapter(adp);
                adp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Datapenyakit>> call, Throwable t) {
                Toast.makeText(InfoPenyakit.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                barr.setVisibility(View.GONE);
            }
        });
    }
}