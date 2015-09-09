package com.cristinashaver.d20;

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
}
