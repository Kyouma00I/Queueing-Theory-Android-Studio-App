package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojects.queueingtheoryapp.R;
import com.myprojects.queueingtheoryapp.casescalculation.MM1_Math;

public class MM1 extends AppCompatActivity {

    EditText lambdaValue3;
    EditText muValue3;
    TextView lValue;
    TextView lqValue;
    TextView wValue;
    TextView wqValue;
    EditText nValue;
    TextView pnValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm1);

        lambdaValue3 = findViewById(R.id.lambdaValue3);
        muValue3 = findViewById(R.id.muValue3);
        lValue = findViewById(R.id.lValue);
        lqValue = findViewById(R.id.lqValue);
        wValue = findViewById(R.id.wValue);
        wqValue = findViewById(R.id.wqValue);
        nValue = findViewById(R.id.nValue);
        pnValue = findViewById(R.id.pnValue);

        Button buttonSum3 = findViewById(R.id.buttonSum3);


        buttonSum3.setOnClickListener(v -> {
            try {


                double lambda = Double.parseDouble(lambdaValue3.getText().toString());
                double mu = Double.parseDouble(muValue3.getText().toString());
                MM1_Math mm1 = new MM1_Math(lambda, mu);

                lValue.setText("L = " + mm1.getL());
                lqValue.setText("Lq = " + mm1.getLq());
                wValue.setText("W = " + mm1.getW());
                wqValue.setText("Wq = " + mm1.getWq());

                if (nValue.getText().toString().length() == 0){
                    pnValue.setText("P(n) = ??");
                } else {
                    int n = Integer.parseInt(nValue.getText().toString());
                    mm1.pn(n);
                    pnValue.setText("P(n) = " + mm1.getPnString() + " = "+mm1.getPnPrc()+"%");
                }


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
            }
        });


    }
}