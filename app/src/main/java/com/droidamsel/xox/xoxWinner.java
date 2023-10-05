package com.droidamsel.xox;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class xoxWinner extends Dialog {
    private final String message;
    private final MainActivity mainActivity;
    public  xoxWinner(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xox_result);

        //INITIALIZATION
        TextView messageText = findViewById(R.id.msgText);
        Button startAgainButton = findViewById(R.id.playAgainButton);
        Button exitButton = findViewById(R.id.exitButton);

        messageText.setText(message);

        //PLAY AGAIN BUTTON
        startAgainButton.setOnClickListener(view -> {
            mainActivity.restartMatch();
            dismiss();
        });

        //EXIT BUTTON
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mainActivity.startActivity(intent);
                mainActivity.finishAffinity();
            }
        });

    }
}