package com.example.sispakkehamilan.Admin;

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

public class AdminHome extends AppCompatActivity {

    TextView nam;
    SessionManager sm;
    String nama;
    Button butpen, butgej, butrul, butakun;
    ImageView gam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        sm = new SessionManager(AdminHome.this);

        if (!sm.isLoggedIn() || !sm.getUserLevel().equals("admin")) {
            // Jika tidak, pindahkan pengguna ke aktivitas login
            movelogin();
            finish();}

            nam = findViewById(R.id.titadmin);
            nama = sm.getUserDetail().get(SessionManager.NAME);
            nam.setText(nama);
            butpen = findViewById(R.id.butdatpen);
            butgej = findViewById(R.id.butdatgej);
            butrul = findViewById(R.id.butdatpak);
            butakun = findViewById(R.id.butkeloakun);
            gam = findViewById(R.id.imaoutadm);
            gam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sm.logoutSession();
                    movelogin();
                    finish();
                }
            });

            butpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminHome.this, DataPenyakit.class);
                    startActivity(intent);
                    finish();
                }
            });

            butgej.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminHome.this, DataGejala.class);
                    startActivity(intent);
                    finish();
                }
            });
            butrul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminHome.this, DataRules.class);
                    startActivity(intent);
                    finish();
                }
            });
            butakun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminHome.this, KelolaAkun.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        private void movelogin () {
            Intent intent = new Intent(AdminHome.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }


}