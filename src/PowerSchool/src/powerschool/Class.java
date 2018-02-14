package PowerSchool.src.powerschool;

import java.util.HashMap;
import java.util.Map;

public class Class //implements Writable
{
	private String id;
	private Teacher teacher;
	private Course course;
	private int periodHeld; // between 0 and 6
	private int assignmentGenerationIDIndex;
	private Map<String, Student> students = new HashMap<String, Student>();
    private Map<String, Double> studentAverages = new HashMap<>(); // -2 means all exempt
	private Map<String, Assignment> assignments = new HashMap<String, Assignment>();




	public Class()
	{
		/*id = ""+0;
		teacher = null;
		students = null;
		course = null;
		assignments = null;*/
		//assignClassToTeacher();
	}

	public Class(int zero) // Study hall constructor
	{
		id = "0";
		//course = studyHall;
	}

	public Class(Course course, Teacher teacher, String id, int periodHeld)
	{
		PowerSchool.numClassesExisted++;
		this.course = course;
		this.teacher = teacher;
		this.id = id;
        this.periodHeld = periodHeld;
		assignmentGenerationIDIndex = 1;
		assignClassToTeacher(this);
		assignClassToCourse(this);
	}




	public String getId()
	{
		return id;
	}

	public void setiD(String s)
	{
		this.id = s;
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public Course getCourse()
	{
		return course;
	}

	public void setCourse(Course course)
	{
		this.course = course;
	}

	public Map<String, Student> getStudents()
	{
		return students;
	}

	public void setStudents(Map<String, Student> students)
	{
		this.students = students;
	}

    public Map<String, Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<String, Assignment> assignments) {
        this.assignments = assignments;
    }

    public Map<String, Double> getStudentAverages() {
        return studentAverages;
    }

    public void setStudentAverages(Map<String, Double> studentAverages) {
        this.studentAverages = studentAverages;
    }

    public void assignClassToTeacher(Class c)
	{
		teacher.addToNewClass(c);
	}

	public void assignClassToCourse(Class c)
	{
		course.assignClassToCourse(c);
	}

	public void removeStudentFromClass(Student s)
	{
		students.remove(s.getStudentID());
		studentAverages.remove(s.getStudentID());
		for(Assignment a: assignments.values())
		{
			a.removeAssignmentForStudent(s);
		}
	}

	public void removeTeacherFromClass(Teacher t)
	{
		teacher = new Teacher(0);
	}

    public int getPeriodHeld() {
        return periodHeld;
    }

    public void setPeriodHeld(int periodHeld) {
        this.periodHeld = periodHeld;
    }

    public void putStudentInClass(Student s)
    {
        students.put(s.getStudentID(), s);
    }

    public void putAssignmentInClass(Assignment a)
    {
        assignments.put(a.getId(), a);
    }

	public int getAssignmentGenerationIDIndex() {
		return assignmentGenerationIDIndex;
	}

	public void increaseAssignmentGenerationIDIndex() {
		this.assignmentGenerationIDIndex++;
	}

	public void initializeStudentAverageForNewStudent(Student s)
	{
		studentAverages.put(s.getStudentID(), -2.0);
	}

    public void setOneGrade(Student s, Double grade)
    {
        studentAverages.put(s.getStudentID(), grade);
    }

	public void calculateStudentGrades()
    {
        double studentScoreOnAssignment;
        double pointValueOfAssignment;
        double overallGradeInClass;

        for(Student s: students.values())
        {
            double studentPoints = 0;
            double possiblePoints = 0;
            double numExemptAssignments = 0;
            for(Assignment a: assignments.values())
            {
                studentScoreOnAssignment = a.getEntireStudentPoints().get(s.getStudentID());
                pointValueOfAssignment = a.getPointValue();

                if(studentScoreOnAssignment == -2.0)
                {
                    numExemptAssignments++;
                    continue;
                }
                else
                {
                    studentPoints = studentPoints + studentScoreOnAssignment;
                    possiblePoints = possiblePoints + pointValueOfAssignment;
                }

            }
            if(numExemptAssignments == assignments.size())
            {
                overallGradeInClass = -2.0;
            }
            else
            {
                overallGradeInClass = (studentPoints/possiblePoints)*100;
            }
            setOneGrade(s, overallGradeInClass);
        }
    }
}
