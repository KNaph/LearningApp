package com.example.kylephan.learningapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InputFragment extends AbstractFragment {

    private final String defaultString = "Modify this text!";
    private final int defaultTextSize = 24;


    private String userWord;
    private Float userNumber;

    @BindView(R.id.wordInput) EditText wordInput;
    @BindView(R.id.numberInput) EditText numberInput;

    @BindView(R.id.wordButton) Button wordButton;
    @BindView(R.id.numberButton) Button numberButton;

    @BindView(R.id.textDisplay) TextView textDisplay;

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
        setButtonListeners();
        return inflatedView;
    }


    private void setButtonListeners() {
        wordButton.setOnClickListener(new View.OnClickListener()     {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (wordInput.getText().length() != 0) {
                    userWord = wordInput.getText().toString();
                    textDisplay.setText(userWord);
                } else {
                    Toast.makeText(getContext(), "Invalid text input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberButton.setOnClickListener(new View.OnClickListener()     {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty((CharSequence) numberInput.getText().toString())) {
                    userNumber = Float.valueOf(numberInput.getText().toString());
                    textDisplay.setTextSize(userNumber);
                } else {
                    Toast.makeText(getContext(), "Invalid number input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
