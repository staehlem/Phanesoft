package model;

public class LocalPictureUrlResponseWrapper {
	
	public LocalPictureUrlResponseWrapper(){}
	
	private String message = null;
	
	public LocalPictureUrlResponseWrapper(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocalPictureUrlResponseWrapper [message=" + message + "]";
	}

}
