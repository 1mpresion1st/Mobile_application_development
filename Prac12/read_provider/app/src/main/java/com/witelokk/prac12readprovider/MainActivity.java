package com.witelokk.prac12readprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Uri contentUri = Uri.parse("content://com.witelokk.prac12contentprovider.provider/books");
        ContentResolver resolver = getContentResolver();
        resolver.acquireContentProviderClient(contentUri);
        Cursor cursor = resolver.query(contentUri, new String[]{"id", "title", "author"}, null, null, "title ASC");

        if (cursor != null) {
            try {
                int idColumn = cursor.getColumnIndex("id");
                int titleColumn = cursor.getColumnIndex("title");
                int authorColumn = cursor.getColumnIndex("author");
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(idColumn);
                    String title = cursor.getString(titleColumn);
                    String author = cursor.getString(authorColumn);
                    System.out.println("Book ID: " + id);
                    System.out.println("Book Title: " + title);
                    System.out.println("Author: " + author);
                }
            } finally {
                cursor.close();
            }
        }
    }
}