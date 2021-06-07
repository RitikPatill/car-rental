package com.example.carrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carrentalapp.Models.NewCarDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NewCarDetails extends AppCompatActivity {
    String uID;
    private Button confirmDetailss;
    EditText name,address,number,carNumber;
    FirebaseDatabase database;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car_details);
        //
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        //
        confirmDetailss=findViewById(R.id.confirmDetailss);
        name=findViewById(R.id.etUserName);
        address=findViewById(R.id.Address);
        number=findViewById(R.id.phoneNumber);
        carNumber=findViewById(R.id.days);
        confirmDetailss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewCarDetails.this,"Details Added ",Toast.LENGTH_LONG).show();

                NewCarDatabase newCarDatabase=new NewCarDatabase(name.getText().toString(),address.getText().toString(),number.getText().toString(),carNumber.getText().toString());
                uID=number.getText().toString();
                database.getReference().child("New Car").child(uID).setValue(newCarDatabase);
                Toast.makeText(NewCarDetails.this,"Details Added ",Toast.LENGTH_LONG).show();

            }
        });

    }
}