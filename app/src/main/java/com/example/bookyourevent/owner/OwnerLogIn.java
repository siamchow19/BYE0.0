package com.example.bookyourevent.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookyourevent.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class OwnerLogIn extends AppCompatActivity {

    private EditText OwnerEmail, OwnerPass;
    private TextView OwnerSignUp;
    private Button OwnerLogInButton;
    private FirebaseAuth auth;
    private boolean isOwner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_log_in);

        OwnerEmail = findViewById(R.id.owner_login_email);
        OwnerPass = findViewById(R.id.owner_login_password);
        OwnerLogInButton = findViewById(R.id.owner_login_button);
        OwnerSignUp = findViewById(R.id.goto_signup_owner);
        auth = FirebaseAuth.getInstance();

        OwnerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(), OwnerRegister.class);
                startActivity(intent);

            }
        });

        OwnerLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = OwnerEmail.getText().toString();
                String userPassword = OwnerPass.getText().toString();
                userLogIN(userEmail, userPassword);
            }
        });
    }


    private void userLogIN(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(OwnerLogIn.this, "Log in successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OwnerHomePage.class);
                startActivity(intent);
            }
        });
    }

}