package com.example.demo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class ContactPerit extends AppCompatActivity {

    private static final int REQUEST_SEND_FEEDBACK = 1;
    private EditText editTextMessage;
    private EditText editTextSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_perit);


        Toolbar toolbarFeedback = (Toolbar) findViewById(R.id.toolbarFeedback);
        toolbarFeedback.setTitle("Send");
        toolbarFeedback.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbarFeedback.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbarFeedback);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        editTextMessage = findViewById(R.id.editTextMessage);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextSubject.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feedback_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                String subject = editTextSubject.getText().toString();
                String message = editTextMessage.getText().toString();
                if (subject.isEmpty() || message.isEmpty()) {
                    Snackbar.make(getCurrentFocus(), "Please fill all fields", BaseTransientBottomBar.LENGTH_LONG).show();
                } else {
                    Intent Email = new Intent(Intent.ACTION_SEND);
                    Email.setType("text/email");
                    Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"albert.ricart@hotmail.com"});
                    Email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    Email.putExtra(Intent.EXTRA_TEXT, message);
                    startActivityForResult(Intent.createChooser(Email, "Select your preferred email app"), REQUEST_SEND_FEEDBACK);
                }
                break;

            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}