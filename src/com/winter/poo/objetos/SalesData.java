package com.winter.poo.objetos;

public class SalesData {

    private double[] sales; // The sales data

    public SalesData(double[] s) {
        // Create an array as large as s.
        sales = new double[s.length];
        // Copy the elements from s to sales.
        for (int index = 0; index < s.length; index++)
            sales[index] = s[index];
    }

    public double getTotal() {
        double total = 0.0; // Accumulator
        // Accumulate the sum of the elements
        // in the sales array.
        for (int index = 0; index < sales.length; index++)
            total += sales[index];
        // Return the total.
        return total;
    }

    public double getAverage() {
        return getTotal() / sales.length;
    }

    public double getHighest() {
        double highest = sales[0];
        for (int index = 1; index < sales.length; index++) {
            if (sales[index] > highest)
                highest = sales[index];
        }
        return highest;
    }

    public double getLowest() {
        double lowest = sales[0];
        for (int index = 1; index < sales.length; index++)
            if (sales[index] < lowest)
                lowest = sales[index];
        return lowest;
    }

}
