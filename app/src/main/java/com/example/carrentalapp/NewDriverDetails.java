package com.example.carrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carrentalapp.Models.DriverDetailsDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NewDriverDetails extends AppCompatActivity {
    FirebaseDatabase database;
    private FirebaseAuth auth;
    String uID;
    private Button confirmDetails;
    EditText name,address,number,drivingLicenseNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_driver_details);
        confirmDetails=findViewById(R.id.confirmDetails);
        //
        auth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        //
        name=findViewById(R.id.etUserName);
        address=findViewById(R.id.Address);
        number=findViewById(R.id.phoneNumber);
        drivingLicenseNumber=findViewById(R.id.days);
        confirmDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverDetailsDatabase driverDetailsDatabase=new DriverDetailsDatabase(name.getText().toString(),address.getText().toString(),number.getText().toString(),drivingLicenseNumber.getText().toString());
                uID=number.getText().toString();
                database.getReference().child("New Drivers").child(uID).setValue(driverDetailsDatabase);
                Toast.makeText(NewDriverDetails.this,"Details Added ",Toast.LENGTH_LONG).show();

            }
        });
    }
}