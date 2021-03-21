package g1t2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registration_table")
public class Registration {

    @Id
    private String emailSuffix;

    public Registration() {
    	
    }
    
    public Registration(String emailSuffix) {
    	this.emailSuffix = emailSuffix;
    }
	public String getEmailSuffix() {
		return emailSuffix;
	}

	public void setEmailSuffix(String emailSuffix) {
		this.emailSuffix = emailSuffix;
	}

   
}
