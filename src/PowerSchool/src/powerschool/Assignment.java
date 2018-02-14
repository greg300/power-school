package PowerSchool.src.powerschool;

import java.util.HashMap;
import java.util.Map;

//import powerschool.src.powerschool.Writable;

public class Assignment //implements Writable
{
	private String name;
	private String id;
	private String date;
	private String classThatThisBelongsTo; // id of class
    private String category; // to calculate someone's grade, loop over each category and determine their avg for each separate category; then add
	private double pointValue;
	private Map<String, Double> studentPoints = new HashMap<String, Double>(); // -2 means exempt
	
	/*public static void main(String[] args)
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String test = "{\"name\":\"bar\", \"date\":\"Greg\"}";
		Assignment test1 = gson.fromJson(test, Assignment.class);
		
		System.out.println(test1.name);
		System.out.println(test1.date);
		
		Assignment test2 = new Assignment();
		test2.name = "ulm";
		test2.date = "1/2/3";
		test2.studentPoints.put(""+170172, 10.0);
		test2.studentPoints.put(""+170173, 8.0);
		test2.studentPoints.put(""+170174, 6.0);
		test2.pointValue = 10.0;
		System.out.println(gson.toJson(test2));
	
	}*/

	public Assignment()
	{
		/*name = null;
		date = null;
		pointValue = 0;
		category = null;
		classThatThisBelongsTo = null;*/
	}

    public Assignment(String name, String id, String date, String classThatThisBelongsTo, String category, double pointValue)
    {
        this.name = name;
        this.id = id;
        this.date = date;
        this.classThatThisBelongsTo = classThatThisBelongsTo;
        this.category = category;
        this.pointValue = pointValue;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPointValue() {
		return pointValue;
	}
	public void setPointValue(double pointValue) {
		this.pointValue = pointValue;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setClassThatThisBelongsTo(String s)
	{
		classThatThisBelongsTo = s;
	}
	public String getClassThatThisBelongsTo()
	{
		return classThatThisBelongsTo;
	}
	public void setEntireStudentPoints(Map<String, Double> studentScores)
	{
		studentPoints = studentScores;
	}
	public Map<String, Double> getEntireStudentPoints()
	{
		return studentPoints;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void giveClassToAssignment(Class c)
    {
        classThatThisBelongsTo = c.getId();
    }

    public void putStudentGradeInAssignment(Student s, double grade)
    {
        studentPoints.put(s.getStudentID(), grade);
    }

    public void setStudentGradeForAssignment(Student s, double grade)
    {
        studentPoints.put(s.getStudentID(), grade);
    }

	public void removeAssignmentForStudent(Student s)
	{
		studentPoints.remove(s.getStudentID());
	}

	public void addAssignmentForStudent(Student s)
	{
		studentPoints.put(s.getStudentID(), -2.0);
	}
}
