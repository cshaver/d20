package com.cristinashaver.d20;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<DiceRow> mDiceRows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                layoutInflated(stub);
            }
        });
    }

    private void layoutInflated(WatchViewStub stub) {
        setupDicePages();
        setupGridViewPager();
    }

    private void setupDicePages() {
        mDiceRows.add(new DiceRow(1, 4, R.drawable.d4));
        mDiceRows.add(new DiceRow(1, 6, R.drawable.d6));
        mDiceRows.add(new DiceRow(1, 8, R.drawable.d8));
        mDiceRows.add(new DiceRow(1, 10, R.drawable.d10));
        mDiceRows.add(new DiceRow(1, 12, R.drawable.d12));
        mDiceRows.add(new DiceRow(1, 20, R.drawable.d20));
    }

    private void setupGridViewPager() {
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new DiceGridPagerAdapter(this, mDiceRows, getFragmentManager()));
    }

    public Roll rollDiceButton(int row) {
        DiceRow diceRow = mDiceRows.get(row);

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 500, 50, 300};
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);

        return diceRow.rollDice();
    }

    public List<Roll> getRollHistory(int row) {
        DiceRow diceRow = mDiceRows.get(row);
        return diceRow.getRollHistory();
    }
}
