package com.myprojects.queueingtheoryapp.casescalculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class CaseOne_Lambda_GT_Mu_Math {
    private double k;
    private double lambda;
    private double mu;
    private int ti;
    private int nt;
    private int t;
    private double wqn;
    private String wqnLTString;
    private int wqnGT1;
    private int wqnGT2;
    private String wqnGTString;


    public CaseOne_Lambda_GT_Mu_Math(double lambda, double mu, double k) {
        this.lambda = lambda;
        this.mu = mu;
        this.k = k;

    }

    private void tiValueCalculation() {
        int a = (int) ((k - (mu / lambda)) / (lambda - mu));
        int lambdainv = (int) (1 / (lambda));
        for (ti = a; ti > 0; ) {
            if ((int) (lambda * ti) - (int) ((mu * ti) - (mu / lambda)) == k) {
                ti = ti - lambdainv;
            } else {
                ti = ti + lambdainv;
                break;
            }
        }
    }

    public int getTi() {
        this.tiValueCalculation();
        return ti;
    }

    private void nOftBetween() {
        nt = (int) (lambda * t) - (int) (mu * t - mu / lambda);
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getNt() {
        this.nOftBetween();
        return (int) (nt);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void wqnLTBalkCalculation() {
        ArrayList<String> ar = new ArrayList<>();
        for (int n = 1; n < lambda * ti; n++) {
            wqn = (1 / mu - 1 / lambda) * (n - 1);
            int wqnr = (int) Math.round(wqn);
            String x = "Wq(" + n + ") = " + wqnr;
            ar.add(x);
        }
        wqnLTString = String.join(", ", ar);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getWqnLTString() {
        this.wqnLTBalkCalculation();
        return wqnLTString;
    }

    private void wqnGTBalkCalculation() {
        wqnGT1 = (int) Math.round((1 / mu - 1 / lambda) * ((lambda * ti) - 2));
        wqnGT2 = (int) Math.round((1 / mu - 1 / lambda) * ((lambda * ti) - 3));
        if (Math.round(1 / mu) % Math.round(1 / lambda) == 0) {
            wqnGTString = "Wq(n) = " + wqnGT1;
        } else {
            wqnGTString = "Wq(n) = " + wqnGT1 + " or " + wqnGT2;
        }
    }

    public String getWqnGTString() {
        this.wqnGTBalkCalculation();
        return wqnGTString;
    }
}
