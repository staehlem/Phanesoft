package model;

public class UserTypes {
	private int idUserTypes;
	private String UserType;
	
	public UserTypes(int idUserTypes, String UserType) {
		this.idUserTypes = idUserTypes;
		this.UserType = UserType;
	}

	/**
	 * @param idUserTypes the idUserTypes to set
	 */
	public void setIdUserTypes(int idUserTypes) {
		this.idUserTypes = idUserTypes;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		UserType = userType;
	}

	/**
	 * @return the idUserTypes
	 */
	public int getIdUserTypes() {
		return idUserTypes;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return UserType;
	}

	@Override
	public String toString() {
		return "UserTypes [idUserTypes=" + idUserTypes + ", UserType="
				+ UserType + "]";
	}
	
	
}