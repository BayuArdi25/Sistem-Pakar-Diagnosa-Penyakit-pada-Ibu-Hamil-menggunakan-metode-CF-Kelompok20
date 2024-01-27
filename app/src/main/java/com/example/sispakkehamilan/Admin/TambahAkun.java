package com.example.sispakkehamilan.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

public class TambahAkun extends AppCompatActivity {

    EditText username,password,nama,alamat,nohp;
    Spinner level;
    String usernm,pass,nm,alam,hp,lvl,tgl;
    Button tmbah;
    ImageButton back;
    ApiInterface api;
    String[] items = {"1", "2", "3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_akun);
        username = findViewById(R.id.tmbhusername);
        password = findViewById(R.id.tmbhpassword);
        nama = findViewById(R.id.tmbhnamaakun);
        alamat = findViewById(R.id.tmbhalamat);
        tmbah = findViewById(R.id.buttambahakun);
        back = findViewById(R.id.tambahakunkembali);
        nohp = findViewById(R.id.tmbhnohp);
        level = findViewById(R.id.spintmbhlevel);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahAkun.this, KelolaAkun.class);
                startActivity(intent);
                finish();
            }
        });
        tmbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(nama, alamat, nohp, username, password)) {
                    usernm = username.getText().toString();
                    pass = password.getText().toString();
                    nm = nama.getText().toString();
                    alam = alamat.getText().toString();
                    hp = nohp.getText().toString();
                    lvl = level.getSelectedItem().toString();
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    String tnggal = date.format(calendar.getTime());
                    register(usernm, pass, nm, alam, hp, lvl, tnggal);

                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
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
                    Toast.makeText(TambahAkun.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    nama.setText("");
                    alamat.setText("");
                    username.setText("");
                    password.setText("");
                    nohp.setText("");
                }else{
                    Toast.makeText(TambahAkun.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(TambahAkun.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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