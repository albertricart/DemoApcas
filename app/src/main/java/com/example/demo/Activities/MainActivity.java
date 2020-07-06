package com.example.demo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.EspecialitatsAdapter;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Dialogs.EspecialitatDialog;
import com.example.demo.R;
import com.example.demo.Utils.GridAutofitLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EspecialitatsAdapter.OnEspecialitatListener {

    private CardView cardViewEspecialitats;
    private Button buttonEspecialitats;

    private View.OnClickListener especialitatsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startEspecialitatsActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEspecialitats = (Button) findViewById(R.id.buttonEspecialitats);
        cardViewEspecialitats = (CardView) findViewById(R.id.cardEspecialitats);

        buttonEspecialitats.setOnClickListener(especialitatsOnClickListener);
        cardViewEspecialitats.setOnClickListener(especialitatsOnClickListener);


        mostrarLlistaEspecialitats(EspecialitatsPeritsActivity.getEspecialitats());

    }

    @Override
    public void OnEspecialitatClick(int position) {
        Especialitat especialitat = EspecialitatsPeritsActivity.getEspecialitats().get(position);
        EspecialitatDialog dialog = new EspecialitatDialog(this, especialitat);
        dialog.show();
        dialog.setDialogResult(new EspecialitatDialog.OnEspecialitatDialogResult() {
            @Override
            public void finish(String result) {

            }
        });

    }

    public void startEspecialitatsActivity() {
        Intent intent = new Intent(MainActivity.this, EspecialitatsPeritsActivity.class);
        startActivity(intent);
    }

    public void mostrarLlistaEspecialitats(ArrayList<Especialitat> especialitats) {
        //obtenim la referencia de la recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEspecialitats);
        //recyclerView.setHasFixedSize(true);

        //li diem a la recycler view que utilitzi un gridlayoutmanager
        GridAutofitLayoutManager gridAutofitLayoutManager = new GridAutofitLayoutManager(this, 300);
        //show all especialitats without having to scroll recyclerview
        gridAutofitLayoutManager.setSpanCount(especialitats.size());
        //recyclerView.setLayoutManager(gridAutofitLayoutManager);
        //li passem el adapter a la recycler view
        recyclerView.setAdapter(new EspecialitatsAdapter(especialitats, this, getApplicationContext()));
    }


}