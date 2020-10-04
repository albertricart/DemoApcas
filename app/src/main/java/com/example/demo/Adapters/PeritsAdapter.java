package com.example.demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Classes.Perit;
import com.example.demo.R;

import java.util.ArrayList;

public class PeritsAdapter extends RecyclerView.Adapter<PeritsAdapter.PeritViewHolder> implements EspecialitatsFiltersAdapter.OnEspecialitatListener {

    private static Context context;
    private ArrayList<Perit> perits;
    private OnPeritListener mOnPeritListener;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;
    private final static int ANIM_DURATION = 700; //ANIM_DURATION in milliseconds

    //ADAPTER CONSTRUCTOR
    public PeritsAdapter(ArrayList<Perit> perits, OnPeritListener onPeritListener, Context context) {
        this.perits = perits;
        this.mOnPeritListener = onPeritListener;
        this.context = context;
    }

    //VIEWHOLDER CLASS OF PERITS ADAPTER
    public static class PeritViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewNom;
        TextView textViewTelf;
        TextView textViewUbicacio;
        ImageView imageViewFoto;
        RecyclerView recyclerView;

        //interface used to allow a click on each view
        OnPeritListener onPeritListener;

        public PeritViewHolder(@NonNull View itemView, OnPeritListener onPeritListener) {
            super(itemView);
            this.onPeritListener = onPeritListener;
            textViewNom = (TextView) itemView.findViewById(R.id.textViewNomPerit);
            textViewTelf = (TextView) itemView.findViewById(R.id.textViewTelefon);
            textViewUbicacio = (TextView) itemView.findViewById(R.id.textViewUbicacio);
            imageViewFoto = (ImageView) itemView.findViewById(R.id.imageViewPerit);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerViewEspecialitatsPeritCard);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPeritListener.OnPeritClick(getAdapterPosition());
        }

        public void bindPerit(Perit perit) {
            textViewNom.setText(perit.getNom());
            textViewTelf.setText(String.valueOf(perit.getTelefon()));
            textViewUbicacio.setText(perit.getProvincia());
            imageViewFoto.setImageResource(R.drawable.sample_profile_pic);
            recyclerView.setHasFixedSize(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            //recyclerView.addItemDecoration(new SpacesItemDecoration(10));
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new EspecialitatsFiltersAdapter(perit.getEspecialitats(), null, context));
        }

    }


    @NonNull
    @Override
    public PeritViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_perit, parent, false);

        return new PeritViewHolder(itemView, mOnPeritListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PeritViewHolder holder, int position) {
        Perit perit = perits.get(position);
        holder.bindPerit(perit);
        // Set the view to fade in
        setFadeAnimation(holder.itemView);
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return perits.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setDuration(ANIM_DURATION);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(ANIM_DURATION);
        view.startAnimation(anim);
    }


    public interface OnPeritListener {
        void OnPeritClick(int position);
    }

    @Override
    public void OnEspecialitatClick(int position) {

    }

}
