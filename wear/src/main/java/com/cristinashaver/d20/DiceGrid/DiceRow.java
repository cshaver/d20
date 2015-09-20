package com.cristinashaver.d20.DiceGrid;

import com.cristinashaver.d20.Util.Roll;

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

    public Roll rollDice() {
        mRollHistory.add(new Roll(mNumDice, mNumSides));
        return getLastRoll();
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

    public int getNumDice() {
        return mNumDice;
    }

    public int getDiceImage() {
        return mDiceImage;
    }

    public String getDiceNotation() {
        return mNumDice + "d" + mNumSides;
    }

    public String getInitialText() {
        if (mRollHistory.size() > 0) {
            return String.valueOf(getLastRoll().getValue());
        }
        else {
            return getSimpleDiceNotation();
        }
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
