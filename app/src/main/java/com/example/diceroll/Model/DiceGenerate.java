package com.example.diceroll.Model;

import com.example.diceroll.R;

import java.util.ArrayList;
import java.util.List;

public class DiceGenerate {


    private List<Dice> diceList = new ArrayList<>();

    public DiceGenerate() {

        diceList.add(new Dice(1, R.drawable.d1));
        diceList.add(new Dice(2, R.drawable.d2));
        diceList.add(new Dice(3, R.drawable.d3));
        diceList.add(new Dice(4, R.drawable.d4));
        diceList.add(new Dice(5, R.drawable.d5));
        diceList.add(new Dice(6, R.drawable.d6));
    }

    public List<Dice> getDiceList() {
        return diceList;
    }
}
