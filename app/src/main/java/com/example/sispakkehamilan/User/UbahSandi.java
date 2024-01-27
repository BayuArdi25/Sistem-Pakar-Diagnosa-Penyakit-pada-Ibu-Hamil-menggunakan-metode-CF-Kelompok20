package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.Login;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.SessionManager;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahSandi extends AppCompatActivity {

    ApiInterface api;
    EditText pass, pass2;
    Button ubh;
    SessionManager sm;
    ImageButton ima;
    String usernm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_sandi);
        pass = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        ubh = findViewById(R.id.butubahpass);
        sm = new SessionManager(UbahSandi.this);
        ima = findViewById(R.id.butkemubahsandi);
        usernm = sm.getUserDetail().get(SessionManager.USERNAME);
        ubh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (areEditTextsFilled(pass, pass2)) {
                    String pas = pass.getText().toString();
                    String pas2 = pass2.getText().toString();
                    if(pas2.equals(pas)){
                        ubahsan(usernm, pas);
                    }else{
                        Toast.makeText(UbahSandi.this, "Password Harus Sama!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    showToast("Kolom Tidak Boleh Kosong!");
                }

            }
        });

        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(UbahSandi.this, UserHome.class);
                startActivity(intet);
                finish();
            }
        });

    }

    private void ubahsan(String user, String pass) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Ubahcall = api.ubahpw(user, pass);

        Ubahcall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(UbahSandi.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    sm.logoutSession();
                    movelogin();
                    finish();
                }else{
                    Toast.makeText(UbahSandi.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(UbahSandi.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void movelogin() {
        Intent intent = new Intent(UbahSandi.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
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