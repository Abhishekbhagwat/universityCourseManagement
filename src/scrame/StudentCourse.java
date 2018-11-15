package scrame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author abhishekbhagwat
 * This class contains the fields and attributes mainly of the Courses Menu. It contains various operations which include read, add, delete operations.
 * It also contains functions to validate and verify the student in the particular courses and its sub operations.
 * It also implements the Interface to read / write data to the file course.dat
 */
public class StudentCourse implements Serializable {

    /**
     * The name of the student
     */
    private String student;

    /**
     * The name of the course
     */
    private String course;

    /**
     * Constructor to initialize the new object of this class with parameters.
     */
    public StudentCourse() {
        student = "";
        course = "";
    }

    /**
     * Constructor Override. This constructor initializes new objects of this class with the parameters passed.
     * @param student       The name of the student to be initialized
     * @param course        The name of the course to be initialized
     */

    public StudentCourse(String student, String course) {
        this.student = student;
        this.course = course;
    }

    // Getter Methods

    /**
     * Get the name of this student
     * @return student             Name of the student
     */
    public String getStudent() {
        return student;
    }

    /**
     * Gets the name of this course
     * @return course             Name of this student
     */
    public String getCourse() {
        return course;
    }

    // Get courses registered by this student

    /**
     * This method gets the list of the courses being taken by this student currently
     * @param matric              Matric number of this student
     * @return courseList         List of the courses pursued by this student
     */
    public static List<Course> getStudentCourses(String matric) {
        List<StudentCourse> list = getRegisterList();
        List<Course> courseList = new ArrayList<Course>();
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.student.equals(matric))
                courseList.add(Course.getCourseByCode(r.course));
        }
        return courseList;
    }

    // Get student registered for this course

    /**
     * This method gets the list of the students registered for this course.
     * @param course    The name of the current course
     * @return          The list of the students who have been registered for this course
     */
    public static List<Student> getCourseStudents(String course) {
        List<StudentCourse> list = getRegisterList();
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.course.equals(course))
                studentList.add(Student.getStudentByMatric(course));
        }
        return studentList;
    }

    // Check if student is registered

    /**
     * This method basically checks if a student has been successfully enrolled in this course
     * @param matric        The matric number of the student who has to be validated
     * @param course        The course for which the student needs to be validated
     * @return              Boolean indicator of whether the student has been enrolled
     */
    public static boolean isEnrolled(String matric, String course) {
        if (matric.length() > 0) {
            List list = getRegisterList();
            for (int i = 0; i < list.size(); i++) {
                StudentCourse r = (StudentCourse) list.get(i);
                if (r.student.equals(matric) && r.course.equals(course))
                    return true;
            }
        }
        return false;
    }

    // Register student for a course

    /**
     * This method is used to register a student to a particular course.
     * @param student       The name of the student to be registered
     * @param course        The name of the course to be registered to this student
     * @return              The boolean indicator to show the registration process whether it is successful or not.
     */
    public static boolean register(String student, String course) {
        if (student.length() > 0) {
            List list = getRegisterList();

            if (!isEnrolled(student, course)) {
                try {
                    StudentCourse r = new StudentCourse(student, course);
                    list.add(r);
                    save(list);
                    System.out.println("Student successfully enrolled in course.");
                    return true;
                }
                catch (Exception e) {
                    System.out.println("ERROR : Could not add student to course.");
                }
            }
            else {
                System.out.println("ERROR : Student is already registered for this course.");
            }
        }
        else {
            System.out.println("ERROR : Student does not exist.");
        }
        return false;
    }

    /**
     * This method updates the Matriculation number of this student to the new number passed as a parameter
     * @param studentMatric     The old matric number of the student
     * @param matric            The new matric number to be updated
     */
    public static void updateStudentMatric(String studentMatric, String matric) {
        List list = getRegisterList();
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.student.equals(studentMatric)) {
                int rIndex = list.indexOf(r);
                if (rIndex != -1) {
                    r.student = matric;
                    list.set(rIndex, r);
                    save(list);
                }
            }
        }
    }

    // Update course codes

    /**
     * This method updates the course code of this course to a new code passed as a parameter
     * @param oldCode       The old course code of this course
     * @param newCode       The new code to be updated for this course
     */
    public static void updateCode(String oldCode, String newCode) {
        List list = getRegisterList();
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.course.equals(oldCode)) {
                int rIndex = list.indexOf(r);
                if (rIndex != -1) {
                    r.course = newCode;
                    list.set(rIndex, r);
                    save(list);
                }
            }
        }
    }

    // Delete student

    /**
     * This method deletes this student record from this database
     * @param matric        The matric number of this student
     */
    public static void deleteStudent(String matric) {
        List list = getRegisterList();
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.student.equals(matric)) {
                list.remove(r);
                save(list);
            }
        }
    }

    // Delete course

    /**
     * This method deletes this course record from the database
     * @param code      The course code whose record must be deleted
     */
    public static void deleteCourse(String code) {
        List list = getRegisterList();
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.course.equals(code)) {
                list.remove(r);
                save(list);
            }
        }
    }

    /**
     * This method is used to print the student list who have registered for this course
     * @param course    This is the name of the course whose student's records must be printed
     */
    public static void printRegisterList(String course) {
        List list = getRegisterList();
        if (list != null && list.size() > 0) {
            System.out.println("Students enrolled");
            System.out.println("************************");
            for (int i = 0; i < list.size(); i++) {
                StudentCourse r = (StudentCourse) list.get(i);
                if (r.course.equals(course)) {
                    Student s = Student.getStudentByMatric(r.student);
                    System.out.println(s.getName() + " (" + s.getStudentMatric() + ")");
                }
            }
            System.out.println("************************");
        }
        else
            System.out.println("There are no students registered for this course.");
    }


    /**
     * This method returns a count of the number of students who have enrolled in this course
     * @param course        This is the name of the course whose registrations must be counted
     * @return count            Integer value of the number of enrollments
     */
    public static int countStudentsByCourse(String course) {
        List list = getRegisterList();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            StudentCourse r = (StudentCourse) list.get(i);
            if (r.course.equals(course))
                count++;
        }
        return count;
    }

    /**
     * This method returns the Registration list of the students from the file studentcourse.dat
     * @return
     */
    public static List getRegisterList() {
        return getRegisterList("studentcourse.dat");
    }

    /**
     * This method overrides the above method to take a file parameter from the method and fetches the data from the required file
     * @param file
     * @return
     */
    public static List getRegisterList(String file) {
        List list = null;
        try {
            list = (ArrayList) SerializeDB.readSerializedObject(file);
        }
        catch ( Exception e ) {
        }
        if (list == null)
            list = new ArrayList();
        return list;
    }

    /**
     * This method writes the list passed as the parameter into the file studentcourse.dat
     * @param list
     */
    private static void save(List list) {
        SerializeDB.writeSerializedObject("studentcourse.dat", list);
    }
}
