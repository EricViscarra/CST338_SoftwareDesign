import java.util.ArrayList;

public class Student {

	private int id;
	private String name;
	private ArrayList<Course> registered = new ArrayList<>();
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void register(Course c) {
		registered.add(c);
	}
	
	public void unregister(Course c) {
		registered.remove(c);
	}
	
	public String toString() {
		String result = "Student Number: "+id+"\nName: "+name+"\nCourses Enrolled:\n";
		double avg = 0;
		for (int i = 0; i < registered.size(); i++) {
			result += "\t"+registered.get(i).getNum()+": "+registered.get(i).getScore(id)+"\n";
			avg += registered.get(i).getScore(id);
		}
		avg /= registered.size();
		result += "Course Average: "+avg+"\n";
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addCourse(Course c) {
		
	}
}