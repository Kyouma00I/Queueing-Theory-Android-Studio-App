package com.myprojects.queueingtheoryapp.casescalculation;

import java.text.DecimalFormat;

public class MMc_Math {

    private double lambda;
    private double mu;
    private double c;
    private double r;
    private double rho;
    private double pn;
    private double p0;
    private double l;
    private double lq;
    private double w;
    private double wq;
    private double ci;

    public MMc_Math(double lambda, double mu, double c) {
        this.lambda = lambda;
        this.mu = mu;
        this.c = c;
    }


    DecimalFormat df = new DecimalFormat("0.####");

    private void rCalculation() {
        r = lambda / mu;
    }

    private void rhoCalculation() {
        rCalculation();
        rho = r / c;
    }


    private void p0Calculation() {
        this.rhoCalculation();
        double p0_inv;
        double sum = 0;
        if (rho < 1) {
            for (int n = 0; n <= c - 1; n++) {
                sum += Math.pow(r, n) / Factorial.fact(n);
            }
            p0_inv = sum + ((c * Math.pow(r, c)) / (Factorial.fact(c) * (c - r)));
            p0 = Math.pow(p0_inv, -1);
        } else if (rho >= 1) {
            for (int n = 0; n <= c - 1; n++) {
                sum += (1 / Factorial.fact(n)) * (Math.pow(lambda / mu, n));
            }
            p0_inv = sum + ((1 / Factorial.fact(c)) * Math.pow(lambda / mu, c) * ((c * mu) / (c * mu - lambda)));
            p0 = Math.pow(p0_inv, -1);
        }
    }

    public void pnCalculation(double n) {
        p0Calculation();
        if (n >= 0 && n < c) {
            pn = (Math.pow(lambda, n) / (Factorial.fact(n) * Math.pow(mu, n))) * p0;
        } else if (n >= c) {
            pn = (Math.pow(lambda, n) / (Math.pow(c, n - c) * Factorial.fact(c) * Math.pow(mu, n))) * p0;
        }
    }

    private void lqCalculation() {
        p0Calculation();
        lq = ((Math.pow(r, c + 1) / c) / (Factorial.fact(c) * Math.pow(1 - (r / c), 2))) * p0;
    }

    private void wCalculation() {
        lqCalculation();
        w = (lq / lambda) + (1 / mu);
    }

    private void wqCalculation() {
        lqCalculation();
        wq = lq / lambda;
    }

    private void lCalculation() {
        lqCalculation();
        l = lq + (lambda / mu);
    }

    private void ciCalculation() {
        ci = c - r;
    }

    public String getPn() {
        return df.format(pn);
    }

    public String getP0() {
        p0Calculation();
        return df.format(p0);
    }

    public String getL() {
        lCalculation();
        return df.format(l);
    }

    public String getLq() {
        lqCalculation();
        return df.format(lq);
    }

    public String getW() {
        wCalculation();
        return df.format(w);
    }

    public String getWq() {
        wqCalculation();
        return df.format(wq);
    }

    public String getCi() {
        ciCalculation();
        return df.format(ci);
    }
}
