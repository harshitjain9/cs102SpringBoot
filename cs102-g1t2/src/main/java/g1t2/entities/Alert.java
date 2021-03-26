package g1t2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "alert_table")
@IdClass(AlertComposite.class)
public class Alert {
	@Id
	private String email;
	@Id
	private String abbrVslM;
	@Id
	private String inVoyN;
	
//	@OneToMany(mappedBy = "alert_triggered")
//	private Set<Alert> alerts;
	
	public Alert() {
		
	}
	
	public Alert(String email, String abbrVslM, String inVoyN) {
		this.email = email;
		this.abbrVslM = abbrVslM;
		this.inVoyN = inVoyN;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAbbrVslM() {
		return abbrVslM;
	}
	
	public void setAbbrVslM(String abbrVslM) {
		this.abbrVslM = abbrVslM;
	}
	
	public String getInVoyN() {
		return inVoyN;
	}
	
	public void setInVoyN(String inVoyN) {
		this.inVoyN = inVoyN;
	} 

}
