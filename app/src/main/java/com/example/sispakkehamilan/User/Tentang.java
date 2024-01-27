package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;

public class Tentang extends AppCompatActivity {

    Button tenpak;
    ImageButton kem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        tenpak = findViewById(R.id.buttentangpakar);
        kem = findViewById(R.id.tentangkembali);

        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tentang.this, UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        tenpak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tentang.this, TentangPakar.class);
                startActivity(intent);
                finish();
            }
        });


    }
}