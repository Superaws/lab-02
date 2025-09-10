package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    GridLayout addCityGrid;
    EditText cityNameInput;
    Button addCity;
    Button deleteCity;
    Button confirmCity;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        deleteCity = findViewById(R.id.del_city);
        confirmCity = findViewById(R.id.confirm_button);
        addCityGrid = findViewById(R.id.add_grid);
        cityNameInput = findViewById(R.id.city_name_input);
        selectedCity = "";
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();

        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View clickedView, int position, long id) {
                cityList.getSelector().setAlpha(255);
                selectedCity = (String)parent.getItemAtPosition(position);
            }
        });

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityGrid.setVisibility(View.VISIBLE);
                cityNameInput.setText("");
            }
        });

        confirmCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityNameInput.getText().toString().isEmpty()) return;
                addCityGrid.setVisibility(View.INVISIBLE);
                cityAdapter.add(cityNameInput.getText().toString());
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCity.isEmpty()) return;
                cityAdapter.remove(selectedCity);
                selectedCity = "";
                cityList.getSelector().setAlpha(0);
            }
        });
        /*
          ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
               Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
               v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
               return insets;
          });
         */


    }
}