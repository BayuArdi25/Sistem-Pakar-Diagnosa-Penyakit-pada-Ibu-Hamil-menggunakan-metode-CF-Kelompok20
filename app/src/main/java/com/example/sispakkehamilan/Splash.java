package com.example.sispakkehamilan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.Admin.AdminHome;
import com.example.sispakkehamilan.Dokter.DokterHome;
import com.example.sispakkehamilan.User.UserHome;

public class Splash extends AppCompatActivity {
    private SessionManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sm = new SessionManager(getApplicationContext());

        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sm != null && sm.isLoggedIn()) {
                    if (sm.getUserLevel().equals("admin")) {
                        Intent intent = new Intent(Splash.this, AdminHome.class);
                        startActivity(intent);
                        finish();
                    } else if(sm.getUserLevel().equals("dokter")) {
                        Intent intent = new Intent(Splash.this, DokterHome.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(Splash.this, UserHome.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000l);
    }

}