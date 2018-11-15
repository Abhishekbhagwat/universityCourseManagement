package scrame;

/**
 * @author abhishekbhagwat
 * This class does the formatting the text wherever required. It is also converts the score to a alphabetic Grade based on set parameters.
 */

public class TextFormat {

    /**
     * This method converts the first character of the parameter string to Uppercase
     * @param text      Text which has to be capitalizes
     * @return          Capitalized text
     */
    public static String capitalize(String text){
        return text.substring(0,1).toUpperCase() + text.substring(1);
    }

    /**
     * This methods converts the score parameter to a alphabetic grade.
     * @param score     The score to be converted to a grade
     * @return          The letter grade corresponding to the score
     */
    public static String scoreToGrade(double score) {
        double gradeDistinction = 90.0;
        double gradeA = 80.0;
        double gradeB = 70.0;
        double gradeC = 60.0;
        double gradeD = 50.0;

        if (score >= gradeDistinction) return "DISTINCTION";
        else if (score >= gradeA) return "A";
        else if (score >= gradeB) return "B";
        else if (score >= gradeC) return "C";
        else if (score >= gradeD) return "D";

        return "F";
    }

    /**
     * Method which pads (inserts spaces) to the right of the string. A good practice would be to create a padLeft method also, but such a use case has is not present in the code, hence ignored.\
     * @param s     String to which padding must be done
     * @param n     The amount of padding to be added
     * @return      The padded string
     */
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }
}

