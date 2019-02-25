package com.example.diceroll.Model;

import android.graphics.drawable.Drawable;

import com.example.diceroll.R;

import java.io.Serializable;

public class Dice implements Serializable {

    private int DiceValue;
    private int ImageRef;

    public Dice(int value, int image) {

        this.DiceValue = value;
        this.ImageRef = image;

    }

    public int getDiceValue() {
        return DiceValue;
    }

    public int getImage() {
        return ImageRef;
    }
}
