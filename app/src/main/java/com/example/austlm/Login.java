package com.example.austlm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText loginEmail, loginPass;
    Button loginBtn;
    TextView loginText;
    FirebaseAuth mFirebaseAuth;

    private ProgressDialog pd;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.loginEmail);
        loginPass = findViewById(R.id.loginPass);
        loginBtn = findViewById(R.id.loginBtn);
        loginText = findViewById(R.id.loginText);

        pd = new ProgressDialog(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
//                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Toast.makeText(Login.this, "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pwd = loginPass.getText().toString();

                if(email.isEmpty()){
                    loginEmail.setError("Empty");
                    loginEmail.requestFocus();
                }
                else if(pwd.isEmpty()){
                    loginPass.setError("Empty");
                    loginPass.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(Login.this, "Field ar empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    pd.setMessage("Logging in...");
                    pd.show();
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                pd.dismiss();
                                Toast.makeText(Login.this, "Login unsuccessful, please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                pd.dismiss();
                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else {
                    pd.dismiss();
                    Toast.makeText(Login.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}