package com.example.uselessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;

    private ConstraintLayout constraintLayout;

    private int selfDestructCounter;


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    /**
     * Sets the listeners for the widgets
     */
    private void setListeners() {
        // TODO self destruct button
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelfDestructSequence();
            }
        });

        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    startSwitchOffTimer();
                    //Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startSelfDestructSequence() {
        // Disable the button
        buttonSelfDestruct.setEnabled(false);

        // Start a 10 second countdown timer that updates the display every second
        startSelfDestructTimer();

        // Want the button to show the countdown
        // Destruct in 10...
        // Destruct in 9...

        // At the end, we're going to close the activity
        // call the finish() method
    }

    private void startSelfDestructTimer() {
        selfDestructCounter = 1;
        new CountDownTimer(10000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

                //Log.d(TAG, "onTick: Ticked");
                if(selfDestructCounter % 2 == 0) {
                    buttonSelfDestruct.setText("Destruct in " + (millisUntilFinished + 1000) / 1000 + "...");
                    constraintLayout.setBackgroundColor(Color.rgb(255, 0, 0));
                }
                else {
                    constraintLayout.setBackgroundColor(Color.rgb(255, 255, 255));
                }

                selfDestructCounter++;

            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void startSwitchOffTimer() {
        new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(!switchUseless.isChecked()) {
                    //Log.d(TAG, "onTick: cancelling");
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                //Log.d(TAG, "onFinish: switch set to false");
            }
        }.start();
    }

    /**
     * Wires widgets
     */
    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
        constraintLayout = findViewById(R.id.activity_main);
    }
}
