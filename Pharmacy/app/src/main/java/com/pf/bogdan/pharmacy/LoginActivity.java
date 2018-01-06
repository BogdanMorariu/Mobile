package com.pf.bogdan.pharmacy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.Login_Button);

        firebaseAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText)findViewById(R.id.email_filed)).getText().toString();
                String password = ((EditText)findViewById(R.id.password_field)).getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"No field can be empty!",Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent;
                                if(email.equals("bogdanmorariu96@gmail.com") && password.equals("admin1")){
                                    intent = new Intent(LoginActivity.this,MainActivity.class);
                                    Toast.makeText(LoginActivity.this,"Hello Mr. Administrator",Toast.LENGTH_LONG).show();
                                }
                                else
                                    intent = new Intent(LoginActivity.this,UserActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Invalid username or password!",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
