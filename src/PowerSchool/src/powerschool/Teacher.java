package PowerSchool.src.powerschool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gregorygiovannini on 1/15/16.
 */
public class Teacher extends Person
{
	private String teacherID;
	private Map<String, Class> classes = new HashMap<>(); // id of class to actual class taught
	private Class[] schedule = new Class[7]; // fixed; schedule has 7 classes

    public Teacher() // default constructor
    {
        generateTeacherID();
		password = teacherID;
		username = lastName;
    }

	public Teacher(int zero) // this is a special constructor used only for if the teacher is deleted/removed from a class; it is a placeholder teacher
	{
		firstName = "";
		lastName = "Unassigned";
		teacherID = "-1";
	}

	public Teacher(String firstName, String lastName) // used in initial data generation / all other uses
	{

		this.firstName = firstName;
		this.lastName = lastName;
		generateTeacherID();
		password = teacherID;
		username = this.lastName;
		PowerSchool.numTeachersExisted++;

	}

	public String getTeacherID() {
		return teacherID;
	}

	/*public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}*/

	private void generateTeacherID()
	{
		teacherID = "100"+ PowerSchool.numTeachersExisted;
	}

	public void scheduleTeacher(Class c)
	{
		schedule[c.getPeriodHeld()] = c;
	}

	public void deScheduleTeacher(Class c)
	{
		schedule[c.getPeriodHeld()] = null;
		classes.remove(c.getId());
	}


    public void printSchedule()
    {
        System.out.println("\n***************************************************************************");
        System.out.println("*\tPERIOD\t\t\tCLASS\t\t\t\t\t\tID\t\t\t\t\t\t  *");
        System.out.println("*-------------------------------------------------------------------------*");
        for(int i = 0; i < 7; i++)
        {

            System.out.print("*\t" + (i+1) + "\t\t\t\t");
            if(schedule[i] == null)
            {
                System.out.print("Prep\t\t\t\t\t\tN/A\t\t\t\t\t\t  *");
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

    public Class[] getSchedule() {
        return schedule;
    }

    public void setSchedule(Class[] schedule) {
        this.schedule = schedule;
    }

    public Map<String, Class> getClasses() {
		return classes;
	}

	public void setClasses(Map<String, Class> classes) {
		this.classes = classes;
	}

    public void addToNewClass(Class c)
    {
        classes.put(c.getId(), c);
    }
}
