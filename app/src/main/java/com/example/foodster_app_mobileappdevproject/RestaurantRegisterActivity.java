package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RestaurantRegisterActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_register);
        dbh = new DataBaseHelper( this);
        EditText editTextRestaurantUserNameEmail = findViewById(R.id.editTextUserEmailRestaurant);
        EditText editTextNameOfRestaurant = findViewById(R.id.editTextNewRestaurantName);
        EditText editTextRestaurantPassword = findViewById(R.id.editTextRestaurantRegistrationPassword);
        EditText editTextFirstNameRestaurant = findViewById(R.id.editTextOwnerRestaurantFirstName);
        EditText editTextLastNameRestaurant = findViewById(R.id.editTextRestaurantOwnerLastName);
        EditText editTextAddressRestaurant = findViewById(R.id.editTextAddressRestaurantRegister);
        EditText editTextPhoneRestaurant = findViewById(R.id.editTextPhoneNumberRestaurantOwner);

        Button buttonRegisterRestaurant = findViewById(R.id.btnRegisterNewRestaurant);
        Spinner spinnerRestaurantCity = findViewById(R.id.spinnerRestaurantCity);

        buttonRegisterRestaurant.setOnClickListener(new View.OnClickListener() {
            boolean isRegistered;
            boolean userFoundInCustomerTable = false;
            @Override
            public void onClick(View view) {
                String chosenCityFromSpinner = spinnerRestaurantCity.getSelectedItem().toString();

                if (TextUtils.isEmpty(editTextRestaurantUserNameEmail.getText().toString()) ||
                TextUtils.isEmpty(editTextRestaurantPassword.getText().toString()) ||
                TextUtils.isEmpty(editTextNameOfRestaurant.getText().toString()) ||
                TextUtils.isEmpty(editTextFirstNameRestaurant.getText().toString()) ||
                TextUtils.isEmpty(editTextLastNameRestaurant.getText().toString()) ||
                TextUtils.isEmpty(editTextPhoneRestaurant.getText().toString()) ||
                TextUtils.isEmpty(editTextAddressRestaurant.getText().toString())) {
                    Toast.makeText(RestaurantRegisterActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor cursorCustomerTable = dbh.viewDataFromCustomerTable();
                    if (cursorCustomerTable.getCount() > 0)
                        if (cursorCustomerTable.getCount() > 0){
                            while (cursorCustomerTable.moveToNext()) {
                                if (editTextRestaurantUserNameEmail.getText().toString().equals(cursorCustomerTable.getString(0))){
                                    userFoundInCustomerTable = true;
                                }
                            }
                        }
                    if(userFoundInCustomerTable){
                        Toast.makeText(RestaurantRegisterActivity.this, "Restaurant Registration unsuccessful, User Email already registered as customer", Toast.LENGTH_LONG).show();
                    }
                    else {
                            isRegistered = dbh.addDataRestaurantTable(editTextRestaurantUserNameEmail.getText().toString(),
                                    editTextRestaurantPassword.getText().toString(),
                                    editTextNameOfRestaurant.getText().toString(),
                                    editTextFirstNameRestaurant.getText().toString(),
                                    editTextLastNameRestaurant.getText().toString(),
                                    editTextPhoneRestaurant.getText().toString(),
                                    chosenCityFromSpinner,
                                    editTextAddressRestaurant.getText().toString());
                            if(isRegistered){
                                Toast.makeText(RestaurantRegisterActivity.this, "Restaurant Registered Successfully", Toast.LENGTH_LONG).show();
                                editTextRestaurantUserNameEmail.setText("");
                                editTextRestaurantPassword.setText("");
                                editTextNameOfRestaurant.setText("");
                                editTextFirstNameRestaurant.setText("");
                                editTextLastNameRestaurant.setText("");
                                editTextPhoneRestaurant.setText("");
                                spinnerRestaurantCity.setSelection(0);
                                editTextAddressRestaurant.setText("");
                            }
                            else{
                                Toast.makeText(RestaurantRegisterActivity.this, "Restaurant Registration unsuccessful, Restaurant User Email and Restaurant Name already registered", Toast.LENGTH_LONG).show();
                            }
                        }

                }


            }
        });

    }
}