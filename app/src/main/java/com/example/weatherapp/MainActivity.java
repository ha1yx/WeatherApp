package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText user_field;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_field = findViewById(R.id.user_field);
        nextBtn = findViewById(R.id.next_page_btn);

        nextBtn.setOnClickListener(v -> {
            String city = user_field.getText().toString();

            if (city.isEmpty()) {
                Toast.makeText(this, "Введите город", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("USER_INPUT", city);
            startActivity(intent);
        });
    }
}
