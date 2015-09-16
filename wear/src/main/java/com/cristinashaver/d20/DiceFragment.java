package com.cristinashaver.d20;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.CardFrame;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceFragment extends Fragment
                          implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DICE_NOTATION = "diceNotation";
    private static final String ARG_DICE_IMAGE = "diceImage";
    private static final String ARG_ROW = "row";
    private static final String ARG_COL = "col";

    private String mDiceNotation;
    private int mDiceImage;
    private int mRow;
    private int mCol;

    private TextView mDiceValue;
    private CardFrame mRollTextCardFrame;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param diceNotation Dice Notation.
     * @return A new instance of fragment DiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiceFragment newInstance(String diceNotation, int diceImage, int row, int col) {
        DiceFragment fragment = new DiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DICE_NOTATION, diceNotation);
        args.putInt(ARG_DICE_IMAGE, diceImage);
        args.putInt(ARG_ROW, row);
        args.putInt(ARG_COL, col);
        fragment.setArguments(args);
        return fragment;
    }

    public DiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDiceNotation = getArguments().getString(ARG_DICE_NOTATION);
            mDiceImage = getArguments().getInt(ARG_DICE_IMAGE);
            mRow = getArguments().getInt(ARG_ROW);
            mCol = getArguments().getInt(ARG_COL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dice, container, false);

        mDiceValue = (TextView) view.findViewById(R.id.diceValue);
        mDiceValue.setText(mDiceNotation);

        mRollTextCardFrame = (CardFrame) view.findViewById(R.id.RollTextCardFrame);

        ImageButton diceButton = (ImageButton) view.findViewById(R.id.diceButton);
        diceButton.setBackground(getActivity().getDrawable(mDiceImage));
        diceButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View view) {
        Roll roll = ((MainActivity) getActivity()).rollDiceButton(mRow);
        displayRoll(roll.getValue());
        Log.d("ROLL", "" + roll.getValue());
    }

    private void displayRoll(int value) {
        mDiceValue.setText("" + value);
        mRollTextCardFrame.animate()
                .translationY(mRollTextCardFrame.getHeight())
                .setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
