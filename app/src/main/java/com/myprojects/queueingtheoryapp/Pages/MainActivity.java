package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.myprojects.queueingtheoryapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFirstCase = findViewById(R.id.buttonFirstCase);
        buttonFirstCase.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FirstCase_Lambda_GT_Mu.class));
        });

        Button buttonSecondCase = findViewById(R.id.buttonSecondCase);
        buttonSecondCase.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SecondCase_Lambda_LT_Mu.class));
        });

        Button buttonThirdCase = findViewById(R.id.buttonThirdCase);
        buttonThirdCase.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MM1.class));
        });

        Button buttonFourthCase = findViewById(R.id.buttonFourthCase);
        buttonFourthCase.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MM1K.class));
        });

        Button buttonFifthCase = findViewById(R.id.buttonFifthCase);
        buttonFifthCase.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MMc.class));
        });

        Button buttonSixthCase = findViewById(R.id.buttonSixthCase);
        buttonSixthCase.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MMcK.class));
        });
    }
}