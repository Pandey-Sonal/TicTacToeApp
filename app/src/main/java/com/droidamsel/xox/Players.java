package com.droidamsel.xox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Players extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        //INITIALIZATION
        EditText playerOne = findViewById(R.id.player1);
        EditText playerTwo = findViewById(R.id.player2);
        Button startGame = findViewById(R.id.startGameButton);

        //START GAME BUTTON
        startGame.setOnClickListener(view -> {
            String getPlayerOneName = playerOne.getText().toString(); //get from player1
            String getPlayerTwoName = playerTwo.getText().toString(); //get from player2

            if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) { //when empty
                showToast();
            }else{
                Intent intent = new Intent(Players.this, MainActivity.class); //starting next activity after getting the names
                intent.putExtra("Player 1", getPlayerOneName);
                intent.putExtra("Player 2", getPlayerTwoName);
                startActivity(intent);
            }

        }
        );
    }
    @SuppressLint("SetTextI18n")
    //TOAST DISPLAY
    private void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, findViewById(R.id.toast_text));

        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText("Please Enter Player Name");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}