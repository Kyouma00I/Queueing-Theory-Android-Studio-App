package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojects.queueingtheoryapp.R;
import com.myprojects.queueingtheoryapp.casescalculation.MMc_Math;

public class MMc extends AppCompatActivity {

    EditText lambdaValue;
    EditText muValue;
    EditText cValue;
    TextView lValue;
    TextView lqValue;
    TextView wValue;
    TextView wqValue;
    TextView p0Value;
    TextView pnValue;
    TextView ciValue;
    EditText nValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmc);

        lambdaValue = findViewById(R.id.lambdaValue);
        muValue = findViewById(R.id.muValue);
        cValue = findViewById(R.id.cValue);
        Button sumButton = findViewById(R.id.buttonSum3);
        lValue = findViewById(R.id.lValue);
        lqValue = findViewById(R.id.lqValue);
        wValue = findViewById(R.id.wValue);
        wqValue = findViewById(R.id.wqValue);
        p0Value = findViewById(R.id.p0Value);
        pnValue = findViewById(R.id.pnValue);
        ciValue = findViewById(R.id.ciValue);
        nValue = findViewById(R.id.nValue);


        sumButton.setOnClickListener(v -> {
            try {

                double lambda = Double.parseDouble(lambdaValue.getText().toString());
                double mu = Double.parseDouble(muValue.getText().toString());
                double c = Double.parseDouble(cValue.getText().toString());
                MMc_Math mmc = new MMc_Math(lambda, mu, c);

                lValue.setText("L = " + mmc.getL());
                lqValue.setText("Lq = " + mmc.getLq());
                wValue.setText("W = " + mmc.getW());
                wqValue.setText("Wq = " + mmc.getWq());
                p0Value.setText(("P(0) = " + mmc.getP0()));
                ciValue.setText("Ci = " + mmc.getCi());
                if (nValue.getText().toString().length() == 0) {
                    pnValue.setText("P(n) = ??");
                } else if (Double.parseDouble(nValue.getText().toString()) >= 0){
                    mmc.pnCalculation(Double.parseDouble(nValue.getText().toString()));
                    pnValue.setText("P(n) = " + mmc.getPn());
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number for n (0 or greater)", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
            }

        });


    }
}