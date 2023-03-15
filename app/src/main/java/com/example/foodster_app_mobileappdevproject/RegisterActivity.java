package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinnerOfTypeOfRegistration = findViewById(R.id.spinnerTypeOfRegistration);
        Button btnConfirmTypeOfRegistration = findViewById(R.id.btnConfirmTypeOfRegistration);

        btnConfirmTypeOfRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typeOfRegistration = spinnerOfTypeOfRegistration.getSelectedItem().toString();
                if(typeOfRegistration.equals("Customer")) {
                    startActivity(new Intent(RegisterActivity.this,CustomerRegisterActivity.class));
                }
                else if (typeOfRegistration.equals("Restaurant")){
                    startActivity(new Intent(RegisterActivity.this,RestaurantRegisterActivity.class));
                }
            }
        });
    }
}