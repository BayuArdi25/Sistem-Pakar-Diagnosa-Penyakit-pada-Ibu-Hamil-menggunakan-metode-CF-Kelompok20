package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;

public class TentangPakar extends AppCompatActivity {

    ImageButton kem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_pakar);
        kem = findViewById(R.id.tentangpakarkembali);
        kem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TentangPakar.this, Tentang.class);
                startActivity(intent);
                finish();
            }
        });
    }
}