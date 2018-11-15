package scrame;

import java.io.Serializable;

/**
 * This class is a base class for the Student and Professor. It implements the Serializable class for I/O.
 * @author abhishekbhagwat
 */

public class Person implements Serializable {

    /**
     * The name of this student
     */
    private String name;

    /**
     * The email of this student
     */
    private String email;

    /**
     * The contact number of this student
     */
    private int contact;

    /**
     * Constructor to initialize the initial values of the new object of this class.
     * @param name      The name of the person
     * @param email     The email of this person
     * @param contact   The contact of this person
     */

    public Person (String name, String email, int contact) {
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    // Getter methods

    /**
     *@return       Name of this person
     */
    public String getName() {
        return name;
    }

    /**
     *@return       Email of this person
     */
    public String getEmail() {
        return email;
    }

    /**
     *@return       Contact of this person
     */
    public int getContact() {
        return contact;
    }

    // Setter methods

    /**
     * Sets the name of this person
     * @param name
     */
    public void setName (String name) {
        this.name = name;
    }


    /**
     * Sets the email of this person
     * @param email
     */
    public void setEmail (String email) {
        this.email = email;
    }


    /**
     * Sets the contact of this person
     * @param contact
     */
    public void setContact (int contact) {
        this.contact = contact;
    }

    // Validation methods

    /**
     * Validates the name of the student
     * @param person
     * @return      Boolean indicator of the validation method
     */
    public static String validateName (String person) {
        String name = "";

         do {
            System.out.println("Enter" + person + " 's name:");
            name = CheckType.getString();
            if (name.length() == 0){
                System.out.println("ERROR : Invalid name. Ensure that the name contains atleast one character");
            }
            else if(name.matches(".*\\d+.*")){
                System.out.println("ERROR : Invalid name. Ensure that the name does not contain any numbers");
            }
            else
                return name;
        } while(true);
    }

    /**
     * Validates the contact of the student
     * @param person
     * @return      Boolean indicator of the validation method
     */
    public static int validateContact (String person) {
        int contact;
         do {
             try {
                 System.out.println ("Enter" + person + " 's contact:");
                 contact = CheckType.getInt();
                 return contact;
             }
             catch (Exception e) {
                 System.out.println("ERROR : Invalid contact number. Ensure that the contact number contains only digits");
             }
        } while(true);
    }

    /**
     * Validates the gender of the student
     * @param person
     * @return      Boolean indicator of the validation method
     */
    public static char validateGender (String person) {
        char gender;

        do {
            System.out.println ("Enter" + person + " 's gender (M/F):");
            gender = CheckType.getChar();
            if (gender == 'M' || gender == 'm')
                return 'M';
            else if (gender =='F' || gender == 'f')
                return 'F';
            else
                System.out.println ("ERROR : Invalid gender. Ensure that the gender is 'M' or 'F' ");
        } while(true);
    }

    /**
     * Validates the email of the student
     * @param person
     * @return      Boolean indicator of the validation method
     */
    public static String validateEmail(String person) {
        String email;

        do {
            System.out.print("Enter " + person + "'s email: ");
            email = CheckType.getString();
            if (email.length() > 0) return email;
            else return "NONE";
        } while (true);
    }
}


