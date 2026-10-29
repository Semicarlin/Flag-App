package com.example.flags.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flags.R;

public class GameSetupActivity extends AppCompatActivity {

    // Attributes

    // UI attributes
    Button startGameButton;

    // Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        /* Option list:
            - Answer type
                - Multiple choice
                    - Incorrect answers based on
                        - Random
                        - Same continent
                        - Similar name
                        - Confusion scores
                    - Number of available answers (hardcode 2-8?)
                - Write
                    - Suggestions
                        - Yes
                        - No
            - End condition
                - Get X correct (Quiz)
                - Get X incorrect
            - Question set
                - All (random)
                - Select certain continents
                - Difficulty (based on confusion scores)
                    - Easy
                    - Medium
                    - Hard
            - Answer revealed on incorrect answer?

         */

        // Get UI elements
        startGameButton = findViewById(R.id.start_game_button);

        // Listeners
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Game.class)
                        .putExtra("questionCount", 20)
                );
            }
        });
    }
}