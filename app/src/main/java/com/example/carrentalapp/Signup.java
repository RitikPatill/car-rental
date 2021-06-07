package com.example.carrentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.carrentalapp.Models.Users;
import com.example.carrentalapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        //progress Dialog for showing Progress
        progressDialog=new ProgressDialog(Signup.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are Creating your Account");
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                 auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),
                         binding.etPassword.getText().toString())
                         .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Users user=new Users(binding.etUserName.getText().toString(),binding.etEmail.getText().toString(),binding.etPassword.getText().toString());
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);//creating database
                            Toast.makeText(Signup.this,"User Created Sucessfully",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                     }
                 });

            }
        });
        binding.etSignUpLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Signup.this,MainActivity.class);
            }
        });
        binding.alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signup.this,SignInActivity.class);
                startActivity(intent);

            }

        });
}}