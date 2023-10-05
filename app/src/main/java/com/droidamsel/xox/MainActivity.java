package com.droidamsel.xox;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final ImageView[] cells = new ImageView[9]; //array of image view for the 9 cells
    private final List<int[]> combination = new ArrayList<>(); //
    private int[] position = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //position array for each index
    private int turn = 1;
    private int totalSelectedBoxes = 0; // Start from 0 initially
    private  TextView playerOneNameTextView ;
    private TextView playerTwoNameTextView;

    private LinearLayout layFor1;

    private LinearLayout layFor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INITIALIZING EACH CELLS WITH THEIR RESPECTIVE ID's
        cells[0] = findViewById(R.id.cell1);
        cells[1] = findViewById(R.id.cell2);
        cells[2] = findViewById(R.id.cell3);
        cells[3] = findViewById(R.id.cell4);
        cells[4] = findViewById(R.id.cell5);
        cells[5] = findViewById(R.id.cell6);
        cells[6] = findViewById(R.id.cell7);
        cells[7] = findViewById(R.id.cell8);
        cells[8] = findViewById(R.id.cell9);

        layFor1 = findViewById(R.id.lay_for_1);
        layFor2 = findViewById(R.id.lay_for_2);

        //POSSIBLE COMBINATIONS OF WINNING THE GAME
        combination.add(new int[]{0, 1, 2});
        combination.add(new int[]{3, 4, 5});
        combination.add(new int[]{6, 7, 8});
        combination.add(new int[]{0, 3, 6});
        combination.add(new int[]{1, 4, 7});
        combination.add(new int[]{2, 5, 8});
        combination.add(new int[]{2, 4, 6});
        combination.add(new int[]{0, 4, 8});

        playerOneNameTextView = findViewById(R.id.player1_name);
        playerTwoNameTextView = findViewById(R.id.player2_name);

        String getPlayerOneName = getIntent().getStringExtra("Player 1");
        String getPlayerTwoName = getIntent().getStringExtra("Player 2");

        playerOneNameTextView.setText(getPlayerOneName);
        playerTwoNameTextView.setText(getPlayerTwoName);

        setClickListeners();
    }

    private void setClickListeners() {
        for (int i = 0; i < cells.length; i++) {
            final int position = i;
            cells[i].setOnClickListener(view -> {
                if (isBoxSelectable(position)) {
                    performAction(cells[position], position);
                }
            });
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        position[selectedBoxPosition] = turn;
        if (turn == 1) {
            imageView.setImageResource(R.drawable.img_cross);
        } else {
            imageView.setImageResource(R.drawable.img_zero);
        }

        if (checkResults()) {
            String winnerName = (turn == 1) ? playerOneNameTextView.getText().toString() :
                    playerTwoNameTextView.getText().toString();
            showWinnerDialog(winnerName);
        } else if (totalSelectedBoxes == 8) { // Check for draw at 8 totalSelectedBoxes
            showDrawDialog();
        } else {
            changeTurn();
            totalSelectedBoxes++;
        }
    }


    private void changeTurn() {
        if (turn == 1) {
            turn = 2;
            layFor1.setBackgroundResource(R.drawable.highlight);
            layFor2.setBackgroundResource(R.drawable.olive_transparent);
        } else {
            turn = 1;
            layFor2.setBackgroundResource(R.drawable.highlight);
            layFor1.setBackgroundResource(R.drawable.olive_transparent);
        }
    }

    private boolean checkResults() {
        for (int[] combine : combination) {
            if (position[combine[0]] == turn && position[combine[1]] == turn &&
                    position[combine[2]] == turn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return position[boxPosition] == 0;
    }

    private void showWinnerDialog(String winnerName) {
        String message = winnerName + " is a Winner!";
        xoxWinner resultDialog = new xoxWinner(MainActivity.this, message, this);
        resultDialog.setCancelable(false);
        resultDialog.show();
    }

    private void showDrawDialog() {
       xoxWinner resultDialog = new xoxWinner(MainActivity.this, "Match Draw", this);
        resultDialog.setCancelable(false);
        resultDialog.show();
    }

    public void restartMatch() {
        position = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        turn = 1;
        totalSelectedBoxes = 0;

        // Resetting the image resources for all cells
        for (ImageView cell : cells) {
            cell.setImageResource(R.drawable.no_border_olive);
        }
    }
}
