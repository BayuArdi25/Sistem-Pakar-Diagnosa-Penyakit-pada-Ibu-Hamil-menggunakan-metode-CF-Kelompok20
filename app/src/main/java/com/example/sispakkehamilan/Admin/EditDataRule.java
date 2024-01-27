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
import com.example.sispakkehamilan.modul.Datarule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataRule extends AppCompatActivity {

    EditText nilai;
    Button edit, hps;
    ImageButton back;
    ApiInterface api;
    Spinner idpny, idrul, idgjl;
    ArrayList<Datapenyakit> listpny;
    ArrayList<Datarule> listrul;
    ArrayList<Datagejala> listgjl;
    String idr,idp,idg,nil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_rule);
        idpny = findViewById(R.id.spineditpnyrul);
        idrul = findViewById(R.id.spineditrul);
        idgjl = findViewById(R.id.spineditgjlrul);
        nilai = findViewById(R.id.editnilairul);
        edit = findViewById(R.id.buteditrule);
        hps = findViewById(R.id.buthapusrule);
        back = findViewById(R.id.editrulkembali);
        init();
        initt();
        inittt();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataRule.this, DataRules.class);
                startActivity(intent);
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(nilai)) {
                    idr = idrul.getSelectedItem().toString();
                    nil = nilai.getText().toString();
                    idp = idpny.getSelectedItem().toString();
                    idg = idgjl.getSelectedItem().toString();
                    edit(idr, idp, idg, nil);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });
        hps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idr = idrul.getSelectedItem().toString();
                hapus(idr);
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
                Toast.makeText(EditDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(EditDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void inittt(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Datarule>> call = api.getdatarule();
        call.enqueue(new Callback<ArrayList<Datarule>>() {
            @Override
            public void onResponse(Call<ArrayList<Datarule>> call, Response<ArrayList<Datarule>> response) {
                if(response.isSuccessful()){
                    listrul = response.body();
                    populateSpin(listrul);
                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Datarule>> call, Throwable t) {
                Toast.makeText(EditDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void edit(String idrl, String idpn, String idgj, String nl) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.editdatarule(idrl, idpn, idgj, nl);
        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataRule.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataRule.this, DataRules.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataRule.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void hapus(String idrl) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.hapusdatarule(idrl);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataRule.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataRule.this, DataRules.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataRule.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataRule.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        idgjl.setAdapter(adapter);
    }
    private void populateSpin(ArrayList<Datarule> data) {
        List<String> namaList = new ArrayList<>();

        for (Datarule item : data) {
            namaList.add(item.getIdRule());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idrul.setAdapter(adapter);
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