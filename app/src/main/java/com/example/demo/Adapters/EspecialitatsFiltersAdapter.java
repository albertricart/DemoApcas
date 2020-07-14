package com.example.demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Classes.Especialitat;
import com.example.demo.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class EspecialitatsFiltersAdapter extends RecyclerView.Adapter<EspecialitatsFiltersAdapter.EspecialitatViewHolder> {

    private static Context context;
    private ArrayList<Especialitat> especialitats;
    private OnEspecialitatListener mOnEspecialitatListener;

    //ADAPTER CONSTRUCTOR
    public EspecialitatsFiltersAdapter(ArrayList<Especialitat> especialitats, OnEspecialitatListener mOnEspecialitatListener, Context context) {
        this.especialitats = especialitats;
        this.mOnEspecialitatListener = mOnEspecialitatListener;
        this.context = context;

    }

    //VIEWHOLDER CLASS OF EspecialitatsAdapter
    public static class EspecialitatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Chip chip;

        //interface used to allow a click on each view
        OnEspecialitatListener onEspecialitatListener;

        public EspecialitatViewHolder(@NonNull View itemView, OnEspecialitatListener onEspecialitatListener) {
            super(itemView);
            this.onEspecialitatListener = onEspecialitatListener;
            chip = (Chip) itemView.findViewById(R.id.chipEspecialitat);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            onEspecialitatListener.OnEspecialitatClick(getAdapterPosition());
//            if (chip.isChecked()){
//                chip.setChecked(false);
//                chip.setChipBackgroundColorResource(R.color.mtrl_chip_background_color);
//                chip.setTextColor(context.getResources().getColor(R.color.mtrl_chip_text_color));
//                chip.setChipIconTintResource(android.R.color.white);
//            }else{
//                chip.setChecked(true);
//                chip.setChipBackgroundColorResource(R.color.colorPrimary);
//                chip.setTextColor(context.getResources().getColor(android.R.color.white));
//                chip.setChipIconTintResource(android.R.color.white);
//            }

        }

        public void bindEspecialitat(Especialitat especialitat) {
            chip.setText(especialitat.getNom());
            chip.setChipIconResource(especialitat.getImatge());
        }

    }


    @NonNull
    @Override
    public EspecialitatsFiltersAdapter.EspecialitatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chip_item, parent, false);

        return new EspecialitatViewHolder(itemView, mOnEspecialitatListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialitatsFiltersAdapter.EspecialitatViewHolder holder, int position) {
        Especialitat especialitat = especialitats.get(position);
        holder.bindEspecialitat(especialitat);
    }

    @Override
    public int getItemCount() {
        return especialitats.size();
    }


    public interface OnEspecialitatListener {
        void OnEspecialitatClick(int position);
    }
}
