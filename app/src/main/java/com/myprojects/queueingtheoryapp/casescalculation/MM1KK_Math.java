package com.myprojects.queueingtheoryapp.casescalculation;

import java.text.DecimalFormat;

public class MM1KK_Math {

    double lambda;
    double mu;
    double k;
    double rho;
    double pk;
    double l;
    double lq;
    double w;
    double wq;
    double pn;

    public MM1KK_Math(double lambda, double mu, double k) {
        this.lambda = lambda;
        this.mu = mu;
        this.k = k;
    }

    DecimalFormat df = new DecimalFormat("0.####");

    private void rhoCalculation() {
        rho = lambda / mu;
    }

    private void lCalucaltion() {
        this.rhoCalculation();
        if (rho != 1) {
            l = rho * ((1 - ((k + 1) * Math.pow(rho, k)) + (k * Math.pow(rho, k + 1))) / ((1 - rho) * (1 - Math.pow(rho, k + 1))));
        } else if (rho == 1) {
            l = k / 2;
        }
    }

    public void pnCalculation(double n) {
        this.rhoCalculation();
        if (rho != 1) {
            pn = Math.pow(rho, n) * ((1 - rho) / (1 - Math.pow(rho, k + 1)));
        } else if (rho == 1) {
            pn = 1 / (k + 1);
        }
    }

    private void pkCalculation() {
        this.rhoCalculation();
        if (rho != 1) {
            pk = Math.pow(rho, k) * ((1 - rho) / (1 - Math.pow(rho, k + 1)));
        } else if (rho == 1) {
            pk = 1 / (k + 1);
        }
    }

    private void wCalculation() {
        this.lCalucaltion();
        this.pkCalculation();
        w = l / (lambda * (1 - pk));
    }

    private void wqCalculation() {
        this.wCalculation();
        wq = w - (1 / mu);
    }

    private void lqCalculation() {
        this.lCalucaltion();
        this.pkCalculation();
        lq = l - rho * (1 - pk);
    }

    public String getPk() {
        this.pkCalculation();
        return df.format(pk);
    }

    public String getL() {
        this.lCalucaltion();
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

    public String getPn() {
        return df.format(pn);
    }
}
