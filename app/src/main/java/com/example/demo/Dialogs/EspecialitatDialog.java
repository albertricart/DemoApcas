package com.example.demo.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.demo.Activities.EspecialitatsPeritsActivity;
import com.example.demo.Activities.MainActivity;
import com.example.demo.Classes.Especialitat;
import com.example.demo.R;


public class EspecialitatDialog extends Dialog {

    private Especialitat especialitat;
    private OnEspecialitatDialogResult mDialogResult;


    public EspecialitatDialog(@NonNull Context context, Especialitat especialitat) {
        super(context);
        this.especialitat = especialitat;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.especialitat_dialog);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageViewEspecialitat = (ImageView) findViewById(R.id.imageViewEspecialitat);
        TextView textViewNomEspecialitat = (TextView) findViewById(R.id.textViewNomEspecialitat);
        TextView textViewDescripcioEspecialitat = (TextView) findViewById(R.id.textViewDescripcioEspecialitat);

        imageViewEspecialitat.setImageResource(especialitat.getImatge());
        textViewNomEspecialitat.setText(especialitat.getNom());
        textViewDescripcioEspecialitat.setText(R.string.lorem_ipsum);
    }

    private class OKListener implements android.view.View.OnClickListener {
        @Override
        public void onClick(View v) {
            if( mDialogResult != null ){
                mDialogResult.finish(String.valueOf(especialitat.getNom()));
            }
            EspecialitatDialog.this.dismiss();
        }
    }

    public void setDialogResult(OnEspecialitatDialogResult dialogResult) {
        mDialogResult = dialogResult;
    }

    public interface OnEspecialitatDialogResult {
        void finish(String result);
    }

}
