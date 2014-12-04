package model;

public class UserTypes {
	private String UserType;
	
	public UserTypes(String UserType) {
		this.UserType = UserType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		UserType = userType;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return UserType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserTypes [UserType=" + UserType + "]";
	}

	
	
	
}