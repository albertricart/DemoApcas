package com.example.demo.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.EspecialitatsAdapter;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Classes.Perit;
import com.example.demo.Dialogs.EspecialitatDialog;
import com.example.demo.R;
import com.example.demo.Utils.GridAutofitLayoutManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PeritActivity extends AppCompatActivity implements EspecialitatsAdapter.OnEspecialitatListener {

    private Perit perit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        perit = (Perit) getIntent().getSerializableExtra(EspecialitatsPeritsActivity.PERIT_EXTRA);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(perit.getNom());
        mostrarLlistaEspecialitats(perit.getEspecialitats());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void mostrarLlistaEspecialitats(ArrayList<Especialitat> especialitats) {
        //obtenim la referencia de la recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEspecialitats);
        recyclerView.setHasFixedSize(true);
        //creem un nou liniesAdapter
        EspecialitatsAdapter especialitatsAdapter = new EspecialitatsAdapter(especialitats, this, getApplicationContext());
        //li diem a la recycler view que utilitzi un gridlayoutmanager
        GridAutofitLayoutManager gridAutofitLayoutManager = new GridAutofitLayoutManager(this, 300);
        gridAutofitLayoutManager.setSpanCount(perit.getEspecialitats().size());
        recyclerView.setLayoutManager(gridAutofitLayoutManager);
        //li passem el adapter a la recycler view
        recyclerView.setAdapter(especialitatsAdapter);
    }

    @Override
    public void OnEspecialitatClick(int position) {
        Especialitat especialitat = EspecialitatsPeritsActivity.getEspecialitats().get(position);
        EspecialitatDialog dialog = new EspecialitatDialog(this, especialitat);
        dialog.show();
    }
}