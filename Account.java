package olx;

import java.util.Date;
import java.time.LocalDate;
public class Account {
    String Name;
	LocalDate JoinDate;
    String Email;
    String PhoneNumber;
    String Password;
    public Account(String name, LocalDate joinDate, String email, String phoneNumber, String password) {
        Name = name;
        JoinDate = joinDate;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
    }
	public String getName() {
		return Name;
	}
	public LocalDate getJoinDate() {
		return JoinDate;
	}
	public String getEmail() {
		return Email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public String getPassword() {
		return Password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		return true;
	}



}
