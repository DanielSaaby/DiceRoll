package com.example.diceroll;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diceroll.Model.Dice;
import com.example.diceroll.Model.DiceGenerate;
import com.example.diceroll.Model.DiceHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    EditText numberDice;
    TextView txtOutput;
    ImageView testImageView;

    List<Dice> diceList;
    int[] imageRefs;

    ArrayList<DiceHistory> diceHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiceGenerate dc = new DiceGenerate();

        diceList = dc.getDiceList();

        Button rollBtn = this.findViewById(R.id.btnRoll);
        numberDice = this.findViewById(R.id.numberDice);
        numberDice.setCursorVisible(false);
        txtOutput = this.findViewById(R.id.txtOutput);

        imageRefs = new int[] {R.id.imageView1, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5, R.id.imageView6};
        resetDice();
        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetDice();

                try
                {
                    int valueSum = 0;
                    Date timestamp = new Date();
                    List<Dice> diceRolls = new ArrayList<>();
                    int numberOfDice = Integer.parseInt(numberDice.getText().toString().trim());

                    if(numberOfDice >= 1 && numberOfDice <=6)
                    {
                        for(int i = 1; i <= numberOfDice; i++)
                        {
                            int diceValue = generateDiceValue();
                            Dice correspondingDice = null;
                            valueSum = valueSum + diceValue;
                            for (Dice dice : diceList)
                            {
                                if(dice.getDiceValue() == diceValue)
                                {
                                    correspondingDice = dice;
                                    diceRolls.add(dice);
                                }
                            }

                            ImageView imageView = (ImageView) findViewById(imageRefs[i-1]);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageResource(correspondingDice.getImage());

                        }

                        DiceHistory rollInfo = new DiceHistory(valueSum, timestamp, diceRolls);
                        diceHistoryList.add(rollInfo);

                        txtOutput.setText(valueSum + "");
                        txtOutput.setTextColor(Color.parseColor("#000000"));


                    }
                    else
                    {
                        txtOutput.setText("Please Enter a number between 1 & 6");
                        txtOutput.setTextColor(Color.parseColor("#DC143C"));
                    }

                } catch (Exception ex)
                {

                }


            }
        });


        Button btnHistory = this.findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToHistory();
            }
        });






    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void onClickGoToHistory() {
        Intent x = new Intent(MainActivity.this, HistoryActivity.class);
        x.putExtra("history", diceHistoryList);
        startActivityForResult(x, 1);
    }


    private void resetDice() {

        for (int i = 0; i < imageRefs.length; i++)
        {
            ImageView imageView = (ImageView) findViewById(imageRefs[i]);
            imageView.setVisibility(View.INVISIBLE);
        }

    }

    private int generateDiceValue() {
        Random rng = new Random();
        int number = rng.nextInt(6)+1;
        return number;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    String newText = data.getStringExtra("shouldDelete");

                    if(newText.equals("yes"))
                    {
                        diceHistoryList.clear();
                    }
                }
                break;
            }
        }
    }
}
