package com.example.sispakkehamilan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sispakkehamilan.R;
import com.example.sispakkehamilan.User.DetailPenyakit;
import com.example.sispakkehamilan.User.InfoPenyakit;
import com.example.sispakkehamilan.modul.Datapenyakit;

import java.util.List;

public class AdapterInfoPenyakit extends RecyclerView.Adapter<AdapterInfoPenyakit.AdapterHolder> {

    private Context context;
    private List<Datapenyakit> dtpny;

    public AdapterInfoPenyakit(Context context, List<Datapenyakit> dtpny){
        this.context = context;
        this.dtpny = dtpny;
    }

    @NonNull
    @Override
    public AdapterInfoPenyakit.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_info_penyakit,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        Datapenyakit dtmodel = dtpny.get(position);
        String namapny = dtmodel.getNamaPenyakit();

        holder.nmpn.setText(namapny);
        holder.linear.setOnClickListener(v ->{

            Intent inten = new Intent(context, DetailPenyakit.class);
            inten.putExtra("id penyakit", dtmodel.getIdPenyakit());
            inten.putExtra("nama penyakit", dtmodel.getNamaPenyakit());
            inten.putExtra("keterangan", dtmodel.getKeterangan());
            inten.putExtra("solusi", dtmodel.getSolusi());
            inten.putExtra("ciri", dtmodel.getCiri());
            context.startActivity(inten);
            ((InfoPenyakit) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return dtpny.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nmpn;
        LinearLayout linear;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nmpn = itemView.findViewById(R.id.nminfopny);
            linear = itemView.findViewById(R.id.linearinfopny);
        }
    }
}
