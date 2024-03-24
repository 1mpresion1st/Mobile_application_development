package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String[] fruits = null; // Переменная для хранения списка фруктов

        String message = getIntent().getStringExtra("selectedItem");

        ArrayList<String> selectedSorts = new ArrayList<String>();
        ListView listView = findViewById(R.id.sortList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        switch (message) {
            case "Яблоки":
                fruits = getResources().getStringArray(R.array.apples);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Бананы":
                fruits = getResources().getStringArray(R.array.bananas);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Сливы":
                fruits = getResources().getStringArray(R.array.plums);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Персики":
                fruits = getResources().getStringArray(R.array.peaches);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Ананасы":
                fruits = getResources().getStringArray(R.array.pinapples);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Вишни":
                fruits = getResources().getStringArray(R.array.cherris);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Черешни":
                fruits = getResources().getStringArray(R.array.cherris);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Гранаты":
                fruits = getResources().getStringArray(R.array.grenate);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Киви":
                fruits = getResources().getStringArray(R.array.qiwies);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Мандарины":
                fruits = getResources().getStringArray(R.array.mandarines);
                renderSorts(listView, selectedSorts, fruits);
                break;
            case "Апельсины":
                fruits = getResources().getStringArray(R.array.oranges);
                renderSorts(listView, selectedSorts, fruits);
                break;
        }

    }

    public void onCartCheck(View view) {
        ListView listView = findViewById(R.id.sortList);
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        ArrayList<String> selectedCategories = new ArrayList<>();

        for (int i = 0; i < checkedItems.size(); i++) {
            int position = checkedItems.keyAt(i);
            if (checkedItems.valueAt(i)) {
                selectedCategories.add((String) listView.getItemAtPosition(position));
            }
        }

        Intent intent = new Intent(this, Cart.class);
        intent.putStringArrayListExtra("selectedCategories", selectedCategories);
        startActivity(intent);
    }

    public void renderSorts(ListView sorta_list_view, ArrayList<String> selected_sorts, String[] fruits){
        ArrayList<String> apple = new ArrayList<String>();
        Collections.addAll(apple, fruits);
        ArrayAdapter<String> apple_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, apple);
        sorta_list_view.setAdapter(apple_adapter);

        sorta_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sort_apple = apple_adapter.getItem(position);
                if (sorta_list_view.isItemChecked(position))
                    selected_sorts.add(sort_apple);
                else
                    selected_sorts.remove(sort_apple);
            }
        });
        Button buttonAddSort = findViewById(R.id.buttonAdd);
        buttonAddSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort_add(view,selected_sorts,apple_adapter);
            }
        });

        Button buttonremove = findViewById(R.id.buttonDelete);
        buttonremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i< selected_sorts.size();i++){
                    apple_adapter.remove(selected_sorts.get(i));
                }
                // снимаем все ранее установленные отметки
                sorta_list_view.clearChoices();
                // очищаем массив выбраных объектов
                selected_sorts.clear();
                apple_adapter.notifyDataSetChanged();
            }
        });
    }

    public void sort_add(View view, ArrayList<String> selected_sorts, ArrayAdapter<String> apple_adapter) {
        EditText new_sort = findViewById(R.id.editSort);
        String sort_n = new_sort.getText().toString();

        if (!sort_n.isEmpty()) {
            selected_sorts.add(sort_n);
            apple_adapter.add(sort_n);
            apple_adapter.notifyDataSetChanged();
            new_sort.setText("");
        }

    }

    public void onBack(View view) {finish();}
}