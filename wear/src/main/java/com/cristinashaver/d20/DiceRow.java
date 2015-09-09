package com.cristinashaver.d20;

import java.util.ArrayList;
import java.util.List;

public class DiceRow {
    public static final int ROW_LENGTH = 3;

    private int mNumSides;
    private int mNumDice;
    private List<Roll> mRollHistory;
    
    public DiceRow(int numSides, int numDice) {
        mNumSides = numSides;
        mNumDice = numDice;
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
}
