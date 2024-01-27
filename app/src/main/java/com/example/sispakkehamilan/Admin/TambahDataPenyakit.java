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

public class TambahDataPenyakit extends AppCompatActivity {

    EditText namapny,idpny,kete,solu,cir;
    String nampny,idpn,ket,sol,cr;
    Button tmbah;
    ImageButton back;
    ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_penyakit);
        namapny = findViewById(R.id.tmbhnamapny);
        idpny = findViewById(R.id.tmbhidpny);
        kete = findViewById(R.id.tmbhketepny);
        solu = findViewById(R.id.tmbhsolupny);
        tmbah = findViewById(R.id.buttambahpenyakit);
        back = findViewById(R.id.tambahpnykembali);
        cir = findViewById(R.id.tmbhciripny);

        tmbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(namapny, idpny, kete, solu, cir)) {
                    nampny = namapny.getText().toString();
                    idpn = idpny.getText().toString();
                    ket = kete.getText().toString();
                    sol = solu.getText().toString();
                    cr = cir.getText().toString();
                    tambah(idpn, nampny, ket, sol,cr);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahDataPenyakit.this, DataPenyakit.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void tambah(String idp, String namap, String keter, String solusi, String ciri) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.TambahdatapnyResponse(idp, namap, keter, solusi, ciri);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(TambahDataPenyakit.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    namapny.setText("");
                    idpny.setText("");
                    kete.setText("");
                    solu.setText("");
                    cir.setText("");
                }else{
                    Toast.makeText(TambahDataPenyakit.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(TambahDataPenyakit.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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