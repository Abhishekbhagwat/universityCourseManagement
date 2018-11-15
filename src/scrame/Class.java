package scrame;

/**
 * @author abhishekbhagwat
 * This class contains all the methods and attributes which are present in a particular class of a course.
 */

import java.awt.*;
import java.beans.IntrospectionException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Class implements Serializable { //Implements Serializable as we need to read and write to a file. It is used to convert objects to a stream of charactersa
    /**
     * The name of the professor taking this class.
     */
    private String professorName;

    /**
     * The name of this class.
     */
    private String name;

    /**
     * The name of this course.
     */
    private String course;

    /**
     * The number of students taking this course.
     */
    private int size;

    /**
     * The List of all students taking this course.
     */
    private ArrayList<Student> students;

    /**
     * Constructor of this class. Used to assign initial values for all the parameters when a new object of this class is created.
     * @param name This is the name of the class of the course to be set
     * @param course This is the name of the course to be set
     * @param size This is the size of the course to be set
     */
    public Class (String name, String course, int size) {
        professorName = "";
        this.name = name;
        this.course = course;
        this.size = size;
        students = new ArrayList<Student>();
    }

    // Getter Methods

    /**
     * @return name     The name of this class of the course
     */
    public String getName() {
        return name;
    }

    /**
     * @return course   The name of the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @return size     The number of students taking this course
     */
    public int getSize() {
        return size;
    }

    /**
     * Vacancy is calculated as the difference of the total size and the number of students in this class
     * @return vacancy  The number of leftover spots in this class
     */

    public int getVacancy() {
        int vacancy = size - students.size();
        return vacancy;
    }

    // Setter Methods

    /**
     * @param name  The name that needs to be assigned to this class
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * @param size  The size of this class
     */
    public void setSize (int size) {
        this.size = size;
    }


    /**
     * This method is the menu of the Class. It provides various operations which can be performed on this class.
     */
    public void menuClass () {
        System.out.println("");
        System.out.println ("Edit Class Menu");
        System.out.println ("************************");
        System.out.println ("(1) Name - " + this.name);
        System.out.println ("(2) Size - " + this.size + "[Vacancy left - " + getVacancy() + "]");
        System.out.println ("(3) View student roster");
        System.out.println ("(D) Delete class");
        System.out.println ("");
        System.out.println ("(0) Back to " + this.course + "class list");
        System.out.println ("(Q) Quit Program");
        System.out.println ("************************");
        System.out.println ("Enter your choice");
    }

    // Checks if the object is the current object
    public boolean equals (Object O) {
        if (O instanceof Class) {
            Class C = (Class) O;
            return (getName().equals(C.getName()));
        }
        return false;
    }

    /**
     *
     * @param S     The instance of the student whose existence in the class must be checked
     * @return boolean  THe boolean flag indicating the presence of the this student
     */
    public boolean inClass (Student S) {
        // Check if student exists in <student> array
        if (students != null && students.size() > 0){
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (S.equals(student)) return true;
            }
        }
        return false;
    }

    /**
     * Checks for the presence of the student in this class. If student is not present then adds the student to the class
     * @param S     The student who must be added to this class
     */

    public void addStudent (Student S) {
        if (inClass(S))
            System.out.println (S.getName() + "(" + S.getStudentMatric() + ") is already preesence in this class roster");
        else {
            students.add(S);
            System.out.println(S.getName() + "(" + S.getStudentMatric() + ") has been successfully enrolled in the class");
        }
    }

    /**
     * Checks for the presence of the student in this class. If student is present then removes the student from the class.
     * @param S     The student who must be removed from the class.
     * @return boolean  The flag indicating the removal of the student from this class.
     */

    public boolean removeStudent (Student S) {
        if (students.remove(S))
            return true;
        return false;
    }

    /**
     * This method prints the Student Roster and the associated functions like terminating the program, exiting to the previous known menu.
     * User input is by the the CheckType class and returned. Then by using a switch statement we compare the input to the preset blocks and perform
     * corresponding actions if it matches else the default statement is executed.
     */

    public void printStudentList() {
        String choice = "";
        do {
            if (students.size() > 0){
                System.out.println ( name + " Student Roster");
                System.out.println ("************************");
                for (int i =0 ; i < students.size(); i++) {
                    Student S = students.get(i);
                    System.out.println ((i+1) + ")" + S.getName() + "(" + S.getStudentMatric() + ")");
                }
                System.out.println();
                System.out.println ("(0) Back to " + name + "menu");
                System.out.println ("(Q) Quit Program");
                System.out.println ("************************");
                System.out.println ("Enter choice - ");

                choice = CheckType.getString();

                switch (choice) {
                    case "0" :
                        System.out.println ("Exiting to " + name + "menu >>>>");
                        break;

                    case "q" :
                    case "Q" :
                        Menu.terminateMenu();
                        break;

                    default :
                        int studentChoice;
                        String choice2;

                        try {
                            studentChoice = Integer.parseInt(choice);
                            if (studentChoice > 0 && studentChoice <= students.size()){
                                Student S = students.get(studentChoice - 1);

                                do {
                                    System.out.println (name + " > " + S.getName());
                                    System.out.println ("************************");
                                    System.out.println ("(1) Remove student from class");
                                    System.out.println ();
                                    System.out.println ("(0) Back to " + name + " student list");
                                    System.out.println ("(Q) Quit Program");
                                    System.out.println ("************************");
                                    System.out.println ("Enter choice - ");

                                    choice2 = CheckType.getString();

                                    switch (choice2) {
                                        case "0" :
                                            System.out.println ("Exiting to " + name + "student roster >>>>");
                                            break;

                                        case "1":
                                            if (removeStudent(S)) {
                                                System.out.println ("Remove student from class.");
                                            }
                                            else {
                                                System.out.println ("Could not remove student from class");
                                            }
                                            break;

                                        case "q":
                                        case "Q":
                                            Menu.terminateMenu();
                                            break;

                                        default:
                                            System.out.println ("Invalid choice");
                                    }
                                } while(!choice2.equals("0") && !choice2.equals("q") && !choice2.equals("Q") && !choice2.equals("1"));
                            }

                            else {
                                System.out.println("Invalid choice");
                            }
                        }

                        catch (Exception e) {
                            System.out.println ("Invalid choice");
                        }
                        break;
                }
            }

            else{
                System.out.println("There are no students enrolled in this class");
            }
        } while (students.size() > 0 && !choice.equals("0") && !choice.equals("q") && !choice.equals("Q"));
    }

    /**
     * @return classList    The list of students from the file class.dat
     */

    public static List getClassList() {
        return getClassList("class.dat");
    }

    /**
     * @param file          The filename containing the list of the students
     * @return list         List obtained from the file class.dat
     */

    public static List getClassList (String file){
        List list = null;
        try {
            list = (ArrayList) SerializeDB.readSerializedObject(file);
        }
        catch (Exception e) {}
            if (list == null) list = new ArrayList();
            return list;
    }

    /**
     * @param name      Name of the class
     * @return C        Class variable
     */

    public static Class getClassByName (String name) {
        List list = getClassList();
        for (int i = 0; i < list.size(); i++) {
            Class C = (Class) list.get(i);
            if (C.getName().equals(name)) {
                return C;
            }
        }
        return null;
    }

    /**
     * @param list      List to be written to the file class.dat
     */

    public void save (List list) {
        SerializeDB.writeSerializedObject("class.dat", list);
    }
}
