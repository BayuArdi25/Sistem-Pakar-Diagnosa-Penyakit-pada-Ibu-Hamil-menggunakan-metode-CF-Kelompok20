package com.example.sispakkehamilan.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Datagejala;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Diagnosa extends AppCompatActivity {

    ArrayList<HashMap<String, String>> list;
    ArrayList<Double> hasil;
    ProgressDialog pDialog;
    ArrayList<Datagejala> dtgj;
    ArrayList<String> namhasil;
    TextView pertanyaan, nmr;
    ApiInterface api;
    int counter;
    RadioGroup rg;
    Button lan, lngs;
    ImageButton kem;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        pertanyaan = findViewById(R.id.nmgejaladiag);
        nmr = findViewById(R.id.nmrdiag);
        rg = findViewById(R.id.rgdiagnosa);
        lan = findViewById(R.id.butdiagnosa);
        kem = findViewById(R.id.diagnosakembali);
        lngs = findViewById(R.id.butlangdiagnosa);
        getData();
        lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = rg.getCheckedRadioButtonId();
                if (selected == -1) {
                    Toast.makeText(Diagnosa.this, "Harus memilih 1 jawaban", Toast.LENGTH_SHORT).show();
                } else {
                    rb = findViewById(selected);
                    hasil.add(konversiJawaban(rb.getText().toString()));
                    showPertanyaan(++counter);
                }
            }
        });

        lngs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = rg.getCheckedRadioButtonId();
                if (selected == -1) {
                    Toast.makeText(Diagnosa.this, "Harus memilih 1 jawaban", Toast.LENGTH_SHORT).show();
                } else {
                    rb = findViewById(selected);
                    hasil.add(konversiJawaban(rb.getText().toString()));
                    ArrayList<Double> nilaiLebihDari02 = pilihNilaiLebihDari02(hasil);
                    if (nilaiLebihDari02.size() >= 4) {
                        langsung();
                    }else{
                        Toast.makeText(Diagnosa.this, "Harus memilih 4 Gejala", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diagnosa.this, UserHome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Double> pilihNilaiLebihDari02(ArrayList<Double> array) {
        ArrayList<Double> hasilPemilihan = new ArrayList<>();

        for (double nilai : array) {
            if (nilai > 0.1) {
                hasilPemilihan.add(nilai);
            }
        }
        return hasilPemilihan;
    }


    private double konversiJawaban(String value) {
        switch (value) {
            case "Tidak":
                return 0;
            case "Mungkin Tidak":
                return 0.2;
            case "Mungkin":
                return 0.4;
            case "Kemungkinan Besar":
                return 0.6;
            case "Hampir Pasti":
                return 0.8;
            case "Pasti":
                return 1;
        }
        return 0;
    }

    private void langsung(){
        while (hasil.size() < dtgj.size()) {
            hasil.add(0.0);
        }

        while (namhasil.size() < dtgj.size()) {
            Datagejala dtt = dtgj.get(namhasil.size());
            String namm = dtt.getIdGejala();

            if (!namhasil.contains(namm)) {
                namhasil.add(namm);
            }
        }

        Intent intent = new Intent(getApplicationContext(), HasilDiagnosa.class);
        intent.putExtra("namhasil", android.text.TextUtils.join("#", namhasil));
        intent.putExtra("hasil", android.text.TextUtils.join("#", hasil));
        startActivity(intent);
        finish();
    }


    private void showPertanyaan(int index) {
        if (index >= dtgj.size()) {
            Intent intent = new Intent(getApplicationContext(), HasilDiagnosa.class);
            intent.putExtra("hasil", android.text.TextUtils.join("#", hasil));
            intent.putExtra("namhasil", android.text.TextUtils.join("#", namhasil));
            startActivity(intent);
            finish();
        } else {
            Datagejala item = dtgj.get(index);
            String namm = item.getIdGejala();

            if (!namhasil.contains(namm)) {
                namhasil.add(namm);
            }
            pertanyaan.setText("Apakah anda mengalami " + item.getNamaGejala()+ " ?");
            nmr.setText(index+1 +"/"+dtgj.size());
            rg.clearCheck();
        }
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(Diagnosa.this);
        pDialog.setMessage("Sedang diproses...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void getData() {
        displayLoader();
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datagejala>> call = api.getdatagejala();
        call.enqueue(new Callback<ArrayList<Datagejala>>() {
            @Override
            public void onResponse(Call<ArrayList<Datagejala>> call, Response<ArrayList<Datagejala>> response) {

                pDialog.dismiss();
                if (response.isSuccessful()) {
                    dtgj = response.body();
                    if (0 == 0) {
                        if (dtgj.isEmpty()) {
                            Toast.makeText(Diagnosa.this, "Tidak ada data gejala", Toast.LENGTH_SHORT).show();
                        } else {
                            hasil = new ArrayList<>();
                            namhasil = new ArrayList<>();
                            counter = 0;
                            showPertanyaan(counter);
                        }
                    } else {
                        Toast.makeText(Diagnosa.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Diagnosa.this, "Error in response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datagejala>> call, Throwable t) {
                Toast.makeText(Diagnosa.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
