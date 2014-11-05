
public abstract class UserID {
	int appID;
	protected boolean hasPrivilages;
	protected String first, last, email, password;
	
	
	public UserID(int appID, String first, String last, String email,
			String password) {
		super();
		this.appID = appID;
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
	}
}
