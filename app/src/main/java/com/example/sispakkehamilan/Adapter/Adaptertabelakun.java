package com.example.sispakkehamilan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.modul.Dataakun;

import java.util.ArrayList;

public class Adaptertabelakun extends RecyclerView.Adapter<Adaptertabelakun.AdapterHolder> {

    private Context context;
    private ArrayList<Dataakun> dtak;


    public Adaptertabelakun(Context context, ArrayList<Dataakun> dtak){
        this.context = context;
        this.dtak = dtak;
    }

    @NonNull
    @Override
    public Adaptertabelakun.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabelakun,parent, false);
        Adaptertabelakun.AdapterHolder holder = new Adaptertabelakun.AdapterHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Adaptertabelakun.AdapterHolder holder, int position) {

        int rowPos = holder.getAdapterPosition();
        if (rowPos == 0) {
            holder.user.setBackgroundResource(R.drawable.headercell);
            holder.pass.setBackgroundResource(R.drawable.headercell);
            holder.nama.setBackgroundResource(R.drawable.headercell);
            holder.alamat.setBackgroundResource(R.drawable.headercell);
            holder.nohp.setBackgroundResource(R.drawable.headercell);
            holder.level.setBackgroundResource(R.drawable.headercell);
            holder.tanggal.setBackgroundResource(R.drawable.headercell);
            holder.user.setText("Username");
            holder.pass.setText("Password");
            holder.nama.setText("Nama Lengkap");
            holder.alamat.setText("Alamat");
            holder.nohp.setText("No HP");
            holder.level.setText("Level");
            holder.tanggal.setText("Tanggal Buat");

            holder.user.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.user.setTypeface(null, Typeface.BOLD);
            holder.pass.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.pass.setTypeface(null, Typeface.BOLD);
            holder.nama.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.nama.setTypeface(null, Typeface.BOLD);
            holder.alamat.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.alamat.setTypeface(null, Typeface.BOLD);
            holder.nohp.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.nohp.setTypeface(null, Typeface.BOLD);
            holder.level.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.level.setTypeface(null, Typeface.BOLD);
            holder.tanggal.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.tanggal.setTypeface(null, Typeface.BOLD);

        } else {

            Dataakun modal = dtak.get(rowPos - 1);
            holder.user.setBackgroundResource(R.drawable.bodicell);
            holder.pass.setBackgroundResource(R.drawable.bodicell);
            holder.nama.setBackgroundResource(R.drawable.bodicell);
            holder.alamat.setBackgroundResource(R.drawable.bodicell);
            holder.nohp.setBackgroundResource(R.drawable.bodicell);
            holder.level.setBackgroundResource(R.drawable.bodicell);
            holder.tanggal.setBackgroundResource(R.drawable.bodicell);
            holder.user.setText(modal.getUsername());
            holder.pass.setText(modal.getPassword());
            holder.nama.setText(modal.getNama());
            holder.alamat.setText(modal.getAlamat());
            holder.nohp.setText(modal.getNohp());
            holder.level.setText(modal.getLevel());
            holder.tanggal.setText(modal.getTanggal());

            holder.user.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.user.setTypeface(null, Typeface.BOLD);
            holder.pass.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.pass.setTypeface(null, Typeface.BOLD);
            holder.nama.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.nama.setTypeface(null, Typeface.BOLD);
            holder.alamat.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.alamat.setTypeface(null, Typeface.BOLD);
            holder.nohp.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.nohp.setTypeface(null, Typeface.BOLD);
            holder.level.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.level.setTypeface(null, Typeface.BOLD);
            holder.tanggal.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.tanggal.setTypeface(null, Typeface.BOLD);

        }
    }

    @Override
    public int getItemCount() {
        return dtak.size()+1;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView user, pass, nama, alamat, nohp, level, tanggal;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.tabusername);
            pass = itemView.findViewById(R.id.tabpassword);
            nama = itemView.findViewById(R.id.tabnamaakun);
            alamat = itemView.findViewById(R.id.tabalamat);
            nohp = itemView.findViewById(R.id.tabnohp);
            level = itemView.findViewById(R.id.tablevel);
            tanggal = itemView.findViewById(R.id.tabtanggal);
        }
    }
}