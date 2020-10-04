package com.example.demo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.demo.R;


public class ZonesActuacioFragment extends Fragment {


    public ZonesActuacioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TextInputEditText editText = viewCodis.findViewById(R.id.editTextNumberCodi);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                ArrayList<CodiPostal> cps = CodisPostalsManager.searchCodes(perit.getCodisPostals(), charSequence.toString());
//                recyclerViewCps.setHasFixedSize(true);
//                recyclerViewCps.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerViewCps.setAdapter(new CodisPostalsAdapter(cps));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zones_actuacio, container, false);
    }
}