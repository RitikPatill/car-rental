package com.example.carrentalapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carrentalapp.ListCar;
import com.example.carrentalapp.NewCarDetails;
import com.example.carrentalapp.NewDriverDetails;
import com.example.carrentalapp.R;

public class HomeFragment extends Fragment {
    Button button,becomeDriver,rentUrCar;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rentUrCar=root.findViewById(R.id.rentUrcar);
        rentUrCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), NewCarDetails.class);
                startActivity(intent);
            }
        });
        becomeDriver=root.findViewById(R.id.becomeDriver);
        becomeDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent =new Intent(getActivity(), NewDriverDetails.class);
            startActivity(intent);
            }
        });
        button=root.findViewById(R.id.carlist);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), ListCar.class);
                startActivity(intent);
            }
        });

        //        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}