package com.example.demo.Activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.EspecialitatsFiltersAdapter;
import com.example.demo.R;
import com.example.demo.Utils.CodisPostalsManager;
import com.example.demo.Utils.GridAutofitLayoutManager;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import static com.example.demo.API.api.getEspecialitats;

public class FilterActivity extends AppCompatActivity implements EspecialitatsFiltersAdapter.OnEspecialitatListener {

    private View viewCodis;
    private RecyclerView recyclerViewEspecialitats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Transparent background
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setFinishOnTouchOutside(true);

        setContentView(R.layout.activity_filter);
        this.setTitle(null);
        viewCodis = findViewById(R.id.includeCodisPostals);
        CheckBox checkBoxActive = findViewById(R.id.checkBoxExercent);
        checkBoxActive.setChecked(true);
        findCode(viewCodis, true);
        fillEspecialitats();


    }


    public static void findCode(View viewCodis, boolean snackbarAlert) {
        Button buttonCercar = viewCodis.findViewById(R.id.buttonAfegir);
        EditText editTextCodi = viewCodis.findViewById(R.id.editTextNumberCodi);
        buttonCercar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CodisPostalsManager.getCode(CodisPostalsManager.codisPostals, String.valueOf(editTextCodi.getText())) != null) {
                    //code found
                    editTextCodi.setText("");
                } else {
                    if (snackbarAlert)
                        Snackbar.make(viewCodis, R.string.code_not_found, BaseTransientBottomBar.LENGTH_LONG).show();
                    else
                        Toast.makeText(viewCodis.getContext(), R.string.code_not_found, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void fillEspecialitats() {
        //obtenim la referencia de la recycler view
        recyclerViewEspecialitats = (RecyclerView) findViewById(R.id.recyclerViewFiltresEspecialitats);
//
//
//        recyclerViewEspecialitats.setHasFixedSize(true);
//
//        //performance improvements
//        recyclerViewEspecialitats.setItemViewCacheSize(20);
//        recyclerViewEspecialitats.setDrawingCacheEnabled(true);
//        recyclerViewEspecialitats.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
//
//        recyclerViewEspecialitats.setLayoutManager(new GridLayoutManager(this, 2));
        //li diem a la recycler view que utilitzi un gridlayoutmanager
        GridAutofitLayoutManager gridAutofitLayoutManager = new GridAutofitLayoutManager(this, 0);
        //show all especialitats without having to scroll recyclerview
        gridAutofitLayoutManager.setSpanCount(getEspecialitats().size());
        //recyclerView.setLayoutManager(gridAutofitLayoutManager);

        //li passem el adapter a la recycler view
        recyclerViewEspecialitats.setAdapter(new EspecialitatsFiltersAdapter(getEspecialitats(), this, getApplicationContext()));

    }

    @Override
    public void OnEspecialitatClick(int position) {
    }
}