package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOpeningActivity extends AppCompatActivity {

    String[] restaurants = {"Chicko Chicken", "Old Spaghetti Factory", "North Noodle House", "Tim Horton's", "Subway"};
    int[] images = {R.drawable.chicko, R.drawable.spaghetti, R.drawable.noodle, R.drawable.tim, R.drawable.subway};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_opening);

        ArrayList<HashMap<String,String>> aList =
                new ArrayList<HashMap<String,String>>();

        for(int i=0;i<restaurants.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("txt",restaurants[i]);
            hashMap.put("images",Integer.toString(images[i]));
            aList.add(hashMap);

            String[] from = {"images","txt"};
            int[] to = {R.id.imgRestaurant,R.id.txtRestaurant};

            SimpleAdapter adapter = new SimpleAdapter(CustomerOpeningActivity.this,
                    aList,R.layout.listview,from,to);

            ListView listView = findViewById(R.id.listview);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view,
                                        int position, long id) {
                    switch (position){
                        case 0:
                            startActivity(new Intent(CustomerOpeningActivity.this,restaurant1.class));
                            break;
                        case 1:
                            startActivity(new Intent(CustomerOpeningActivity.this,restaurant2.class));
                            break;
                        case 2:
                            startActivity(new Intent(CustomerOpeningActivity.this,restaurant3.class));
                            break;
                        case 3:
                            startActivity(new Intent(CustomerOpeningActivity.this,restaurant4.class));
                            break;
                        case 4:
                            startActivity(new Intent(CustomerOpeningActivity.this,restaurant5.class));
                            break;
                    }

                }
            });
        }
    }
}