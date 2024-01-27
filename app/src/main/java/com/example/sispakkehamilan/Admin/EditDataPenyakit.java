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
import com.example.sispakkehamilan.modul.Datapenyakit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataPenyakit extends AppCompatActivity {

    EditText namapny,kete,solu,cir;
    ArrayList<Datapenyakit> listpny;
    String nampny,idpn,ket,sol,cr;
    Button edit, hps;
    ImageButton back;
    ApiInterface api;
    Spinner idpny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_penyakit);

        namapny = findViewById(R.id.editnamapny);
        idpny = findViewById(R.id.spinnerpenyakit);
        kete = findViewById(R.id.editketepny);
        solu = findViewById(R.id.editsolupny);
        back = findViewById(R.id.editpnykembali);
        edit = findViewById(R.id.buteditpenyakit);
        hps = findViewById(R.id.buthapuspenyakit);
        cir = findViewById(R.id.editciripny);
        init();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(namapny, kete, solu, cir)) {
                    nampny = namapny.getText().toString();
                    idpn = idpny.getSelectedItem().toString();
                    ket = kete.getText().toString();
                    sol = solu.getText().toString();
                    cr = cir.getText().toString();
                    edit(idpn, nampny, ket, sol, cr);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });

        hps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idpn = idpny.getSelectedItem().toString();
                hapus(idpn);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataPenyakit.this, DataPenyakit.class);
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
                    populateSpinner(listpny);

                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datapenyakit>> call, Throwable t) {
                Toast.makeText(EditDataPenyakit.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateSpinner(ArrayList<Datapenyakit> data) {
        List<String> namaList = new ArrayList<>();

        for (Datapenyakit item : data) {
            namaList.add(item.getIdPenyakit());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idpny.setAdapter(adapter);
    }

    private void edit(String idp, String namap, String keter, String solusi, String ciri) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.EditdatapnyResponse(idp, namap, keter, solusi, ciri);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataPenyakit.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataPenyakit.this, DataPenyakit.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataPenyakit.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataPenyakit.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void hapus(String idp) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.HapusdatapnyResponse(idp);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataPenyakit.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataPenyakit.this, DataPenyakit.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataPenyakit.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataPenyakit.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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