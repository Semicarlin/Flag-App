package com.example.flags.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flags.R;
import com.example.flags.models.Country;
import com.example.flags.models.Database;

public class MainActivity extends AppCompatActivity {

    // Attributes
    Database db;

    // UI attributes
    TextView countryName;
    ImageView countryFlag;
    Button randomCountryButton;
    Button setupGameButton;


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

        // Initialize ui attributes
        db = new Database();
        countryName = findViewById(R.id.country_name);
        countryFlag = findViewById(R.id.country_flag_image);
        randomCountryButton = findViewById(R.id.random_country_button);
        setupGameButton = findViewById(R.id.setup_game_button);

        randomCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Country country = db.getRandomCountry();
                countryName.setText(country.getName());
                countryFlag.setImageResource(country.getResourceID(MainActivity.this, getPackageName()));
            }
        });

        setupGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameSetupActivity.class));
            }
        });
    }
}