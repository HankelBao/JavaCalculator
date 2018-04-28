package JavaCalculator;

import java.util.Scanner;

/**
 * Java Calculator
 * I hate Java, but AP uses JAVA
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "[Java Calculator]" );
        System.out.println( "Enter equation:" );
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String equationString = scanner.nextLine();

        MathEquation equation = new MathEquation(equationString);
        equation.print();

        equation.adjust();
        equation.print();

        MathCalculator calculator = new MathCalculator();
        double[] results = calculator.getResult(equation.leftExpression.extractCoefficients());
        calculator.printResult(results);
        scanner.close();
    }
}
