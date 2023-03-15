package com.example.foodster_app_mobileappdevproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "FoodsterDatabase.db";
    final static int DATABASE_VERSION = 1;

    //TABLE 1 -> Customer
    final static String TABLE_FOR_CUSTOMER_NAME= "Customer_Table";
    final static String T1COL_1 = "CustomerEmail";
    final static String T1COL_2 = "PasswordOfUser";
    final static String T1COL_3 = "FirstNameCustomer";
    final static String T1COL_4 = "LastNameCustomer";
    final static String T1COL_5 = "AddressCustomer";
    final static String T1COL_6 = "PhoneNumberCustomer";

    //TABLE 2 -> Order
    final static String TABLE_FOR_ORDER_NAME= "Order_Table";
    final static String T2COL_1 = "OrderID";
    final static String T2COL_2 = "CustomerEmail";
    final static String T2COL_3 = "RestaurantName";
    final static String T2COL_4 = "Date";
    final static String T2COL_5 = "FoodID";
    final static String T2COL_6 = "OrderedAmount";
    final static String T2COL_7 = "DeliveryorPickup";
    final static String T2COL_8 = "TotalPaymentAmount";
    final static String T2COL_9 = "Status";
    final static String T2COL_10 = "Reminder";

    //TABLE 3 -> Restaurant
    final static String TABLE_FOR_RESTAURANT_NAME= "Restaurant_Table";
    final static String T3COL_1 = "RestaurantUserNameEmail";
    final static String T3COL_2 = "PasswordOfRestaurant";
    final static String T3COL_3 = "RestaurantName";
    final static String T3COL_4 = "FirstNameOwner";
    final static String T3COL_5 = "LastNameOwner";
    final static String T3COL_6 = "PhoneNumberRestaurant";
    final static String T3COL_7 = "City";
    final static String T3COL_8 = "AddressRestaurant";
    final static String T3COL_9 = "RestaurantId"; //primary key

    //TABLE 4 -> Food stocks of the restaurant
    final static String TABLE_FOR_FOODOFRESTAURANT_NAME= "RestaurantFoodStocks_Table";
    final static String T4COL_1 = "RestaurantName"; // primary key
    final static String T4COL_2 = "FoodId"; //Food name
    final static String T4COL_3 = "Date";
    final static String T4COL_4 = "FoodAmount";
    final static String T4COL_5 = "Price";
    final static String T4COL_6 = "ProcessPickUpTime";
    final static String T4COL_7 = "ExtraFoodDetails";

    public DataBaseHelper (@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable1 = "CREATE TABLE " + TABLE_FOR_CUSTOMER_NAME + "(" + T1COL_1 + " TEXT UNIQUE, " +
                T1COL_2 + " TEXT, " + T1COL_3 + " TEXT, " + T1COL_4 + " TEXT, " + T1COL_5 + " TEXT, " +
                T1COL_6 + " TEXT, PRIMARY KEY (" + T1COL_1+ ") )";
        String queryTable2 = "CREATE TABLE " + TABLE_FOR_ORDER_NAME + "(" + T2COL_1 + " INTEGER PRIMARY KEY, " +
                T2COL_2 + " TEXT, " + T2COL_3 + " TEXT, " + T2COL_4 + " TEXT, " + T2COL_5 + " TEXT, " +
                T2COL_6 + " TEXT, " + T2COL_7 + " TEXT, " + T2COL_8 + " TEXT, " + T2COL_9 + " TEXT, " + T2COL_10 + " TEXT, " +
                "FOREIGN KEY (" + T2COL_2+ ") REFERENCES " + TABLE_FOR_CUSTOMER_NAME + "(" + T1COL_1 + ")," +
                "FOREIGN KEY (" + T2COL_3+ ") REFERENCES " + TABLE_FOR_RESTAURANT_NAME + "(" + T3COL_3 + "))" ;

        String queryTable3 = "CREATE TABLE " + TABLE_FOR_RESTAURANT_NAME + "(" + T3COL_9 + " INTEGER PRIMARY KEY, " +
                T3COL_2 + " TEXT, " + T3COL_3 + " TEXT UNIQUE, " + T3COL_4 + " TEXT, " + T3COL_5 + " TEXT, " +
                T3COL_6 + " TEXT, " + T3COL_7 + " TEXT, " + T3COL_8 + " TEXT, " + T3COL_1 + " TEXT UNIQUE)";

        String queryTable4 = "CREATE TABLE " + TABLE_FOR_FOODOFRESTAURANT_NAME + "(" + T4COL_1 + " INTEGER, " +
                T4COL_2 + " TEXT UNIQUE, " + T4COL_3 + " DATE, " + T4COL_4 + " TEXT, " + T4COL_5 + " TEXT, " +
                T4COL_6 + " TEXT, " + T4COL_7 + " TEXT, " +
                "PRIMARY KEY (" + T4COL_1 +", "+ T4COL_2 + "), " +
                "FOREIGN KEY (" + T4COL_1+ ") REFERENCES " + TABLE_FOR_RESTAURANT_NAME + "(" + T3COL_3 + ")," +
                "FOREIGN KEY (" + T4COL_2+ ") REFERENCES " + TABLE_FOR_ORDER_NAME + "(" + T2COL_5 + "))" ;

        sqLiteDatabase.execSQL(queryTable1);
        sqLiteDatabase.execSQL(queryTable2);
        sqLiteDatabase.execSQL(queryTable3);
        sqLiteDatabase.execSQL(queryTable4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newerVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_CUSTOMER_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_ORDER_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_RESTAURANT_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_FOODOFRESTAURANT_NAME);
        onCreate(sqLiteDatabase);
    }

    /// METHOD TO ADD DATA TO THE the Customer TABLE
    public boolean addDataCustomerTable (String customerUserNameEmail, String passwordCustomer, String firstNameCustomer, String lastNameCustomer,
                                         String addressCustomer, String PhoneNumberCustomer){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // save values in key value pairs
        values.put(T1COL_1, customerUserNameEmail);
        values.put(T1COL_2, passwordCustomer);
        values.put(T1COL_3, firstNameCustomer);
        values.put(T1COL_4, lastNameCustomer);
        values.put(T1COL_5, addressCustomer);
        values.put(T1COL_6, PhoneNumberCustomer);

        long l = sqLiteDatabase.insert(TABLE_FOR_CUSTOMER_NAME, null,values); // insert data to table 1
        //positive value indicates success
        if (l > 0 )
            return true;
        else
            return false;
    }

    /// METHOD TO ADD DATA TO THE the Restaurant TABLE
    public boolean addDataRestaurantTable (String restaurantUserNameEmail, String passwordRestaurant, String restaurantName, String firstNameOwnerRestaurant,
                                         String lastNameOwnerRestaurant, String phoneNumberRestaurant, String cityRestaurant, String addressOfRestaurant){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // save values in key value pairs
        values.put(T3COL_1,restaurantUserNameEmail);
        values.put(T3COL_2,passwordRestaurant);
        values.put(T3COL_3, restaurantName);
        values.put(T3COL_4, firstNameOwnerRestaurant);
        values.put(T3COL_5, lastNameOwnerRestaurant);
        values.put(T3COL_6, phoneNumberRestaurant);
        values.put(T3COL_7, cityRestaurant);
        values.put(T3COL_8,addressOfRestaurant);

        long l = sqLiteDatabase.insert(TABLE_FOR_RESTAURANT_NAME, null,values); // insert data to table 1
        //positive value indicates success
        if (l > 0 )
            return true;
        else
            return false;
    }

    //METHOD TO ADD DATA TO THE ORDER TABLE
    public boolean addDataOrderTable () {
        return true;
    }

    //METHOD TO ADD DATA TO THE Food stocks TABLE
    public boolean addDataFoodStocksTable () {
        return true;
    }

    //METHOD TO VIEW DATA of Customer Table, combined with the activity where you are using it
    public Cursor viewDataFromCustomerTable(){
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_CUSTOMER_NAME;
        Cursor cursor = database.rawQuery(query,null);

//        String query = "SELECT * FROM " + TABLE_FOR_RESTAURANT_NAME + " WHERE "+ whereClauseParameter+ "= ?";
//        Cursor cursor = database.rawQuery(query,new String[]{customerUserEmail});
        return cursor; //cursor object allows you to retrieve data row by row
    }

    //METHOD TO VIEW DATA of Restaurant Table, combined with the activity where you are using it
    public Cursor viewDataFromRestaurantTable(){
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_RESTAURANT_NAME;
        Cursor cursor = database.rawQuery(query,null);

//        String query = "SELECT * FROM " + TABLE_FOR_RESTAURANT_NAME + " WHERE "+ whereClauseParameter+ "= ?";
//        Cursor cursor = database.rawQuery(query,new String[]{restaurantUser});
        return cursor; //cursor object allows you to retrieve data row by row
    }

//    //METHOD TO DELETE A PARTICULAR RECORD AND RETURNA  BOOLEAN VALUE
//    public boolean deleteRec (int id ) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        int d = sqLiteDatabase.delete(TABLE1_NAME,"Id=?",
//                new String[]{Integer.toString(id)}); //positive value indicates success
//        if(d>0)
//            return true;
//        else
//            return false;
//    }
//
//    public boolean updateRec(int id,String c) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(T1COL_4,c);
//        int u = sqLiteDatabase.update(TABLE1_NAME,contentValues,"Id=?",
//                new String[]{Integer.toString(id)});
//        if(u > 0)
//            return true;
//        else
//            return false;
//    }

}
