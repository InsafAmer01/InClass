package com.example.inclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnSave;
    private EditText bTitle;
    private EditText aName;
    private EditText pages;
    private Switch switch1;
    ArrayList<Book> books = new ArrayList<>();
    private static String DATA = "DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
        bTitle = findViewById(R.id.bTitle);
        aName = findViewById(R.id.aName);
        pages = findViewById(R.id.pages);
        switch1 = findViewById(R.id.switch1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = bTitle.getText().toString();
                String author = aName.getText().toString();
                Integer pages = Integer.valueOf(bTitle.getText().toString());
                if (switch1.isChecked()) {
                    books.add(new Book(title,author,pages,Boolean.TRUE));
                } else {
                    books.add(new Book(title,author,pages,Boolean.FALSE));
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefs.edit();
                Gson gson = new Gson();
                String bookString = gson.toJson(books);

                editor.putString(DATA, bookString);
                editor.commit();
                Toast.makeText(this, "Data Saved:\n" + bookString, Toast.LENGTH_SHORT).show();


            }
        });

    }


}