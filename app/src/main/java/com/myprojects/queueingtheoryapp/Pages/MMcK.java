package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojects.queueingtheoryapp.R;
import com.myprojects.queueingtheoryapp.casescalculation.MMcK_Math;
import com.myprojects.queueingtheoryapp.casescalculation.MMc_Math;

public class MMcK extends AppCompatActivity {

    EditText lambdaValue;
    EditText muValue;
    EditText cValue;
    EditText kValue;
    TextView lValue;
    TextView lqValue;
    TextView wValue;
    TextView wqValue;
    TextView p0Value;
    TextView pnValue;
    EditText nValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmc_k);

        lambdaValue = findViewById(R.id.lambdaValue);
        muValue = findViewById(R.id.muValue);
        cValue = findViewById(R.id.cValue);
        kValue = findViewById(R.id.kValue);
        Button sumButton = findViewById(R.id.buttonSum3);
        lValue = findViewById(R.id.lValue);
        lqValue = findViewById(R.id.lqValue);
        wValue = findViewById(R.id.wValue);
        wqValue = findViewById(R.id.wqValue);
        p0Value = findViewById(R.id.p0Value);
        pnValue = findViewById(R.id.pnValue);
        nValue = findViewById(R.id.nValue);


        sumButton.setOnClickListener(v -> {

            try {

                double lambda = Double.parseDouble(lambdaValue.getText().toString());
                double mu = Double.parseDouble(muValue.getText().toString());
                double c = Double.parseDouble(cValue.getText().toString());
                double k = Double.parseDouble(kValue.getText().toString());
                MMcK_Math mmck = new MMcK_Math(lambda, mu, c, k);

                lValue.setText("L = " + mmck.getL());
                lqValue.setText("Lq = " + mmck.getLq());
                wValue.setText("W = " + mmck.getW());
                wqValue.setText("Wq = " + mmck.getWq());
                p0Value.setText(("P(0) = " + mmck.getP0()));
                if (nValue.getText().toString().length() == 0) {
                    pnValue.setText("P(n) = ??");
                } else if (Double.parseDouble(nValue.getText().toString()) >= 0 && Double.parseDouble(nValue.getText().toString()) <= k) {
                    mmck.pnCalculation(Double.parseDouble(nValue.getText().toString()));
                    pnValue.setText("P(n) = " + mmck.getPn());
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number for n (0 <= n < K)", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
            }
        });
    }
}