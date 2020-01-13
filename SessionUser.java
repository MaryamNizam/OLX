package olx;


public class SessionUser extends SessionState {

	@Override
	public UserAccount getCurrentUserAccount() {
		return (UserAccount)(OLX.getInstance().getActiveAccount());
	}

	@Override
	public AdminAccount getCurrentAdminAccount() {
		return null;
	}

	@Override
	public boolean logInUser() {
		throw new IllegalStateException();
	}

	@Override
	public boolean loginAdmin() {
		throw new IllegalStateException();
	}

	@Override
	public void logOut() {
		OLX.getInstance().setActiveAccount(null);
		OLX.getInstance().setState(new SessionInactive());
		
	}
	
}
