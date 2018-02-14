package PowerSchool.src.powerschool;

import java.util.ArrayList;
import java.util.List;

public class Course //implements Writable
{
    private String name;
    private String courseID;
    private List<Class> classesOfThisCourse = new ArrayList<Class>();
    private int numClasses = 0;

    public Course(String name, String courseID)
    {
        PowerSchool.numCoursesExisted++;
        this.name = name;
        this.courseID = courseID;
        numClasses = classesOfThisCourse.size();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCourseID()
    {
        return courseID;
    }

    public void setCourseID(String courseID)
    {
        this.courseID = courseID;
    }

    public int getNumClasses()
    {
        return numClasses;
    }

    public void setNumClasses()
    {
        numClasses = classesOfThisCourse.size();
    }

    public List<Class> getClassesOfThisCourse()
    {
        return classesOfThisCourse;
    }

    public void setClassesOfThisCourse(List<Class> classesOfThisCourse)
    {
        this.classesOfThisCourse = classesOfThisCourse;
    }

    public void assignClassToCourse(Class c)
    {
        classesOfThisCourse.add(c);
    }

    /*public void setNumClasses(int numClasses)
    {
        this.numClasses = numClasses;
    }*/
}
