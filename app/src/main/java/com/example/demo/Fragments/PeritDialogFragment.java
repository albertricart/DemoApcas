package com.example.demo.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.demo.Classes.Perit;

public class PeritDialogFragment extends DialogFragment {

    private Perit perit;

    public PeritDialogFragment(Perit perit) {
        this.perit = perit;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
