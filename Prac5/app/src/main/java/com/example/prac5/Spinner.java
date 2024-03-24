package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class Spinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        android.widget.Spinner spinnerName = findViewById(R.id.spinner);

        String[] fruits = getResources().getStringArray(R.array.fruits);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruits);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerName.setAdapter(adapter);
    }

    public void onBack(View view) {finish();}
}