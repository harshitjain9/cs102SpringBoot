package g1t2.entities;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alert_triggered")
public class AlertTriggered {
//	Date dt = new Date();
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	String DateTime = sdf.format(dt);
	@Id
	@ManyToOne
	private Alert alert;
	@Id
	private String dateTime;

	public AlertTriggered() {
	
	}
	
	public AlertTriggered(Alert alert, String dateTime) {
		this.alert = alert;
		this.dateTime = dateTime;
	}
	
	
	public Alert getAlert() {
		return alert;
	}
	
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
