import java.util.ArrayList;

public class Instructor {

		private int num;
		private String name;
		private String email;
		private String phone;
		private ArrayList<Course> courses = new ArrayList<>();
		
		public Instructor(int num, String name, String email, String phone) {
				this.num = num;
				this.name = name;
				this.email = email;
				this.phone = phone;
		}
		
		public void teach(Course c) {
			courses.add(c);
		}
		public String toString() {
			String ret = "Instructor Number: "+num+"\nName: "+name+"\nCourses Teaching:\n";
			for (int i = 0; i < courses.size(); i++) {
				ret += "\t"+courses.get(i).getNum()+": "+courses.get(i).getCurSize()+" enrolled\n";
			}
			return ret;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
}
