package com.example.sispakkehamilan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahDataGejala extends AppCompatActivity {

    Button tmba;
    ImageButton back;
    ApiInterface api;
    EditText idgjl, nmgjl;
    String id, nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_gejala);
        back = findViewById(R.id.tambahgjlkembali);
        tmba = findViewById(R.id.buttambahgejala);
        idgjl = findViewById(R.id.tmbhidgjl);
        nmgjl = findViewById(R.id.tmbhnamagjl);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahDataGejala.this, DataGejala.class);
                startActivity(intent);
                finish();
            }
        });

        tmba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(nmgjl, idgjl)) {
                    id = idgjl.getText().toString();
                    nm = nmgjl.getText().toString();
                    tambah(id,nm);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });

    }

    private void tambah(String idg, String namag) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Tambahdatagjl(idg, namag);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(TambahDataGejala.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    nmgjl.setText("");
                    idgjl.setText("");
                }else{
                    Toast.makeText(TambahDataGejala.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(TambahDataGejala.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
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