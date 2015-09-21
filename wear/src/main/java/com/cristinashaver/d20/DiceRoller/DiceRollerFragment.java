package com.cristinashaver.d20.DiceRoller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.CardFrame;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cristinashaver.d20.MainActivity;
import com.cristinashaver.d20.R;
import com.cristinashaver.d20.Util.Roll;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiceRollerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiceRollerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceRollerFragment extends Fragment
                          implements View.OnClickListener, View.OnDragListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DICE_NOTATION = "diceNotation";
    private static final String ARG_DICE_IMAGE = "diceImage";
    private static final String ARG_DICE_TEXT = "diceText";
    private static final String ARG_ROW = "row";
    private static final String ARG_COL = "col";
    private static final String ARG_SHOW_CARD = "showCard";

    private String mDiceNotation;
    private int mDiceImage;
    private int mRow;
    private int mCol;
    private boolean mShowCard;
    private boolean mAnimationComplete = true;

    private String mDiceText;
    private TextView mDiceValue;
    private CardFrame mRollTextCardFrame;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param diceNotation Dice Notation.
     * @return A new instance of fragment DiceRollerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiceRollerFragment newInstance(String diceNotation, int diceImage, int row, int col, String initialDiceText, boolean showCard) {
        DiceRollerFragment fragment = new DiceRollerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DICE_NOTATION, diceNotation);
        args.putInt(ARG_DICE_IMAGE, diceImage);
        args.putString(ARG_DICE_TEXT, initialDiceText);
        args.putInt(ARG_ROW, row);
        args.putInt(ARG_COL, col);
        args.putBoolean(ARG_SHOW_CARD, showCard);
        fragment.setArguments(args);
        return fragment;
    }

    public DiceRollerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDiceNotation = getArguments().getString(ARG_DICE_NOTATION);
            mDiceNotation = getArguments().getString(ARG_DICE_TEXT);
            mDiceText = getArguments().getString(ARG_DICE_TEXT);
            mDiceImage = getArguments().getInt(ARG_DICE_IMAGE);
            mRow = getArguments().getInt(ARG_ROW);
            mCol = getArguments().getInt(ARG_COL);
            mShowCard = getArguments().getBoolean(ARG_SHOW_CARD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;

        // Inflate the layout for this fragment
        if (mShowCard) {
            view = inflater.inflate(R.layout.fragment_dice_roller, container, false);
            mRollTextCardFrame = (CardFrame) view.findViewById(R.id.RollTextCardFrame);
        }
        else {
            view = inflater.inflate(R.layout.fragment_dice_roller_without_card, container, false);
        }

        mDiceValue = (TextView) view.findViewById(R.id.diceValue);
        mDiceValue.setText(mDiceText);

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
        MainActivity activity = (MainActivity) getActivity();
        Roll roll = activity.rollDiceButton(mRow);
        mDiceText = roll.getValueText();
        displayRoll();
    }

    private void displayRoll() {
        final MainActivity activity = (MainActivity) getActivity();
        mDiceValue.setText(mDiceText);

        if (mShowCard) {
            mAnimationComplete = false;
            mRollTextCardFrame.animate()
                    .translationY(mRollTextCardFrame.getHeight())
//                    .setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                    .setDuration(getResources().getInteger(android.R.integer.config_longAnimTime))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mAnimationComplete = true;
                            activity.getDiceGridPagerAdapter().notifyDataSetChanged();
                        }
                    });
        }
        else {
            activity.getDiceGridPagerAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (!mAnimationComplete) {
            ((MainActivity) getActivity()).getDiceGridPagerAdapter().notifyDataSetChanged();;
        }
        return false;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
