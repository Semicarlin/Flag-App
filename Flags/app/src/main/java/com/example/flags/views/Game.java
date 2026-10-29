package com.example.flags.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flags.R;
import com.example.flags.models.AnswerButton;
import com.example.flags.models.Country;
import com.example.flags.models.Database;
import com.example.flags.models.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Game extends AppCompatActivity {

    // Attributes
    private int questionCount;
    private int answerCount;
    private Database db;
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private ArrayList<AnswerButton> answerButtons;
    private ImageView flagImageView;

    // Buttons
    AnswerButton buttonA;
    AnswerButton buttonB;
    AnswerButton buttonC;
    AnswerButton buttonD;
    AnswerButton buttonE;
    AnswerButton buttonF;
    AnswerButton buttonG;
    AnswerButton buttonH;

    // Button containers
    LinearLayout layoutAB;
    LinearLayout layoutCD;
    LinearLayout layoutEF;
    LinearLayout layoutGH;

    // Methods
    private void assignAnswersToButtons() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        for (int i = 0; i < answerCount; ++i) {
            AnswerButton answerButton = answerButtons.get(i);
            String answer = currentQuestion.getAnswers().get(i).getName();
            answerButton.setText(answer);
            answerButton.setCorrect(Objects.equals(answer, currentQuestion.getCorrectAnswer().getName()));
        }
    }

    private void configureForCurrentQuestion() {
        assignAnswersToButtons();
        flagImageView.setImageResource(questions.get(currentQuestionIndex).getCorrectAnswer().getResourceID(Game.this, getPackageName()));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize attributes
        db = new Database();
        questions = new ArrayList<>();
        answerButtons = new ArrayList<>();

        // Get extras
        questionCount = getIntent().getIntExtra("questionCount", 1);
        answerCount = getIntent().getIntExtra("answerCount", 4);

        // Initialize buttons
        buttonA = findViewById(R.id.button_a);
        buttonB = findViewById(R.id.button_b);
        buttonC = findViewById(R.id.button_c);
        buttonD = findViewById(R.id.button_d);
        buttonE = findViewById(R.id.button_e);
        buttonF = findViewById(R.id.button_f);
        buttonG = findViewById(R.id.button_g);
        buttonH = findViewById(R.id.button_h);

        // Initialize button containers
        layoutAB = findViewById(R.id.ab_layout);
        layoutCD = findViewById(R.id.cd_layout);
        layoutEF = findViewById(R.id.ef_layout);
        layoutGH = findViewById(R.id.gh_layout);

        // Hide unneeded buttons
        if (answerCount >= 2) {
            answerButtons.add(buttonA);
            answerButtons.add(buttonB);
            layoutAB.setVisibility(View.VISIBLE);
        }
        if (answerCount >= 4) {
            answerButtons.add(buttonC);
            answerButtons.add(buttonD);
            layoutCD.setVisibility(View.VISIBLE);
        }
        if (answerCount >= 6) {
            answerButtons.add(buttonE);
            answerButtons.add(buttonF);
            layoutEF.setVisibility(View.VISIBLE);
        }
        if (answerCount >= 8) {
            answerButtons.add(buttonG);
            answerButtons.add(buttonH);
            layoutGH.setVisibility(View.VISIBLE);
        }
        assert answerButtons.size() == answerCount;

        // Initialize other UI elements
        flagImageView = findViewById(R.id.imageview_flag);

        // Make questions
        for (int i = 0; i < questionCount; ++i) {
            questions.add(db.makeQuestion(answerCount));
        }
        currentQuestionIndex = 0;
        Question currentQuestion = questions.get(currentQuestionIndex);
        assert currentQuestion.getAnswers().size() == answerCount;

        // Assign answers to buttons for first question
        configureForCurrentQuestion();

        // Define button behaviour
        for (AnswerButton answerButton:answerButtons) {
            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answerButton.isCorrect()) {
                        Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Incorrect (" + questions.get(currentQuestionIndex).getCorrectAnswer().getName() + ")", Toast.LENGTH_SHORT).show();
                    }
                    currentQuestionIndex += 1;
                    if (currentQuestionIndex >= questions.size()) {
                        // panic
                    }
                    configureForCurrentQuestion();
                }
            });
        }
    }
}
