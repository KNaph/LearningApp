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


public class InputFragment extends AbstractFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final String defaultString = "Modify this text!";
    private final int defaultTextSize = 24;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String userWord;
    private Float userNumber;

    private EditText wordInput;
    private EditText numberInput;

    private Button wordButton;
    private Button numberButton;

    private TextView textDisplay;

    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_input, container, false);

        // Inflate the layout for this fragment
        wordInput = inflatedView.findViewById(R.id.wordInput);
        numberInput = inflatedView.findViewById(R.id.numberInput);
        wordButton = inflatedView.findViewById(R.id.wordButton);
        numberButton = inflatedView.findViewById(R.id.numberButton);
        textDisplay = inflatedView.findViewById(R.id.textDisplay);

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
