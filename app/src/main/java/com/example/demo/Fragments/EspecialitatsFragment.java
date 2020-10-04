package com.example.demo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.EspecialitatsAdapter;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Classes.Perit;
import com.example.demo.Dialogs.EspecialitatDialog;
import com.example.demo.R;
import com.example.demo.Utils.GridAutofitLayoutManager;

import java.util.ArrayList;

public class EspecialitatsFragment extends Fragment implements EspecialitatsAdapter.OnEspecialitatListener {

    private Perit perit;

    public EspecialitatsFragment(Perit perit) {
        this.perit = perit;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public void mostrarLlistaEspecialitats(ArrayList<Especialitat> especialitats) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //obtenim la referencia de la recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEspecialitats);
        recyclerView.setHasFixedSize(true);
        //creem un nou liniesAdapter
        EspecialitatsAdapter especialitatsAdapter = new EspecialitatsAdapter(perit.getEspecialitats(), this, getContext());
        //li diem a la recycler view que utilitzi un gridlayoutmanager
        GridAutofitLayoutManager gridAutofitLayoutManager = new GridAutofitLayoutManager(getContext(), 300);
        gridAutofitLayoutManager.setSpanCount(perit.getEspecialitats().size());
        recyclerView.setLayoutManager(gridAutofitLayoutManager);
        //li passem el adapter a la recycler view
        recyclerView.setAdapter(especialitatsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_especialitats, container, false);
    }


    @Override
    public void OnEspecialitatClick(int position) {
        Especialitat especialitat = perit.getEspecialitats().get(position);
        EspecialitatDialog dialog = new EspecialitatDialog(getContext(), especialitat);
        dialog.show();
    }

}