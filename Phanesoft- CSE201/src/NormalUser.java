
public class NormalUser extends UserID{

	public NormalUser(int appID, String first, String last, String email,
			String password) {
		super(appID, first, last, email, password);
		hasPrivilages = false;
	}
	
}
