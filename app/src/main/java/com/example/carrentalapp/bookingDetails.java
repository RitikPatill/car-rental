package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carrentalapp.Models.PriceForCars;

public class bookingDetails extends AppCompatActivity {
    private EditText name,phoneNumber,address,days;
    private Button bookNow,back;
    private TextView pricePerday;
    private int x=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        name=findViewById(R.id.etUserName);
        phoneNumber=findViewById(R.id.phoneNumber);
        address=findViewById(R.id.Address);
        days=findViewById(R.id.days);
       // PriceForCars priceForCars=new PriceForCars();
        //x=priceForCars.getPricePerDay();
        pricePerday=findViewById(R.id.pricePerDay);
     //   pricePerday.setText(x);
    //   pricePerday.setText(x);
        bookNow=findViewById(R.id.confirmDetails);
        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=name.getText().toString();
                String pnumber=phoneNumber.getText().toString();
                String add =address.getText().toString();
                String dys=days.getText().toString();
                Intent intent=new Intent(bookingDetails.this, BookingConfirmation.class);
                intent.putExtra("keyname",username);
                intent.putExtra("keypnum",pnumber);
                intent.putExtra("keyadd",add);
                intent.putExtra("keydys",dys);
                startActivity(intent);
            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(bookingDetails.this,ListCar.class);
                startActivity(intent);
                Toast toast=Toast.makeText(getApplicationContext(),"Car List",Toast.LENGTH_SHORT);
            }
        });

    }
}