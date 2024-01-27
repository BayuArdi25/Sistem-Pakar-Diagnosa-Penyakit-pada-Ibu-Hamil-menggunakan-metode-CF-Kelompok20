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
import com.example.sispakkehamilan.Dokter.RiwayatPasien;
import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.api.ApiInterface;
import com.example.sispakkehamilan.api.Apiclient;
import com.example.sispakkehamilan.modul.Akun;
import com.example.sispakkehamilan.modul.DataRiwayat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataRiwayat extends RecyclerView.Adapter<AdapterDataRiwayat.AdapterHolder> {

    ApiInterface api;
    private Context context;
    private List<DataRiwayat> dtrw;

    public AdapterDataRiwayat(Context context, List<DataRiwayat> dtrw){
        this.context = context;
        this.dtrw = dtrw;
    }

    @NonNull
    @Override
    public AdapterDataRiwayat.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewdatariwayat,parent, false);
        AdapterDataRiwayat.AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataRiwayat.AdapterHolder holder, int position) {

        DataRiwayat dtmodel = dtrw.get(position);
        String nmor = dtmodel.getNama();
        String nmpny = dtmodel.getPenyakit();
        String alama = dtmodel.getAlamat();
        String hp = dtmodel.getNohp();
        String tang = dtmodel.getTanggal();

        holder.nmorg.setText(nmor);
        holder.nmpn.setText(nmpny);
        holder.alam.setText(alama);
        holder.nohpp.setText(hp);
        holder.tgl.setText(tang);

        holder.ima.setOnClickListener(v ->{
            String idrw = dtmodel.getIdRiwayat();
            hapus(idrw);
            Intent intent = new Intent(context, DokterHome.class);
            context.startActivity(intent);
            ((RiwayatPasien) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return dtrw.size();
    }

    private void hapus(String idr) {
        api = Apiclient.getClient().create(ApiInterface.class);
        Call<Akun> Regiscall = api.Hapusdatariwayat(idr);

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
            nmorg = itemView.findViewById(R.id.namapasriwayat);
            nmpn = itemView.findViewById(R.id.penyakitriwayat);
            alam = itemView.findViewById(R.id.alamatriwayat);
            nohpp = itemView.findViewById(R.id.nohpriwayat);
            tgl = itemView.findViewById(R.id.tanggalriwayat);
            ima = itemView.findViewById(R.id.hapusriwayat);
            lin = itemView.findViewById(R.id.linearriwayat);

        }
    }


}