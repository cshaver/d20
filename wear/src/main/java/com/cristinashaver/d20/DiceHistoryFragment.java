package com.cristinashaver.d20;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.CardFrame;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiceHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiceHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceHistoryFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DICE_NOTATION = "diceNotation";
    private static final String ARG_ROW = "row";
    private static final String ARG_COL = "col";

    private String mDiceNotation;
    private int mRow;
    private int mCol;

    private TextView mDiceValue;
    private CardFrame mRollTextCardFrame;

    private List<Roll> mRollHistory;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param diceNotation Dice Notation.
     * @return A new instance of fragment DiceHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiceHistoryFragment newInstance(String diceNotation, int row, int col) {
        DiceHistoryFragment fragment = new DiceHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DICE_NOTATION, diceNotation);
        args.putInt(ARG_ROW, row);
        args.putInt(ARG_COL, col);
        fragment.setArguments(args);
        return fragment;
    }

    public DiceHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDiceNotation = getArguments().getString(ARG_DICE_NOTATION);
            mRow = getArguments().getInt(ARG_ROW);
            mCol = getArguments().getInt(ARG_COL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dice_history, container, false);

        fetchRollHistory();

        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.list);

        listView.setAdapter((new DiceHistoryAdapter(inflater, mRollHistory)));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void fetchRollHistory() {
        mRollHistory = ((MainActivity) getActivity()).getRollHistory(mRow);
    }

}
