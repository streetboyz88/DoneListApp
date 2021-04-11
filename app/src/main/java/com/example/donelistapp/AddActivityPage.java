package com.example.donelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivityPage extends AppCompatActivity {

    private TextView textView;
    private TextView textName;
    private EditText editText;
    private Button addButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_page);

        textView = (TextView)findViewById(R.id.textView);
        textName = (TextView)findViewById(R.id.textUserName);
        editText = (EditText) findViewById(R.id.editActivityBox);
        addButton = (Button)findViewById(R.id.addBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivityPage.this, HomePage.class);
                //String message = editText.toString();
                intent.putExtra("theText", editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
