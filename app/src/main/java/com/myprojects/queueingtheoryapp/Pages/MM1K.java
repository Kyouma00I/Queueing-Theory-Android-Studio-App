package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojects.queueingtheoryapp.R;
import com.myprojects.queueingtheoryapp.casescalculation.MM1KK_Math;

public class MM1K extends AppCompatActivity implements View.OnClickListener {

    EditText lambdaValue;
    EditText muValue;
    EditText kValue;
    TextView lValue;
    TextView lqValue;
    TextView wValue;
    TextView wqValue;
    EditText nValue;
    TextView pnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm1_k);

        lambdaValue = findViewById(R.id.lambdaValue);
        muValue = findViewById(R.id.muValue);
        kValue = findViewById(R.id.kValue);
        Button buttonSum3 = (Button) findViewById(R.id.buttonSum3);
        buttonSum3.setOnClickListener(this);
        Button buttonSum3_2 = (Button) findViewById(R.id.buttonSum3_2);
        buttonSum3_2.setOnClickListener(this);
        lValue = findViewById(R.id.lValue);
        lqValue = findViewById(R.id.lqValue);
        wValue = findViewById(R.id.wValue);
        wqValue = findViewById(R.id.wqValue);
        nValue = findViewById(R.id.nValue);
        pnValue = findViewById(R.id.pnValue);


    }

    @Override
    public void onClick(View v) {
        try {

            switch (v.getId()) {

                case R.id.buttonSum3:
                case R.id.buttonSum3_2:

                    double lambda = Double.parseDouble(lambdaValue.getText().toString());
                    double mu = Double.parseDouble(muValue.getText().toString());
                    double k = Double.parseDouble(kValue.getText().toString());
                    MM1KK_Math mm1k = new MM1KK_Math(lambda, mu, k);

                    lValue.setText("L = " + mm1k.getL());
                    lqValue.setText("Lq = " + mm1k.getLq());
                    wValue.setText("W = " + mm1k.getW());
                    wqValue.setText("Wq = " + mm1k.getWq());

                    if (nValue.getText().toString().length() == 0) {
                        pnValue.setText("P(n) = ??");
                    } else {
                        double n = Double.parseDouble(nValue.getText().toString());
                        if (n == k) {
                            pnValue.setText("P(k) = " + mm1k.getPk());
                        } else {
                            mm1k.pnCalculation(n);
                            pnValue.setText("P(" + (int) n + ") = " + mm1k.getPn());
                        }
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
        }

    }
}