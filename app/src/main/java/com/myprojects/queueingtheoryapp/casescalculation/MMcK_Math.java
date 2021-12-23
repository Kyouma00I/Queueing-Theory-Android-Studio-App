package com.myprojects.queueingtheoryapp.casescalculation;

import java.text.DecimalFormat;

public class MMcK_Math {
    private double lambda;
    private double mu;
    private double c;
    private double k;
    private double r;
    private double rho;
    private double pn;
    private double p0;
    private double l;
    private double lq;
    private double w;
    private double wq;
    private double ci;

    public MMcK_Math(double lambda, double mu, double c, double k) {
        this.lambda = lambda;
        this.mu = mu;
        this.c = c;
        this.k = k;
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
        if (rho != 1) {
            for (int n = 0; n <= c - 1; n++) {
                sum += Math.pow(r, n) / Factorial.fact(n);
            }
            p0_inv = sum + ((Math.pow(r, c) / Factorial.fact(c)) * ((1 - Math.pow(rho, k - c + 1)) / (1 - rho)));
            p0 = Math.pow(p0_inv, -1);
        } else if (rho == 1) {
            for (int n = 0; n <= c - 1; n++) {
                sum += (1 / Factorial.fact(n)) * (Math.pow(lambda / mu, n));
            }
            p0_inv = sum + ((Math.pow(r, c) / Factorial.fact(c)) * (k - c + 1));
            p0 = Math.pow(p0_inv, -1);
        }
    }

//    public void pnCalculation(double n) {
//        p0Calculation();
//        if (n >= 0 && n < c) {
//            pn = (Math.pow(r, n) / Factorial.fact(n)) * p0;
//        } else if (n >= c && n <= k) {
//            pn = (Math.pow(r, n) / (Math.pow(c, n - c) * Factorial.fact(c))) * p0;
//        }
//    }

    public void pnCalculation(double n) {
        p0Calculation();
        if (n >= 0 && n < c) {
            pn = (Math.pow(lambda, n / (Factorial.fact(n) * Math.pow(mu, n)))) * p0;
        } else if (n >= c && n <= k) {
            pn = (Math.pow(lambda, n)/(Math.pow(c, n-c)*Factorial.fact(c)*Math.pow(mu, n))) * p0;
        }
    }

    private void lqCalculation() {
        p0Calculation();
        lq = ((rho * Math.pow(r, c) * p0) / (Factorial.fact(c) * Math.pow((1 - rho), 2))) * (1 - Math.pow(rho, k - c + 1) - ((1 - rho) * (k - c + 1) * Math.pow(rho, k - c)));
    }

    private void wCalculation() {
        lqCalculation();
        pnCalculation(k);
        w = l / (lambda * (1 - Double.parseDouble(getPn())));
    }

    private void wqCalculation() {
        lqCalculation();
        wq = lq / (lambda * (1 - Double.parseDouble(getPn())));
    }

    private void lCalculation() {
        lqCalculation();
        int sum = 0;
        for (int n = 0; n <= c - 1; n++) {
            sum += (c - n) * (Math.pow(r, n) / Factorial.fact(n));
        }
        l = lq + c - (p0 * sum);
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

}
