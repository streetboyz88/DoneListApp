package com.example.donelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.jar.Attributes;


public class RegisterPage extends AppCompatActivity {

    private TextView Rname;
    private TextView Remail;
    private TextView Rpassword;
    private Button Rbutton;

    String name, email, password;

    private FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    Users users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        setupUIViews();

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        users = new Users();

        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_email = Remail.getText().toString().trim();
                    String user_password = Rpassword.getText().toString().trim();

                    users.setName(Rname.getText().toString().trim());
                    users.setEmail(Remail.getText().toString().trim());
                    users.setPassword(Rpassword.getText().toString().trim());

                    reference.push().setValue(users);
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterPage.this, "Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterPage.this, LoginPage.class));
                            } else {
                                Toast.makeText(RegisterPage.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private void setupUIViews() {
        Rname = (TextView) findViewById(R.id.RegisterName);
        Remail = (TextView) findViewById(R.id.RegisterEmail);
        Rpassword = (TextView) findViewById(R.id.RegisterPassword);
        Rbutton = (Button) findViewById(R.id.RegisterPageButton);
    }


    private Boolean validate() {
        name = Rname.getText().toString();
        email = Remail.getText().toString();
        password = Rpassword.getText().toString();

        boolean results = false;
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
        } else {
            results = true;
        }
        return results;
    }


}
