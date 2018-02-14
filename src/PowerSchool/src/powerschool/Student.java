package PowerSchool.src.powerschool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gregorygiovannini on 1/15/16.
 */
public class Student extends Person
{

    private double gpa = 0.0;
    private int yearGraduate; // 2016, 2017, 2018, 2019
    private String studentID;
    //private int[] studentsInGrade = new int[4];
    private Map<String, Class> classes = new HashMap<String, Class>(); // id of class to actual class object
    private Map<String, ProgressReport> progressReports = new HashMap<>(); // report id to report
    private Class[] schedule = new Class[7]; // fixed; schedule has 7 classes

    public Student()
    {

    }

    public Student(String firstName, String lastName, int yearGraduate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearGraduate = yearGraduate;
        generateStudentID();
        password = studentID;
        username = lastName;
        for(int i = 0; i < 7; i ++)
        {
            if(schedule[i] == null)
            {
                //schedule[i] == new Class(0);
            }
        }
        PowerSchool.numStudentsExisted++;

    }

   

    public double getGpa()
    {
        return gpa;
    }

    /*public void setGpa(double grade)
    {
        this.gpa = grade;
    }*/

    public int getYearGraduate()
    {
        return yearGraduate;
    }

    public void setYearGraduate(int yearGraduate)
    {
        this.yearGraduate = yearGraduate;
    }

    public String getStudentID()
    {
        return studentID;
    }

    public Class[] getSchedule()
    {
        return schedule;
    }

    public void setSchedule(Class[] schedule)
    {
        this.schedule = schedule;
    }

    /*public void setId(String id)
    {
        studentID = id;
    }*/

    private void generateStudentID()
    {
        if(yearGraduate == 2016)
        {
            studentID = "160"+ PowerSchool.numStudentsExisted;
            //studentsInGrade[0]++;
        }
        else if(yearGraduate == 2017)
        {
            studentID = "170"+ PowerSchool.numStudentsExisted;
            //studentsInGrade[1]++;
        }
        else if(yearGraduate == 2018)
        {
            studentID = "180"+ PowerSchool.numStudentsExisted;
            //studentsInGrade[2]++;
        }
        else if(yearGraduate == 2019)
        {
            studentID = "190"+ PowerSchool.numStudentsExisted;
            //studentsInGrade[3]++;
        }
    }

    public void printScheduleWithoutGrades()
    {
        System.out.println("\n***************************************************************************");
        System.out.println("*\tPERIOD\t\t\tCLASS\t\t\t\t\t\tID\t\t\t\t\t\t  *");
        System.out.println("*-------------------------------------------------------------------------*");
        for(int i = 0; i < 7; i++)
        {

            System.out.print("*\t" + (i+1) + "\t\t\t\t");
            if(schedule[i] == null)
            {
                System.out.print("Study Hall\t\t\t\t\tN/A\t\t\t\t\t\t  *");
            }
            else
            {
                String className = schedule[i].getCourse().getName();
                if(className.length() <= 3)
                {
                    System.out.print(className + "\t\t\t\t\t\t\t" + schedule[i].getId() + "\t\t\t\t\t\t  *");
                }
                else if(className.length() <= 7)
                {
                    System.out.print(className + "\t\t\t\t\t\t" + schedule[i].getId() + "\t\t\t\t\t\t  *");
                }
                else if(className.length() <= 11)
                {
                    System.out.print(className + "\t\t\t\t\t" + schedule[i].getId() + "\t\t\t\t\t\t  *");
                }
                else if(className.length() <= 15)
                {
                    System.out.print(className + "\t\t\t\t" + schedule[i].getId() + "\t\t\t\t\t\t  *");
                }
                else if(className.length() <= 19)
                {
                    System.out.print(className + "\t\t\t" + schedule[i].getId() + "\t\t\t\t\t\t  *");
                }
                else
                {
                    System.out.print(className + "\t\t" + schedule[i].getId() + "\t\t\t\t\t\t  *");
                }

            }
            if(i < 6)
            {
                System.out.println("\n*-------------------------------------------------------------------------*");
            }
        }
        System.out.println("\n***************************************************************************");
    }

