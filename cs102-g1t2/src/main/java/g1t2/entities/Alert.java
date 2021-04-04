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
	private String fullVslM;
	@Id
	private String inVoyN;
	
//	@OneToMany(mappedBy = "alert_triggered")
//	private Set<Alert> alerts;
	
	public Alert() {
		
	}
	
	public Alert(String email, String fullVslM, String inVoyN) {
		this.email = email;
		this.fullVslM = fullVslM;
		this.inVoyN = inVoyN;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullVslM() {
		return fullVslM;
	}
	
	public void setFullVslM(String fullVslM) {
		this.fullVslM = fullVslM;
	}
	
	public String getInVoyN() {
		return inVoyN;
	}
	
	public void setInVoyN(String inVoyN) {
		this.inVoyN = inVoyN;
	} 

}
