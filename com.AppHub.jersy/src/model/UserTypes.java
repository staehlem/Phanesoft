package model;

public class UserTypes {
	private int idUserTypes;
	private String UserType;
	
	public UserTypes(int idUserTypes, String UserType) {
		this.idUserTypes = idUserTypes;
		this.UserType = UserType;
	}

	/**
<<<<<<< HEAD
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
=======
>>>>>>> 43fca71b24c4177233486beae5fbead4de0cf836
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