    public void printScheduleWithGrades()
    {
        System.out.println("\n***************************************************************************");
        System.out.println("*\tPERIOD\t\t\tCLASS\t\t\t\t\t\tID\t\t\tGRADE\t\t  *");
        System.out.println("*-------------------------------------------------------------------------*");
        for(int i = 0; i < 7; i++)
        {

            System.out.print("*\t" + (i+1) + "\t\t\t\t");
            if(schedule[i] == null)
            {
                System.out.print("Study Hall\t\t\t\t\tN/A\t\t\tN/A\t\t\t  *");
            }
            else
            {
                String className = schedule[i].getCourse().getName();
                if(className.length() <= 3)
                {
                    System.out.print(className + "\t\t\t\t\t\t\t" + schedule[i].getId() + "\t\t\t");
                }
                else if(className.length() <= 7)
                {
                    System.out.print(className + "\t\t\t\t\t\t" + schedule[i].getId() + "\t\t\t");
                }
                else if(className.length() <= 11)
                {
                    System.out.print(className + "\t\t\t\t\t" + schedule[i].getId() + "\t\t\t");
                }
                else if(className.length() <= 15)
                {
                    System.out.print(className + "\t\t\t\t" + schedule[i].getId() + "\t\t\t");
                }
                else if(className.length() <= 19)
                {
                    System.out.print(className + "\t\t\t" + schedule[i].getId() + "\t\t\t");
                }
                else
                {
                    System.out.print(className + "\t\t" + schedule[i].getId() + "\t\t\t");
                }

                if(schedule[i].getStudentAverages().get(this.getStudentID()) == -2.0)
                {
                    System.out.print("N/A\t\t\t  *");
                }
                else
                {
                    System.out.print(schedule[i].getStudentAverages().get(this.getStudentID()) + " \t\t  *");
                }
            }
            if(i < 6)
            {
                System.out.println("\n*-------------------------------------------------------------------------*");
            }
        }
        System.out.println("\n***************************************************************************");
    }

    public void giveClassToStudent(Class c)
    {
        classes.put(c.getId(), c);
    }

    public void scheduleStudent(Class c)
    {
        schedule[c.getPeriodHeld()] = c;
    }

    public void deScheduleStudent(Class c)
    {
        schedule[c.getPeriodHeld()] = null;
        classes.remove(c.getId());
        progressReports.remove(c.getId());
    }

    public Map<String, Class> getClasses()
    {
        return classes;
    }

    public void setClasses(Map<String, Class> classes)
    {
        this.classes = classes;
    }

    public void calculateGpa()
    {
        if(classes.isEmpty())
        {
            gpa = -2.0;
            return;
        }

        double grandTotal = 0;
        int numClasses = 0;
        int numClassesExemptFrom = 0;
        for(Class c: classes.values())
        {
            double averageForClass = c.getStudentAverages().get(studentID);
            if(averageForClass != -2.0)
            {
                grandTotal = grandTotal + averageForClass;
                numClasses++;
            }
            else numClassesExemptFrom++;
        }
        if(numClassesExemptFrom == classes.size())
        {
            gpa = -2.0;
            return;
        }

        double grandAverage = grandTotal/numClasses;
        gpa = grandAverage;
    }

    public void addNewProgressReport(ProgressReport pr)
    {
        progressReports.put(pr.getReportID(), pr);
    }

    public void removeProgressReport(ProgressReport pr)
    {
        progressReports.remove(pr.getReportID(), pr);
    }

    public Map<String, ProgressReport> getProgressReports() {
        return progressReports;
    }




    /*public void setProgressReports(Map<String, ProgressReport> progressReports) {
        this.progressReports = progressReports;
    }*/
}
