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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataGejala extends AppCompatActivity {

    EditText namagjl;
    ArrayList<Datagejala> listgj;
    String nmgjl, idgj;
    Button edit, hps;
    ImageButton back;
    ApiInterface api;
    Spinner idgjl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_gejala);
        namagjl = findViewById(R.id.editnamagjl);
        idgjl = findViewById(R.id.spinnergejala);
        back = findViewById(R.id.editgjlkembali);
        edit = findViewById(R.id.buteditgejala);
        hps = findViewById(R.id.buthapusgejala);
        init();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(namagjl)) {
                    nmgjl = namagjl.getText().toString();
                    idgj = idgjl.getSelectedItem().toString();
                    edit(idgj,nmgjl);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });

        hps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idgj = idgjl.getSelectedItem().toString();
                hapus(idgj);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataGejala.this, DataGejala.class);
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
                    populateSpinner(listgj);

                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datagejala>> call, Throwable t) {
                Toast.makeText(EditDataGejala.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateSpinner(ArrayList<Datagejala> data) {
        List<String> namaList = new ArrayList<>();

        for (Datagejala item : data) {
            namaList.add(item.getIdGejala());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idgjl.setAdapter(adapter);
    }

    private void edit(String idg, String namag) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Editdatagjl(idg, namag);
        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataGejala.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataGejala.this, DataGejala.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataGejala.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataGejala.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void hapus(String idg) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Hapusdatagjl(idg);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataGejala.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataGejala.this, DataGejala.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataGejala.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataGejala.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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