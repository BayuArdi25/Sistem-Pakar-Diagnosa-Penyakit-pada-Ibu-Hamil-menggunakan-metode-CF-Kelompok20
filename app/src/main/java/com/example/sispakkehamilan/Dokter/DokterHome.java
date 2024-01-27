package com.example.sispakkehamilan.Dokter;

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

public class DokterHome extends AppCompatActivity {

    TextView nam;
    SessionManager sm;
    String nama;
    Button but1,but2,but3;
    ImageView imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_home);
        sm = new SessionManager(DokterHome .this);
        imm = findViewById(R.id.imaoutdok);
        nam = findViewById(R.id.titdok);
        nama = sm.getUserDetail().get(SessionManager.NAME);
        nam.setText(nama);
        but1 = findViewById(R.id.butriwayat);
        but2 = findViewById(R.id.butregis);
        but3 = findViewById(R.id.buthapusakun);
        if (!sm.isLoggedIn() || !sm.getUserLevel().equals("dokter")) {
            // Jika tidak, pindahkan pengguna ke aktivitas login
            movelogin();
            finish();}
        imm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sm.logoutSession();
            movelogin();
            finish();
        }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(DokterHome.this, Register.class);
                startActivity(inte);
                finish();
            }
        });

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(DokterHome.this, RiwayatPasien.class);
                startActivity(intet);
                finish();
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(DokterHome.this, HapusUser.class);
                startActivity(intet);
                finish();
            }
        });
}

    private void movelogin() {
        Intent intent = new Intent(DokterHome.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
