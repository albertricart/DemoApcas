package com.example.demo.Dialogs;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class FiltersDialog extends Dialog {
    public FiltersDialog(@NonNull Context context) {
        super(context);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // Get the layout inflater
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.layout_filtres, null))
//                // Add action buttons
//                .setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // sign in the user ...
//                    }
//                })
//                .setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        LoginDialogFragment.this.getDialog().cancel();
//                    }
//                });
//
//        return builder.create();
//    }
}
