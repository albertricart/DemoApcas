package com.example.demo.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.demo.Adapters.EspecialitatsFiltersAdapter;

public class FiltersDialog extends Dialog implements EspecialitatsFiltersAdapter.OnEspecialitatListener, View.OnClickListener {


    public FiltersDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnEspecialitatClick(int position) {

    }
}


//    private RecyclerView recyclerViewEspecialitats, recyclerViewCodis;
//    private Button yes, no, clear;
//    private Activity activity;
//    private EspecialitatsFiltersAdapter adapter;
//
//    public FiltersDialog(@NonNull Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    public FiltersDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }
//
//    public FiltersDialog(Activity a, EspecialitatsFiltersAdapter adapter) {
//        super(a);
//        this.activity = a;
//        this.adapter = adapter;
//        setupLayout();
//    }
//
//    private void setupLayout() {
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        setContentView(R.layout.layout_filtres);
//        yes = (Button)findViewById(R.id.buttonFilter);
//        no = (Button)findViewById(R.id.buttonCancel);
//        clear = (Button)findViewById(R.id.buttonClear);
//
////        recyclerViewCodis = (RecyclerView)findViewById(R.id.recyclerViewCodisPostals);
////        recyclerViewCodis.setHasFixedSize(true);
////        recyclerViewCodis.setLayoutManager(new GridLayoutManager(getContext(), 5));
////        //setadapter
////        EspecialitatsFiltersAdapter adapter = new EspecialitatsFiltersAdapter(EspecialitatsPeritsActivity.getEspecialitats(), this, getContext());
////        recyclerViewCodis.setAdapter(adapter);
//
//        recyclerViewEspecialitats = (RecyclerView)findViewById(R.id.recyclerViewFiltresEspecialitats);
//        recyclerViewEspecialitats.setHasFixedSize(true);
//        recyclerViewEspecialitats.setLayoutManager(new GridLayoutManager(activity, 2));
//        //setadapter
//        recyclerViewEspecialitats.setAdapter(adapter);
//    }
////
////    // this method create view for your Dialog
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        //inflate layout with recycler view
////        View v = inflater.inflate(R.layout.layout_filtres, container, false);
////        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewFiltresEspecialitats);
////        mRecyclerView.setHasFixedSize(true);
////        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
////        //setadapter
////        EspecialitatsFiltersAdapter adapter = new EspecialitatsFiltersAdapter(EspecialitatsPeritsActivity.getEspecialitats(), this, getContext());
////        mRecyclerView.setAdapter(adapter);
////        //get your recycler view and populate it.
////        return v;
////    }
////
//    @Override
//    public void OnEspecialitatClick(int position) {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.buttonFilter:
//                Toast.makeText(getContext(), "Filtrat", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.buttonCancel:
//                dismiss();
//                break;
//            default:
//                break;
//        }
//        dismiss();
//    }

//
//    /* The activity that creates an instance of this dialog fragment must
//     * implement this interface in order to receive event callbacks.
//     * Each method passes the DialogFragment in case the host needs to query it. */
//    public interface FiltersDialogListener {
//        public void onDialogPositiveClick(DialogFragment dialog);
//
//        public void onDialogNegativeClick(DialogFragment dialog);
//    }
//
//    // Use this instance of the interface to deliver action events
//    FiltersDialogListener listener;
//
//    // Override the Fragment.onAttach() method to instantiate the FiltersDialogListener
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the FiltersDialogListener so we can send events to the host
//            listener = (FiltersDialogListener) context;
//
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(getActivity().toString()
//                    + " must implement FiltersDialogListener");
//        }
//    }
//
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // Get the layout inflater
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.layout_filtres, null))
//                // Add action buttons
//                .setPositiveButton(R.string.filter, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(getContext(), "Filtrats", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dismiss();
//                    }
//                });
//
//
//        return builder.create();
//    }

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

