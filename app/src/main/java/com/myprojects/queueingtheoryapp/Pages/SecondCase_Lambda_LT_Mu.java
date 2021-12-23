package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojects.queueingtheoryapp.R;
import com.myprojects.queueingtheoryapp.casescalculation.CaseTwo_Lambda_LT_Mu_Math;

public class SecondCase_Lambda_LT_Mu extends AppCompatActivity {

    private EditText mValue;
    private EditText lambdaValue;
    private EditText muValue;
    private EditText kValue;
    private TextView tiValue;
    private TextView tLTtiCaseTwo;
    private TextView nOftLTCaseTwo;
    private TextView tGTtiCaseOne;
    private TextView wqOfnAtZeroCaseTwo;
    private TextView nLTBalkCaseOne;
    private TextView nOftLTBalckCase2;
    private TextView nGTBalkCaseOne;
    private EditText tValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_case_lambda_lt_mu);

        mValue = findViewById(R.id.mValue);
        lambdaValue = findViewById(R.id.lambdaValue);
        muValue = findViewById(R.id.muValue);
        kValue = findViewById(R.id.kValue);
        Button buttonSum2 = findViewById(R.id.buttonSum2);
        Button getChart = findViewById(R.id.getChart);
        tiValue = findViewById(R.id.tiValue);
        tLTtiCaseTwo = findViewById(R.id.tLTtiCaseTwo);
        nOftLTCaseTwo = findViewById(R.id.nOftLTCaseTwo);
        tGTtiCaseOne = findViewById(R.id.tGTtiCaseOne);
        wqOfnAtZeroCaseTwo = findViewById(R.id.wqOfnAtZeroCaseTwo);
        nLTBalkCaseOne = findViewById(R.id.nLTBalkCaseOne);
        nOftLTBalckCase2 = findViewById(R.id.nOftLTBalckCase2);
        nGTBalkCaseOne = findViewById(R.id.nGTBalkCaseOne);
        tValue = findViewById(R.id.tValue);

        // Get Chart
        getChart.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(SecondCase_Lambda_LT_Mu.this, Chart.class);
                intent.putExtra("Lambda", Double.parseDouble(lambdaValue.getText().toString()));
                intent.putExtra("Mu", Double.parseDouble(muValue.getText().toString()));
                intent.putExtra("K", Double.parseDouble(kValue.getText().toString()));
                intent.putExtra("M", Double.parseDouble(mValue.getText().toString()));

                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
            }
        });

        // When Clicking Calculate Button
        buttonSum2.setOnClickListener(v -> {
            try {


                double M = Double.parseDouble(mValue.getText().toString());
                double lambda = Double.parseDouble(lambdaValue.getText().toString());
                double mu = Double.parseDouble(muValue.getText().toString());
                double k = Double.parseDouble(kValue.getText().toString());
                CaseTwo_Lambda_LT_Mu_Math bcase = new CaseTwo_Lambda_LT_Mu_Math(M, lambda, mu, k);

                if (lambda > mu) {
                    Toast.makeText(getApplicationContext(), "Invalid! λ < μ not achieved", Toast.LENGTH_LONG).show();
                } else {
                    // Get the value of ti.......
                    int ti = (int) bcase.getTi();
                    tiValue.setText("ti = " + ti);
                    // Get the Value of n(t)
                    tLTtiCaseTwo.setText("For t < ti or t < " + ti);
                    if (tValue.getText().toString().length() == 0) {
                        nOftLTCaseTwo.setText("n(t) = M + [λt] - [μt}");
                    } else if (Integer.parseInt(tValue.getText().toString()) < ti && Integer.parseInt(tValue.getText().toString()) >= 0) {
                        bcase.setT(Integer.parseInt(tValue.getText().toString()));
                        nOftLTCaseTwo.setText("n(" + bcase.getT() + ") = " + bcase.getNt());
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter a number between 0 and " + ti + " (0 include)", Toast.LENGTH_LONG).show();
                    }
                    tGTtiCaseOne.setText("For t >= ti or t >= " + ti);

                    //Get the value of Wq(n)
                    wqOfnAtZeroCaseTwo.setText("Wq(0) = " + (int) bcase.getWqZero());
                    nLTBalkCaseOne.setText("For n < λti or n < " + ti);
                    nOftLTBalckCase2.setText(bcase.getWqOfn());
                    nGTBalkCaseOne.setText("For n >= λti or n >= " + ti);
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please Enter valid numbers!", Toast.LENGTH_LONG).show();
            }
        });


    }
}