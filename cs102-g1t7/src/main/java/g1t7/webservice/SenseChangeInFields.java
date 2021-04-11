package g1t7.webservice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import g1t7.entities.Alert;
import g1t7.entities.AlertTriggered;
import g1t7.entities.Vessel;
import g1t7.service.AlertService;
import g1t7.service.AlertTriggeredService;
import g1t7.service.EmailServerService;
import g1t7.service.VesselService;


@Component
public class SenseChangeInFields {

	
    @Autowired
    private VesselService vesselService;

    @Autowired
    private  EmailServerService emailServerService;

    @Autowired
    private AlertService alertService;
    
    @Autowired
    private AlertTriggeredService alertTriggeredService;

    public List<Alert> getAlertListForVessel(Vessel vessel){
        return alertService.getAlertsAccordingToVesselIdNonResponseEntity(vessel.getFullVslM(), vessel.getInVoyN());
    }
    
    public void emailRegardingChangeInVessel(Vessel existingVessel, List<String> diffFields) {
    	List<Alert> alertList = getAlertListForVessel(existingVessel);
    	for (Alert alert: alertList) {
    	      String vesselName = existingVessel.getFullVslM() + " " + existingVessel.getInVoyN();
    	      String subject = "Details of vessel " + vesselName + " have changed";
    	      StringBuffer sb = new StringBuffer();
    	      for(int i = 0; i < diffFields.size(); i++) {
    	         sb.append(diffFields.get(i));
    	      }
    	      String message = sb.toString();
    	      String toEmail = alert.getEmail();
    		try{
                emailServerService.sendEmail(subject, message, toEmail);
                AlertTriggered alertTriggered = new AlertTriggered(toEmail, existingVessel.getFullVslM(),existingVessel.getInVoyN(),LocalDate.now().toString(),message);
                alertTriggeredService.saveAlertInDB(alertTriggered);
            }catch(Exception e){
                System.out.println("Error in sending email");
            }
    	}
    	
    }

	
}
