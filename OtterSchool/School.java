import java.util.*;
import java.io.*;
public class School {

	private String name;
	private HashMap<Integer, Instructor> instructors = new HashMap<>();
	private HashMap<Integer, Course> courses = new HashMap<>();
	private HashMap<Integer, Student> students = new HashMap<>();
	
	public School (String name) {
		this.name = name;
	}
	
	public void readData (String path) {
		boolean notTaken;
		File file = new File(path);
		StringTokenizer st;
		try {
			Scanner input = new Scanner(file);
			while (input.hasNextLine()) {
				int numI = input.nextInt();
				input.nextLine();
				for (int i = 0; i < numI; i++) {
					notTaken = true;
					st = new StringTokenizer(input.nextLine(), ",");
					int num = Integer.valueOf(st.nextToken());
					String name = st.nextToken();
					String email = st.nextToken();
					String phone = st.nextToken();
					if (instructors.containsKey(num)) {
						System.out.println("Instructor info reading failed – Employee number "+num+" already used.");
						notTaken = false;
					}
					if (notTaken) {
						instructors.put(num, new Instructor(num, name, email, phone));
					}
				}
				int numC = input.nextInt();
				input.nextLine();
				for (int i = 0; i < numC; i++) {
					notTaken = true;
					st = new StringTokenizer(input.nextLine(), ",");
					int num = Integer.valueOf(st.nextToken());
					String title = st.nextToken();
					int size = Integer.valueOf(st.nextToken());
					String loc = st.nextToken();
					if (courses.containsKey(num)) {
						System.out.println("Course info reading failed – Course number "+num+" already used.");
						notTaken = false;
					}
					if (notTaken) {
						courses.put(num, new Course(num, title, size, loc));
					}
				}
				int numS = input.nextInt();
				input.nextLine();
				for (int i = 0; i < numS; i++) {
					notTaken = true;
					st = new StringTokenizer(input.nextLine(), ",");
					int num = Integer.valueOf(st.nextToken());
					String name = st.nextToken();
					if (students.containsKey(num)) {
						System.out.println("Student info reading failed – Student ID "+num+" already used.");
						notTaken = false;
					}
					if (notTaken) {
						students.put(num, new Student(num, name));
					}
				}
			}
			input.close();
			System.out.println("Done.");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void schoolInfo() {
		System.out.println("School Name: "+this.name);
		System.out.println("Instructor Information");
		for (int num : instructors.keySet()) {
			System.out.println("\t"+instructors.get(num).getName());
		}
		System.out.println("Course Information");
		for (int num : courses.keySet()) {
			System.out.println("\t"+courses.get(num).getTitle());
		}
		System.out.println("Student Information");
		for (int id : students.keySet()) {
			System.out.println("\t"+students.get(id).getName());
		}
	}
	
	public void searchByEmail (String email) {
		System.out.println("Search key: "+email);
		for (int num : instructors.keySet()) {
			if (instructors.get(num).getEmail().equals(email)) {
				System.out.println("Employee Number: "+instructors.get(num).getNum());
				System.out.println("\tName: "+instructors.get(num).getName());
				System.out.println("\tPhone: "+instructors.get(num).getPhone());
				return;
			}
		}
		System.out.println("No employee with email "+email);
	}
	
	public void addInstructor (int num, String name, String email, String phone) {
		if (instructors.containsKey(num)) {
			System.out.println("Instructor addition failed - Employee number "+num+" already used.");
		}
		else {
			instructors.put(num, new Instructor(num, name, email, phone));
		}
	}
	
	public void addCourse (int num, String title, int size, String loc) {
		if (courses.containsKey(num)) {
			System.out.println("Course addition failed - Course number "+num+" already used.");
		}
		else {
			courses.put(num, new Course(num, title, size, loc));
		}
	}
	
	public void addStudent (int id, String name) {
		if (students.containsKey(id)) {
			System.out.println("Student addition failed - Student number "+id+" already used.");
		}
		else {
			students.put(id, new Student(id, name));
		}
	}
	
	public void assignInstructor (int numC, int numI) {
		if (instructors.containsKey(numI)) {
			if (courses.containsKey(numC)) {
				if (courses.get(numC).getInstructor() != null) {
					System.out.println("Error: Instructor "+courses.get(numC).getInstructor().getNum()+" already assigned to course "+numC);
				}
				else {
					courses.get(numC).setInstructor(instructors.get(numI));
					instructors.get(numI).teach(courses.get(numC));
				}
			}
			else {
				System.out.println("Course "+numC+" does not exist.");
			}
		}
		else {
			System.out.println("Instructor "+numI+" does not exist.");
		}
	}
	
	public void register (int num, int id) {
		if (students.containsKey(id)) {
			if (courses.containsKey(num)) {
				if (courses.get(num).getCurSize() >= courses.get(num).getSize()) {
					System.out.println("Registration failed - Class is full.");
				}
				else {
					courses.get(num).addStudent(students.get(id));
					students.get(id).register(courses.get(num));
				}
			}
			else {
				System.out.println("Course "+num+" does not exist.");
			}
		}
		else {
			System.out.println("Student "+id+" does not exist.");
		}
	}
	
	public void putScore (int num, int id, double score) {
		if (students.containsKey(id)) {
			if (courses.containsKey(num)) {
				if (!courses.get(num).addScore(id, score)) {
					System.out.println("Student "+id+" ("+students.get(id).getName()
										+") is not enrolled in "+num);
				}
					
			}
			else {
				System.out.println("Course "+num+" does not exist.");
			}
		}
		else {
			System.out.println("Student "+id+" does not exist.");
		}
	}
	
	public void unRegister (int num, int id) {
		if (students.containsKey(id)) {
			if (courses.containsKey(num)) {
				courses.get(num).removeStudent(students.get(id));
				students.get(id).unregister(courses.get(num));
			}
			else {
				System.out.println("Course "+num+" does not exist.");
			}
		}
		else {
			System.out.println("Student "+id+" does not exist.");
		}
	}
	
	public void courseInfo (int num) {
		System.out.println("Course Number: "+num);
		System.out.println("Instructor: "+courses.get(num).getInstructor().getName());
		System.out.println("Course Title: "+courses.get(num).getTitle());
		System.out.println("Room: "+courses.get(num).getLoc());
		System.out.println("Total Enrolled: "+courses.get(num).getCurSize());
		System.out.println("Course Average: "+courses.get(num).getCourseAverage());
	}
	
	public void courseInfo () {
		System.out.println("Number of Courses: "+courses.size());
		for (int num : courses.keySet()) {
			System.out.println("\t"+courses.get(num).getNum()+": "+courses.get(num).getCurSize()+" enrolled");
		}
		System.out.println();
	}
	
	public Instructor getInstructor (int num) {
		if (courses.containsKey(num)) {
			return courses.get(num).getInstructor();
		}
		else {
			return null;
		}
	}

	public Student getStudent (int id) {
		if (students.containsKey(id)) {
			return students.get(id);
		}
		else {
			return null;
		}
	}
	
	public Course getCourse (int num) {
		if (courses.containsKey(num)) {
			return courses.get(num);
		}
		else {
			return null;
		}
}
	
	public boolean deleteCourse (int num) {
		if (courses.get(num).getCurSize() > 0) {
			System.out.println("Course deletion failed – Enrolled student(s) in the class");
			return false;
		}
		else {
			courses.remove(num);
			return true;
		}
	}
	
	public void graduateStudent (int id) {
		for (int num : courses.keySet()) {
			courses.get(num).removeStudent(students.get(id));
			students.get(id).unregister(courses.get(num));
		}
		students.remove(id);
	}
}
