package PowerSchool.src.powerschool;

import PowerSchool.src.com.ethanzeigler.utils.KeyboardReader;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gregorygiovannini on 1/15/16.
 */

//TODO Under Class, add a functionality to change the period of a class (optional - LOTS of code)
// Create schedules
    //TODO Add assignment weights (tests count more than quizzes, etc) (optional)
    //TODO Add cancel options when adding new assignments and classes and courses and students and teachers
    // Add separate methods for enrolling students in your (teacher's) classes ONLY and enrolling in any class (like an admin)
    // update student points to "null" for newly added students before grades are changed
    // Add view options 12, 13
    // make search methods that print out Student/Teacher schedules
    //TODO add opt out option for login
    //TODO Option 1, option 7 - print out all assignments - if none, say no relevant data and return
    // Fix username - does not update when last name is changed
    // Fix student average for class display under student options and for assignment grade display
    // calculate all student GPAs after adding a new student to the school

    /**
    //***** INFO FOR ULMER TO USE WHEN CHECKING - READ THIS!!!*****
    // Teacher login for Mr. Ulmer is:
    // username: Ulmer
    // password: 1000
    // Student login for Greg Giovannini is:
    // username: Giovannini
    // password: 1700
    // Here is where to find the various options that were mandatory for the program:
    // 1. Add, delete, and modify grades
    //    Option 4 in Main Menu
    // 2. Add, delete, and modify the Person object
    //    Option 7 and 8 in Main Menu (NOTE: the Student and Teacher objects inherit properties from the Person superclass
    // 3. Add a person object to a specific class
    //    Option 5 in Main menu to continuously give students to a class, or Option 7 to enroll one specific student to a particular class
    //    Option 8 in Main menu to switch Teachers between classes (A class cannot be formed without a teacher, so there is no need to add a new teacher to a class)
    // 4. List all Person objects in a specific class
    //    Option 1 in Main menu, then Option 9 (this does not display student schedules)
    // 5. Search for a person given the name
    //    Option 2 in Main menu, then option 2 for students and option 4 for teachers
    // 6. Print out the student schedule
    //    Many ways to do this, but, when logged in as a student, it displays automatically
    //    If logged in as a teacher - search the student by name or ID as detailed for number 5
    // 7. Print the entire list at any given time
    //    Option 1 in Main menu, then option 12 for students/schedules or option 13 for teachers/schedules
    // 8. Add progress report comments to a Person object
    //    Option 9 in Main menu
    // Extra credit is weaved into the program.
     */



public class PowerSchool
{
    private static KeyboardReader reader = new KeyboardReader();
    public static DecimalFormat df = new DecimalFormat("0.0");
    public static Map<String, Student> allStudents = new HashMap<>();
    public static Map<String, Teacher> allTeachers = new HashMap<>();
    public static Map<String, Class> allClasses = new HashMap<>();
    public static Map<String, Course> allCourses = new HashMap<>();
    public static Map<String, ProgressReport> allProgressReports = new HashMap<>();
    public static int numStudentsExisted = 0;
    public static int numTeachersExisted = 0;
    public static int numClassesExisted = 0;
    public static int numCoursesExisted = 0;
    public static int numProgressReportsExisted = 0;



