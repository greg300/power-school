package PowerSchool.src.powerschool;

//import powerschool.src.com.google.gson.Gson;
//import powerschool.src.com.google.gson.GsonBuilder;


public class ReadData 
{
	
	/*public static void main(String[] args) throws FileNotFoundException
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		List<Assignment> assignments = new ArrayList<Assignment>();
		List<Student> students = new ArrayList<Student>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Class> classes = new ArrayList<Class>();
		List<Course> courses = new ArrayList<Course>();

		Scanner s = new Scanner(new File("src/assignments.txt"));
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			assignments.add(gson.fromJson(line, Assignment.class));
			//System.out.println(s.nextLine());
		}
		//TODO Going to make a new file that reads in and parses via Gson all the data, then takes the objects and integrates them into the program (i.e. enrolling students, adding assignments to list, assigning teachers to classes, etc)
		// Consider reading in the entire list of (objects) at once as an arraylist
		for(Assignment a: assignments)
		{
			System.out.println(a.getName());
			System.out.println(a.getDate());
			for(Entry<String, Double> e: a.getEntireStudentPoints().entrySet())
			{
				System.out.println(e.getKey()+": "+e.getValue());
			}
			System.out.println(a.getCategory());
			System.out.println(a.getPointValue());
			//TODO sort my maps ? 
			
		}
		s.close();
	}*/

}
