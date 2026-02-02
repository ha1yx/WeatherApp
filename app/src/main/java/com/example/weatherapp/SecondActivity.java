package com.example.weatherapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {

    TextView cityText, tempText, infoText;

    String API_KEY = "7f75ba93d702ac5eaa4d315f8d1bba91";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        cityText = findViewById(R.id.city);
        tempText = findViewById(R.id.temp);
        infoText = findViewById(R.id.info);

        String city = getIntent().getStringExtra("USER_INPUT");
        cityText.setText(city);

        loadWeather(city);
    }

    private void loadWeather(String city) {
        new Thread(() -> {
            try {
                runOnUiThread(() -> infoText.setText("Загрузка..."));

                String url = "https://api.openweathermap.org/data/2.5/weather?q="
                        + city + "&appid=" + API_KEY + "&units=metric&lang=ru";

                String result = NetworkUtils.fetchUrl(url);

                if (result == null) {
                    runOnUiThread(() -> infoText.setText("result == null"));
                    return;
                }

                JSONObject json = new JSONObject(result);
                JSONObject main = json.getJSONObject("main");
                JSONArray weather = json.getJSONArray("weather");

                int temp = main.getInt("feels_like");
                String desc = weather.getJSONObject(0).getString("description");

                runOnUiThread(() -> {
                    tempText.setText(temp + " °C");
                    infoText.setText(desc);
                });

            } catch (Exception e) {
                runOnUiThread(() -> infoText.setText("EXCEPTION"));
            }
        }).start();
    }

}

