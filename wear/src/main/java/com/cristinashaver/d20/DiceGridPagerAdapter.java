package com.cristinashaver.d20;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.app.Fragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;

import java.util.List;

public class DiceGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List<DiceRow> mDiceRows;

    public DiceGridPagerAdapter(Context ctx, List<DiceRow> diceRows, FragmentManager fm) {
        super(fm);
        mContext = ctx;
        mDiceRows = diceRows;
    }

    @Override
    public Fragment getFragment(int row, int col) {
        DiceRow diceRow = mDiceRows.get(row);

        switch (col) {
            case 1:
                return getDiceHistoryFragment(diceRow, row, col);
            case 2:
                return getDiceOptionsFragment(diceRow, row, col);
            default:
                return getDiceFragment(diceRow, row, col);
        }
    }

    private DiceFragment getDiceFragment(DiceRow diceRow, int row, int col) {
        return DiceFragment.newInstance(
                diceRow.getSimpleDiceNotation(),
                diceRow.getDiceImage(),
                row,
                col);
    }

    private DiceHistoryFragment getDiceHistoryFragment(DiceRow diceRow, int row, int col) {
        return DiceHistoryFragment.newInstance(
                diceRow.getSimpleDiceNotation(),
                row,
                col);
    }

    private DiceFragment getDiceOptionsFragment(DiceRow diceRow, int row, int col) {
        return DiceFragment.newInstance(
                diceRow.getSimpleDiceNotation(),
                diceRow.getDiceImage(),
                row,
                col);
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return mContext.getResources().getDrawable(R.drawable.dice, null);
    }

    @Override
    public Drawable getBackgroundForPage(int row, int col) {
//        if (col == 1) {
////            return mContext.getDrawable(R.color.semitransparent_grey).setAlpha(100);
//            Drawable drawable = mContext.getDrawable(R.drawable.dice);
//            drawable.setAlpha(100);
//            return drawable;
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