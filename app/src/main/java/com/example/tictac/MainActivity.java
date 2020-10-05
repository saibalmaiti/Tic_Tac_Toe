package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private int activeplayer = 0;
    private int[][] winningpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean gameActive = true;
    private TextView winningText, playAgain;
    private int count = 0;
    public void drop(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(view.getTag().toString());
        if (gameState[tappedCounter] == -1 && gameActive){
            count+=1;
            gameState[tappedCounter] = activeplayer;
        counter.setAlpha(0f);
        if (activeplayer == 0) {
            activeplayer = 1;
            counter.setImageResource(R.drawable.cross);
        } else {
            activeplayer = 0;
            counter.setImageResource(R.drawable.round);
        }
        counter.animate().alpha(1).setDuration(300);
        for (int[] winningpos : winningpos) {
            if (gameState[winningpos[0]] == gameState[winningpos[1]] && gameState[winningpos[1]] == gameState[winningpos[2]] && gameState[winningpos[0]] != -1) {
                gameActive = false;
                winningText = findViewById(R.id.textView);
                playAgain = findViewById(R.id.textView2);
                ImageView crossImage = findViewById(R.id.imageCross);
                ImageView roundImage = findViewById(R.id.imageRound);
                if (activeplayer == 1){
                    crossImage.setVisibility(View.VISIBLE);
                }
                else {
                    roundImage.setVisibility(View.VISIBLE);

                }
                winningText.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }
            else {
                if(count == 9 && gameActive) {
                    gameActive = false;
                    playAgain = findViewById(R.id.textView2);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
        }
    }
    public void playAgain(View view){
        ImageView crossImage = findViewById(R.id.imageCross);
        ImageView roundImage = findViewById(R.id.imageRound);
        winningText = findViewById(R.id.textView);
        playAgain = findViewById(R.id.textView2);
        TextView drawText = findViewById(R.id.drawText);
        drawText.setVisibility(View.INVISIBLE);
        crossImage.setVisibility(View.INVISIBLE);
        roundImage.setVisibility(View.INVISIBLE);
        winningText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i =0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++)
            gameState[i] = -1;
        activeplayer = 0;
        count = 0;
        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.animate().alpha(1).setDuration(1000);
    }
}