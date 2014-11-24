package model;

public class Platform {
	private int idPlatform;
	private String platformName;
	
	public Platform(){}
	
	public Platform(int idPlatform, String platformName) {
		this.idPlatform = idPlatform;
		this.platformName = platformName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Platform [idPlatform=" + idPlatform + ", platformName="
				+ platformName + "]";
	}

	/**
<<<<<<< HEAD
	 * @param idPlatform the idPlatform to set
	 */
	public void setIdPlatform(int idPlatform) {
		this.idPlatform = idPlatform;
	}

	/**
	 * @param platformName the platformName to set
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	/**
=======
>>>>>>> 43fca71b24c4177233486beae5fbead4de0cf836
	 * @return the idPlatform
	 */
	public int getIdPlatform() {
		return idPlatform;
	}

	/**
	 * @return the platformName
	 */
	public String getPlatformName() {
		return platformName;
	}
}
