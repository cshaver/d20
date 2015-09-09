package com.cristinashaver.d20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                setupDicePages();
                setupGridViewPager();
            }
        });
    }

    private void setupDicePages() {
        mDiceRows.add(new DiceRow(1, 4));
        mDiceRows.add(new DiceRow(1, 6));
        mDiceRows.add(new DiceRow(1, 8));
        mDiceRows.add(new DiceRow(1, 10));
        mDiceRows.add(new DiceRow(1, 12));
        mDiceRows.add(new DiceRow(1, 20));
    }

    private void setupGridViewPager() {
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new DiceGridPagerAdapter(this, mDiceRows, getFragmentManager()));
    }
}
