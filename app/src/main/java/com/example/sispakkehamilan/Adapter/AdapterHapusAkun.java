package com.example.sispakkehamilan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.Dokter.DokterHome;
import com.example.sispakkehamilan.Dokter.HapusUser;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;
import com.example.sispakkehamilan.modul.Dataakun;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterHapusAkun extends RecyclerView.Adapter<AdapterHapusAkun.AdapterHolder> {

    ApiInterface api;
    private Context context;
    private List<Dataakun> dtak;

    public AdapterHapusAkun(Context context, List<Dataakun> dtak){
        this.context = context;
        this.dtak = dtak;
    }

    @NonNull
    @Override
    public AdapterHapusAkun.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewdataakun,parent, false);
        AdapterHapusAkun.AdapterHolder holder = new AdapterHapusAkun.AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHapusAkun.AdapterHolder holder, int position) {

        Dataakun dtmodel = dtak.get(position);
        String nmor = dtmodel.getNama();
        String alama = dtmodel.getAlamat();
        String hp = dtmodel.getNohp();
        String tang = dtmodel.getTanggal();

        holder.nmorg.setText(nmor);
        holder.alam.setText(alama);
        holder.nohpp.setText(hp);
        holder.tgl.setText(tang);

        holder.ima.setOnClickListener(v ->{
            String usernm = dtmodel.getUsername();
            hapus(usernm);
            Intent intent = new Intent(context, DokterHome.class);
            context.startActivity(intent);
            ((HapusUser) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return dtak.size();
    }

    private void hapus(String idr) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Hapusakun(idr);

        Regiscall.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(context, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nmorg,nmpn,alam,nohpp,tgl;
        ImageButton ima;
        LinearLayout lin;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nmorg = itemView.findViewById(R.id.namaakun);
            alam = itemView.findViewById(R.id.alamatakun);
            nohpp = itemView.findViewById(R.id.nohpakun);
            tgl = itemView.findViewById(R.id.tanggalakun);
            ima = itemView.findViewById(R.id.hapusakn);
            lin = itemView.findViewById(R.id.linearakun);

        }
    }


}