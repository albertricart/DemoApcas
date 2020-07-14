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
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Classes.Especialitat;
import com.example.demo.R;

import java.util.ArrayList;

public class EspecialitatsAdapter extends RecyclerView.Adapter<EspecialitatsAdapter.EspecialitatViewHolder> {

    private Context context;
    private ArrayList<Especialitat> especialitats;
    private OnEspecialitatListener mOnEspecialitatListener;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;
    private final static int ANIM_DURATION = 1000; //ANIM_DURATION in milliseconds

    //ADAPTER CONSTRUCTOR
    public EspecialitatsAdapter(ArrayList<Especialitat> especialitats, OnEspecialitatListener mOnEspecialitatListener, Context context) {
        this.especialitats = especialitats;
        this.mOnEspecialitatListener = mOnEspecialitatListener;
        this.context = context;
    }

    //VIEWHOLDER CLASS OF EspecialitatsAdapter
    public static class EspecialitatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewNom;
        ImageView imageViewFoto;

        //interface used to allow a click on each view
        OnEspecialitatListener onEspecialitatListener;

        public EspecialitatViewHolder(@NonNull View itemView, OnEspecialitatListener onEspecialitatListener) {
            super(itemView);
            this.onEspecialitatListener = onEspecialitatListener;
            textViewNom = (TextView)itemView.findViewById(R.id.textViewNomEspecialitat);
            imageViewFoto = (ImageView)itemView.findViewById(R.id.imageViewEspecialitat);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onEspecialitatListener.OnEspecialitatClick(getAdapterPosition());

        }

        public void bindEspecialitat(Especialitat especialitat){
            textViewNom.setText(especialitat.getNom());
            imageViewFoto.setImageResource(especialitat.getImatge());
        }

    }



    @NonNull
    @Override
    public EspecialitatsAdapter.EspecialitatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_especialitat, parent, false);

        return new EspecialitatViewHolder(itemView, mOnEspecialitatListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialitatsAdapter.EspecialitatViewHolder holder, int position) {
        Especialitat especialitat = especialitats.get(position);
        holder.bindEspecialitat(especialitat);

        // Set the view to fade in
        setFadeAnimation(holder.itemView);
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return especialitats.size();
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
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

    public interface OnEspecialitatListener {
        void OnEspecialitatClick(int position);
    }
}
