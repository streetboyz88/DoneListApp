package com.example.donelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {

    private TextView textHi;
    private TextView textName;
    private TextView textActivity;
    private TextView editActivity;
    private FloatingActionButton floatButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        textHi = (TextView)findViewById(R.id.textHi);
        textName = (TextView)findViewById(R.id.textName);
        textActivity = (TextView)findViewById(R.id.textActivityDone);
        editActivity = (TextView)findViewById(R.id.textViewActivity);
        floatButton = (FloatingActionButton)findViewById(R.id.floatButton);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, AddActivityPage.class));
            }
        });

        editActivity.setText(getIntent().getStringExtra("theText"));
    }
}
