package com.example.austlm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.austlm.Models.SpinnerAdapterText;
import com.example.austlm.Models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    EditText signUpEmail, signUpPass;
    Button signUpBtn;
    TextView signUpText;
    FirebaseAuth mFirebaseAuth;
    private AutoCompleteTextView studentDeptSpinner;

    ProgressDialog pd;
    FirebaseDatabase databaseReference;
    TextInputLayout studentDept;
    TextInputEditText studentNameTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeStudentDepartment();
        mFirebaseAuth = FirebaseAuth.getInstance();

        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPass = findViewById(R.id.signUpPass);
        signUpBtn = findViewById(R.id.signUpBtn);
        signUpText = findViewById(R.id.signUpText);

        studentDept=findViewById(R.id.studentDept);
        studentNameTextInput=findViewById(R.id.name);
        pd = new ProgressDialog(this);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signUpEmail.getText().toString();
                String pwd = signUpPass.getText().toString();

                if(email.isEmpty()){
                    signUpEmail.setError("Empty");
                    signUpEmail.requestFocus();
                }
                else if(pwd.isEmpty()){
                    signUpPass.setError("Empty");
                    signUpPass.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(SignUp.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    databaseReference = FirebaseDatabase.getInstance();
                    pd.setMessage("Creating account");
                    pd.show();
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String userId = task.getResult().getUser().getUid();
                                String studentName=studentNameTextInput.getText().toString();
                                String studentDeptString=studentDept.getEditText().getText().toString();
                                Student studentObj=new Student(userId,studentName,email,pwd,studentDeptString);
                                databaseReference.getReference("Students").child(userId).setValue(studentObj);
                                pd.dismiss();
                                Toast.makeText(SignUp.this, "Account created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, Login.class);
                                startActivity(intent);
                            }
                            else {
                                pd.dismiss();
                                Toast.makeText(SignUp.this, "SignUp unsuccessful, please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    pd.dismiss();
                    Toast.makeText(SignUp.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
    private void initializeStudentDepartment() {
        ArrayList<String> bloodGroup = new ArrayList<>();
        bloodGroup.add("CSE");
        bloodGroup.add("EEE");
        bloodGroup.add("TE");
        bloodGroup.add("CE");
        SpinnerAdapterText adapter = new SpinnerAdapterText(getApplicationContext(), bloodGroup);
        studentDeptSpinner = findViewById(R.id.filledExposedDropdown);
        studentDeptSpinner.setAdapter(adapter);
    }
}