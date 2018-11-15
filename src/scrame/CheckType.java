package scrame;

/**
 * @author abhishekbhagwat
 * This class is used to typecast the input variable in the favorable choice.
 */

import java.util.Scanner;

public class CheckType {

    /**
     * This method takes the input variable of char datatype. If it is not of char type, it will throw an exception
     * @return charChoice
     */

    public static char getChar() {
        Scanner sc = new Scanner(System.in);

        char charChoice = '\u0000';
        try {
            String input = sc.nextLine();
            charChoice = input.charAt(0);
        }
        catch (Exception e){
        }
        return charChoice;
    }

    /**
     * This method takes the input variable of int datatype. If it is not of int type, it will throw an exception
     * @return intChoice
     */

    public static int getInt() {
        Scanner sc = new Scanner(System.in);

        int intChoice = 0;
        try {
           intChoice = sc.nextInt();
        }
        catch (Exception e){
            throw new NullPointerException ("Not an integer");
        }
        return intChoice;
    }

    /**
     * This method takes the input variable of string datatype. If it is not of string type, it will throw an exception
     * @return stringChoice
     */

    public static String getString() {
        Scanner sc = new Scanner(System.in);

        String stringChoice = null;
        try {
            stringChoice = sc.nextLine();
        }
        catch (Exception e){
        }
        return stringChoice;
    }
}
