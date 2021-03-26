package g1t2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	private String email;
	private String password;
	private String dateOfBirth;
	
	public Account() {
		
	}
	
	public Account(String email, String password, String dateOfBirth) {
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}