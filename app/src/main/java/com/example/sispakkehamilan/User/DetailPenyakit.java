package com.example.sispakkehamilan.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sispakkehamilan.R;

public class DetailPenyakit extends AppCompatActivity {

    ImageButton km;
    TextView nmpnya, keter, solusi, cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);
        km = findViewById(R.id.detailpnykembali);
        nmpnya = findViewById(R.id.detailnmpny);
        keter = findViewById(R.id.detailketepny);
        solusi = findViewById(R.id.detailsolupny);
        cr = findViewById(R.id.detailgejpny);

        String nmpn = getIntent().getStringExtra("nama penyakit");
        String ket = getIntent().getStringExtra("keterangan");
        String sol = getIntent().getStringExtra("solusi");
        String cir = getIntent().getStringExtra("ciri");

        nmpnya.setText(nmpn);
        keter.setText(ket);
        solusi.setText(sol);
        cr.setText(cir);

        km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPenyakit.this, InfoPenyakit.class);
                startActivity(intent);
                finish();
            }
        });
    }
}