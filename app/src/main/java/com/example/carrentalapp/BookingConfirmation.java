package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carrentalapp.Models.BookingDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BookingConfirmation extends AppCompatActivity {
   private TextView name,address,phonenumber,numberofdays;
   private Button makechanges,confirmBooking;
    FirebaseDatabase database;
    private FirebaseAuth auth;
    String uID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);
        name=findViewById(R.id.etUserNameConfirmation);
        address=findViewById(R.id.confirmAdress);
        phonenumber=findViewById(R.id.confirmphoneNumber);
        numberofdays=findViewById(R.id.Confirmdays);
        //
        auth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        //
        String username=getIntent().getStringExtra("keyname");
       name.setText(username);
        String addresses=getIntent().getStringExtra("keyadd");
        address.setText(addresses);
        String phnno=getIntent().getStringExtra("keypnum");
        phonenumber.setText(phnno);
        String dys=getIntent().getStringExtra("keydys");
        numberofdays.setText(dys);
        confirmBooking=findViewById(R.id.confirmDetails);
        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingDatabase bookingDatabase= new BookingDatabase(name.getText().toString(),address.getText().toString(),phonenumber.getText().toString(),numberofdays.getText().toString());
                uID=phonenumber.getText().toString();
                database.getReference().child("Bookings").child(uID).setValue(bookingDatabase);
                Toast.makeText(BookingConfirmation.this,"Booking Sucessful",Toast.LENGTH_LONG).show();
            }
        });
       makechanges=findViewById(R.id.makechanges);
       makechanges.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(BookingConfirmation.this,bookingDetails.class);
               startActivity(intent);
           }
       });

    }
}