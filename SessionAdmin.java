package olx;
public class SessionAdmin extends SessionState {

	@Override
	public UserAccount getCurrentUserAccount() {
		return null;
	}

	@Override
	public AdminAccount getCurrentAdminAccount() {
		return (AdminAccount)(OLX.getInstance().getActiveAccount());
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