    public static void generateBasicData()
    {
        Student s = new Student( "Greg", "Giovannini", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Ethan", "Zeigler", 2016 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Tess", "Coval", 2016 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Julian", "Berlin", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Michael", "Berthin", 2018 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Renee", "Le", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Rebecca", "Lewis", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Emmalyn", "Tavani", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Rebecca", "Dahan", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Rahil", "Patel", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Sahil", "Patel", 2017 );
        allStudents.put(s.getStudentID(), s);
        s = new Student( "Anthony", "Passaro", 2017 );
        allStudents.put(s.getStudentID(), s);

        Teacher t = new Teacher( "Keith", "Ulmer" );
        allTeachers.put(t.getTeacherID(), t);
        t = new Teacher( "Regina", "Bosworth" );
        allTeachers.put(t.getTeacherID(), t);
        t = new Teacher( "Kevin", "Farrow" );
        allTeachers.put(t.getTeacherID(), t);
        t = new Teacher( "Walter", "Bowne" );
        allTeachers.put(t.getTeacherID(), t);
        t = new Teacher( "William", "Crozier" );
        allTeachers.put(t.getTeacherID(), t);
        t = new Teacher( "William", "Tapper" );
        allTeachers.put(t.getTeacherID(), t);

        Course co = new Course("Honors Precalculus", "MATH33");
        allCourses.put(co.getCourseID(), co);
        co = new Course("Honors Programming", "PROG11");
        allCourses.put(co.getCourseID(), co);
        co = new Course("Honors English III", "ENGL33");
        allCourses.put(co.getCourseID(), co);
        co = new Course("Honors Physics", "PHYS13");
        allCourses.put(co.getCourseID(), co);
        co = new Course("Accelerated Physics", "PHYS12");
        allCourses.put(co.getCourseID(), co);
        co = new Course("AP Chemistry", "CHEM21");
        allCourses.put(co.getCourseID(), co);
        co = new Course("AP Calculus", "MATH44");
        allCourses.put(co.getCourseID(), co);

        Class cl = new Class(allCourses.get("MATH33"), allTeachers.get("1001"), (allClasses.size()+1)+"", 6);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1001"), cl);
        cl = new Class(allCourses.get("MATH33"), allTeachers.get("1001"), (allClasses.size()+1)+"", 5);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1001"), cl);
        cl = new Class(allCourses.get("PROG11"), allTeachers.get("1000"), (allClasses.size()+1)+"", 1);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1000"), cl);
        cl = new Class(allCourses.get("ENGL33"), allTeachers.get("1003"), (allClasses.size()+1)+"", 3);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1003"), cl);
        cl = new Class(allCourses.get("ENGL33"), allTeachers.get("1003"), (allClasses.size()+1)+"", 5);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1003"), cl);
        cl = new Class(allCourses.get("PHYS13"), allTeachers.get("1005"), (allClasses.size()+1)+"", 0);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1005"), cl);
        cl = new Class(allCourses.get("PHYS12"), allTeachers.get("1005"), (allClasses.size()+1)+"", 1);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1005"), cl);
        cl = new Class(allCourses.get("CHEM21"), allTeachers.get("1004"), (allClasses.size()+1)+"", 4);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1004"), cl);
        cl = new Class(allCourses.get("CHEM21"), allTeachers.get("1004"), (allClasses.size()+1)+"", 6);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1004"), cl);
        cl = new Class(allCourses.get("MATH44"), allTeachers.get("1002"), (allClasses.size()+1)+"", 4);
        allClasses.put(cl.getId(), cl);
        TeacherOptions.enrollTeacherInClass(allTeachers.get("1002"), cl);

        TeacherOptions.enrollStudentInClass(allStudents.get("1700"), allClasses.get("3"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1601"), allClasses.get("3"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1601"), allClasses.get("2"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1700"), allClasses.get("1"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1602"), allClasses.get("4"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1700"), allClasses.get("4"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1700"), allClasses.get("6"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1709"), allClasses.get("8"));
        TeacherOptions.enrollStudentInClass(allStudents.get("17010"), allClasses.get("8"));
        TeacherOptions.enrollStudentInClass(allStudents.get("17010"), allClasses.get("5"));
        TeacherOptions.enrollStudentInClass(allStudents.get("17011"), allClasses.get("4"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1804"), allClasses.get("3"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1706"), allClasses.get("5"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1708"), allClasses.get("4"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1705"), allClasses.get("6"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1709"), allClasses.get("6"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1602"), allClasses.get("7"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1602"), allClasses.get("1"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1707"), allClasses.get("4"));
        TeacherOptions.enrollStudentInClass(allStudents.get("1703"), allClasses.get("1"));

        Assignment a = new Assignment("Quiz 1.1", "110", "1/1/17", allClasses.get("1").getId(), "Quiz", 50.0 );
        TeacherOptions.addAssignmentToClass(a, allClasses.get("1"));
        TeacherOptions.addGradeForStudentOnAssignment(a, allStudents.get("1700"), 50.0);
        TeacherOptions.addGradeForStudentOnAssignment(a, allStudents.get("1602"), 40.0);
        TeacherOptions.addGradeForStudentOnAssignment(a, allStudents.get("1703"), 37.0);
        //TeacherOptions.addGradeForClassOfStudentsOnAssignment(a, allClasses.get("1"));




    }




    public static Person login(int i)
    {
        String username, password;
        boolean validUsername = false;
        boolean validPassword = false;
        Person personLoggingIn = null;

        while(!validUsername) // check to see if username exists
        {
            username = reader.readLine("\tEnter your username");
            if(i == 0) // if a student, look at all students
            {
                for(Student s: allStudents.values())
                {
                    if(s.getUsername().equals(username)) // if this student's username matches, this is the student logging in
                    {
                        validUsername = true;
                        personLoggingIn = s;
                        break;
                    }
                    else validUsername = false;
                }
            }
            else if(i == 1) // if a teacher, look at all teachers
            {
                for(Teacher t: allTeachers.values())
                {
                    if(t.getUsername().equals(username)) // if this teacher's username matches, this is the teacher logging in
                    {
                        validUsername = true;
                        personLoggingIn = t;
                        break;
                    }
                    else validUsername = false;
                }
            }

            if(!validUsername)
            {
                System.out.println("Invalid username; please try again.");
            }
        }

        while(!validPassword) // now check to see if the password of the user matches their corresponding username
        {
            password = reader.readLine("\tEnter your password");
            if(personLoggingIn.getPassword().equals(password))
            {
                validPassword = true;
                break;
            }
            if(!validPassword)
            {
                System.out.println("Invalid password; please try again.");
            }
        }

        System.out.println("Login successful!\n");
        return(personLoggingIn);
    }




    public static void main(String[] args)
    {
        char status;
        generateBasicData();


        System.out.println("                 *");
        System.out.println("                ***");
        System.out.println("               *****");
        System.out.println("              *******");
        System.out.println("             *********");
        System.out.println("            ***********");
        System.out.println("           *************");
        System.out.println("          ***************");
        System.out.println("         *****************");
        System.out.println("        *******************");
        System.out.println("       *********************");
        System.out.println("      ***********************");
        System.out.println("     *************************");
        System.out.println("    ***************************");
        System.out.println("   *****************************");
        System.out.println("  *******************************");
        System.out.println(" *********************************");
        System.out.println("***********************************");
        System.out.println("     <><><> PowerThing <><><>");
        System.out.println("***********************************");
        System.out.println(" *********************************");
        System.out.println("  *******************************");
        System.out.println("   *****************************");
        System.out.println("    ***************************");
        System.out.println("     *************************");
        System.out.println("      ***********************");
        System.out.println("       *********************");
        System.out.println("        *******************");
        System.out.println("         *****************");
        System.out.println("          ***************");
        System.out.println("           *************");
        System.out.println("            ***********");
        System.out.println("             *********");
        System.out.println("              *******");
        System.out.println("               *****");
        System.out.println("                ***");
        System.out.println("                 *");

        System.out.println("\n\n<><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("\nStable build v. 1.0");
        System.out.println("\n\nCreated by Greg Giovannini");
        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("\n\nStudents cannot make any changes to information in the database. They can:" +
                "\n\t* View their grades." +
                "\n\t* View their classes." +
                "\n\t* View their schedules." +
                "\n\t* View progress reports for a specific class.");
        System.out.println("\nTeachers can double as administrators and make changes to both information pertinent to them as well as information in the entire database. They can: ");
        System.out.println("\t* View classes, students, teachers, or schedules.");
        System.out.println("\t* Search classes, students, teachers, or assignments.");
        System.out.println("\t* Add, delete, or modify assignments for a specific class.");
        System.out.println("\t* Add, delete, or modify grades for assignments for a specific class.");
        System.out.println("\t* Add, delete, or modify classes.");
        System.out.println("\t* Add, delete, or modify courses.");
        System.out.println("\t* Add, delete, or modify a student's data in the school (including enrolling students in classes).");
        System.out.println("\t* Add, delete, or modify a teacher's data in the school (including assigning teachers to classes).");
        System.out.println("\t* Add, delete, or modify a progress report comment for a specific student.");
                /*"\n\t* view their classes" +
                "\n\t* view all classes in the school" +
                //"\n\t* view the students in their classes" +
                "\n\t* view all students in the school" +
                "\n\t* view the grades of their students" +
                "\n\t* view all the teachers in the school" +
                "\n\t* view their schedules" +
                //"\n\t* view the schedules of their students" +
                "\n\t* search for a specific student" +
                "\n\t* search for a specific teacher" +
                "\n\t* search for a specific class" +
                //"\n\t* view all students in a specific class" +
                "\n\t* add new grades/assignments" +
                "\n\t* change existing grades/assignments" +
                "\n\t* delete existing grades/assignments" +
                "\n\t* add progress report comments for a specific student" +
                "\n\t* add new students to the school and enroll them in classes" +
                "\n\t* delete existing students" +
                "\n\t* move existing students between classes" +
                "\n\t* add new courses" +
                "\n\t* change existing courses" +
                "\n\t* delete existing courses" +
                "\n\t* add new classes" +
                "\n\t* change existing classes" +
                "\n\t* delete existing classes");*/



        boolean terminate = false; // when user is ready to end program entirely
        while(!terminate) // log-in process will run until user is ready to end program
        {
            System.out.println("\n             <><><> LOG IN <><><>");
            System.out.println("*****************************************************");
            System.out.println("Are you a teacher (t) or a student (s)?");
            System.out.println("Enter (q) to quit.");
            status = reader.readChar("Status");
            while (status != 't' && status != 's' && status != 'q') // check for invalid input
            {
                System.out.println("Error, please try again.");
                status = reader.readChar("Status");
            }
            if (status == 'q') // if ready to end the program
            {
                terminate = true;
                System.out.println("Thank you for using PowerThing! Shutting down...");
                System.exit(0);
            }
            // otherwise, continue the program as if input was a valid status

            int function = 0; // menu navigator



            // ************************************************************************************************************************************
            if (status == 's') // if user is a student
            {
                System.out.println("Welcome, Student!");
                System.out.println("Your username is your last name by default; your password is your student ID by default.");
                Person user = login(0); // 0 if student, 1 if teacher

                while (function != -1)
                {
                    TeacherOptions.calculateEveryStudentAverage();
                    TeacherOptions.calculateEveryStudentGPA();
                    ((Student)user).printScheduleWithGrades();
                    if(((Student) user).getGpa() == -2.0)
                    {
                        System.out.println("GPA: N/A");
                    }
                    else
                    {
                        System.out.println("GPA: " + df.format(((Student) user).getGpa()));
                    }
                    System.out.println("\n");
                    System.out.println("             <><><> MAIN MENU <><><>");
                    System.out.println("*****************************************************");
                    System.out.println("\tEnter the period number of the class you wish to view.");
                    System.out.println("\tA detailed list of assignments and progress reports will be displayed.");
                    System.out.println("\t-1. Log out.");
                    System.out.println("*****************************************************");
                    function = reader.readInt("\nEnter the corresponding number of the period you wish to view", -1, 7);
                    if(function == -1)
                    {
                        System.out.println("Logging out...");
                        break;
                    }
                    if(function == 0)
                    {
                        System.out.println("Invalid entry. Please try again.");
                        continue;
                    }

                    Class c = ((Student)user).getSchedule()[function-1];
                    if(c == null)
                    {
                        System.out.println("Study Halls are not valid classes. Please enter a different period number.");
                        continue;
                    }
                    TeacherOptions.printClassData(c);
                    if(c.getStudentAverages().get(((Student) user).getStudentID()) == -2.0)
                    {
                        System.out.println("Your average: N/A");
                    }
                    else
                    {
                        System.out.println("Your average: " + c.getStudentAverages().get(((Student) user).getStudentID()));
                    }

                    System.out.println("\n*****************************************************");
                    System.out.println("Assignments:");
                    if(c.getAssignments().isEmpty())
                    {
                        System.out.println("None found for this class.");
                    }
                    else for(Assignment a: c.getAssignments().values())
                    {
                        TeacherOptions.printAssignmentData(a);
                        if(a.getEntireStudentPoints().get(((Student) user).getStudentID()) == -2.0)
                        {
                            System.out.println("Exempt");
                        }
                        else
                        {
                            System.out.println("Your score: " + a.getEntireStudentPoints().get(((Student) user).getStudentID()) + " / " + a.getPointValue());
                            System.out.println("Your grade: " + df.format((a.getEntireStudentPoints().get(((Student) user).getStudentID()) / a.getPointValue())*100));
                        }

                    }

                    System.out.println("\n*****************************************************");
                    System.out.println("Progress reports:");

                    boolean found = false;
                    for(ProgressReport pr: ((Student) user).getProgressReports().values())
                    {
                        if(c == pr.getClassStudentIsIn())
                        {
                            System.out.println(pr.getReportID() + ". " + pr.getReport());
                            found = true;
                        }

                    }
                    if(!found)
                    {
                        System.out.println("None found for this class.");
                    }
                    reader.readLine("\nPress enter to return to the main menu");
                }
            }

            // ************************************************************************************************************************************
            if (status == 't') // if user is a teacher
            {

                System.out.println("Welcome, Teacher!");
                System.out.println("Your username is your last name by default; your password is your teacher ID by default.");
                Person user = login(1); // 0 if student, 1 if teacher

                while (function != -1)
                {
                    TeacherOptions.calculateEveryStudentAverage();
                    TeacherOptions.calculateEveryStudentGPA();

                    System.out.println("             <><><> MAIN MENU <><><>");
                    System.out.println("*****************************************************");
                    System.out.println("\t1. View classes, students, teachers, or schedule.");
                    System.out.println("\t2. Search classes, students, teachers, or assignments.");
                    System.out.println("\t3. Add, delete, or modify assignments for a specific class.");
                    System.out.println("\t4. Add, delete, or modify grades for assignments for a specific class.");
                    System.out.println("\t5. Add, delete, or modify classes.");
                    System.out.println("\t6. Add, delete, or modify courses.");
                    System.out.println("\t7. Add, delete, or modify a student's data in the school (including enrolling students in classes).");
                    System.out.println("\t8. Add, delete, or modify a teacher's data in the school (including assigning teachers to classes).");
                    System.out.println("\t9. Add, delete, or modify a progress report comment for a specific student.");
                    System.out.println("\t-1. Log out.");
                    System.out.println("*****************************************************");
                    function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

                    switch (function)
                    {
                        case 1: // menu to teacher view options
                            System.out.println("Navigating to teacher view protocols...");
                            TeacherOptions.teacherViewOptions((Teacher)user);
                            break;
                        case 2: // menu to teacher search options
                            System.out.println("Navigating to teacher search protocols...");
                            TeacherOptions.teacherSearchOptions((Teacher)user);
                            break;
                        case 3: // menu to teacher assignment protocols
                            System.out.println("Navigating to teacher assignment protocols...");
                            TeacherOptions.teacherAssignmentOptions((Teacher)user);
                            break;
                        case 4: // menu to teacher grade protocols
                            System.out.println("Navigating to teacher grade protocols...");
                            TeacherOptions.teacherGradeOptions((Teacher)user);
                            break;
                        case 5: // menu to teacher class protocols
                            System.out.println("Navigating to teacher class protocols...");
                            TeacherOptions.teacherClassOptions((Teacher)user);
                            break;
                        case 6: // menu to teacher course protocols
                            System.out.println("Navigating to teacher course protocols...");
                            TeacherOptions.teacherCourseOptions((Teacher)user);
                            break;
                        case 7: // menu to teacher-student administrative protocols
                            System.out.println("Navigating to teacher-student protocols...");
                            TeacherOptions.teacherStudentOptions((Teacher)user);
                            break;
                        case 8: // menu to teacher-teacher administrative protocols
                            System.out.println("Navigating to teacher-teacher protocols...");
                            TeacherOptions.teacherTeacherOptions((Teacher)user);
                            break;
                        case 9: // add a progress report
                            System.out.println("Navigating to teacher progress report protocols...");
                            TeacherOptions.teacherProgressReportOptions((Teacher)user);
                            break;
                        case -1: // log out
                            System.out.println("Logging out...");
                            break;
                    }

                }
            }


        }


    }
}
