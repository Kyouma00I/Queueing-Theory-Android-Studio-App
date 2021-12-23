package com.myprojects.queueingtheoryapp.casescalculation;

public class CaseTwo_Lambda_LT_Mu_Math {
    private double M;
    private double lambda;
    private double mu;
    private double k;
    private double ti;
    private int t;
    private int nt;
    private double wqZero;
    private String wqOfn;


    public CaseTwo_Lambda_LT_Mu_Math(double M, double lambda, double mu, double k) {
        this.M = M;
        this.lambda = lambda;
        this.mu = mu;
        this.k = k;
    }

    private void tiValueCalculation() {
        int a = (int) (M / (mu - lambda));
        int lambdainv = (int) (1 / (lambda));
        for (ti = a; ti > 0; ) {
            if ((int) (mu * ti) - (int) (lambda * ti) == M) {
                ti = ti - lambdainv;
            } else {
                ti = ti + lambdainv;
                break;
            }
        }
    }

    public double getTi() {
        this.tiValueCalculation();
        return ti;
    }

    public void setT(int t) {
        this.t = t;
    }

    private void nOftLTtiCalculation() {
        nt = (int) M + (int) Math.round(lambda * t) - (int) Math.round(mu * t);
    }

    public int getNt() {
        this.nOftLTtiCalculation();
        return nt;
    }

    public int getT() {
        return t;
    }

    private void wqZeroCaseTwo() {
        wqZero = (M - 1) / (2 * mu);
    }

    public double getWqZero() {
        this.wqZeroCaseTwo();
        return wqZero;
    }

    private void wqOfnCaseTow() {
        if (Math.round((1 / mu) - (1 / lambda)) < 0) {
            wqOfn = "Wq(n) = " + (int) Math.round(1 / mu * M) + " " + (int) Math.round((1 / mu) - (1 / lambda)) + "n";
        } else {
            wqOfn = "Wq(n) " + (int) Math.round(1 / mu * M) + " + " + (int) Math.round((1 / mu) - (1 / lambda)) + "n";
        }
    }

    public String getWqOfn() {
        this.wqOfnCaseTow();
        return wqOfn;
    }
}
