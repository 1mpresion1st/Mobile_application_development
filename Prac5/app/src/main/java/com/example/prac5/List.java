package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView fruitsListView = (ListView) findViewById(R.id.fruitsList);
//
        String[] fruitsList = getResources().getStringArray(R.array.fruits);
//
        ArrayAdapter<String> FruitsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruitsList);
        fruitsListView.setAdapter(FruitsAdapter);

        fruitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                String selectedItem = fruitsList[position];

                Intent intent = new Intent(List.this, Second.class);
                intent.putExtra("selectedItem", selectedItem);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }

    public void onBack(View view) {
        finish();
    }
}