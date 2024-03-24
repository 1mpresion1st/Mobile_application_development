package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        ArrayList<String> selectedCategories = intent.getStringArrayListExtra("selectedCategories");


        TextView textViewCategories = findViewById(R.id.cartView);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Выбранные категории:\n");
        for (String category : selectedCategories) {
            stringBuilder.append("- ").append(category).append("\n");
        }

        textViewCategories.setText(stringBuilder.toString());
    }

    public void onBack(View view) {finish();}
}