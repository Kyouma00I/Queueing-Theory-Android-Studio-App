package com.myprojects.queueingtheoryapp.casescalculation;

import java.text.DecimalFormat;

public class MM1_Math {

    double lambda;
    double mu;
    double rho;
    double pn;
    double p0;
    double l;
    double lq;
    double w;
    double wq;

    public MM1_Math(double lambda, double mu) {
        this.lambda = lambda;
        this.mu = mu;
    }

    DecimalFormat df = new DecimalFormat("0.####");

    private void wCalculation() {
        w = 1 / (mu - lambda);
    }

    private void wqCalculation() {
        wq = lambda / ((mu) * (mu - lambda));
    }

    private void lCalculation() {
        this.wCalculation();
        l = lambda * w;
    }

    private void lqCalculation() {
        this.wqCalculation();
        lq = lambda * wq;
    }

    private void rhoCalculation() {
        rho = lambda / mu;
    }

    private void p0Calculation() {
        p0 = 1 - rho;
    }


    public void pn(int n) {
        this.rhoCalculation();
        this.p0Calculation();
        pn = Math.pow(rho, n) * p0;
    }

    public String getPnString() {
        return df.format(pn);
    }

    public String getPnPrc() {
        double prc = pn * 100;
        return df.format(prc);
    }

    public String getL() {
        this.lCalculation();
        return df.format(l);
    }

    public String getLq() {
        this.lqCalculation();
        return df.format(lq);
    }

    public String getW() {
        this.wCalculation();
        return df.format(w);
    }

    public String getWq() {
        this.wqCalculation();
        return df.format(wq);
    }
}
