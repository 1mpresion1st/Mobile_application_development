package com.example.Prac8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageShow extends AppCompatActivity {

    Button loadImageButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        loadImageButton = findViewById(R.id.loadImageButton);
        imageView = findViewById(R.id.imageView);

        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImageUrl();
            }
        });
    }

    private void fetchImageUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://random.dog/woof.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    final String imageUrl = json.getString("url");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(imageUrl).into(imageView);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void toImageDraw (View view)
    {
        finish();
    }}
