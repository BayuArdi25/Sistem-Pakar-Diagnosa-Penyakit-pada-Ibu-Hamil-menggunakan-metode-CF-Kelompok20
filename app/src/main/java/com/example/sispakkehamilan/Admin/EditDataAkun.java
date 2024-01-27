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
import com.example.sispakkehamilan.modul.Dataakun;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataAkun extends AppCompatActivity {

    EditText password,nama,alamat,nohp;
    Spinner level, username;
    ArrayList<Dataakun> listakn;
    String usernm,pass,nm,alam,hp,lvl,tgl;
    Button edit, hps;
    ImageButton back;
    ApiInterface api;
    String[] items = {"1", "2", "3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_akun);
        username = findViewById(R.id.spineditusername);
        password = findViewById(R.id.editpassword);
        nama = findViewById(R.id.editnamaakun);
        alamat = findViewById(R.id.editalamat);
        edit = findViewById(R.id.buteditakun);
        hps = findViewById(R.id.buthapusakun);
        back = findViewById(R.id.editakunkembali);
        nohp = findViewById(R.id.editnohp);
        level = findViewById(R.id.spineditlevel);
        init();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataAkun.this, KelolaAkun.class);
                startActivity(intent);
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areEditTextsFilled(nama, alamat, nohp, password)) {
                    usernm = username.getSelectedItem().toString();
                    pass = password.getText().toString();
                    nm = nama.getText().toString();
                    alam = alamat.getText().toString();
                    hp = nohp.getText().toString();
                    lvl = level.getSelectedItem().toString();
                    edit(usernm, pass, nm, alam, hp, lvl);
                } else {
                    showToast("Lengkapi data terlebih dahulu.");
                }
            }
        });
        hps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernm = username.getSelectedItem().toString();
                hapus(usernm);
            }
        });
    }

    public void init(){
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<ArrayList<Dataakun>> call = api.liatakun();
        call.enqueue(new Callback<ArrayList<Dataakun>>() {
            @Override
            public void onResponse(Call<ArrayList<Dataakun>> call, Response<ArrayList<Dataakun>> response) {
                if(response.isSuccessful()){
                    listakn = response.body();
                    populateSpinner(listakn);
                }else{
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dataakun>> call, Throwable t) {
                Toast.makeText(EditDataAkun.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateSpinner(ArrayList<Dataakun> data) {
        List<String> namaList = new ArrayList<>();

        for (Dataakun item : data) {
            namaList.add(item.getUsername());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        username.setAdapter(adapter);
    }

    private void edit(String user, String pass, String nam, String almm, String nhp, String lvl) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.editdataakun(user, pass, nam, almm, nhp, lvl);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataAkun.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataAkun.this, KelolaAkun.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataAkun.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataAkun.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void hapus(String usr) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Hapusakun(usr);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(EditDataAkun.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataAkun.this, KelolaAkun.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(EditDataAkun.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(EditDataAkun.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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