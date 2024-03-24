package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toListActivity(View view) {
        Intent intent = new Intent(MainActivity.this, List.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
    public void toListRecycler(View view) {
        Intent intent = new Intent(MainActivity.this, Recycler.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
    public void toListScroll(View view) {
        Intent intent = new Intent(MainActivity.this, Scroll.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
    public void toListSpinner(View view) {
        Intent intent = new Intent(MainActivity.this, Spinner.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}