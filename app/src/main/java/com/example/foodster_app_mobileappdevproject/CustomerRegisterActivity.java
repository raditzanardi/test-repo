package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerRegisterActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        dbh = new DataBaseHelper( this);
        EditText editTextUserNameEmail = findViewById(R.id.editTextCustomerUserEmail);
        EditText editTextCustomerPassword = findViewById(R.id.editTextCustomerRegistrationPassword);
        EditText editTextFirstNameCustomer = findViewById(R.id.editTxtCustomerFirstName);
        EditText editTextLastNameCustomer = findViewById(R.id.editTextCustomerLastName);
        EditText editTextAddressCustomer = findViewById(R.id.editTextAddressCustomer);
        EditText editTextPhoneCustomer = findViewById(R.id.editTextPhoneNumberCustomer);
        Button buttonRegisterCustomer = findViewById(R.id.btnRegisterCustomer);

        buttonRegisterCustomer.setOnClickListener(new View.OnClickListener() {
            boolean isRegistered;
            boolean userExistsInRestaurantTable = false;
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextUserNameEmail.getText().toString()) ||
                        TextUtils.isEmpty(editTextCustomerPassword.getText().toString()) ||
                        TextUtils.isEmpty(editTextFirstNameCustomer.getText().toString()) ||
                        TextUtils.isEmpty(editTextLastNameCustomer.getText().toString()) ||
                        TextUtils.isEmpty(editTextAddressCustomer.getText().toString()) ||
                        TextUtils.isEmpty(editTextPhoneCustomer.getText().toString()) ) {
                    Toast.makeText(CustomerRegisterActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor cursorRestaurantTable = dbh.viewDataFromRestaurantTable();
                    if (cursorRestaurantTable.getCount() > 0){
                        while (cursorRestaurantTable.moveToNext()) {
                            if (editTextUserNameEmail.getText().toString().equals(cursorRestaurantTable.getString(8))){
                                userExistsInRestaurantTable = true;
                            }
                        }
                    }
                    if(userExistsInRestaurantTable){
                        Toast.makeText(CustomerRegisterActivity.this, "Customer Registration unsuccessful, User Email already registered as Restaurant", Toast.LENGTH_LONG).show();
                    } else{
                        isRegistered = dbh.addDataCustomerTable(editTextUserNameEmail.getText().toString(),
                                editTextCustomerPassword.getText().toString(),
                                editTextFirstNameCustomer.getText().toString(),
                                editTextLastNameCustomer.getText().toString(),
                                editTextAddressCustomer.getText().toString(),
                                editTextPhoneCustomer.getText().toString());

                        if(isRegistered){
                            Toast.makeText(CustomerRegisterActivity.this, "Customer Registered Successfully", Toast.LENGTH_LONG).show();
                            editTextUserNameEmail.setText("");
                            editTextCustomerPassword.setText("");
                            editTextFirstNameCustomer.setText("");
                            editTextLastNameCustomer.setText("");
                            editTextAddressCustomer.setText("");
                            editTextPhoneCustomer.setText("");
                        }
                        else{
                            Toast.makeText(CustomerRegisterActivity.this, "Customer Registration unsuccessful, User Email already registered", Toast.LENGTH_LONG).show();
                        }
                    }
                    cursorRestaurantTable.close();
                }

            }
        });

    }
}