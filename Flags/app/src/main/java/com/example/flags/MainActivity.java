package com.example.flags;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Attributes
    Database db;
    TextView countryName;
    ImageView countryFlag;
    Button button;


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

        // Initialize attributes
        db = new Database();
        countryName = findViewById(R.id.country_name);
        countryFlag = findViewById(R.id.country_flag_image);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Country country = db.getRandomCountry();
                countryName.setText(country.getName());
                countryFlag.setImageResource(getResources().getIdentifier(
                        "flag_" + country.getCode().toLowerCase(),
                        "drawable",
                        getPackageName()
                ));
//                countryFlag.setImageResource(R.drawable.ad);
            }
        });
    }
}