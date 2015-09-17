package com.cristinashaver.d20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by owner on 9/16/2015.
 */
public class DiceHistoryAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mContext;
    private List<Roll> mRollHistory;

    public DiceHistoryAdapter(LayoutInflater context, List<Roll> rollHistory) {
        mContext = context;
        mRollHistory = rollHistory;
    }

    @Override
    public int getGroupCount() {
        return mRollHistory.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mRollHistory.get(groupPosition).getThrowCount();
    }

    @Override
    public Roll getGroup(int groupPosition) {
        return mRollHistory.get(groupPosition);
    }

    @Override
    public Throw getChild(int groupPosition, int childPosition) {
        return mRollHistory.get(groupPosition).getThrow(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Roll roll = mRollHistory.get(groupPosition);
        convertView = mContext.inflate(R.layout.dice_history_child, null);

        // do stuff to history child view from roll
        TextView item = (TextView) convertView.findViewById(R.id.item);
//        item.setText(roll.getValue());
        item.setText("hello");


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Roll roll = mRollHistory.get(groupPosition);
        Throw diceThrow = roll.getThrow(childPosition);
        convertView = mContext.inflate(R.layout.dice_history_child, null);

        // do stuff to history child view from roll
        TextView item = (TextView) convertView.findViewById(R.id.item);
//        item.setText(diceThrow.getValue());
        item.setText("child hello");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
