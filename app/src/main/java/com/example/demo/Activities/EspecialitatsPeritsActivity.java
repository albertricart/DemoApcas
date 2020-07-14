package com.example.demo.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.PeritsAdapter;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Classes.Perit;
import com.example.demo.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static com.example.demo.API.api.getEspecialitats;
import static com.example.demo.API.api.getPerits;

public class EspecialitatsPeritsActivity extends AppCompatActivity implements PeritsAdapter.OnPeritListener {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    protected static final String PERIT_EXTRA = "perit";
    private ArrayList<Perit> perits;
    private RecyclerView recyclerViewPerits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialitats_perits);

        recyclerViewPerits = (RecyclerView) findViewById(R.id.recyclerViewPerits);
        recyclerViewPerits.setNestedScrollingEnabled(false);

        perits = getPerits();


        NestedScrollView scrollViewActivity = (NestedScrollView) findViewById(R.id.scrollViewActEspecialitats);

        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) findViewById(R.id.extendedFloatingActionButton);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollViewActivity.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (oldScrollY > scrollY) {
                        extendedFloatingActionButton.extend();
                    } else if (oldScrollY < scrollY) {
                        extendedFloatingActionButton.shrink();
                    }
                }
            });
        }

        mostrarLlistaPerits(perits);


        ArrayAdapter<Especialitat> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getEspecialitats());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filtrarPerits();

        buscarPerits();

        askForStoragePermission();
    }


    @Override
    public void OnPeritClick(int position) {
        showPerit(getPerits().get(position));
    }

    public void showPerit(Perit perit) {
        Intent intent = new Intent(this, PeritActivity.class);
        intent.putExtra(PERIT_EXTRA, perit);
        startActivity(intent);
    }

    public void mostrarLlistaPerits(ArrayList<Perit> perits) {
        //obtenim la referencia de la recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPerits);
        recyclerView.setHasFixedSize(true);

        //performance improvements
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        //creem un nou liniesAdapter
        PeritsAdapter peritsAdapter = new PeritsAdapter(perits, this, getApplicationContext());
        //li diem a la recycler view que utilitzi un gridlayoutmanager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        //li passem el adapter a la recycler view
        recyclerView.setAdapter(peritsAdapter);
    }

    public void buscarPerits() {
        TextInputEditText editText = findViewById(R.id.editTextBuscar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Perit> peritsFiltrats = new ArrayList<>();
                for (Perit perit : perits) {
                    if (perit.buscarCamp(s)) {
                        peritsFiltrats.add(perit);
                    }
                }

                mostrarLlistaPerits(peritsFiltrats);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    public void filtrarPerits() {
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) findViewById(R.id.extendedFloatingActionButton);

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EspecialitatsPeritsActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });
    }





    public void askForStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //filter
            } else {
                //ask for permission
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Snackbar.make(findViewById(R.id.constraintLayoutActivityEspecialtats), getString(R.string.storage_permission), Snackbar.LENGTH_LONG).show();
                }

                requestPermissions((new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}), REQUEST_EXTERNAL_STORAGE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Snackbar.make(findViewById(R.id.constraintLayoutActivityEspecialtats), getString(R.string.storage_permission_denied), Snackbar.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

}