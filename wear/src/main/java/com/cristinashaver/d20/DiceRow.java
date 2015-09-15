package com.cristinashaver.d20;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class DiceRow {
    public static final int ROW_LENGTH = 3;

    private int mNumSides;
    private int mNumDice;
    private int mDiceImage;
    private List<Roll> mRollHistory;
    
    public DiceRow(int numDice, int numSides, int diceImage) {
        mNumSides = numSides;
        mNumDice = numDice;
        mDiceImage = diceImage;
        mRollHistory = new ArrayList<>();
    }

    public void rollDice() {
        mRollHistory.add(new Roll(mNumDice, mNumSides));
    }

    public List<Roll> getRollHistory() {
        return mRollHistory;
    }

    public Roll getLastRoll() {
        return mRollHistory.get(mRollHistory.size() - 1);
    }

    public int getNumSides() {
        return mNumSides;
    }

    public int getNumDice() { return mNumDice; }

    public int getDiceImage() { return mDiceImage; }

    public String getDiceNotation() {
        return mNumDice + "d" + mNumSides;
    }

    public String getSimpleDiceNotation() {
        if (mNumDice == 1) {
            return "d" + mNumSides;
        }
        else {
            return getDiceNotation();
        }
    }
}
