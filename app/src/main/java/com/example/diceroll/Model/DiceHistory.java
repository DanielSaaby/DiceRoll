package com.example.diceroll.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DiceHistory implements Serializable {
    private int DiceSum;
    private Date Time;
    private List<Dice> SavedRoll;

    public DiceHistory(int DiceSum, Date Time, List<Dice> savedRoll) {
        this.DiceSum = DiceSum;
        this.Time = Time;
        this.SavedRoll = savedRoll;
    }

    public int getDiceSum() {
        return DiceSum;
    }

    public void setDiceSum(int diceSum) {
        DiceSum = diceSum;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public List<Dice> getSavedRoll() {
        return SavedRoll;
    }

    public void setSavedRoll(List<Dice> savedRoll) {
        SavedRoll = savedRoll;
    }
}
