package PowerSchool.src.powerschool;

//import powerschool.src.com.google.gson.Gson;
//import powerschool.src.com.google.gson.GsonBuilder;


public class WriteData 
{
	/*public static void writeData(List<? extends Writable> list, String fileName) throws FileNotFoundException
	{
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		File outFile = new File("src/"+fileName);
		FileOutputStream outFileStream = new FileOutputStream(outFile); // Note that a FileOutputStream object is still needed here
		PrintWriter outStream = new PrintWriter(outFileStream);
		for(Writable w: list)
		{
			String s = gson.toJson(w);
			System.out.println(((Assignment) w).getName());
			System.out.println(s);
			outStream.println(s);
		}
		outStream.close();
		//System.out.println(w.get(0).getClass());
		
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		List<Writable> test = new ArrayList<Writable>();
		test.add(new Assignment());
		List<Assignment> assignments = new ArrayList<Assignment>();
		List<Person> students = new ArrayList<Person>();
		Assignment a1, a2;
		a1 = new Assignment();
		Map<String, Double> s1 = new HashMap<String, Double>();
		a1.setName("Greg");
		a1.setDate("2016");
		a1.setCategory("Quiz");
		a1.setClassThatThisBelongsTo("1234345"); //TODO make a system that generates class IDs (done in Main)
		s1.put("170172", 100.0);
		s1.put("170174", 70.0);
		a1.setStudentPoints(s1);
		assignments.add(a1);
		//a2 = new Assignment();
		writeData(assignments, "assignments.txt");
	}*/
}
