package com.cristinashaver.d20.Util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roll {
    private List<Throw> mThrows = new ArrayList<>();
    private Random rand = new Random();

    /**
     *  rolls numDice d numFaces dice
     *  ex Roll(4, 10) is 4d10
     */
    public Roll(int numDice, int numFaces) {
        for (int i = 0; i < numDice; i++) {
            mThrows.add(rollDie(numFaces));
        }
    }

    public Roll(int[] numFaces) {
        for (int faces : numFaces) {
            mThrows.add(rollDie(faces));
        }
    }

    private Throw rollDie(int numFaces) {
        int value = rand.nextInt(numFaces) + 1;
        return new Throw(numFaces, value);
    }

    public int getValue() {
        int total = 0;
        for (Throw dieThrow : mThrows) {
            total += dieThrow.getValue();
        }
        return total;
    }

    public String getAllThrowsText() {
        String text = "";

        for (int i = 0; i < mThrows.size(); i++) {
            text += mThrows.get(i).getValueText();
            if (i < mThrows.size() - 1) {
                text += ", ";
            }
        }

        return text;
    }

    public String getValueText() {
        return String.valueOf(getValue());
    }

    public int getThrowCount() {
        return mThrows.size();
    }

    public Throw getThrow(int index) {
        return mThrows.get(index);
    }

    public int getThrowValue(int index) {
        return mThrows.get(index).getValue();
    }
}
