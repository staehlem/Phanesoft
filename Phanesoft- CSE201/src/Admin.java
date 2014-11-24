
public class Admin extends UserID {
	

	public Admin(int appID, String first, String last, String email,
			String password) {
		super(appID, first, last, email, password);
		hasPrivilages = true;
	}
	
}
