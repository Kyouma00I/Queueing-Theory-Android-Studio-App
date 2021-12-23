package com.myprojects.queueingtheoryapp.casescalculation;

public class Factorial {

    public static double fact(double n){
        double result = 1;

        for (int factor = 2; factor <= n; factor++) {
            result *= factor;
        }

        return result;
    }
}
