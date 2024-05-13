package com.witelokk.prac12;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.witelokk.prac12.databinding.ActivityMainBinding;

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

//        String json = "{\"text\":\"abc\", \"number\":123, \"bool\": true}";

        ActivityResultLauncher<String> filePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            String json = readFileContents(uri);
            Data data = parseJson(json);

            binding.editTextText.setText(data.getText());
            binding.editTextNumber.setText(String.valueOf(data.getNumber()));
            binding.switch1.setChecked(data.isBool());
        });

        ActivityResultLauncher<String> filePickerLauncher2 = registerForActivityResult(new ActivityResultContracts.CreateDocument("application/json"), uri -> {
            String text = binding.editTextText.getText().toString();
            int number = Integer.parseInt(binding.editTextNumber.getText().toString());
            boolean bool = binding.switch1.isChecked();

            Data data = new Data(text, number, bool);
            String json = serializeJson(data);
            writeFileContents(uri, json);
        });

        binding.buttonLoad.setOnClickListener(v -> {
            filePickerLauncher.launch("application/json");
        });

        binding.buttonSave.setOnClickListener(v -> {
            filePickerLauncher2.launch("");
        });
    }

    public Data parseJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Data.class);
    }

    public String serializeJson(Data data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private String readFileContents(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void writeFileContents(Uri uri, String content) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    getContentResolver().openOutputStream(uri));
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            // File content has been written successfully
        } catch (IOException e) {
            e.printStackTrace();
            // Handle errors writing to the file
        }
    }

}