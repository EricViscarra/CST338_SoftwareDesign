import java.util.*;
public class Course {
	
	private int num;
	private String title;
	private int size;
	private int curSize = 0;
	private String loc;
	private Instructor instructor = null;
	private ArrayList<Student> students = new ArrayList<>();
	private HashMap<Integer, Double> scores = new HashMap<>(); //studNum, all scores combined
	private HashMap<Integer, Integer> scoreNum = new HashMap<>(); //stud num, num of tests
	
	public Course (int num, String title, int size, String loc) {
		this.num = num;
		this.title = title;
		this.size = size;
		this.loc = loc;
	}
	
	public String toString () {
		return "Course Number: "+num+"\nInstructor: "+instructor.getName()
							+"\nCourse Title: "+title+"\nRoom: "+loc
							+"\nTotal Enrolled: "+curSize+"\nCourse Average: "
							+getCourseAverage();
	}
	public double getScore(int id) {
		return (scores.get(id)/scoreNum.get(id));
	}
	
	public void updateLocation (String loc) {
		this.loc = loc;
	}
	
	public double getCourseAverage() {
		if (curSize == 0) {
			return 0;
		}
		double avg = 0;
		for (int num : scores.keySet()) {
			avg += (scores.get(num)/scoreNum.get(num));
		}
		return avg/curSize;
	}
	
	public boolean addScore (int studId, double score) {
		boolean isAttending = false;
		for (Student s : students) {
			if (s.getId() == studId) {
				isAttending = true;
			}
		}
		if (isAttending) {
			this.scores.put(studId, scores.get(studId)+score);
			this.scoreNum.put(studId, scoreNum.get(studId)+1);
			return true;
		}
		else {
			return false;
		}
	}

	public int getNum () {
		return num;
	}

	public void setNum (int num) {
		this.num = num;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public int getSize () {
		return size;
	}

	public void setSize (int size) {
		this.size = size;
	}

	public String getLoc () {
		return loc;
	}

	public void setLoc (String loc) {
		this.loc = loc;
	}
	
	public Instructor getInstructor () {
		return instructor;
	}
	
	public void setInstructor (Instructor i) {
		this.instructor = i;
	}
	
	public int getCurSize () {
		return curSize;
	}
	
	public void removeStudent (Student s) {
		this.scores.remove(s.getId());
		this.scoreNum.remove(s.getId());
		students.remove(s);
		this.curSize--;
	}
	
	public void addStudent (Student s) {
		students.add(s);
		scores.put(s.getId(), 0.0);
		scoreNum.put(s.getId(), 0);
		this.curSize++;
	}
}
