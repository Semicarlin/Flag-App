package com.example.flags.models;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class AnswerButton extends androidx.appcompat.widget.AppCompatButton {

    // Attributes
    private boolean correct = false;

    // Constructors
    public AnswerButton(@NonNull Context context) {
        super(context);
    }

    public AnswerButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnswerButton(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
