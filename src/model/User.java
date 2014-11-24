package model;

public abstract class User {
	protected String first, last, email, password, userName;
	
	public User(String userName, String first, String last, String email,
			String password) {
		this.userName = userName;
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
	}

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
}
