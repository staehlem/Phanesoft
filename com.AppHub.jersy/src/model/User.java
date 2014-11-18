package model;

public class User {
	private String first, last, email, password, userName, userType;
	
	public User(String userName, String first, String last, String email,
			String password, String userType) {
		this.userName = userName;
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	public User() {}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getUserType() {
		return userType;
	}

	@Override
	public String toString() {
		return "User [first=" + first + ", last=" + last + ", email=" + email
				+ ", password=" + password + ", userName=" + userName
				+ ", userType=" + userType + "]";
	}
}
