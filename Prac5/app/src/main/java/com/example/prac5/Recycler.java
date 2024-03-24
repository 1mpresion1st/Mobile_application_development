package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Recycler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.aquabedolaga, "В мире, где царит аромат черешни"));
        items.add(new Item(R.drawable.first, "В тени зелёных листьев лимонного деревай "));
        items.add(new Item(R.drawable.second, "В мире, где царит аромат черешни"));
        // Добавьте другие элементы

        SimpleAdapter adapter = new SimpleAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    public void onBack(View view) {finish();}
}