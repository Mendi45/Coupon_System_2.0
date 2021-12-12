package db.Beans;
import java.util.ArrayList;

public class Company {
	
		private int id;
		private String name;
		private String email;
		private String password;
		private ArrayList<Coupon> coupons;

		//CTR without id uses for insert (auto  id) 
		public Company(String name, String email, String password) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
		}
		 //CTR with id uses for select's commands
		public Company(int id, String name, String email, String password) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getId() {
			return id;
		}
		
		@Override
		public String toString() {
			return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
					+ coupons + "]";
		}


}
