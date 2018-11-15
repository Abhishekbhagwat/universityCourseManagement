package scrame;

import java.awt.*;
import java.util.*;

/**
 * @author abhishekbhagwat
 *
 * This class is the Main class. It has the main function and the function call to the menus is made from this class.
 */
public class ScrameMainApp {

    /**
     * main function
     * @param args      Arguments present the buffer
     */
    public static void main (String[] args) {
        boolean success = false;
        boolean loaded = false;
        char choice = '1';
        Database db = new Database();

        do {
            Menu.showMenu();
            choice = CheckType.getChar();

            switch (choice) {
                case '1' :
                    Professor.menuProfessors();
                    break;

                case '2' :
                    Student.menuStudents();
                    break;

                case '3' :
                    Course.menuCourse();
                    break;

                case '4' :
                    Component.menuComponent();
                    break;

                case 'q' :
                case 'Q' :
                    Menu.terminateMenu();
                    break;

                default :
                    System.out.println ("Invalid choice");
            }
        } while (choice != 'q' && choice != 'Q');
    }
}
