package olx;

import java.util.Scanner;

import org.slf4j.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class SessionInactive extends SessionState {

	private TextIO textIO = TextIoFactory.getTextIO();
	@Override
	public UserAccount getCurrentUserAccount() {
		return null;
	}

	@Override
	public AdminAccount getCurrentAdminAccount() {
		return null;
	}

	@Override
	public boolean logInUser() {
		//TAKE APPRORIATE INPUT AND SET APPROPIATE STATE. (WHICH I NO DO IN LAB FINAL SO I GET A D+)//congratulations
		OLX.getInstance().terminal.println("Login screen here");
		String email =  textIO.newStringInputReader().withMinLength(1).read("Email");
		String pass = textIO.newStringInputReader().withMinLength(1).read("Password");
		
		boolean exists = OLX.DBCON.checkUserInstance(email);
		UserAccount account = null;
		if(exists) {
			try
			{
				account = OLX.DBCON.getUserDetails(email);
				if(account.getPassword().equals(pass)){
					OLX.getInstance().setActiveAccount(account);
					OLX.getInstance().setState(new SessionUser());
					return true;
				}
				else {
					return false;
				}
			}
			catch(Exception e) {
				System.out.println(e);
				return false;
			}

			
		}
		else {
			return false;
		}

	}
	@Override
	public boolean loginAdmin() {
		//TAKE APPRORIATE INPUT AND SET APPROPIATE STATE. (WHICH I NO DO IN LAB FINAL SO I GET A D+)
		String email =  textIO.newStringInputReader().withMinLength(1).read("Email");
		String Pass = textIO.newStringInputReader().withMinLength(1).read("Password");

		if(OLX.getInstance().getAdministrator().Email.contentEquals(email) && OLX.getInstance().getAdministrator().Password.contentEquals(Pass))
		{
			OLX.getInstance().setState(new SessionAdmin());
			OLX.getInstance().setActiveAccount(OLX.getInstance().getAdministrator());
			return true;
		}
		return false;
	}

	@Override
	public void logOut() {
		throw new IllegalStateException();
		//this should never be called.
	}
	
}
