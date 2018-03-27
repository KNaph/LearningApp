package com.example.kylephan.learningapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class InputFragment extends AbstractFragment {

    private final String defaultString = "Modify this text!";
    private final int defaultTextSize = 24;

    private String userWord;
    private Float userNumber;

    @BindView(R.id.wordInput) EditText wordInput;
    @BindView(R.id.numberInput) EditText numberInput;
    @BindView(R.id.textDisplay) TextView textDisplay;

    private final int defaultAlpha = 255;
    private final int defaultColor = 0;

    private ColorPicker colorPicker;

    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance() {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         colorPicker = new ColorPicker(getActivity(), defaultAlpha, defaultColor, defaultColor, defaultColor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_input, container, false);

        ButterKnife.bind(this,inflatedView);

        // TESTING LISTENER ------------------------------------------------------
        super.setFragmentTitle("Input Fragment");
        super.setNavId(R.id.nav_first);

        // Restoring previous fragment text state
        if ((userWord != null) && (userNumber != null)) {
            textDisplay.setText(userWord);
            textDisplay.setTextSize(userNumber);
        } else if ((userWord != null) && (userNumber == null)) {
            textDisplay.setText(userWord);
            textDisplay.setTextSize(defaultTextSize);
        } else if ((userWord == null) && (userNumber != null)){
            textDisplay.setText(defaultString);
            textDisplay.setTextSize(userNumber);
        } else {
            textDisplay.setText(defaultString);
            textDisplay.setTextSize(defaultTextSize);
        }
        return inflatedView;
    }

    @OnClick(R.id.colorButton)
    public void onColorClick() {
        colorPicker.show();

        colorPicker.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int color) {
                textDisplay.setTextColor(color);
                colorPicker.hide();
            }
        });
    }

    @OnClick(R.id.wordButton)
    public void onWordClick() {
        if (wordInput.getText().length() != 0) {
            userWord = wordInput.getText().toString();
            textDisplay.setText(userWord);
            Toast.makeText(getContext(),"Hey stop changing the text!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Invalid text input", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.numberButton)
    public void onNumberClick() {
        if (!TextUtils.isEmpty((CharSequence) numberInput.getText().toString())) {
            userNumber = Float.valueOf(numberInput.getText().toString());
            textDisplay.setTextSize(userNumber);
            Toast.makeText(getContext(),"Hey stop changing the text!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Invalid number input", Toast.LENGTH_SHORT).show();
        }
    }
}
