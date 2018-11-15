package scrame;

/**
 * @author abhishekbhagwat
 * This class contains the components of the class like assignments, coursework marks,etc and the operations that can be performed on them.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Component implements Serializable, Comparable<Component>{

    /**
     * Name of the course
     */
    private String course;

    /**
     * Name of the component
     */
    private String name;

    /**
     * Weightage of the marks for this component
     */
    private int weightage;

    /**
     * Type of this coursework
     */
    private String type;

    /**
     * Marks obtained for this component
     */
    private ArrayList marks;

    /**
     * Constructor of the class, sets the initial values when an object of the class is created
     * @param course        The name of the course
     * @param weightage     The weightage of this component
     * @param type          The type of this component
     * @param name          The name of this component
     */
    public Component(String course, int weightage, String type, String name) {
        this.course = course;
        this.name = name;
        this.weightage = weightage;
        this.type = type;
        marks = new ArrayList();
    }

    /**
     * Get's the name of the course
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Get's the name of the component
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get's the weightage of this component
     * @return weightage
     */
    public int getWeightage() {
        return weightage;
    }

    /**
     * Get's the type of the component
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Get's the mark of this student
     * @param _student
     * @return marks
     */
    public int getMarks(Student _student) {
        for (int i = 0; i < marks.size(); i++) {
            Pair pair = (Pair) marks.get(i);
            if (pair.left.equals(_student))
                return (int) pair.right;
        }
        return 0;
    }

    /**
     * Sets the marks of this student
     * @param _student      object of class Student
     * @param _marks        Marks obtained by the student
     */
    public void setMarks(Student _student, int _marks) {
        marks.add(new Pair(_student, _marks));
    }

    /**
     * @param _name         The name to be set of the component
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * @param _weightage    The weightage of the marks towards the final
     */
    public void setWeightage(int _weightage) {
        weightage = _weightage;
    }

    public int compareTo(Component cc) {
        int lastCmp = (type + " " + name).compareTo((cc.type + " " + cc.name));
        return (lastCmp != 0 ? lastCmp : (type + " " + name).compareTo((cc.type + " " + cc.name)));
    }

    //
    //	public int setCalculateScore()
    //	{
    //		return courseComponentPercent*courseComponentMark;
    //	}
    //
    //	public static int processMark(String Component) {
    //		int _mark;
    //		do {
    //			try {
    //				System.out.print("Enter " + Component + "'s mark: ");
    //				_mark = GetType.getInt();
    //				courseComponentMark=_mark;
    //				return courseComponentMark;
    //			}
    //			catch (NumberFormatException e) {
    //				System.out.println("\n  Error: Invalid mark number. Only digits are allowed.\n");
    //			}
    //		} while (true);
    //	}
    //

    // Prompts for and gets input for course

    /**
     *
     * @param component The name of the component
     * @return the boolean indicator of the validating the course code
     */
    public static String processCourseCode (String component) {
        String courseCode;
        List list = Course.getCourseList();
        Collections.sort(list);
        do {
            System.out.print("Enter " + component + "courseCode");
            courseCode = CheckType.getString();
            String storeCourseTitle=Course.SearchCourse(courseCode);
            if(storeCourseTitle!=null){
                System.out.println(storeCourseTitle + " (" + courseCode + ") is found");
                return courseCode;
            }
            else
                System.out.println("There is no such course.");
        } while (true);
    }

    /**
     *
     * @param component The name of the component
     * @return the boolean indicator of validating the percentage
     */
    public static int processPercent(String component) {
        int percent;
        do {
            try {
                System.out.print("Enter " + component + "'s percent: ");
                percent = CheckType.getInt();

                return percent;
            }
            catch (NumberFormatException e) {
                System.out.println("\n  Error: Invalid percent number. Only digits are allowed.\n");
            }
        } while (true);
    }

    /**
     *
     * @param _course The name of the course
     * @return        The list of the components present in this course
     */
    public static List getComponentListByCourse(String _course) {
        List list = getComponentList();
        List componentList = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            Component cc = (Component) list.get(i);
            if (cc.getCourse().equals(_course))
                componentList.add(cc);
        }

        return componentList;
    }

    /**
     * @return      The list of the components from file component.dat
     */
    public static List getComponentList() {
        return getComponentList("component.dat");
    }

    public static List getComponentList(String file) {
        List list = null;
        try {
            list = (ArrayList) SerializeDB.readSerializedObject(file);
        }
        catch ( Exception e ) {
        }
        if (list == null) list = new ArrayList();
        return list;
    }

    /**
     *
     * @param list  The list to be written to the file component.dat, also known as the process of saving
     */
    public static void save(List list) {
        SerializeDB.writeSerializedObject("component.dat", list);
    }

    /**
     * Checks for the existance of the component
     * @param _type
     * @param _name     The name of the component
     * @return
     */
    public static boolean componentExists(String _type, String _name) {
        List list = getComponentList();
        for (int i = 0; i < list.size(); i++) {
            Component cc = (Component) list.get(i);
            if (cc.getType().equals(_type) && cc.getName().equals(_name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the total score
     * @return      The total score
     */
    public double getTotalScore() {
        double total = 0.0;

        for (int i = 0; i < marks.size(); i++) {
            try {
                Pair pair = (Pair) marks.get(i);
                total += (int) pair.right;
            }
            catch (Exception e) {
            }
        }

        return total;
    }

    /**
     * This method validates the new components which are being added
     */
    public void processNewComponent() {
        System.out.println("");
        System.out.println("Adding " + type);
        System.out.println("-----------------");

        do {
            System.out.println("Enter component name:");
            System.out.print(TextFormat.capitalize(type) + ": ");
            String _name = CheckType.getString();

            if (_name.length() > 0) {
                if (componentExists(type, _name))
                    System.out.println("\n  " + TextFormat.capitalize(type) + " " + _name + " already exists.\n");
                else {
                    name = _name;
                    break;
                }
            }
            else
                System.out.println("\n  Component name is required.\n");

        } while (true);

        do {
            try {
                System.out.print("Enter weightage: ");
                int _weightage = CheckType.getInt();

                if (_weightage > 0) {
                    weightage = _weightage;
                    List ccList = Component.getComponentList();
                    ccList.add(this);
                    save(ccList);
                    System.out.println("\n  Added " + TextFormat.capitalize(type) + ": " + name);
                    break;
                }
                else
                    System.out.println("\n  Invalid weightage. Value has to be more than 0%.");
            }
            catch (Exception e) {
                System.out.println("\n  Invalid weightage. Only digits are allowed.");
            }
        } while (true);
    }

    public static void menuComponent() {
        List courseList = Course.getCourseList();
        Collections.sort(courseList);

        if (courseList.size() > 0) {
            String courseChoice = "";
            do {
                System.out.println("\nCourse list");
                System.out.println("---------------");
                for (int i = 0; i < courseList.size(); i++) {
                    Course c = (Course) courseList.get(i);
                    System.out.println(i + 1 +") " + c.getCode() + " " + c.getTitle());
                }
                System.out.println();
                System.out.println("0) Back to main menu");
                System.out.println("Q) Exit program");
                System.out.println("---------------");
                System.out.print("Enter choice (or course code to search): ");

                courseChoice = CheckType.getString();

                switch (courseChoice) {
                    case "0":
                        System.out.println("\n  Exiting to main menu...");
                        break;

                    case "q":
                    case "Q":
                        Menu.terminateMenu();
                        break;

                    default:
                        int courseChoiceInt;
                        try {
                            courseChoiceInt = Integer.parseInt(courseChoice);
                            if (courseChoiceInt > 0 && courseChoiceInt <= courseList.size()) {
                                Course c = (Course) courseList.get(courseChoiceInt-1);
                                Menu.courseComponentMenu(c.getCode());
                            }
                            else {
                                System.out.println("\n  Invalid choice.");
                            }
                        }
                        catch (Exception e) {
                            courseChoice = courseChoice.toUpperCase();
                            String storeCourseTitle = Course.SearchCourse(courseChoice);

                            if (storeCourseTitle != null) {
                                Menu.courseComponentMenu(courseChoice);
                            }

                            else {
                                System.out.println("\n  There is no such course.");
                            }
                        }
                }
            } while (!courseChoice.equals("0") && !courseChoice.equals("q") && !courseChoice.equals("Q"));
        }
        else
            System.out.println("\nThere are no courses in the system.");
    }

    /**
     * Deletes the course
     * @param _course      The name of the course to be deleted
     */
    public static void deleteCourse(String _course) {
        List list = getComponentList();
        for (int i = 0; i < list.size(); i++) {
            Component cc = (Component) list.get(i);
            if (cc.course.equals(_course)) {
                list.remove(cc);
            }
        }
        save(list);
    }

    /**
     * Deletes the student
     * @param _student      The object student that needs to be deleted
     */

    public static void deleteStudent(Student _student) {
        List list = getComponentList();
        for (int i = 0; i < list.size(); i++) {
            Component cc = (Component) list.get(i);
            for (int o = 0; o < cc.marks.size(); o++) {
                Pair pair = (Pair) cc.marks.get(o);
                if (pair.left.equals(_student)) {
                    cc.marks.remove(pair);
                    list.set(i, cc);
                }
            }
        }
        save(list);
    }
}
