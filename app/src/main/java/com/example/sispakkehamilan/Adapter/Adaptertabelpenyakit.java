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
import com.example.sispakkehamilan.modul.Datapenyakit;

import java.util.ArrayList;

public class Adaptertabelpenyakit extends RecyclerView.Adapter<Adaptertabelpenyakit.AdapterHolder> {

    private Context context;
    private ArrayList<Datapenyakit> dtpn;


    public Adaptertabelpenyakit(Context context, ArrayList<Datapenyakit> dtpn){
        this.context = context;
        this.dtpn = dtpn;
    }

    @NonNull
    @Override
    public Adaptertabelpenyakit.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabelpenyakit,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        int rowPos = holder.getAdapterPosition();
        if (rowPos == 0) {
            holder.idpn.setBackgroundResource(R.drawable.headercell);
            holder.namapn.setBackgroundResource(R.drawable.headercell);
            holder.kete.setBackgroundResource(R.drawable.headercell);
            holder.solu.setBackgroundResource(R.drawable.headercell);
            holder.cir.setBackgroundResource(R.drawable.headercell);
            holder.idpn.setText("ID Penyakit");
            holder.namapn.setText("Nama Penyakit");
            holder.kete.setText("Keterangan");
            holder.solu.setText("Solusi");
            holder.cir.setText("Gejala");

            holder.idpn.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.idpn.setTypeface(null, Typeface.BOLD);
            holder.namapn.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.namapn.setTypeface(null, Typeface.BOLD);
            holder.kete.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.kete.setTypeface(null, Typeface.BOLD);
            holder.solu.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.solu.setTypeface(null, Typeface.BOLD);
            holder.cir.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.cir.setTypeface(null, Typeface.BOLD);

        } else {

            Datapenyakit modal = dtpn.get(rowPos - 1);
            holder.idpn.setBackgroundResource(R.drawable.bodicell);
            holder.namapn.setBackgroundResource(R.drawable.bodicell);
            holder.kete.setBackgroundResource(R.drawable.bodicell);
            holder.solu.setBackgroundResource(R.drawable.bodicell);
            holder.cir.setBackgroundResource(R.drawable.bodicell);
            holder.idpn.setText(modal.getIdPenyakit());
            holder.namapn.setText(modal.getNamaPenyakit());
            holder.kete.setText(modal.getKeterangan());
            holder.solu.setText(modal.getSolusi());
            holder.cir.setText(modal.getCiri());

            holder.idpn.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.idpn.setTypeface(null, Typeface.BOLD);
            holder.namapn.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.namapn.setTypeface(null, Typeface.BOLD);
            holder.kete.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.kete.setTypeface(null, Typeface.BOLD);
            holder.solu.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.solu.setTypeface(null, Typeface.BOLD);
            holder.cir.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            holder.cir.setTypeface(null, Typeface.BOLD);



        }
    }

    @Override
    public int getItemCount() {
        return dtpn.size()+1;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView idpn, namapn, kete, solu, cir;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            idpn = itemView.findViewById(R.id.tabidpenyakit);
            namapn = itemView.findViewById(R.id.tabnamapenyakit);
            kete = itemView.findViewById(R.id.tabketerangan);
            solu = itemView.findViewById(R.id.tabsolusi);
            cir = itemView.findViewById(R.id.tabciri);
        }
    }
}