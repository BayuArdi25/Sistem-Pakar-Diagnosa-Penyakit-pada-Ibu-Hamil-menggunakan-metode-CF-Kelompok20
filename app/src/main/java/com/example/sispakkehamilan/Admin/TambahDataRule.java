package com.example.sispakkehamilan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;
import com.example.sispakkehamilan.modul.Datagejala;
import com.example.sispakkehamilan.modul.Datapenyakit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahDataRule extends AppCompatActivity {

    Spinner spindtpny, spindtgjl;
    EditText idrul,nilai;
    ApiInterface api;
    ArrayList<Datapenyakit> listpny;
    ArrayList<Datagejala> listgjl;
    Button tmbh;
    ImageButton kem;
    String idrule,idpny,idgeja,nl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_rule);
        spindtpny = findViewById(R.id.spintmbhpnyrul);
        spindtgjl = findViewById(R.id.spintmbhgjlrul);
        idrul = findViewById(R.id.tmbhidrul);
        nilai = findViewById(R.id.tmbhnilairul);
        kem = findViewById(R.id.tambahrulkembali);
        tmbh = findViewById(R.id.buttambahrul);
        init();
        initt();
        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahDataRule.this, DataRules.class);
                startActivity(intent);
                finish();
            }
        });
        tmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(idrul, nilai)) {
                    idrule = idrul.getText().toString();
                    nl = nilai.getText().toString();
                    idpny = spindtpny.getSelectedItem().toString();
                    idgeja = spindtgjl.getSelectedItem().toString();
                    tambah(idrule, idpny, idgeja, nl);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });
    }

    private void tambah(String idr, String idp, String idg, String nil) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.tambahdatarule(idr, idp, idg, nil);
        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(TambahDataRule.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    nilai.setText("");
                    idrul.setText("");
                }else{
                    Toast.makeText(TambahDataRule.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(TambahDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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
                    populateSpinner(listpny);

                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datapenyakit>> call, Throwable t) {
                Toast.makeText(TambahDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initt(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datagejala>> call = api.getdatagejala();
        call.enqueue(new Callback<ArrayList<Datagejala>>() {
            @Override
            public void onResponse(Call<ArrayList<Datagejala>> call, Response<ArrayList<Datagejala>> response) {
                if(response.isSuccessful()){
                    listgjl = response.body();
                    populateSpinn(listgjl);

                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datagejala>> call, Throwable t) {
                Toast.makeText(TambahDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateSpinn(ArrayList<Datagejala> data) {
        List<String> namaList = new ArrayList<>();

        for (Datagejala item : data) {
            namaList.add(item.getIdGejala());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindtgjl.setAdapter(adapter);
    }
    private void populateSpinner(ArrayList<Datapenyakit> data) {
        List<String> namaList = new ArrayList<>();

        for (Datapenyakit item : data) {
            namaList.add(item.getIdPenyakit());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindtpny.setAdapter(adapter);
    }

    private boolean areEditTextsFilled(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().length() == 0) {
                return false;
            }
        }
        return true;
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}