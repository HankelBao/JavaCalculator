package JavaCalculator;

import java.util.ArrayList;

class MathCalculator {
    public double[] getResult(ArrayList<Double> coefficients) {
        if (coefficients.size() == 1) {
            double[] result = {coefficients.get(0)};
            return result;
        }
        if (coefficients.size() == 2) {
            double a = coefficients.get(1);
            double b = coefficients.get(0);
            double[] result = {(-b/a)};
            return result;
        }
        if (coefficients.size() == 3) {
            double a = coefficients.get(2);
            double b = coefficients.get(1);
            double c = coefficients.get(0);
            double result1 = ((-b)+Math.sqrt(b*b-4*a*c))/2*a;
            double result2 = ((-b)-Math.sqrt(b*b-4*a*c))/2*a;
            double[] results = {result1, result2};
            return results;
        }
        double[] result = {0.00};
        return result;
    }
    public void printResult(double[] results) {
        System.out.print("Results: ");
        for (double result:results) {
            System.out.print(result+" ");
        }
        System.out.println("");
    }
}