package g1t2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "alert_triggered_table")
@IdClass(AlertTriggeredComposite.class)
public class AlertTriggered {
	@Id
	private String email;
	@Id
	private String fullVslM;
	@Id
	private String inVoyN;
	@Id
	private String time;
	
	private String message;

	public AlertTriggered() {
		
	}
	
	public AlertTriggered(String email, String fullVslM, String inVoyN, String time, String message) {
		this.email = email;
		this.fullVslM = fullVslM;
		this.inVoyN = inVoyN;
		this.time = time;
		this.message = message;
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
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage() {
		this.message = message;
	}

}
