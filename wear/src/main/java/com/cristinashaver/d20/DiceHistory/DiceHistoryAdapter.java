package com.cristinashaver.d20.DiceHistory;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.cristinashaver.d20.R;
import com.cristinashaver.d20.Util.Roll;

import java.util.List;

public class DiceHistoryAdapter implements ListAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Roll> mRollHistory;

    public DiceHistoryAdapter(Context context, LayoutInflater inflater, List<Roll> rollHistory) {
        mContext = context;
        mInflater = inflater;
        mRollHistory = rollHistory;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mRollHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return mRollHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Roll roll = mRollHistory.get(position);
        String allThrowsText = roll.getAllThrowsText();
        String valueText = roll.getValueText();

        // if roll was complex (multiple die), show value and sub throws
        if (allThrowsText.length() > 0 && !allThrowsText.equals(valueText)) {
            convertView = mInflater.inflate(R.layout.dice_history_child_with_subitem, null);

            TextView subItem = (TextView) convertView.findViewById(R.id.subItem);
            subItem.setText(allThrowsText);
        }
        // roll was 1 die, only show value
        else {
            convertView = mInflater.inflate(R.layout.dice_history_child, null);
        }

        // do stuff to history child view from roll
        TextView item = (TextView) convertView.findViewById(R.id.item);
        item.setText(roll.getValueText());

        if (position == mRollHistory.size() - 1) {
            item.setPadding(0, 0, 0, 100);
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
