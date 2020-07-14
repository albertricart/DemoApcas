package com.example.demo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.CodisPostalsAdapter;
import com.example.demo.Adapters.EspecialitatsAdapter;
import com.example.demo.Classes.CodiPostal;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Classes.Perit;
import com.example.demo.Dialogs.EspecialitatDialog;
import com.example.demo.R;
import com.example.demo.Utils.CodisPostalsManager;
import com.example.demo.Utils.GridAutofitLayoutManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static com.example.demo.API.api.getEspecialitats;

public class PeritActivity extends AppCompatActivity implements EspecialitatsAdapter.OnEspecialitatListener {

    private View viewCodis;
    private Perit perit;
    private static final int SHOW_CODIS = 3;
    private Button buttonMostrarMes;
    RecyclerView recyclerViewCps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewCodis = findViewById(R.id.includeCodisPostals);
        buttonMostrarMes = viewCodis.findViewById(R.id.buttonMostraMes);
        recyclerViewCps = (RecyclerView) viewCodis.findViewById(R.id.recyclerViewCodisPostals);
        Button buttonCerca = (Button) viewCodis.findViewById(R.id.buttonCerca);
        buttonCerca.setVisibility(View.GONE);
        perit = (Perit) getIntent().getSerializableExtra(EspecialitatsPeritsActivity.PERIT_EXTRA);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(perit.getNom());

        mostrarLlistaEspecialitats(perit.getEspecialitats());
        mostrarCodis(perit.getCodisPostals());

        TextInputEditText editText = viewCodis.findViewById(R.id.editTextNumberCodi);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<CodiPostal> cps = CodisPostalsManager.searchCodes(perit.getCodisPostals(), charSequence.toString());
                recyclerViewCps.setHasFixedSize(true);
                recyclerViewCps.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewCps.setAdapter(new CodisPostalsAdapter(cps));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactPerit.class);
                startActivity(intent);
            }
        });


//        buttonMostrarMes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (buttonMostrarMes.getText().equals("Mostra més")){
//                    mostrarCodis(perit.getCodisPostals());
//                    buttonMostrarMes.setText("Mostra menys");
//                }else{
//                    mostrarCodis(perit.getReducedCodisPostals(SHOW_CODIS));
//                    buttonMostrarMes.setText("Mostra més");
//
//                }
//            }
//        });
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

    public void mostrarCodis(ArrayList<CodiPostal> codisPostals) {
        recyclerViewCps.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewCps.setAdapter(new CodisPostalsAdapter(codisPostals));
        //TODO: view more codis button, mostrar nomes 3 (3 primers de la llista) i si fa click al button mostrar tot el dataset
    }

    @Override
    public void OnEspecialitatClick(int position) {
        Especialitat especialitat = getEspecialitats().get(position);
        EspecialitatDialog dialog = new EspecialitatDialog(this, especialitat);
        dialog.show();
    }
}