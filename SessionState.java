package olx;

public abstract class SessionState {
    public abstract UserAccount getCurrentUserAccount();
    public abstract AdminAccount getCurrentAdminAccount();
    public abstract boolean logInUser();
    public abstract boolean loginAdmin();
    public abstract void logOut();
}
