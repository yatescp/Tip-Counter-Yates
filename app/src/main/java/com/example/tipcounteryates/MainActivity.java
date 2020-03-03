package com.example.tipcounteryates;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View.OnKeyListener;

public class MainActivity extends AppCompatActivity {

    private TextView textEnterTipPercent, textCalculatedCheckTotal, textCalculatedTipTotal, textCalculatedTipPerPerson;
    private EditText inputCheckAmount, inputNumPeople, inputTipPercent;
    private RadioButton tip15, tip18, tip20, tipCustom;
    private RadioGroup radioGroup;

    private float tipPercent, checkAmount, checkTotal, tipPerPerson, tipTotal;
    private int numPeople;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputCheckAmount = findViewById(R.id.inputCheckAmount);
        inputNumPeople = findViewById(R.id.inputNumPeople);
        inputTipPercent = findViewById(R.id.inputTipPercent);
        textCalculatedCheckTotal = findViewById(R.id.textCalculatedCheckTotal);
        textCalculatedTipTotal = findViewById(R.id.textCalculatedTipTotal);
        textCalculatedTipPerPerson = findViewById(R.id.textCalculatedTipPerPerson);
        textEnterTipPercent = findViewById(R.id.textEnterTipPercent);

        tip15 = findViewById(R.id.tip15);
        tip18 = findViewById(R.id.tip18);
        tip20 = findViewById(R.id.tip20);
        tipCustom = findViewById(R.id.tipCustom);

        radioGroup = findViewById(R.id.radioGroup);

        inputCheckAmount.setOnKeyListener(mKeyListener);
        inputNumPeople.setOnKeyListener(mKeyListener);
        inputTipPercent.setOnKeyListener(mKeyListener);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Either way we do it, we need to grab the view to get the name
                RadioButton buttonView = group.findViewById(checkedId);

                // You can use this code to get the index if you need it
                int checkedIndex = group.indexOfChild(buttonView);

                showErrorAlert("Tip Percentage can't be more than 100", R.id.inputTipPercent);

                switch (checkedIndex) {
                case R.id.tip15:
                    //uncheck other radio buttons
                    showErrorAlert("Tip Percentage can't be more than 100", R.id.inputTipPercent);
                    tip18.setChecked(false);
                    tip20.setChecked(false);
                    tipCustom.setChecked(false);
                    break;
                case R.id.tip18:
                    //uncheck other radio buttons
                    tip15.setChecked(false);
                    tip20.setChecked(false);
                    tipCustom.setChecked(false);
                    break;
                case R.id.tip20:
                    //uncheck other radio buttons
                    tip15.setChecked(false);
                    tip18.setChecked(false);
                    tipCustom.setChecked(false);
                    break;
                case R.id.tipCustom:
                    //uncheck other radio buttons
                    tip15.setChecked(false);
                    tip18.setChecked(false);
                    tip20.setChecked(false);
                    break;
            }

            }
        });

    }

    private View.OnKeyListener mKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            switch (v.getId()) {
                case R.id.inputCheckAmount:

                    //Check that the user pressed ENTER
                    if (keyCode == KeyEvent.KEYCODE_ENTER){

                        //Ensure that the user actually input data
                        if (!inputCheckAmount.getText().equals("")){

                            //save the user input into a temporary variable
                            float temp = Float.parseFloat(inputCheckAmount.getText().toString());

                            //Check that the value is greater than 1
                            if (temp < 1.0){
                                showErrorAlert("Check Amount can't be less than $1.00", R.id.inputCheckAmount);
                            }
                            else{
                                checkAmount = temp;
                            }
                        }
                    }
                    break;
                case R.id.inputNumPeople:

                    //Check that the user pressed ENTER
                    if (keyCode == KeyEvent.KEYCODE_ENTER){

                        //Ensure that the user actually input data
                        if (!inputNumPeople.getText().equals("")){

                            //save the user input into a temporary variable
                            int temp = Integer.parseInt(inputCheckAmount.getText().toString());

                            //Check that the value is greater than or equal to 1
                            if (temp < 1){
                                showErrorAlert("Number of People can't be less than 1", R.id.inputNumPeople);
                            }
                            else{
                                numPeople = temp;
                            }
                        }
                    }
                    break;
                case R.id.inputTipPercent:

                    //Check that the user pressed ENTER
                    if (keyCode == KeyEvent.KEYCODE_ENTER){

                        //Ensure that the user actually input data
                        if (!inputTipPercent.getText().equals("")){

                            //save the user input into a temporary variable
                            float temp = Float.parseFloat(inputCheckAmount.getText().toString());

                            //Check that the value is greater than or equal to 1
                            if (temp < 1.0){
                                showErrorAlert("Tip Percentage can't be less than 1", R.id.inputTipPercent);
                            }
                            //Check that the value is less than or equal to 100
                            else if (temp >= 100.0){
                                showErrorAlert("Tip Percentage can't be more than 100", R.id.inputTipPercent);
                            }
                            else{
                                tipPercent = temp;
                            }
                        }
                    }
                    break;
            }
            return false;
        }

    };
    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }
}
