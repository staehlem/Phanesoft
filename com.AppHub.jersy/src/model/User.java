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

	/**
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
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
