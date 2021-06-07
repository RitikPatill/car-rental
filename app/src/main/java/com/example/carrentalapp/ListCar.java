package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carrentalapp.Models.PriceForCars;

public class ListCar extends AppCompatActivity {
   private Button booknow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);
        booknow=findViewById(R.id.bookNowNswift);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListCar.this, bookingDetails.class);
                Toast toast=Toast.makeText(getApplicationContext(),"Booking Details",Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });
        booknow=findViewById(R.id.bookNowBreeza);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListCar.this, bookingDetails.class);
                Toast toast=Toast.makeText(getApplicationContext(),"Booking Details",Toast.LENGTH_LONG);
             //   PriceForCars priceForCars=new PriceForCars(500);
              //  priceForCars.setPricePerDay(500);
                toast.show();
                startActivity(intent);
            }
        });
        booknow=findViewById(R.id.bookNowHector);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListCar.this, bookingDetails.class);
                Toast toast=Toast.makeText(getApplicationContext(),"Booking Details",Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });
        booknow=findViewById(R.id.bookNowHyundai);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListCar.this, bookingDetails.class);
                Toast toast=Toast.makeText(getApplicationContext(),"Booking Details",Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });
        booknow=findViewById(R.id.bookNowXccent);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListCar.this, bookingDetails.class);
                Toast toast=Toast.makeText(getApplicationContext(),"Booking Details",Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });
    }
}