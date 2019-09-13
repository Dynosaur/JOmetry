package old;

import old.combo.Combination;
import old.combo.Equation;
import old.combo.Expression;

import java.util.Scanner;

public class Runner {

    public static boolean validate(String input) {
        if(input.trim().isEmpty()) return false;
        return true;
    }

    public static void main(String[] args) {

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(!validate(input)) {
                System.err.println("Invalid input.");
                continue;
            }
            switch(Combination.determineType(input)) {
                case EXPRESSION:
                    System.out.println("EXPRESSION");
                    Expression exp = new Expression(input);
                    System.out.println(exp);
                    break;
                case EQUATION:
                    System.out.println("EQUATION");
                    Equation eq = new Equation(input);
                    System.out.println(eq);
                    break;
            }

        }

    }

}
