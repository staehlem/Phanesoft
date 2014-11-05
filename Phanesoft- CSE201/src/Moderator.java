
public class Moderator extends UserID {
	

	public Moderator(int appID, String first, String last, String email,
			String password) {
		super(appID, first, last, email, password);
		hasPrivilages = true;
	}

}
