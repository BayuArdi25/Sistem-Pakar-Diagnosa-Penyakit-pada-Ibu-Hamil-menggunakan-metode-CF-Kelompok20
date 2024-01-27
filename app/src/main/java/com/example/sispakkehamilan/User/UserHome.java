package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.Login;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.SessionManager;

public class UserHome extends AppCompatActivity {

    TextView nam;
    SessionManager sm;
    String nama;
    Button butpen, butdiag, butubah, butten;
    ImageView gam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        sm = new SessionManager(UserHome.this);

        if (!sm.isLoggedIn() || !sm.getUserLevel().equals("user")) {
            // Jika tidak, pindahkan pengguna ke aktivitas login
            movelogin();
            finish();}

        butubah = findViewById(R.id.butubahuser);
        nam = findViewById(R.id.tituser);
        nama = sm.getUserDetail().get(SessionManager.NAME);
        nam.setText(nama);
        butpen = findViewById(R.id.butpenyakituser);
        gam = findViewById(R.id.logoutuser);
        butdiag = findViewById(R.id.butdiagnosauser);
        butten = findViewById(R.id.buttentanguser);
        gam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logoutSession();
                movelogin();
                finish();

            }
        });

        butdiag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, Diagnosa.class);
                startActivity(intent);
                finish();
            }
        });

        butpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, InfoPenyakit.class);
                startActivity(intent);
                finish();
            }
        });

        butubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, UbahSandi.class);
                startActivity(intent);
                finish();
            }
        });

        butten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, Tentang.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void movelogin() {
        Intent intent = new Intent(UserHome.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

}