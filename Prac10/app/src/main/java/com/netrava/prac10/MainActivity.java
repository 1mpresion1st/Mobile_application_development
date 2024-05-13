package com.netrava.prac10;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.netrava.prac10.databinding.ActivityMainBinding;
import com.netrava.prac10.databinding.DialogAddEditUserBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        binding.textInputLayout.getEditText().setText(
                sharedPreferences.getString("username", "")
        );
        binding.button.setOnClickListener(v -> {
            sharedPreferences
                    .edit()
                    .putString("username", binding.textInputLayout.getEditText().getText().toString())
                    .apply();
        });

        // Database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        ArrayList<Student> students = new ArrayList<>(databaseHelper.getAllStudents());
        StudentsAdapter adapter = new StudentsAdapter(this, databaseHelper, students);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.fabAdd.setOnClickListener(v -> {
            DialogAddEditUserBinding dialogBinding = DialogAddEditUserBinding.inflate(getLayoutInflater());
            new MaterialAlertDialogBuilder(this)
                    .setView(dialogBinding.getRoot())
                    .setTitle("Add student")
                    .setPositiveButton("Add", (dialog, whichButton) -> {
                        Student student = new Student(
                                students.isEmpty() ? 0 : students.get(students.size()-1).getId()+1,
                                dialogBinding.nameTextInputLayout.getEditText().getText().toString(),
                                dialogBinding.emailTextInputLayout.getEditText().getText().toString(),
                                dialogBinding.groupTextInputLayout.getEditText().getText().toString()
                        );
                        databaseHelper.addStudent(student);
                        students.add(student);

                    })
                    .setNegativeButton("Cancel", (dialog, whichButton) -> {})
                    .create()
                    .show();
        });
    }
}