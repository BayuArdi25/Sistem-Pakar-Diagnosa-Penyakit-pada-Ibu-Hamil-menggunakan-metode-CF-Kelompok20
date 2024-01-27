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
import com.example.sispakkehamilan.modul.Datagejala;

import java.util.ArrayList;

public class Adaptertabelgejala extends RecyclerView.Adapter<Adaptertabelgejala.AdapterHolder> {

    private Context context;
    private ArrayList<Datagejala> dtgj;


    public Adaptertabelgejala(Context context, ArrayList<Datagejala> dtgj){
        this.context = context;
        this.dtgj = dtgj;
    }

    @NonNull
    @Override
    public Adaptertabelgejala.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabelgejala,parent, false);
        Adaptertabelgejala.AdapterHolder holder = new Adaptertabelgejala.AdapterHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        int rowPos = holder.getAdapterPosition();
        if (rowPos == 0) {
            holder.idgj.setBackgroundResource(R.drawable.headercell);
            holder.namagj.setBackgroundResource(R.drawable.headercell);
            holder.idgj.setText("ID Gejala");
            holder.namagj.setText("Nama Gejala");

            holder.idgj.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.idgj.setTypeface(null, Typeface.BOLD);
            holder.namagj.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.namagj.setTypeface(null, Typeface.BOLD);

        } else {

            Datagejala modal = dtgj.get(rowPos - 1);
            holder.idgj.setBackgroundResource(R.drawable.bodicell);
            holder.namagj.setBackgroundResource(R.drawable.bodicell);
            holder.idgj.setText(modal.getIdGejala());
            holder.namagj.setText(modal.getNamaGejala());

            holder.idgj.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.idgj.setTypeface(null, Typeface.BOLD);
            holder.namagj.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.namagj.setTypeface(null, Typeface.BOLD);



        }
    }

    @Override
    public int getItemCount() {
        return dtgj.size()+1;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView idgj, namagj;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            idgj = itemView.findViewById(R.id.tabidgejala);
            namagj = itemView.findViewById(R.id.tabnamagejala);
        }
    }
}