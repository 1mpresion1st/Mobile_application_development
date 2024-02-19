package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("RRR","onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("RRR","onDestroy()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("RRR","onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RRR","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("RRR","onPause()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("RRR","onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("RRR","onRestart()");
    }
}