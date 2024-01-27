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
import com.example.sispakkehamilan.modul.Datarule;

import java.util.ArrayList;

public class Adaptertabelrule extends RecyclerView.Adapter<Adaptertabelrule.AdapterHolder> {

    private Context context;
    private ArrayList<Datarule> dtrl;


    public Adaptertabelrule(Context context, ArrayList<Datarule> dtrl){
        this.context = context;
        this.dtrl = dtrl;
    }

    @NonNull
    @Override
    public Adaptertabelrule.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabelrule,parent, false);
        Adaptertabelrule.AdapterHolder holder = new Adaptertabelrule.AdapterHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Adaptertabelrule.AdapterHolder holder, int position) {

        int rowPos = holder.getAdapterPosition();
        if (rowPos == 0) {
            holder.idpny.setBackgroundResource(R.drawable.headercell);
            holder.nilcf.setBackgroundResource(R.drawable.headercell);
            holder.idgj.setBackgroundResource(R.drawable.headercell);
            holder.idrl.setBackgroundResource(R.drawable.headercell);
            holder.idrl.setText("ID Rule");
            holder.idpny.setText("ID Penyakit");
            holder.idgj.setText("ID Gejala");
            holder.nilcf.setText("Nilai CF");

            holder.idrl.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.idrl.setTypeface(null, Typeface.BOLD);
            holder.idpny.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.idpny.setTypeface(null, Typeface.BOLD);
            holder.idgj.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.idgj.setTypeface(null, Typeface.BOLD);
            holder.nilcf.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.nilcf.setTypeface(null, Typeface.BOLD);

        } else {

            Datarule modal = dtrl.get(rowPos - 1);
            holder.idrl.setBackgroundResource(R.drawable.bodicell);
            holder.idpny.setBackgroundResource(R.drawable.bodicell);
            holder.idgj.setBackgroundResource(R.drawable.bodicell);
            holder.nilcf.setBackgroundResource(R.drawable.bodicell);

            holder.idrl.setText(modal.getIdRule());
            holder.idpny.setText(modal.getIdPenyakit());
            holder.idgj.setText(modal.getIdGejala());
            holder.nilcf.setText(modal.getNilaiCf());

            holder.idrl.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.idrl.setTypeface(null, Typeface.BOLD);
            holder.idpny.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.idpny.setTypeface(null, Typeface.BOLD);
            holder.idgj.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.idgj.setTypeface(null, Typeface.BOLD);
            holder.nilcf.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.nilcf.setTypeface(null, Typeface.BOLD);



        }
    }

    @Override
    public int getItemCount() {
        return dtrl.size()+1;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView idgj, idrl, idpny, nilcf;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            idgj = itemView.findViewById(R.id.tabidgejalarule);
            idrl = itemView.findViewById(R.id.tabidrule);
            idpny = itemView.findViewById(R.id.tabidpenyakitrule);
            nilcf = itemView.findViewById(R.id.tabnilaicf);
        }
    }
}