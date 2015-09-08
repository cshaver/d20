package com.cristinashaver.d20;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DiceGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List mRows;

    public DiceGridPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    static final int[] BG_IMAGES = new int[] {
            R.drawable.debug_background_1,
            R.drawable.debug_background_2,
            R.drawable.debug_background_3,
            R.drawable.debug_background_4,
            R.drawable.debug_background_5
    };

    private static class Page {
        int titleRes;
        int textRes;
        int iconRes;
        boolean expansionEnabled;
        int cardGravity;
        int expansionDirection;
        int expansionFactor;
    }

    private final Page[][] PAGES = {

    };

    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];

        String title = page.titleRes != 0 ? mContext.getString(page.titleRes) : null;
        String text  = page.textRes  != 0 ? mContext.getString(page.textRes)  : null;

        CardFragment fragment = CardFragment.create(title, text, page.iconRes);

        fragment.setCardGravity(page.cardGravity);
        fragment.setExpansionEnabled(page.expansionEnabled);
        fragment.setExpansionDirection(page.expansionDirection);
        fragment.setExpansionFactor(page.expansionFactor);
        return fragment;
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return mContext.getResources().getDrawable(
                (BG_IMAGES[row % BG_IMAGES.length]), null);
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        if (row == 2 && column == 1) {
            return mContext.getResources().getDrawable(R.drawable.bugdroid_large, null);
        }
        else {
            return GridPagerAdapter.BACKGROUND_NONE;
        }
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int row) {
        return PAGES[row].length;
    }

}
