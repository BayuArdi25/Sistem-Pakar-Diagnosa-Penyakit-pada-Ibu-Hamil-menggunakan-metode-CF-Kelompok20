package com.example.sispakkehamilan.Dokter;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText nama,alm,hpp,usernm,pss;
    String nam,al,hp,usrnm,ps, lev;
    Button regis;
    ImageButton kem;
    ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = findViewById(R.id.namaleng);
        alm = findViewById(R.id.alamat);
        hpp = findViewById(R.id.nohp);
        usernm = findViewById(R.id.usernameuser);
        pss = findViewById(R.id.passuser);
        regis = findViewById(R.id.tomregis);
        kem = findViewById(R.id.butkemregis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(nama, alm, hpp, usernm, pss)) {
                    usrnm = usernm.getText().toString();
                    ps = pss.getText().toString();
                    nam = nama.getText().toString();
                    al = alm.getText().toString();
                    hp = hpp.getText().toString();
                    lev = "1";
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    String tnggal = date.format(calendar.getTime());
                    register(usrnm, ps, nam, al, hp, lev, tnggal);

                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });

        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(Register.this, DokterHome.class);
                startActivity(intet);
                finish();
            }
        });

    }
    private void register(String user, String pass, String nam, String almm, String nhp, String lvl, String tgg) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.RegisResponse(user, pass, nam, almm, nhp, lvl, tgg);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(Register.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    nama.setText("");
                    alm.setText("");
                    usernm.setText("");
                    hpp.setText("");
                    pss.setText("");
                }else{
                    Toast.makeText(Register.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(Register.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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