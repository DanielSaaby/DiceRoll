package com.example.diceroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.diceroll.Model.Dice;
import com.example.diceroll.Model.DiceGenerate;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    EditText numberDice;
    TextView errTxt;
    ImageView testImageView;

    List<Dice> diceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiceGenerate dc = new DiceGenerate();

        diceList = dc.getDiceList();

        Dice testDice = diceList.get(1);
        testImageView = this.findViewById(R.id.testImageView);
        testImageView.setImageResource(testDice.getImage());




        Button rollBtn = this.findViewById(R.id.btnRoll);
        numberDice = this.findViewById(R.id.numberDice);
        errTxt = this.findViewById(R.id.txtError);

        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int valueSum;
                    int numberOfDice = Integer.parseInt(numberDice.getText().toString().trim());

                    if(numberOfDice >= 1 && numberOfDice <=6)
                    {
                        for(int i = 1; i <= numberOfDice; i++)
                        {
                            int diceValue = generateDiceValue();
                            Dice correspondingDice = null;

                            for (Dice dice : diceList)
                            {
                                if(dice.getDiceValue() == diceValue)
                                {
                                    correspondingDice = dice;
                                    Log.d("test", "onClick:"  + dice.getImage());
                                }
                            }

                            ImageView imageView = findViewById(getResources().getIdentifier("imageView" + i, "drawable", getPackageName()));
                            imageView.setImageResource(correspondingDice.getImage());
                        }
                    }

                } catch (Exception ex)
                {

                }


            }
        });






    }

    private int generateDiceValue() {
        Random rng = new Random();
        int number = rng.nextInt(6)+1;
        return number;
    }
}
