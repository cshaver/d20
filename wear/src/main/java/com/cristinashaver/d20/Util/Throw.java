package com.cristinashaver.d20.Util;

/**
 * Single Dice Roll
 */
public class Throw {
    private int mNumFaces;
    private int mValue;

    public Throw(int numFaces, int value) {
        mNumFaces = numFaces;
        mValue = value;
    }

    public int getNumFaces() {
        return mNumFaces;
    }

    public int getValue() {
        return mValue;
    }

    public String getValueText() {
        return String.valueOf(getValue());
    }
}
