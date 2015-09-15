package com.cristinashaver.d20;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.app.Fragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;

import java.util.List;

public class DiceGridPagerAdapter extends FragmentGridPagerAdapter {

    private static final float MAXIMUM_CARD_EXPANSION_FACTOR = 3.0f;

    private final Context mContext;
    private List<DiceRow> mDiceRows;

    public DiceGridPagerAdapter(Context ctx, List<DiceRow> diceRows, FragmentManager fm) {
        super(fm);
        mContext = ctx;
        mDiceRows = diceRows;
    }

    @Override
    public Fragment getFragment(int row, int col) {
        // important things

        DiceRow diceRow = mDiceRows.get(row);

        DiceFragment fragment = DiceFragment.newInstance(
                diceRow.getSimpleDiceNotation(),
                diceRow.getDiceImage());

        return fragment;
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return mContext.getResources().getDrawable(R.drawable.dice, null);
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
//        if (row == 2 && column == 1) {
//            return mContext.getResources().getDrawable(R.drawable.dice, null);
//        }
//        else {
            return GridPagerAdapter.BACKGROUND_NONE;
//        }
    }

    @Override
    public int getRowCount() {
        return mDiceRows.size();
    }

    @Override
    public int getColumnCount(int row) {
        return DiceRow.ROW_LENGTH;
    }

}