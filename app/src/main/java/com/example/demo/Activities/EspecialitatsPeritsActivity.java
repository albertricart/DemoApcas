package com.example.demo.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapters.PeritsAdapter;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Classes.Perit;
import com.example.demo.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EspecialitatsPeritsActivity extends AppCompatActivity implements PeritsAdapter.OnPeritListener {

    private Dialog dialog;
    private ArrayList<Perit> perits;
    private RecyclerView recyclerViewPerits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialitats_perits);

        dialog = new Dialog(this);
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

        Spinner spinnerEspecialitzacio = (Spinner) findViewById(R.id.spinnerEspecialitzacio);

        ArrayAdapter<Especialitat> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getEspecialitats());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialitzacio.setAdapter(adapter);

        filtrarPerits();

        buscarPerits();
    }


    @Override
    public void OnPeritClick(int position) {
        showPerit(getPerits().get(position));
    }

    public void showPerit(Perit perit) {
        TextView textViewNom;
        TextView textViewTelf;
        TextView textViewUbicacio;

        textViewNom = (TextView) findViewById(R.id.textViewNomPerit);
        textViewTelf = (TextView) findViewById(R.id.textViewTelefon);
        textViewUbicacio = (TextView) findViewById(R.id.textViewUbicacio);

        dialog.setContentView(R.layout.popup_perit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
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
            public void onClick(View v) {
                final String[] listItems = new String[getEspecialitats().size()];
                final boolean[] checkedItems = new boolean[getEspecialitats().size()];
                final ArrayList<Integer> mUserItems = new ArrayList<>();

                for (int i = 0; i < getEspecialitats().size(); i++) {
                    listItems[i] = getEspecialitats().get(i).getNom();
                }


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EspecialitatsPeritsActivity.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                        if (isChecked) {
                            mUserItems.add(position);
                        } else {
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);

                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


        Spinner spinnerEspecialitzacio = (Spinner) findViewById(R.id.spinnerEspecialitzacio);
        spinnerEspecialitzacio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ArrayList<Perit> perits = new ArrayList<>();

                if (position == 0) {
                    perits = getPerits();
                } else {
                    for (Perit p : getPerits()) {
                        for (Especialitat e : p.getEspecialitats()) {
                            if (e.getNom().equalsIgnoreCase(getEspecialitats().get(position).getNom())) {
                                perits.add(p);
                            }
                        }
                    }
                }
                mostrarLlistaPerits(perits);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }


    public ArrayList<Perit> getPerits() {
        ArrayList<Perit> perits = new ArrayList<>();
        ArrayList<Especialitat> especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Autos", "Reconeixement comissió mixta"));
        especialitats.add(new Especialitat("Avaries", "Reconeixement comissió mixta"));
        especialitats.add(new Especialitat("Diversos", "Reconeixement comissió mixta"));
        especialitats.add(new Especialitat("Embarcacions", "Reconeixement comissió mixta"));
        perits.add(new Perit(1, "Joan Ricart", 610220556, "Barcelona", "sklip@telefonica.net", especialitats, true));
        especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Autos", "Reconeixement comissió mixta"));
        perits.add(new Perit(2, "Urcisino Rodiel Ruiz", 658843218, "Barcelona", "rodielperitaciones@gmail.com", especialitats, true));
        especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Autos", "Reconeixement comissió mixta"));
        especialitats.add(new Especialitat("Diversos", "Reconeixement comissió mixta"));
        perits.add(new Perit(3, "Jordi Romo Ros", 639117876, "Girona", "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(4, "Jordi Romo Ros", 639117876, "Girona", "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(5, "Jordi Romo Ros", 639117876, "Girona", "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(6, "Jordi Romo Ros", 639117876, "Girona", "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(7, "Jordi Romo Ros", 639117876, "Girona", "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(8, "Jordi Romo Ros", 639117876, "Girona", "jromo@peritatges.com", especialitats, true));
        return perits;
    }

    public static ArrayList<Especialitat> getEspecialitats() {
        ArrayList<Especialitat> especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Agrari", R.drawable.ic_outline_local_florist_24));
        especialitats.add(new Especialitat("Autos", R.drawable.ic_round_directions_car_24));
        especialitats.add(new Especialitat("Avaries", R.drawable.ic_round_build_24));
        especialitats.add(new Especialitat("DGS", R.drawable.ic_baseline_texture_24));
        especialitats.add(new Especialitat("Diversos", R.drawable.ic_baseline_texture_24));
        especialitats.add(new Especialitat("Embarcacions", R.drawable.ic_round_directions_boat_24));
        especialitats.add(new Especialitat("Metges", R.drawable.ic_round_local_hospital_24));

        return especialitats;
    }


}