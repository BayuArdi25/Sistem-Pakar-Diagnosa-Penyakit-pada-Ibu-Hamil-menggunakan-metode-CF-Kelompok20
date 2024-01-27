package com.example.sispakkehamilan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.Admin.AdminHome;
import com.example.sispakkehamilan.Dokter.DokterHome;
import com.example.sispakkehamilan.User.UserHome;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;
import com.example.sispakkehamilan.modul.Dataakun;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText uss, pss;
    Button but;
    String user, pass, lvl;
    ApiInterface apiInterface;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uss = findViewById(R.id.uss);
        pss = findViewById(R.id.pss);
        but = findViewById(R.id.butlogin);
        sm = new SessionManager(getApplicationContext());
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = uss.getText().toString();
                pass = pss.getText().toString();
                logi(user, pass);
            }
        });
    }

    private void logi(String user, String pass) {
        apiInterface = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> logincall = apiInterface.loginResponse(user, pass);
        logincall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
                    Dataakun loginData = response.body().getDataakun();
                    lvl = loginData.getLevel();
                    sm = new SessionManager(Login.this);
                    if (lvl.equals("3")) {
                        String userLevel = "admin";
                        sm.createLoginSession(loginData);
                        sm.setLogin(true, userLevel);
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, AdminHome.class);
                        startActivity(intent);
                        finish();
                    } else if (lvl.equals("2")) {
                        String userLevel = "dokter";
                        sm.createLoginSession(loginData);
                        sm.setLogin(true, userLevel);
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, DokterHome.class);
                        startActivity(intent);
                        finish();
                    }else if (lvl.equals("1")) {
                        String userLevel = "user";
                        sm.createLoginSession(loginData);
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        sm.setLogin(true, userLevel);
                        Intent intent = new Intent(Login.this, UserHome.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "Salah", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}