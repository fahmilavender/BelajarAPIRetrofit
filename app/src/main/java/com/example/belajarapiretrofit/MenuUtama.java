package com.example.belajarapiretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuUtama extends AppCompatActivity {

    ImageButton imgBtnPremier;
    ImageButton imgBtnLaliga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_utama);

        imgBtnPremier = findViewById(R.id.imgBtnPremier);
        imgBtnLaliga = findViewById(R.id.imgBtnLaLiga);

        imgBtnPremier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, MainActivity.class);
                intent.putExtra("LEAGUE", "EPL");
                startActivity(intent);
            }
        });

        imgBtnLaliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, MainActivity.class);
                intent.putExtra("LEAGUE", "LALIGA");
                startActivity(intent);
            }
        });
    }
}