package com.example.demo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Classes.CodiPostal;
import com.example.demo.R;

import java.util.ArrayList;

public class CodisPostalsAdapter extends RecyclerView.Adapter<CodisPostalsAdapter.CodiViewHolder> {

    private ArrayList<CodiPostal> codisPostals;
    private final static int ANIM_DURATION = 750; //ANIM_DURATION in milliseconds

    public CodisPostalsAdapter(ArrayList<CodiPostal> codisPostals) {
        this.codisPostals = codisPostals;
    }


    public class CodiViewHolder extends RecyclerView.ViewHolder {
        private TextView codiPostal;
        private TextView codiMunicipi;
        private TextView nomMunicipi;

        public CodiViewHolder(@NonNull View itemView) {
            super(itemView);
            codiPostal = itemView.findViewById(R.id.textViewCodiPostal);
            codiMunicipi = itemView.findViewById(R.id.textViewCodiMunicipi);
            nomMunicipi = itemView.findViewById(R.id.textViewMunicipi);
        }
    }


    @NonNull
    @Override
    public CodiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_codi_postal, parent, false);

        return new CodiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CodiViewHolder holder, int position) {

        CodiPostal codiPostal = codisPostals.get(position);

        holder.codiPostal.setText(codiPostal.getCodiPostal());
        holder.codiMunicipi.setText(codiPostal.getCodiMunicipi());
        holder.nomMunicipi.setText(codiPostal.getNomMunicipi());

        // Set the view to fade in
        setFadeAnimation(holder.itemView);

    }

    @Override
    public int getItemCount() {
        return codisPostals.size();
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(ANIM_DURATION);
        view.startAnimation(anim);
    }


}
