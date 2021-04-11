package com.example.donelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private TextView Lemail;
    private TextView Lpassword;
    private Button Llogin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Lemail = (TextView)findViewById(R.id.LoginEmail);
        Lpassword = (TextView)findViewById(R.id.LoginPassword);
        Llogin = (Button)findViewById(R.id.LoginPageButton);

        Llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Lemail.getText().toString().equals("admin") && Lpassword.getText().toString().equals("admin")) {
                    startActivity(new Intent(LoginPage.this, HomePage.class));
                } else if (Lemail.getText().toString().isEmpty() || Lpassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginPage.this, "Please fill in the details", Toast.LENGTH_SHORT).show();
                } else {
                    validate(Lemail.getText().toString(), Lpassword.getText().toString());
                }
            }
        });

    }

    private void validate(final String email, final String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginPage.this, HomePage.class));
                    Toast.makeText(LoginPage.this, "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
