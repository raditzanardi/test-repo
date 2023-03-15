package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityLogin extends AppCompatActivity {
    DataBaseHelper  dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbh = new DataBaseHelper(this);
        EditText editTextLoginUser =findViewById(R.id.editTextForUserEmailLogin);
        EditText editTextPasswordLogin = findViewById(R.id.editTextForLoginPassword);
        Button registerButton = findViewById(R.id.btnOpenRegisterActivity);
        Button loginButton = findViewById(R.id.btnLogin);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityLogin.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextLoginUser.getText().toString()) || TextUtils.isEmpty(editTextPasswordLogin.getText().toString()) ) {
                    Toast.makeText(MainActivityLogin.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    String passwordOfLogin = editTextPasswordLogin.getText().toString() ;
                    String loginUser = editTextLoginUser.getText().toString();

                    Cursor cursorRestaurantTable = dbh.viewDataFromRestaurantTable();
                    String strForRestaurantTablePassword = "";

                    Cursor cursorCustomerTable = dbh.viewDataFromCustomerTable();
                    String strForCustomerTablePassword = "";
                    boolean userFoundInRestaurantTable = false;
                    boolean userFoundInCustomerTable = false;

                    String userType = "";
                    //check Restaurant Table OR  the customer table to loop in both of them and find the login user
                    if (cursorRestaurantTable.getCount() > 0  || cursorCustomerTable.getCount() > 0 ) {
                        //to move through the whole data rows
                        while (cursorRestaurantTable.moveToNext()) {
                            //get string 8= restaurant email string 1 = password string 3 = Restaurant Name st 4 =FnOwner
                            //string 0 = restaurantID THIS ARE OBTAINED IN THE SAME ORDER AS THE DATABASE WAS CREATED
                            if (loginUser.equals(cursorRestaurantTable.getString(8))){
                                strForRestaurantTablePassword = (cursorRestaurantTable.getString(1));
                                userFoundInRestaurantTable = true;
                            }
                        }
                        if (userFoundInRestaurantTable) {
                            if (strForRestaurantTablePassword.equals(passwordOfLogin)) {
                                startActivity(new Intent(MainActivityLogin.this, RestaurantOpeningActivity.class));
                                editTextLoginUser.setText("");
                                editTextPasswordLogin.setText("");
                            } else {
                                Toast.makeText(MainActivityLogin.this, "password not correct", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            while (cursorCustomerTable.moveToNext()) {
                                //get string 0= customer email string 1 = password string 3 = FN st 4 =LN
                                if (loginUser.equals(cursorCustomerTable.getString(0))){
                                    strForCustomerTablePassword = cursorCustomerTable.getString(1);
                                    userFoundInCustomerTable = true;
                                }
                            }
                            if (userFoundInCustomerTable) {
                                if(strForCustomerTablePassword.equals(passwordOfLogin)){
                                    startActivity(new Intent(MainActivityLogin.this,CustomerOpeningActivity.class));
                                    editTextLoginUser.setText("");
                                    editTextPasswordLogin.setText("");
                                }
                                else {
                                    Toast.makeText(MainActivityLogin.this, "password not correct", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(MainActivityLogin.this, "User not found, please check your information", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    cursorCustomerTable.close();
                    cursorRestaurantTable.close();
                }
            }
        });
    }
}