package com.myprojects.queueingtheoryapp.Pages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojects.queueingtheoryapp.R;
import com.myprojects.queueingtheoryapp.casescalculation.CaseOne_Lambda_GT_Mu_Math;

public class FirstCase_Lambda_GT_Mu extends AppCompatActivity {
    // attributes
    private EditText lambdaValue;
    private EditText muValue;
    private EditText kValue;
    private TextView tiValue;
    private TextView tLTCaseOne;
    private TextView tBetweenCaseOne;
    private EditText tValue;
    private TextView nOftBetweenCaseOne;
    private TextView tGTtiCaseOne;
    private TextView nOftGTCaseOne;
    private TextView nLTBalkCaseOne;
    private TextView wqOfnLTBalkCaseOne;
    private TextView nGTBalkCaseOne;
    private TextView wqOfnGTBalkCaseOne;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_case_lambda_gt_mu);

        // attributes values
        lambdaValue = findViewById(R.id.lambdaValue);
        muValue = findViewById(R.id.muValue);
        kValue = findViewById(R.id.kValue);
        Button buttonSum1 = findViewById(R.id.buttonSum1);
        Button getChart = findViewById(R.id.getChart);
        tiValue = findViewById(R.id.tiValue);
        tLTCaseOne = findViewById(R.id.tLTCaseOne);
        tBetweenCaseOne = findViewById(R.id.tBetweenCaseOne);
        tValue = findViewById(R.id.tValue);
        nOftBetweenCaseOne = findViewById(R.id.nOftBetweenCaseOne);
        tGTtiCaseOne = findViewById(R.id.tGTtiCaseOne);
        nOftGTCaseOne = findViewById(R.id.nOftGTCaseOne);
        nLTBalkCaseOne = findViewById(R.id.nLTBalkCaseOne);
        wqOfnLTBalkCaseOne = findViewById(R.id.wqOfnLTBalkCaseOne);
        nGTBalkCaseOne = findViewById(R.id.nGTBalkCaseOne);
        wqOfnGTBalkCaseOne = findViewById(R.id.wqOfnGTBalkCaseOne);

        // Get Chart
        getChart.setOnClickListener(v -> {
            try {

                Intent intent = new Intent(FirstCase_Lambda_GT_Mu.this, Chart.class);
                intent.putExtra("Lambda", Double.parseDouble(lambdaValue.getText().toString()));
                intent.putExtra("Mu", Double.parseDouble(muValue.getText().toString()));
                intent.putExtra("K", Double.parseDouble(kValue.getText().toString()));

                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
            }
        });


        // When Clicking Calculate Button
        buttonSum1.setOnClickListener(v -> {
            try {

                double lambda = Double.parseDouble(lambdaValue.getText().toString());
                double mu = Double.parseDouble(muValue.getText().toString());
                double k = Double.parseDouble(kValue.getText().toString());
                CaseOne_Lambda_GT_Mu_Math acase = new CaseOne_Lambda_GT_Mu_Math(lambda, mu, k);
                if (lambda < mu || lambda == mu) {
                    Toast.makeText(getApplicationContext(), "Invalid! λ > μ not achieved", Toast.LENGTH_LONG).show();
                } else {
                    // Get the value of ti.......
                    int ti = acase.getTi();
                    tiValue.setText("ti = " + ti);

                    // Get the value of n(t)....
                    tLTCaseOne.setText("For t < 1/λ or t < " + (int) (1 / lambda));
                    tBetweenCaseOne.setText("For 1/λ <= t < ti or " + (int) (1 / lambda) + " <= t < " + ti);
                    if (tValue.getText().toString().length() == 0) {
                        nOftBetweenCaseOne.setText("n(t) = [λ*t] - [μ*t - μ/λ]");
                    } else if (Integer.parseInt(tValue.getText().toString()) >= (int) (1 / lambda) && Integer.parseInt(tValue.getText().toString()) < ti) {
                        acase.setT(Integer.parseInt(tValue.getText().toString()));
                        nOftBetweenCaseOne.setText("n(t) = " + acase.getNt());
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter a number between : " + (int) (1 / lambda) + " and " + ti, Toast.LENGTH_LONG).show();
                    }
                    tGTtiCaseOne.setText("For t >= ti or t >= " + ti);

                    if (Math.round(1 / mu) % Math.round(1 / lambda) == 0) {
                        nOftGTCaseOne.setText("n(t) = " + (int) (k - 1));
                    } else {
                        nOftGTCaseOne.setText("n(t) = " + (int) (k - 1) + " or " + (int) (k - 2));
                    }


                    // Get the Value of Wq(n).......
                    nLTBalkCaseOne.setText("For n < λti or n < " + (int) (lambda * ti));
                    wqOfnLTBalkCaseOne.setText(acase.getWqnLTString());
                    nGTBalkCaseOne.setText("For n >= λti or n >= " + (int) (lambda * ti));
                    wqOfnGTBalkCaseOne.setText(acase.getWqnGTString());

                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
            }
        });


    }
}