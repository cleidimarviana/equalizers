package me.acklay.equilizers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.ack.Equalizers;

public class MainActivity extends AppCompatActivity{

    private Equalizers equalizers;
    private Button toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equalizers = (Equalizers) findViewById(R.id.equalizers);
        toggleButton = (Button) findViewById(R.id.toggleButton);
        showEqualizer();
        initButton();
    }

    private void initButton() {
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEqualizer();
            }
        });
    }

    private void toggleEqualizer() {
        if (equalizers.isAnimating()) {
            equalizers.stopAnimateBars();
        } else {
            equalizers.startAnimateBars();
        }
    }

    private void showEqualizer() {
        equalizers.startAnimateBars();
    }
}