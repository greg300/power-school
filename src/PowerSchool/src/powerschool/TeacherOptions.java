package PowerSchool.src.powerschool;

import PowerSchool.src.com.ethanzeigler.utils.KeyboardReader;
import PowerSchool.src.com.greg.utils.Searcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by GregG on 2/21/16.
 */
public class TeacherOptions
{
    private static KeyboardReader reader = new KeyboardReader();




    // ************************************************************************************************************************************
    public static void teacherViewOptions(Teacher user)
    {
        int function = 0;
        //View classes, students, teachers, or schedule
        while (function != -1)
        {
            //TODO Reorganize this menu!
            System.out.println("\n");
            System.out.println("              <><><> VIEW OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. View a list of all your classes.");
            System.out.println("\t2. View a list of all your students.");
            System.out.println("\t3. View a list of all of the students in the school.");
            System.out.println("\t4. View a list of all of the teachers in the school.");
            System.out.println("\t5. View your schedule.");
            System.out.println("\t6. View the assignments for a particular class (not restricted to your own)."); //TODO Change this?
            System.out.println("\t7. View the grades of all students on a particular assignment (your assignments only).");
            System.out.println("\t8. View the class averages of all students for a particular class.");
            System.out.println("\t9. View all the students in a particular class."); //TODO add class ID display here?
            System.out.println("\t10. View all courses in the school.");
            System.out.println("\t11. View all classes in the school.");
            System.out.println("\t12. View all students in the school and their schedules (may be a long list).");
            System.out.println("\t13. View all teachers in the school and their schedules (may be a long list).");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            int numMatches;
            switch (function)
            {
                case 1: // view all classes taught
                    numMatches = viewTaughtClasses(user);
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 2: // view all students taught
                    numMatches = viewTaughtStudents(user);
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 3: // view all students in the school
                    numMatches = viewAllStudents();
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 4: // view all teachers in the school
                    numMatches = viewAllTeachers();
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 5: // view teacher schedule
                    user.printSchedule();
                    break;
                case 6: // view the assignments for a particular class
                    Class c = searchClassesByID(reader.readLine("Enter the ID of the class for which you are searching"));
                    if(c == null)
                        break;
                    numMatches = viewAssignmentsForClass(c);
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 7: // view the grades of all students on a particular assignment
                    Assignment a = searchAssignmentsByID(reader.readLine("Enter the ID of the assignment for which you are searching"), user);
                    if(a == null)
                        break;
                    viewStudentGradesForAssignment(a);
                    break;
                case 8: // view student overall grades
                    Class c2 = searchClassesByID(reader.readLine("Enter the ID of the class for which you are searching"));
                    if(c2 == null)
                        break;
                    viewStudentOverallGrades(c2);
                    break;
                case 9: // view students in a class
                    numMatches = viewAllClasses();
                    if(numMatches == 0)
                    {
                        System.out.println("No relevant data.");
                        break;
                    }
                    Class c3 = searchClassesByID(reader.readLine("\nEnter the ID of the class for which you are searching"));
                    if(c3 == null)
                        break;
                    System.out.println();
                    numMatches = viewStudentsInClass(c3);
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 10: // view all courses
                    numMatches = viewAllCourses();
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 11: // view all classes
                    numMatches = viewAllClasses();
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 12: // view all students + schedules
                    numMatches = viewAllStudentsWithSchedules();
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case 13: // view all teachers + schedules
                    numMatches = viewAllTeachersWithSchedules();
                    if(numMatches == 0)
                        System.out.println("No relevant data.");
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static int viewTaughtClasses(Teacher user)
    {
        int numClasses = 0;
        String currentClassID;
        Class currentClass;

        Iterator<String> it = user.getClasses().keySet().iterator();

        while(it.hasNext())
        {
            numClasses++;
            currentClassID = it.next();
            currentClass = user.getClasses().get(currentClassID);
            System.out.print(currentClass.getCourse().getName());
            System.out.println("\t\t"+currentClass.getId());
        }
        return numClasses;
    }


    public static int viewTaughtStudents(Teacher user)
    {
        int numStudents = 0;
        String currentClassID;
        Class currentClass;
        String currentStudentID;
        Student currentStudent;

        Iterator<String> it = user.getClasses().keySet().iterator();

        while(it.hasNext())
        {
            numStudents++;
            currentClassID = it.next();
            currentClass = user.getClasses().get(currentClassID);

            Iterator<String> it2 = currentClass.getStudents().keySet().iterator();

            System.out.print(currentClass.getCourse().getName());
            System.out.println("\t\t"+currentClass.getId());
            while(it2.hasNext())
            {
                currentStudentID = it2.next();
                currentStudent = currentClass.getStudents().get(currentStudentID);
                System.out.print("\t"+currentStudent.getFirstName()+" "+currentStudent.getLastName());
                System.out.println("\t\t"+currentStudentID);
            }

        }
        return numStudents;
    }


    public static int viewAllStudents()
    {
        int numStudents = 0;
        String currentStudentID;
        Student currentStudent;

        Iterator<String> it = PowerSchool.allStudents.keySet().iterator();

        while(it.hasNext())
        {
            numStudents++;
            currentStudentID = it.next();
            currentStudent = PowerSchool.allStudents.get(currentStudentID);
            System.out.print(currentStudent.getFirstName()+" "+currentStudent.getLastName());
            System.out.println("\t\t"+currentStudentID);
        }
        return numStudents;
    }


    public static int viewAllTeachers()
    {
        int numTeachers = 0;
        String currentTeacherID;
        Teacher currentTeacher;

        Iterator<String> it = PowerSchool.allTeachers.keySet().iterator();

        while(it.hasNext())
        {
            numTeachers++;
            currentTeacherID = it.next();
            currentTeacher = PowerSchool.allTeachers.get(currentTeacherID);
            System.out.print(currentTeacher.getFirstName()+" "+currentTeacher.getLastName());
            System.out.println("\t\t"+currentTeacherID);
        }
        return numTeachers;
    }


    public static int viewAssignmentsForClass(Class c)
    {
        int numAssignments = 0;
        for(Assignment a: c.getAssignments().values())
        {
            numAssignments++;
            System.out.println(a.getName());
            System.out.println("\tID: " + a.getId());
            System.out.println("\tDate: " + a.getDate());
            System.out.println("\tCategory: " + a.getCategory());
            System.out.println("\t" + a.getPointValue() + " points");
            System.out.println();
        }

        return numAssignments;
    }


    public static void viewStudentGradesForAssignment(Assignment a)
    {
        Map<String, Double> points = a.getEntireStudentPoints();
        String currentStudentID;
        Student currentStudent;
        Double currentPointValue;

        Iterator<String> it = points.keySet().iterator();

        while(it.hasNext())
        {
            currentStudentID = it.next();
            currentPointValue = points.get(currentStudentID);
            currentStudent = PowerSchool.allStudents.get(currentStudentID);
            System.out.print(currentStudent.getFirstName()+" "+currentStudent.getLastName());
            if(currentPointValue == -2.0)
            {
                System.out.println("\t\tExempt");
            }
            else
            {
                System.out.println("\t\t" + currentPointValue + " / " + a.getPointValue());
            }

        }
    }


    public static void viewStudentOverallGrades(Class c)
    {
        Map<String, Double> grades = c.getStudentAverages();
        String currentStudentID;
        Student currentStudent;
        Double currentGrade;

        Iterator<String> it = grades.keySet().iterator();

        while(it.hasNext())
        {
            currentStudentID = it.next();
            currentGrade = grades.get(currentStudentID);
            currentStudent = PowerSchool.allStudents.get(currentStudentID);
            System.out.print(currentStudent.getFirstName()+" "+currentStudent.getLastName());
            if(currentGrade == -2.0)
                System.out.println("\t\tN/A");
            else
                System.out.println("\t\t" + currentGrade);
        }
    }

    public static int viewStudentsInClass(Class c)
    {
        int numStudents = 0;
        Map<String, Student> students = c.getStudents();
        String currentStudentID;
        Student currentStudent;

        Iterator<String> it = c.getStudents().keySet().iterator();

        while(it.hasNext())
        {
            numStudents++;
            currentStudentID = it.next();
            currentStudent = PowerSchool.allStudents.get(currentStudentID);
            System.out.print(currentStudent.getFirstName()+" "+currentStudent.getLastName());
            System.out.println("\t\t"+currentStudentID);
        }
        return numStudents;
    }


    public static int viewAllCourses()
    {
        int numCourses = 0;
        String currentCourseID;
        Course currentCourse;

        Iterator<String> it = PowerSchool.allCourses.keySet().iterator();

        while(it.hasNext())
        {
            numCourses++;
            currentCourseID = it.next();
            currentCourse = PowerSchool.allCourses.get(currentCourseID);
            System.out.println(currentCourse.getName()+"\t\t"+currentCourse.getCourseID());
        }
        return numCourses;
    }


    public static int viewAllClasses()
    {
        int numClasses = 0;
        String currentClassID;
        Class currentClass;

        Iterator<String> it = PowerSchool.allClasses.keySet().iterator();

        while(it.hasNext())
        {
            numClasses++;
            currentClassID = it.next();
            currentClass = PowerSchool.allClasses.get(currentClassID);
            System.out.println(currentClass.getCourse().getName()+"\t\t"+currentClassID);
        }
        return numClasses;

    }


    public static int viewAllStudentsWithSchedules()
    {
        int numStudents = 0;
        String currentStudentID;
        Student currentStudent;

        Iterator<String> it = PowerSchool.allStudents.keySet().iterator();

        while(it.hasNext())
        {
            numStudents++;
            currentStudentID = it.next();
            currentStudent = PowerSchool.allStudents.get(currentStudentID);
            System.out.println("\n\n\n");
            System.out.print(currentStudent.getFirstName()+" "+currentStudent.getLastName());
            System.out.println("\t\t"+currentStudentID);
            currentStudent.printScheduleWithoutGrades();
        }
        return numStudents;
    }


    public static int viewAllTeachersWithSchedules()
    {
        int numTeachers = 0;
        String currentTeacherID;
        Teacher currentTeacher;

        Iterator<String> it = PowerSchool.allTeachers.keySet().iterator();

        while(it.hasNext())
        {
            numTeachers++;
            currentTeacherID = it.next();
            currentTeacher = PowerSchool.allTeachers.get(currentTeacherID);
            System.out.println("\n\n\n");
            System.out.print(currentTeacher.getFirstName()+" "+currentTeacher.getLastName());
            System.out.println("\t\t"+currentTeacherID);
            currentTeacher.printSchedule();
        }
        return numTeachers;
    }





    // ************************************************************************************************************************************
    public static void teacherSearchOptions(Teacher user)
    {
        int function = 0;
        //Search classes, students, or teachers
        System.out.println("\nNote: functions 1-5 examine the relevant data in the ENTIRE database, not just for your particular classes or students.");
        while (function != -1)
        {
            System.out.println("\n");
            System.out.println("             <><><> SEARCH OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Search for a particular class by ID.");
            System.out.println("\t2. Search for a particular student by name.");
            System.out.println("\t3. Search for a particular student by ID.");
            System.out.println("\t4. Search for a particular teacher by name.");
            System.out.println("\t5. Search for a particular teacher by ID.");
            System.out.println("\t6. Search for a particular assignment by ID (your assignments only).");
            System.out.println("\t7. Search for a particular course by ID.");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // search for a class by ID
                    Class c = searchClassesByID(reader.readLine("Enter the ID of the class for which you are searching"));
                    if(c == null)
                        break;
                    printClassData(c);
                    break;
                case 2: // search for a student by name
                    searchStudentsByName(reader.readLine("Enter the name of the student for whom you are searching"));
                    break;
                case 3: // search for a student by ID
                    Student s = searchStudentsByID(reader.readLine("Enter the ID of the student for whom you are searching"));
                    if(s == null)
                        break;
                    printStudentData(s);
                    System.out.println("");
                    s.printScheduleWithoutGrades();
                    break;
                case 4: // search for a teacher by name
                    searchTeachersByName(reader.readLine("Enter the name of the teacher for whom you are searching"));
                    break;
                case 5: // search for a teacher by ID
                    Teacher t = searchTeachersByID(reader.readLine("Enter the ID of the teacher for whom you are searching"));
                    if(t == null)
                        break;
                    printTeacherData(t);
                    System.out.println("");
                    t.printSchedule();
                    break;
                case 6: // search for an assignment by ID
                    Assignment a = searchAssignmentsByID(reader.readLine("Enter the ID of the assignment for which you are searching"), user);
                    // TODO create none found for valid ID case
                    if(a == null)
                        break;
                    printAssignmentData(a);
                    break;
                case 7: // search for a course by ID
                    Course co = searchAllCoursesByID(reader.readLine("Enter the ID of the course for which you are searching"));
                    if(co == null)
                        break;
                    printCourseData(co);
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static Class searchClassesByID(String id)
    {
        boolean valid = false;
        char tryAgain;
        do
        {
            for(String s: PowerSchool.allClasses.keySet())
            {
                if(id.equals(s))
                {
                    valid = true;
                    break;
                }
            }
            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the ID of the class for which you are searching");
            }

        }while(!valid);

        Class c = PowerSchool.allClasses.get(id);
        return c;
    }


    public static Student searchStudentsByID(String id)
    {
        boolean valid = false;
        char tryAgain;
        do
        {
            for(String s: PowerSchool.allStudents.keySet())
            {
                if(id.equals(s))
                {
                    valid = true;
                    break;
                }
            }
            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the ID of the student for whom you are searching");
            }

        }while(!valid);

        Student s = PowerSchool.allStudents.get(id);
        return s;

    }


    public static void searchStudentsByName(String name) // also prints out info
    {
        ArrayList<Integer> matches = new ArrayList<>(); // indexes of matches
        ArrayList<Student> values = new ArrayList<>();
        for(Student s: PowerSchool.allStudents.values())
        {
            values.add(s);
        }
        //ArrayList<Student> values = (ArrayList<Student>) PowerSchool.allStudents.values();

        char tryAgain; // if no matches are found, user will be prompted to try again or not
        do
        {
            matches = Searcher.searchForString(values, name);
            if(matches.size() == 0) // if no matches are found, ask to either try again or return to search methods menu
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return;
                }
                name = reader.readLine("Enter the name of the student for whom you are searching");
            }
            else
            {
                for(int i=0;i<matches.size();i++) // prints out info of matches
                {
                    // print out the name of the student in index i of the list of indices that the search method returned
                    System.out.println("Found " + values.get(matches.get(i)).getFirstName()+" "+values.get(matches.get(i)).getLastName());
                    System.out.println("\tStudent ID: " + values.get(matches.get(i)).getStudentID());
                    System.out.println("\tGraduating Class: " + values.get(matches.get(i)).getYearGraduate());
                    System.out.println("\tGPA: " + values.get(matches.get(i)).getGpa());
                    System.out.println();
                    values.get(matches.get(i)).printScheduleWithoutGrades();
                }
            }
        } while(matches.size()==0);
    }


    public static void searchTeachersByName(String name) // also prints out info
    {
        ArrayList<Integer> matches = new ArrayList<>(); // indexes of matches
        ArrayList<Teacher> values = new ArrayList<>();
        for(Teacher t: PowerSchool.allTeachers.values())
        {
            values.add(t);
        }
        //ArrayList<Teacher> values = (ArrayList<Teacher>) PowerSchool.allTeachers.values();

        char tryAgain; // if no matches are found, user will be prompted to try again or not
        do
        {
            matches = Searcher.searchForString(values, name);
            if(matches.size() == 0) // if no matches are found, ask to either try again or return to search methods menu
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return;
                }
                name = reader.readLine("Enter the name of the teacher for whom you are searching");
            }
            else
            {
                for(int i=0;i<matches.size();i++) // prints out names of matches
                {
                    // print out the info of the teacher in index i of the list of indices that the search method returned
                    System.out.println("Found "+values.get(matches.get(i)).getFirstName()+" "+values.get(matches.get(i)).getLastName());
                    System.out.println("\tTeacher ID: "+values.get(matches.get(i)).getTeacherID());
                    System.out.println();
                    values.get(matches.get(i)).printSchedule();
                }
            }
        } while(matches.size()==0);

    }


    public static Teacher searchTeachersByID(String id)
    {
        boolean valid = false;
        char tryAgain;
        do
        {
            for(String s: PowerSchool.allTeachers.keySet())
            {
                if(id.equals(s))
                {
                    valid = true;
                    break;
                }
            }
            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the ID of the teacher for whom you are searching");
            }

        }while(!valid);

        Teacher t = PowerSchool.allTeachers.get(id);
        return t;

    }


    public static Assignment searchAssignmentsByID(String id, Teacher user)
    {
        boolean valid = false;
        char tryAgain;
        Class classAssignmentBelongsTo = null;
        do
        {
            for(Class c: user.getClasses().values())
            {
                for(String s: c.getAssignments().keySet())
                {
                    if(id.equals(s))
                    {
                        valid = true;
                        classAssignmentBelongsTo = c;
                        break;
                    }
                }
            }

            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the ID of the assignment for which you are searching");
            }

        }while(!valid);

        Assignment a = classAssignmentBelongsTo.getAssignments().get(id);
        return a;
    }


    // *** Not used in menu but necessary for methods ***
    public static Class searchTaughtClassesByID(String id, Teacher user)
    {
        boolean valid = false;
        char tryAgain;
        do
        {
            for(Class c: user.getClasses().values())
            {
                for(String s: user.getClasses().keySet())
                {
                    if(id.equals(s))
                    {
                        valid = true;
                        break;
                    }
                }
            }

            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the ID of the class for which you are searching");
            }

        }while(!valid);

        Class c = PowerSchool.allClasses.get(id);
        return c;
    }


    public static Course searchAllCoursesByID(String id)
    {
        boolean valid = false;
        char tryAgain;
        do
        {
            for(String s: PowerSchool.allCourses.keySet())
            {
                if(id.equals(s))
                {
                    valid = true;
                    break;
                }
            }
            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the ID of the course for which you are searching");
            }

        }while(!valid);

        Course c = PowerSchool.allCourses.get(id);
        return c;
    }


    public static void printStudentData(Student s)
    {
        System.out.println("Found " + s.getStudentID());
        System.out.println("\tName: " + s.getFirstName() + " " + s.getLastName());
        System.out.println("\tGraduating Class: " + s.getYearGraduate());
        System.out.println("\tGPA: " + s.getGpa());
    }

    public static void printTeacherData(Teacher t)
    {
        System.out.println("Found " + t.getTeacherID());
        System.out.println("\tName: " + t.getFirstName() + " " + t.getLastName());
    }

    public static void printClassData(Class c)
    {
        System.out.println("Found " + c.getId());
        System.out.println("\tCourse: " + c.getCourse().getName());
        System.out.println("\tCourse ID: " + c.getCourse().getCourseID());
        System.out.println("\tTeacher: " + c.getTeacher().getFirstName() + " " + c.getTeacher().getLastName());
        System.out.println("\tPeriod: " + (c.getPeriodHeld()+1));
        System.out.println("\tNumber of Students: " + c.getStudents().size());
    }

    public static void printAssignmentData(Assignment a)
    {
        System.out.println("Found " + a.getId());
        System.out.println("\t" + a.getName());
        System.out.println("\tDate: " + a.getDate());
        System.out.println("\tCategory: " + a.getCategory());
        System.out.println("\t" + a.getPointValue() + " points");

    }

    public static void printCourseData(Course c)
    {
        System.out.println("Found " + c.getCourseID());
        System.out.println("\t" + c.getName());

    }




    // ************************************************************************************************************************************
    public static void teacherAssignmentOptions(Teacher user)
    {
        int function = 0;
        //Add, delete, or modify assignments for a specific class
        System.out.println("\nNote: these functions examine the relevant data that applies only to YOU, not for the entire database.");
        while (function != -1)
        {
            System.out.println("\n");
            System.out.println("           <><><> ASSIGNMENT OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Add a new assignment.");
            System.out.println("\t2. Modify an existing assignment.");
            System.out.println("\t3. Delete an existing assignment.");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // add a new assignment
                    addAssignment(user);
                    break;
                case 2: // modify an assignment
                    modifyAssignment(user);
                    break;
                case 3: // delete an assignment
                    deleteAssignment(user);
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static void addAssignment(Teacher user)
    {
        String classID;
        int numMatches;
        char choice;

        System.out.println("Here is a list of all your classes and their respective IDs:");
        numMatches = viewTaughtClasses(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nTo which class would you like to add this assignment? (Enter the ID number, -1 to go back to menu)");
        classID = reader.readLine("Class");
        if(classID.equals("-1"))
        {
            return;
        }
        Class c = searchTaughtClassesByID(classID, user);
        if(c == null)
        {
            return;
        }

        String id = c.getId() + c.getAssignmentGenerationIDIndex();
        System.out.println("A new assignment will be created for this class with an ID of " + id + ".");
        c.increaseAssignmentGenerationIDIndex();

        String name = reader.readLine("What is the name of the assignment?");
        String date = reader.readLine("What is the date of the assignment?");
        String category = reader.readLine("What is the category of the assignment? (Test, Quiz, Project, etc.)");
        System.out.print("How many points is this assignment worth?");
        double pointValue = reader.readDouble();

        Assignment a = new Assignment(name, id, date, c.getId(), category, pointValue);
        c.getAssignments().put(id, a);

        System.out.print("Assignment added! Would you like to enter your students' grades now? ('y' for now, 'n' for later)");
        initializeGradeForClassOfStudentsOnAssignment(a, c);
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Edit grades? ('y' to edit, 'n' to go back to menu)");
            choice = reader.readChar();
        }
        if(choice == 'n')
        {
            return;
        }

        addGradeForClassOfStudentsOnAssignment(a, c);

    }


    public static void deleteAssignment(Teacher user)
    {
        String classID, assignmentID;
        char choice;
        int numMatches;

        System.out.println("Here is a list of all your classes and their respective IDs:");
        numMatches = viewTaughtClasses(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nFrom which class would you like to delete an assignment? (Enter the ID number, -1 to go back to menu)");
        classID = reader.readLine("Class");
        if(classID.equals("-1"))
        {
            return;
        }
        Class c = searchTaughtClassesByID(classID, user);
        if(c == null)
        {
            return;
        }

        System.out.println("\nHere is a list of all assignments for this class and their respective IDs:");
        numMatches = viewAssignmentsForClass(c);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nWhich assignment would you like to delete? (Enter the ID number, -1 to go back to menu)");
        assignmentID = reader.readLine("Assignment");
        if(assignmentID.equals("-1"))
        {
            return;
        }
        Assignment a = searchAssignmentsByID(assignmentID, user);
        if(a == null)
        {
            return;
        }
        printAssignmentData(a);

        System.out.print("\nAre you sure you wish to delete this assignment? ('y' to delete, 'n' to go back to menu)");
        choice = reader.readChar();

        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.println("Delete this assignment? ('y' to delete, 'n' to go back to menu)");
            choice = reader.readChar();
        }
        if(choice == 'n')
        {
            return;
        }
        deletedAssignmentCleanup(a, c);
        System.out.println("Assignment deleted!");


    }


    public static void modifyAssignment(Teacher user)
    {
        String classID, assignmentID;
        int numMatches;

        System.out.println("Here is a list of all your classes and their respective IDs:");
        numMatches = viewTaughtClasses(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nFrom which class would you like to modify an assignment? (Enter the ID number, -1 to go back to menu)");
        classID = reader.readLine("Class");
        if(classID.equals("-1"))
        {
            return;
        }
        Class c = searchTaughtClassesByID(classID, user);
        if(c == null)
        {
            return;
        }

        System.out.println("\nHere is a list of all assignments for this class and their respective IDs:");
        numMatches = viewAssignmentsForClass(c);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nWhich assignment would you like to modify? (Enter the ID number, -1 to go back to menu)");
        assignmentID = reader.readLine("Assignment");
        if(assignmentID.equals("-1"))
        {
            return;
        }
        Assignment a = searchAssignmentsByID(assignmentID, user);
        if(a == null)
        {
            return;
        }

        int function = 0;
        while (function != -1)
        {
            printAssignmentData(a);
            System.out.println("\n");
            System.out.println("           <><><> MODIFY OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("What do you want to modify?");
            System.out.println("\t1. Name.");
            System.out.println("\t2. Date.");
            System.out.println("\t3. Category.");
            System.out.println("\t4. Point value.");
            System.out.println("\t-1. Stop modifying.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // change name
                    String name = reader.readLine("Enter the new name for this assignment (-1 to cancel)");
                    if(name.equals("-1"))
                        break;
                    a.setName(name);
                    System.out.println("Name changed!\n");
                    break;
                case 2: // change date
                    String date = reader.readLine("Enter the new date for this assignment (-1 to cancel)");
                    if(date.equals("-1"))
                        break;
                    a.setDate(date);
                    System.out.println("Date changed!\n");
                    break;
                case 3: // change category
                    String category = reader.readLine("Enter the new category for this assignment (-1 to cancel)");
                    if(category.equals("-1"))
                        break;
                    a.setCategory(category);
                    System.out.println("Category changed!\n");
                    break;
                case 4: // change point value
                    System.out.print("Enter the new point value for this assignment (-1 to cancel)");
                    double value = reader.readDouble(-1, 1000000000);
                    if(value == (-1))
                        break;
                    a.setPointValue(value);
                    System.out.println("Point value changed! Be sure to change your students' individual grades if necessary.");
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    return;
            }

        }





    }




    // ************************************************************************************************************************************
    public static void teacherGradeOptions(Teacher user)
    {

        int check = 0;
        //Add, delete, or modify grades for assignments for a specific class
        while (check != -1)
        {
            System.out.println("\n");
            System.out.println("            <><><> GRADE OPTIONS <><><>");
            System.out.println("*****************************************************");
            /*//System.out.println("\t1. Add a grade for a particular student for a particular assignment.");
            System.out.println("\t1. Add/Modify a grade for a particular student on a particular assignment.");
            System.out.println("\t2. Delete a grade for a particular student on a particular assignment (i.e. nullify the grade).");
            System.out.println("\t-1. Return to the main menu.");*/
            System.out.println("\tPick a class that you teach (see list below) and choose one.");
            System.out.println("\tNext, pick an assignment that you want to modify grades for.");
            System.out.println("\tThen, pick the student whose grade you want to modify.");
            System.out.println("\tEnter -1 at any time to return to the main menu.");
            System.out.println("*****************************************************");
            System.out.println();

            check = modifyGrade(user);

            System.out.println("Returning to main menu...\n");


        }
    }


    public static int modifyGrade(Teacher user)
    {
        String classID;
        String assignmentID;
        String studentID;
        int numMatches;

        System.out.println("Here is a list of all your classes and their respective IDs:");
        numMatches = viewTaughtClasses(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return -1;
        }

        System.out.println("\nFrom which class would you like to modify a grade? (Enter the ID number, -1 to go back to menu)");
        classID = reader.readLine("Class");
        if(classID.equals("-1"))
        {
            return -1;
        }
        Class c = searchTaughtClassesByID(classID, user);
        if(c == null)
        {
            return -1;
        }

        System.out.println("\nHere is a list of all assignments for this class and their respective IDs:");
        numMatches = viewAssignmentsForClass(c);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return -1;
        }

        System.out.println("\nFor which assignment would you like to modify a grade? (Enter the ID number, -1 to go back to menu)");
        assignmentID = reader.readLine("Assignment");
        if(assignmentID.equals("-1"))
        {
            return -1;
        }
        Assignment a = searchAssignmentsByID(assignmentID, user);
        if(a == null)
        {
            return -1;
        }

        System.out.println("\nHere is a list of all students for this class and their respective IDs:");
        numMatches = viewStudentsInClass(c);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return -1;
        }

        System.out.println("\nFor which student would you like to modify a grade? (Enter the ID number, -1 to go back to menu)");
        studentID = reader.readLine("Student");
        if(studentID.equals("-1"))
        {
            return -1;
        }
        Student s = searchStudentsByID(studentID);
        if(s == null)
        {
            return -1;
        }

        double currentStudentGrade = a.getEntireStudentPoints().get(s.getStudentID());
        System.out.println(s.getFirstName() + " " + s.getLastName() + "'s current grade on " + a.getName() + " is:");

        if(currentStudentGrade == -2.0)
        {
            System.out.println("Exempt");
        }
        else
        {
            System.out.println(currentStudentGrade + " / " + a.getPointValue());
        }
        System.out.print("\nTo what would you like to change the grade? (Enter -2 to make the grade exempt, -1 to cancel)");
        double newGrade;
        newGrade = reader.readDouble(-100000000, 100000000);

        if(newGrade == -1)
            return -1;
        else if(newGrade == -2)
        {
            System.out.println("The grade will be 'exempt'.");
        }
        else if(newGrade <= -3)
        {
            while (newGrade <= -3) // check for invalid input
            {
                System.out.println("Negative grades other than those mentioned above are not valid.");
                System.out.print("\nTo what would you like to change the grade to? (Enter -2 to make the grade exempt, -1 to cancel)");
                newGrade = reader.readDouble();
            }
        }


        a.setStudentGradeForAssignment(s, newGrade);
        System.out.println(s.getFirstName() + "'s grade has been changed!");


        //a.getEntireStudentPoints().put()

        return -1;
    }

    public static void addGradeForStudentOnAssignment(Assignment a, Student s, double grade)
    {
        //Map<String, Double> studentPoints = a.getEntireStudentPoints();
        a.putStudentGradeInAssignment(s, grade);
    }

    public static void addGradeForClassOfStudentsOnAssignment(Assignment a, Class c)
    {
        Map<String, Student> studentsInClass = c.getStudents();
        double grade;
        System.out.println("Enter -1 to stop at any time, or enter -2 to keep the grade marked as exempt (weightless) - extra credit is valid.");
        for(Student s: studentsInClass.values())
        {
            System.out.print("Enter the grade for " + s.getFirstName() + " " + s.getLastName() + " on this assignment (out of " + a.getPointValue() + ")");
            grade = reader.readDouble(-2.0, 1000000000);
            if(grade == -1.0)
            {
                return;
            }
            addGradeForStudentOnAssignment(a, s, grade);
        }
    }

    public static void initializeGradeForClassOfStudentsOnAssignment(Assignment a, Class c)
    {
        Map<String, Student> studentsInClass = c.getStudents();
        double grade;
        for(Student s: studentsInClass.values())
        {
            grade = -2.0;
            addGradeForStudentOnAssignment(a, s, grade);
        }
    }




    // ************************************************************************************************************************************
    public static void teacherClassOptions(Teacher user)
    {
        int function = 0;
        //Add, delete, or modify classes
        while (function != -1)
        {
            System.out.println("\n");
            System.out.println("            <><><> CLASS OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Add a new class.");
            System.out.println("\t2. Enroll students in a class.");
            System.out.println("\t3. Delete an existing class.");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // add a new class
                    addClass();
                    break;
                case 2: // enroll students in a class
                    enroll();
                    break;
                case 3: // delete a class
                    deleteClass();
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static void addClass()
    {
        char choice;
        int numMatches;
        String teacherID;
        String courseID;

        String id = PowerSchool.numClassesExisted+1+"";
        Teacher teacher;
        Course course;
        int periodHeld;
        System.out.println("A new class will be created with an ID of " + id + ".");

        System.out.println("Here is a list of all courses in the school:");
        numMatches = viewAllCourses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data. A class must be created from a course.");
            return;
        }
        System.out.print("\nWhat is the course that the class is based on? (Enter the ID, -1 to go back to menu)");
        courseID = reader.readLine();
        if(courseID.equals("-1"))
            return;
        course = searchAllCoursesByID(courseID);
        if(course == null)
        {
            return;
        }


        System.out.println("Here is a list of all teachers in the school:");
        numMatches = viewAllTeachers();
        if(numMatches == 0)
        {
            System.out.println("No relevant data. There must be a teacher before a class can be created.");
            return;
        }
        System.out.print("\nWho is the teacher of this class? (Enter the ID)");
        teacherID = reader.readLine();
        teacher = searchTeachersByID(teacherID);
        if(teacher == null)
        {
            return;
        }

        System.out.print("What period will this class be held? (Enter a number between 1 and 7)");
        periodHeld = reader.readInt(1, 7)-1;
        Class[] teacherSchedule = teacher.getSchedule();


        while(!canFitInTeacherSchedule(teacher, periodHeld))
        {
            periodHeld = reader.readInt("Enter a different period (-1 to go back to menu)")-1;
            if(periodHeld == -2)
            {
                return;
            }
        }


        Class c = new Class(course, teacher, id, periodHeld);
        PowerSchool.allClasses.put(id, c);
        enrollTeacherInClass(teacher, c);
        System.out.println("Class created!");

        System.out.print("All new classes start out empty; would you like to enroll some students now? ('y' to enroll, 'n' to go back to menu')");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Enroll students? ('y' to go, 'n' to go back to menu)");
            choice = reader.readChar();
        }
        if(choice == 'n')
        {
            return;
        }

        enrollMultipleStudentsInClass(c);

    }


    public static void deleteClass()
    {
        int numMatches;
        String classID;
        char choice;

        System.out.println("Here is a list of all classes in the school and their respective IDs:");
        numMatches = viewAllClasses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nWhich class would you like to delete? (Enter the ID, -1 to go back to menu)");
        classID = reader.readLine();
        if(classID.equals("-1"))
        {
            return;
        }
        Class c = searchClassesByID(classID);
        if(c == null)
        {
            return;
        }
        printClassData(c);
        System.out.print("\nAre you sure you wish to delete this class? ('y' to delete, 'n' to go back to menu)");
        choice = reader.readChar();

        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Delete this class? ('y' to delete, 'n' to go back to menu)");
            choice = reader.readChar();
        }
        if(choice == 'n')
        {
            return;
        }

        PowerSchool.allClasses.remove(classID);
        cleanSchedules(c);
        System.out.println("Class deleted!");
    }

    public static void cleanSchedules(Class c)
    {
        Map<String, Student> studentsInClass = c.getStudents();
        Teacher t = c.getTeacher();

        // Clean students'
        for(Student s: studentsInClass.values())
        {
            s.deScheduleStudent(c);
        }
        t.deScheduleTeacher(c);
    }

    public static void enroll() // When enroll students option is picked from Class menu
    {
        int numMatches;
        String classID;

        System.out.println("Here is a list of all classes in the school and their respective IDs:");
        numMatches = viewAllClasses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nTo which class would you like to enroll students? (Enter the ID)");
        classID = reader.readLine();
        Class c = searchClassesByID(classID);
        if(c == null)
        {
            return;
        }

        enrollMultipleStudentsInClass(c);
    }


    public static void enrollMultipleStudentsInClass(Class c)
    {
        char addAnother = 'y', choice;
        int numMatches;

        System.out.println("Here is a list of all students in the school and their matching IDs:");
        numMatches = viewAllStudents();
        if(numMatches == 0)
        {
            System.out.println("No relevant data found. Students cannot be enrolled if none exist.");
            return;
        }
        System.out.println();

        while(addAnother == 'y')
        {
            Student s = checkIfStudentCanBeEnrolled(c);
            if(s == null)
                return;
            while(s.getYearGraduate() == 0)
            {
                s = checkIfStudentCanBeEnrolled(c);
                if(s == null)
                    return;
            }
            enrollStudentInClass(s, c);
            System.out.println(s.getStudentID() + " successfully enrolled!");
            System.out.print("Enroll another? ('y' for yes, 'n' for no)");
            addAnother = reader.readChar();
            while (addAnother != 'y' && addAnother != 'n') // check for invalid input
            {
                System.out.println("Error, please enter a valid input.");
                System.out.print("Try again? ('y' to try again, 'n' to go back to menu)");
                addAnother = reader.readChar();
                if(addAnother == 'n')
                    return;
            }
        }
    }


    public static Student checkIfStudentCanBeEnrolled(Class c) // return student for "can be enrolled", null to stop trying ; checks if already in class and if schedule is full at period held
    {
        String studentID;
        char choice;
        int canBeEnrolled = 0;

        System.out.print("Enter the ID of the student you want to enroll (you cannot add students who are already in the class or who have conflicting schedules)");
        studentID = reader.readLine();
        Student s = searchStudentsByID(studentID);
        if(s == null)
            return null;
        while(c.getStudents().containsKey(studentID)) // check if in class
        {
            if(c.getStudents().containsKey(studentID))
            {
                System.out.print("That student is already in this class. Try again? (Enter 'y' for yes, 'n' to go back to the class menu)"); //TODO Fix so that this does not return to menu when entering no
                choice = reader.readChar();
                while (choice != 'y' && choice != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    System.out.print("Try again? ('y' to try again, 'n' to go back to menu)");
                    choice = reader.readChar();
                }
                if(choice == 'n')
                    return null;

            }
            System.out.print("Enter the ID of the student you want to enroll (you cannot add students who are already in the class or who have conflicting schedules)");
            studentID = reader.readLine();
            s = searchStudentsByID(studentID);
        }

        boolean canFit;
        canFit = canFitInStudentSchedule(s, c.getPeriodHeld()); // check if student schedule is open
        if(!canFit)
        {
            System.out.print("Try another student? (Enter 'y' for yes, 'n' for no)");
            choice = reader.readChar();
            while (choice != 'y' && choice != 'n') // check for invalid input
            {
                System.out.println("Error, please enter a valid input.");
                System.out.print("Try again? ('y' to try again, 'n' to go back to menu)");
                choice = reader.readChar();
            }
            if(choice == 'n')
                return null;
            else if(choice == 'y')
            {
                s = new Student();
                s.setYearGraduate(0); // this is a fake student with default constructor designed as a check for whether or not the check needs to be run again
            }
        }
        return s;
    }


    public static void enrollStudentInClass(Student s, Class c)
    {
        c.putStudentInClass(s);
        s.giveClassToStudent(c);
        s.scheduleStudent(c);
        c.initializeStudentAverageForNewStudent(s);
        for(Assignment a: c.getAssignments().values())
        {
            a.addAssignmentForStudent(s); // sets student grade for all existing assignments to exempt
        }

    }


    public static void enrollTeacherInClass(Teacher t, Class c)
    {
        t.scheduleTeacher(c);
        t.addToNewClass(c);
    }




    // ************************************************************************************************************************************
    public static void teacherCourseOptions(Teacher user)
    {
        int function = 0;
        //Add, delete, or modify courses
        while (function != -1)
        {
            System.out.println("\n");
            System.out.println("            <><><> COURSE OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Add a new course.");
            System.out.println("\t2. Modify an existing course.");
            System.out.println("\t3. Delete an existing course (CAUTION - HAS BRANCHING EFFECTS).");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // add a new course
                    addCourse();
                    break;
                case 2: // modify a course
                    modifyCourse();
                    break;
                case 3: // delete a course
                    deleteCourse();
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static void addCourse()
    {
        String id;
        String name;

        System.out.print("Enter the name for the new course (-1 to go back to menu)");
        name = reader.readLine();
        if(name.equals("-1"))
            return;

        System.out.println("By convention, course IDs are 4 capital letters followed by 2 numbers.");
        System.out.print("Enter the id for the new course (-1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;

        Course c = new Course(name, id);
        PowerSchool.allCourses.put(id, c);

    }


    public static void modifyCourse()
    {
        String id;
        Course c;
        int numMatches = 0;

        System.out.println("Here is a list of all courses in the school:");
        numMatches = viewAllCourses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }
        System.out.print("\nWhat is the course that you want to modify? (Enter the ID, -1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;
        c = searchAllCoursesByID(id);
        if(c == null)
        {
            return;
        }

        int function = 0;
        while (function != -1)
        {
            printCourseData(c);
            System.out.println("\n");
            System.out.println("           <><><> MODIFY OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("What do you want to modify?");
            System.out.println("\t1. Name.");
            System.out.println("\t2. ID.");
            System.out.println("\t-1. Stop modifying.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // change name
                    String newName = reader.readLine("Enter the new name for this course (-1 to cancel)");
                    if(newName.equals("-1"))
                        break;
                    c.setName(newName);
                    System.out.println("Name changed!\n");
                    break;
                case 2: // change ID
                    String newID = reader.readLine("Enter the new ID for this course (-1 to cancel)");
                    if(newID.equals("-1"))
                        break;
                    PowerSchool.allCourses.remove(id);
                    c.setCourseID(newID);
                    PowerSchool.allCourses.put(newID, c);
                    System.out.println("ID changed!\n");
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    return;
            }

        }

    }


    public static void deleteCourse()
    {
        char choice;
        int numMatches;
        String id;
        Course c;

        System.out.println("WARNING: DELETING A COURSE WILL ALSO DELETE ALL OF ITS AFFILIATED CLASSES.");
        System.out.println("THIS MEANS ALL OF THOSE CLASSES' ASSIGNMENTS AND GRADES WILL BE LOST FOREVER.");
        System.out.println("MAKE SURE THAT YOU REALLY WANT TO DELETE A COURSE BEFORE CHOOSING TO, AS THIS CANNOT BE UNDONE.");

        System.out.print("\nReturn to the menu? (Enter 'y' to go back, 'n' to continue with deleting a course.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Return to the menu? ('y' to go back, 'n' to continue with deleting a course)");
            choice = reader.readChar();
        }
        if(choice == 'y')
            return;

        System.out.println("Here is a list of all courses in the school:");
        numMatches = viewAllCourses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nWhat is the course that you want to delete? (Enter the ID, -1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;
        c = searchAllCoursesByID(id);
        if(c == null)
        {
            return;
        }

        printCourseData(c);
        System.out.print("\nReally delete this course? (Enter 'y' to delete, 'n' to go back to menu.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really delete this course? (Enter 'y' to delete, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        PowerSchool.allCourses.remove(id);
        deletedCourseCleanup(c);

        System.out.println("Course deleted!");


    }


    public static void deletedCourseCleanup(Course c)
    {
        for(Class cl: c.getClassesOfThisCourse())
        {
            PowerSchool.allClasses.remove(cl.getId());
            cleanSchedules(cl);
        }

    }





    public static void teacherStudentOptions(Teacher user)
    {
        int function = 0;
        //Add, delete, or modify a student's data in the school
        while (function != -1)
        {
            TeacherOptions.calculateEveryStudentAverage();
            TeacherOptions.calculateEveryStudentGPA();

            System.out.println("\n");
            System.out.println("           <><><> STUDENT OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Add a new student to the school.");
            System.out.println("\t2. Modify an existing student.");
            System.out.println("\t3. Remove an existing student from the entire school.");
            System.out.println("\t4. Remove an existing student from a particular class.");
            System.out.println("\t5. Enroll an existing student in a class.");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // add a new student
                    addStudent();
                    break;
                case 2: // modify a student
                    modifyStudent();
                    break;
                case 3: // remove a student from the school
                    deleteStudent();
                    break;
                case 4: // remove a student from a class
                    removeStudentFromClass();
                    break;
                case 5: // enroll student in class
                    enrollOneAtATime();
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static void addStudent()
    {
        String firstName, lastName;
        int yearGraduate;

        System.out.println("A new student will be added.");
        System.out.print("Enter the first name of the new student");
        firstName = reader.readLine();
        System.out.print("Enter the last name of the new student");
        lastName = reader.readLine();
        System.out.print("What year will the student graduate? (2016, 2017, 2018, 2019)");
        yearGraduate = reader.readInt(2016,2019);

        Student s = new Student(firstName, lastName, yearGraduate);
        PowerSchool.allStudents.put(s.getStudentID(), s);

        System.out.println("New student added with ID " + s.getStudentID() + ".");
        System.out.println("Don't forget to enroll this student in some classes and update their grades on any existing assignments in those classes!");
    }


    public static void modifyStudent()
    {
        String id;
        Student s;
        int numMatches = 0;

        System.out.println("Here is a list of all students in the school:");
        numMatches = viewAllStudents();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }
        System.out.print("\nWhat is the student whose data you want to modify? (Enter the ID, -1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;
        s = searchStudentsByID(id);
        if(s == null)
        {
            return;
        }

        int function = 0;
        while (function != -1)
        {
            printStudentData(s);
            System.out.println("\n");
            System.out.println("           <><><> MODIFY OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("What do you want to modify?");
            System.out.println("\t1. First name.");
            System.out.println("\t2. Last name.");
            System.out.println("\t-1. Stop modifying.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // change first name
                    String newFirstName = reader.readLine("Enter the new first name for this student (-1 to cancel)");
                    if(newFirstName.equals("-1"))
                        break;
                    s.setFirstName(newFirstName);
                    System.out.println("First name changed!\n");
                    break;
                case 2: // change last name
                    String newLastName = reader.readLine("Enter the new last name for this student (-1 to cancel)");
                    if(newLastName.equals("-1"))
                        break;
                    s.setLastName(newLastName);
                    s.setUsername(newLastName);
                    System.out.println("Last name changed!\n");
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    return;
            }

        }

    }


    public static void deleteStudent()
    {
        String id;
        Student s;
        char choice;
        int numMatches = 0;

        System.out.println("Here is a list of all students in the school:");
        numMatches = viewAllStudents();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }
        System.out.print("\nWhat is the student who you wish to delete? (Enter the ID, -1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;
        s = searchStudentsByID(id);
        if(s == null)
        {
            return;
        }

        printStudentData(s);
        System.out.print("\nReally delete this student? (Enter 'y' to delete, 'n' to go back to menu.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really delete this student? (Enter 'y' to delete, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        PowerSchool.allStudents.remove(id);
        deletedStudentCleanup(s);

        System.out.println("Student deleted!");


    }


    public static void removeStudentFromClass()
    {
        int numMatches;
        String classID;
        String studentID;
        boolean valid;
        char choice;

        System.out.println("Here is a list of all classes in the school and their respective IDs:");
        numMatches = viewAllClasses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nFrom which class would you like to remove a student? (Enter the ID)");
        classID = reader.readLine();
        Class c = searchClassesByID(classID);
        if(c == null)
        {
            return;
        }

        System.out.println("Here is a list of all students in this class and their respective IDs:");
        numMatches = viewStudentsInClass(c);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nWhich student would you like to remove? (Enter the ID)");
        studentID = reader.readLine();

        Student s = searchStudentsByID(studentID);
        if(s == null)
        {
            return;
        }
        valid = studentIsInClass(s, c);
        if(!valid)
            return;

        System.out.print("Are you sure you wish to remove this student from this class? All of their grades will be lost! (Enter 'y' for yes, 'n' for no)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really remove this student? (Enter 'y' to remove, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        c.removeStudentFromClass(s);
        s.deScheduleStudent(c);

        System.out.println("Student removed!");
    }


    public static void enrollOneAtATime()
    {
        String studentID;
        String classID;
        Student s;
        char choice;
        int numMatches = 0;

        System.out.println("Here is a list of all students in the school:");
        numMatches = viewAllStudents();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }
        System.out.print("\nWhat is the student who you wish to enroll in a class? (Enter the ID, -1 to go back to menu)");
        studentID = reader.readLine();
        if(studentID.equals("-1"))
            return;
        s = searchStudentsByID(studentID);
        if(s == null)
        {
            return;
        }

        System.out.println("Here is a list of all classes in the school and their respective IDs:");
        numMatches = viewAllClasses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        boolean validPeriod = false;
        boolean notInClass = false;
        Class realClass = null;
        while(!validPeriod && !notInClass)
        {
            System.out.print("\nTo which class would you like to enroll this student? (Enter the ID, -1 to go back to menu)");
            classID = reader.readLine();
            if(classID.equals("-1"))
                return;
            Class c = searchClassesByID(classID);
            if(c == null)
            {
                return;
            }

            int period = c.getPeriodHeld();

            if(c.getStudents().containsKey(s.getStudentID()))
            {
                notInClass = false; // student is already in this class
                System.out.println("This student is already in that class.");
            }

            else for(int i = 0; i < 7; i++)
            {
                if(s.getSchedule()[period] == null)
                {
                    // fits in schedule
                    validPeriod = true;
                    realClass = c;
                    break;
                }
            }

            if(!validPeriod && notInClass) // TODO why warning here?
            {
                System.out.println("This student already has a class at that time.");
            }

        }


        System.out.print("Are you sure you wish to enroll this student in this class? (Enter 'y' to enroll, 'n' to go back to menu.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really enroll this student? (Enter 'y' to enroll, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        enrollStudentInClass(s, realClass);
        System.out.println("Student enrolled!");


    }


    public static void deletedStudentCleanup(Student s)
    {
        for(Class c: s.getClasses().values())
        {
            c.removeStudentFromClass(s);
        }

    }




    // ************************************************************************************************************************************
    public static void teacherTeacherOptions(Teacher user)
    {
        int function = 0;
        //Add, delete, or modify a teacher's data in the school (including assigning teachers to classes)
        while (function != -1)
        {
            System.out.println("\n");
            System.out.println("            <><><> TEACHER OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Add a new teacher.");
            System.out.println("\t2. Modify an existing teacher.");
            System.out.println("\t3. Remove an existing teacher from the school.");
            System.out.println("\t4. Remove an existing teacher from a particular class.");
            System.out.println("\t5. Assign a teacher a particular class.");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // add a new teacher
                    addTeacher();
                    break;
                case 2: // modify a teacher
                    modifyTeacher();
                    break;
                case 3: // remove a teacher from the school
                    deleteTeacher();
                    break;
                case 4: // remove a teacher from a class
                    removeTeacherFromClass();
                    break;
                case 5: // assign a teacher a class
                    assignTeacherClass();
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static void addTeacher()
    {
        String firstName, lastName;

        System.out.println("A new teacher will be added.");
        System.out.print("Enter the first name of the new teacher");
        firstName = reader.readLine();
        System.out.print("Enter the last name of the new teacher");
        lastName = reader.readLine();

        Teacher t = new Teacher(firstName, lastName);
        PowerSchool.allTeachers.put(t.getTeacherID(), t);

        System.out.println("New teacher added with ID " + t.getTeacherID() + ".");
        System.out.println("Don't forget to assign this teacher to some classes (they don't work for nothing!)");
    }


    public static void modifyTeacher()
    {
        int numMatches;
        String id;
        Teacher t;

        System.out.println("Here is a list of all teachers in the school:");
        numMatches = viewAllTeachers();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nWhat is the teacher whose data you want to modify? (Enter the ID, -1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;
        t = searchTeachersByID(id);
        if(t == null)
        {
            return;
        }

        int function = 0;
        while (function != -1)
        {
            printTeacherData(t);
            System.out.println("\n");
            System.out.println("           <><><> MODIFY OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("What do you want to modify?");
            System.out.println("\t1. First name.");
            System.out.println("\t2. Last name.");
            System.out.println("\t-1. Stop modifying.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // change first name
                    String newFirstName = reader.readLine("Enter the new first name for this teacher (-1 to cancel)");
                    if(newFirstName.equals("-1"))
                        break;
                    t.setFirstName(newFirstName);
                    System.out.println("First name changed!\n");
                    break;
                case 2: // change last name
                    String newLastName = reader.readLine("Enter the new last name for this teacher (-1 to cancel)");
                    if(newLastName.equals("-1"))
                        break;
                    t.setLastName(newLastName);
                    t.setUsername(newLastName);
                    System.out.println("Last name changed!\n");
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    return;
            }
        }
    }


    public static void deleteTeacher()
    {
        String id;
        Teacher t;
        char choice;
        int numMatches = 0;

        System.out.println("Here is a list of all teachers in the school:");
        numMatches = viewAllTeachers();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }
        System.out.print("\nWhat is the teacher who you wish to delete? (Enter the ID, -1 to go back to menu)");
        id = reader.readLine();
        if(id.equals("-1"))
            return;
        t = searchTeachersByID(id);
        if(t == null)
        {
            return;
        }

        printTeacherData(t);
        System.out.print("\nReally delete this teacher? (Enter 'y' to delete, 'n' to go back to menu.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really delete this teacher? (Enter 'y' to delete, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        PowerSchool.allTeachers.remove(id);
        deletedTeacherCleanup(t);

        System.out.println("Teacher deleted!");
    }


    public static void removeTeacherFromClass()
    {
        int numMatches;
        String classID;
        Teacher t;
        boolean valid = false;
        char choice;

        System.out.println("Here is a list of all classes in the school and their respective IDs:");
        numMatches = viewAllClasses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.print("\nFrom which class would you like to remove a teacher? (Enter the ID, -1 to go back)");
        classID = reader.readLine();
        if(classID.equals("-1"))
            return;
        Class c = searchClassesByID(classID);
        if(c == null)
        {
            return;
        }

        while(!valid)
        {
            if(c.getTeacher().getTeacherID().equals("-1"))
            {
                System.out.println("There is no teacher for this class.");
                valid = false;
            }
            else
            {
                valid = true;
                break;
            }
            System.out.print("\nFrom which class would you like to remove a teacher? (Enter the ID, -1 to go back)");
            classID = reader.readLine();
            if(classID.equals("-1"))
                return;
            c = searchClassesByID(classID);
            if(c == null)
            {
                return;
            }
        }
        t = c.getTeacher();

        System.out.print("Are you sure you wish to remove this teacher from this class? (Enter 'y' for yes, 'n' for no)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really remove this teacher? (Enter 'y' to remove, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        c.removeTeacherFromClass(t);
        t.deScheduleTeacher(c);
        System.out.println("Teacher removed!");

    }


    public static void deletedTeacherCleanup(Teacher t)
    {
        for(Class c: t.getClasses().values())
        {
            c.removeTeacherFromClass(t);
        }
    }


    public static void assignTeacherClass()
    {
        String teacherID;
        String classID;
        Teacher t;
        char choice;
        int numMatches = 0;

        System.out.println("Here is a list of all teachers in the school:");
        numMatches = viewAllTeachers();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }
        System.out.print("\nWhat is the teacher who you wish to assign to a class? (Enter the ID, -1 to go back to menu)");
        teacherID = reader.readLine();
        if(teacherID.equals("-1"))
            return;
        t = searchTeachersByID(teacherID);
        if(t == null)
        {
            return;
        }

        System.out.println("Here is a list of all classes in the school and their respective IDs:");
        numMatches = viewAllClasses();
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        boolean validPeriod = false;
        boolean notInClass = false;
        Class realClass = null;
        while(!validPeriod || !notInClass)
        {
            System.out.print("\nTo which class would you like to assign this teacher? New teacher will replace the old one in classes with an existing teacher. (Enter the ID, -1 to go back to menu)");
            classID = reader.readLine();
            if(classID.equals("-1"))
                return;
            Class c = searchClassesByID(classID);
            if(c == null)
            {
                return;
            }

            int period = c.getPeriodHeld();

            if(c.getTeacher() == t)
            {
                notInClass = false; // teacher is already in this class
                System.out.println("This teacher is already teaching that class.");
            }

            else for(int i = 0; i < 7; i++)
            {
                if(t.getSchedule()[period] == null)
                {
                    // fits in schedule
                    validPeriod = true;
                    realClass = c;
                    break;
                }
            }

            if(validPeriod)
            {
                break;
            }

            if(!validPeriod && notInClass) // TODO why warning here?
            {
                System.out.println("This teacher already has a class at that time.");
            }

        }

        if(!realClass.getTeacher().getTeacherID().equals("-1")) // if teacher is not unassigned
        {
            System.out.println("Enrolling that teacher in this class will replace the current teacher, " + realClass.getTeacher().getFirstName() + " " + realClass.getTeacher().getLastName() + ".");
        }

        System.out.print("Are you sure you wish to assign this teacher to this class? (Enter 'y' to assign, 'n' to go back to menu.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really assign this teacher? (Enter 'y' to assign, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        realClass.getTeacher().deScheduleTeacher(realClass);
        realClass.setTeacher(t);
        t.scheduleTeacher(realClass);
        t.addToNewClass(realClass);
        System.out.println("Teacher assigned!");
    }




    // ************************************************************************************************************************************
    public static void teacherProgressReportOptions(Teacher user)
    {
        int function = 0;
        int numMatches;
        //Add, delete, or modify a progress report comment for a specific student
        while (function != -1)
        {
            System.out.println("\n");
            System.out.println("       <><><> PROGRESS REPORT OPTIONS <><><>");
            System.out.println("*****************************************************");
            System.out.println("\t1. Add a new progress report for a particular student in a particular class (that you teach).");
            System.out.println("\t2. Modify an existing progress report for a particular student in a particular class (that you teach).");
            System.out.println("\t3. Delete an existing progress report for a particular student in a particular class (that you teach).");
            System.out.println("\t4. View all progress reports you've issued.");
            System.out.println("\t-1. Return to the main menu.");
            System.out.println("*****************************************************");
            function = reader.readInt("\nEnter the corresponding number of the function you wish to carry out");

            switch (function)
            {
                case 1: // add a new progress report
                    addProgressReport(user);
                    break;
                case 2: // modify a progress report
                    modifyProgressReport(user);
                    break;
                case 3: // delete a progress report
                    deleteProgressReport(user);
                    break;
                case 4: // view all issued progress reports
                    numMatches = viewIssuedProgressReports(user);
                    if(numMatches == 0)
                    {
                        System.out.println("No relevant data.");
                    }
                    break;
                case -1: // return to main menu
                    System.out.println("Returning to main menu...\n");
                    break;
            }

        }
    }


    public static void addProgressReport(Teacher user)
    {
        String classID;
        String studentID;
        int numMatches;

        System.out.println("Here is a list of all your classes and their respective IDs:");
        numMatches = viewTaughtClasses(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nIn which class is the student whose progress you wish to report on? (Enter the ID number, -1 to go back to menu)");
        classID = reader.readLine("Class");
        if(classID.equals("-1"))
        {
            return;
        }
        Class c = searchTaughtClassesByID(classID, user);
        if(c == null)
        {
            return;
        }

        System.out.println("\nHere is a list of all students for this class and their respective IDs:");
        numMatches = viewStudentsInClass(c);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nFor which student would you like to add the progress report? (Enter the ID number, -1 to go back to menu)");
        studentID = reader.readLine("Student");
        if(studentID.equals("-1"))
        {
            return;
        }
        Student s = searchStudentsByID(studentID);
        if(s == null)
        {
            return;
        }

        System.out.print("\n");
        printStudentData(s);
        System.out.println("\nPlease enter the text for the progress report:");
        String report = reader.readLine("Report");

        ProgressReport pr = new ProgressReport(c, s, report);
        s.addNewProgressReport(pr);
        PowerSchool.allProgressReports.put(pr.getReportID(), pr);
        System.out.println("Report added!");
    }


    public static void modifyProgressReport(Teacher user)
    {
        int numMatches;
        String reportID;
        char choice;

        System.out.println("Here is a list of all progress reports you've issued:");
        numMatches = viewIssuedProgressReports(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nWhich report would you like to modify? (Enter the report number shown before the period, -1 to go back to menu)");
        reportID = reader.readLine("Report number");
        if(reportID.equals("-1"))
        {
            return;
        }

        ProgressReport pr = searchIssuedProgressReports(user, reportID);
        if(pr == null)
        {
            return;
        }

        String report;
        System.out.println("\n" + pr.getReportID() + ". " + pr.getReport());
        System.out.println("\n           <><><> MODIFY OPTIONS <><><>");
        System.out.println("*****************************************************");
        System.out.println("\tChange the text of this progress report.");
        System.out.println("\tIf you wish to delete it altogether, please return to the menu (-1)");
        System.out.println("*****************************************************");
        System.out.println("\nEnter the new text for the report (-1 to cancel):");
        report = reader.readLine("Report");
        if(report.equals("-1"))
        {
            return;
        }

        pr.setReport(report);
        System.out.println("Report changed!");
    }


    public static void deleteProgressReport(Teacher user)
    {
        int numMatches;
        String reportID;
        char choice;

        System.out.println("Here is a list of all progress reports you've issued:");
        numMatches = viewIssuedProgressReports(user);
        if(numMatches == 0)
        {
            System.out.println("No relevant data.");
            return;
        }

        System.out.println("\nWhich report would you like to delete? (Enter the report number shown before the period, -1 to go back to menu)");
        reportID = reader.readLine("Report number");
        if(reportID.equals("-1"))
        {
            return;
        }

        ProgressReport pr = searchIssuedProgressReports(user, reportID);
        if(pr == null)
        {
            return;
        }

        System.out.print("\nReally delete this report? (Enter 'y' to delete, 'n' to go back to menu.)");
        choice = reader.readChar();
        while (choice != 'y' && choice != 'n') // check for invalid input
        {
            System.out.println("Error, please enter a valid input.");
            System.out.print("Really delete this report? (Enter 'y' to delete, 'n' to go back to menu.)");
            choice = reader.readChar();
        }
        if(choice == 'n')
            return;

        Student s = pr.getStudentReportIsFor();
        PowerSchool.allProgressReports.remove(pr.getClassStudentIsIn().getId(), pr);
        s.removeProgressReport(pr);

        System.out.println("Report deleted!");

    }


    public static int viewIssuedProgressReports(Teacher user) // looks at all students in all classes taught by user //TODO add a check for no classes or students
    {
        int numReports = 0;
        for(Class c: user.getClasses().values())
        {
            for(Student s: c.getStudents().values())
            {
                if(!s.getProgressReports().isEmpty())
                {
                    System.out.print("\n");
                    System.out.print("For " + s.getFirstName()+" "+s.getLastName());
                    System.out.println("\t\t"+s.getStudentID());

                    Map<String, ProgressReport> reports = s.getProgressReports();
                    //int currentReportNum;
                    String currentReportID;
                    ProgressReport currentReport;

                    Iterator<String> it = s.getProgressReports().keySet().iterator();

                    while(it.hasNext())
                    {
                        numReports++;
                        currentReportID = it.next();
                        currentReport = s.getProgressReports().get(currentReportID);
                        System.out.println(currentReport.getReportID() + ". " + currentReport.getReport());
                    }
                }

            }
        }
        return numReports;
    }


    public static ProgressReport searchIssuedProgressReports(Teacher user, String id)
    {
        boolean valid = false;
        char tryAgain;

        do
        {
            for(Class c: user.getClasses().values())
            {
                for(Student s: c.getStudents().values())
                {
                    if(!s.getProgressReports().isEmpty())
                    {
                        Map<String, ProgressReport> reports = s.getProgressReports();
                        //int currentReportNum;
                        String currentReportID;
                        ProgressReport currentReport;

                        Iterator<String> it = s.getProgressReports().keySet().iterator();

                        while(it.hasNext())
                        {
                            currentReportID = it.next();
                            currentReport = s.getProgressReports().get(currentReportID);
                            if(currentReport.getReportID().equals(id))
                            {
                                return currentReport;
                            }
                            else valid = false;
                        }
                    }

                }
            }
            if(!valid)
            {
                System.out.print("No results found. Try again? ('y' to try again, 'n' to go back to menu)");
                tryAgain = reader.readChar();
                while (tryAgain != 'y' && tryAgain != 'n') // check for invalid input
                {
                    System.out.println("Error, please enter a valid input.");
                    tryAgain = reader.readChar("Try again? ('y' to try again, 'n' to go back to menu)");
                }
                if(tryAgain == 'n')
                {
                    return null;
                }
                id = reader.readLine("Enter the number of the report for which you are searching");
            }
        }while(!valid);

        return null;


    }




    public static void calculateEveryStudentAverage() // ever made... duh nuh!
    {
        for(Class c: PowerSchool.allClasses.values())
        {
            c.calculateStudentGrades();

        }
    }


    public static void calculateEveryStudentGPA()
    {
        for(Student s: PowerSchool.allStudents.values())
        {
            s.calculateGpa();
        }
    }



    public static boolean studentIsInClass(Student s, Class c)
    {
        boolean valid = false;
        char choice;
        while(!valid)
        {
            if(c.getStudents().containsKey(s.getStudentID()))
            {
                valid = true;
                break;
            }
            System.out.print("That student is not in this class. Try again? (Enter 'y' for yes, 'n' for no)");
            choice = reader.readChar();
            while (choice != 'y' && choice != 'n') // check for invalid input
            {
                System.out.println("Error, please enter a valid input.");
                System.out.print("Try another student? (Enter 'y' to try again, 'n' to go back to menu.)");
                choice = reader.readChar();
            }
            if(choice == 'n')
                return false;
        }
        return valid;
    }

    public static void addAssignmentToClass(Assignment a, Class c)
    {
        c.putAssignmentInClass(a);
        a.giveClassToAssignment(c);
    }

    public static void deletedAssignmentCleanup(Assignment a, Class c)
    {
        // remove assignment from assignments map in Class
        c.getAssignments().remove(a.getId());
    }

    public static boolean canFitInTeacherSchedule(Teacher t, int period)
    {
        boolean canFit = true;
        Class[] schedule = t.getSchedule();

        for(int i = 0; i < 7; i++) // check each class in teacher's schedule to make sure they can fit this class
        {
            if(schedule[i] == null)
            {
                canFit = true;
                continue;
            }

            if(schedule[i].getPeriodHeld() == period)
            {
                System.out.println("Period " + (period+1) + " is already filled for " + t.getFirstName() + " " + t.getLastName());
                canFit = false;
                break;

            }
        }

        return canFit;

    }

    public static boolean canFitInStudentSchedule(Student s, int period)
    {
        boolean canFit = true;
        Class[] schedule = s.getSchedule();

        for(int i = 0; i < 7; i++) // check each class in student's schedule to make sure they can fit this class
        {
            if(schedule[i] == null)
            {
                canFit = true;
            }

            else if(schedule[i].getPeriodHeld() == period)
            {
                System.out.println("Period " + (period+1) + " is already filled for " + s.getFirstName() + " " + s.getLastName());
                canFit = false;
                break;

            }
        }

        return canFit;
    }

}
