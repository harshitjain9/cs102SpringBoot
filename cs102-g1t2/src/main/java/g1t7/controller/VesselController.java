package g1t7.controller;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import g1t7.entities.Alert;
import g1t7.entities.AlertTriggered;
import g1t7.entities.Vessel;
import g1t7.service.AlertService;
import g1t7.service.AlertTriggeredService;
import g1t7.service.EmailServerService;
import g1t7.service.VesselService;

@CrossOrigin()
@RestController
public class VesselController {
	
	@Autowired
	private VesselService vesselService;
	
	//COMMENT OUT LATER
	@Autowired
	private AlertService alertService;
	@Autowired
	private EmailServerService emailServerService;
	@Autowired
	private AlertTriggeredService alertTriggeredService;
	
	@RequestMapping("/vessels")
	public ResponseEntity<List<Vessel>> getAllVessels() {
		return vesselService.getAllVessels();
	}
	
	@RequestMapping("/vessels/{fullVslM}/{inVoyN}")
	public ResponseEntity<Vessel> getVessel(@PathVariable String fullVslM, @PathVariable String inVoyN) {
		return vesselService.findByFullVslMAndInVoyN(fullVslM, inVoyN);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/vessels")
	public ResponseEntity<Vessel> addVessel(@RequestBody Vessel vessel) {
		return vesselService.addVessel(vessel);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/vessels/{fullVslM}/{inVoyN}/{outVoyN}")
	public ResponseEntity<Vessel> updateVessel(@PathVariable String fullVslM, @PathVariable String inVoyN, @PathVariable String outVoyN, @RequestBody Vessel vessel) {
		return vesselService.updateVessel(fullVslM, inVoyN, outVoyN, vessel);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/vessels/{fullVslM}/{inVoyN}/{outVoyN}")
	public ResponseEntity<Void> deleteVessel(@PathVariable String fullVslM, @PathVariable String inVoyN, @PathVariable String outVoyN) {
		return vesselService.deleteVessel(fullVslM, inVoyN, outVoyN);
	}
	
//	@RequestMapping(method=RequestMethod.PATCH, value="/vessels/{fullVslM}/{inVoyN}")
//	public ResponseEntity<Vessel> updatePartialVessel(@PathVariable String fullVslM, @PathVariable String inVoyN, @RequestBody Map<Object, Object> fields) {
//		return vesselService.updateVesselPartial(fullVslM, inVoyN, fields);
//	}
	
	 public List<Alert> getAlertListForVessel(Vessel vessel){
	        return alertService.getAlertsAccordingToVesselIdNonResponseEntity(vessel.getFullVslM(), vessel.getInVoyN());
	    }
	
	@RequestMapping("/testingEmail")
	public List<String> getDifference(@RequestBody Vessel[] list) throws IllegalAccessException {
		Vessel s1 = list[0];
		Vessel s2 = list[1];
        List<String> values = new ArrayList<>();
        for (Field field : s1.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value1 = field.get(s1);
            Object value2 = field.get(s2);
            if (value1 != null && value2 != null) {
                if (!Objects.equals(value1, value2)) {
                    values.add(String.valueOf(field.getName()+": "+value1+" -> "+value2));
                }
            }
        }
        
        List<Alert> alertList = alertService.getAlertsAccordingToVesselIdNonResponseEntity(s1.getFullVslM(), s1.getInVoyN());
    	for (Alert alert: alertList) {
    	      String vesselName = s1.getFullVslM() + " " + s1.getInVoyN();
    	      String subject = "Details of vessel " + vesselName + " have changed";
    	      StringBuffer sb = new StringBuffer();
    	      for(int i = 0; i < values.size(); i++) {
    	         sb.append(values.get(i));
    	         sb.append('\n');
    	      }
    	      String message = sb.toString();
    	      String toEmail = alert.getEmail();
    		try{
                emailServerService.sendEmail(subject, message, toEmail);
                AlertTriggered alertTriggered = new AlertTriggered(toEmail, s1.getFullVslM(),s1.getInVoyN(),LocalDate.now().toString(),message);
                AlertTriggered saved = alertTriggeredService.saveAlertInDB(alertTriggered);
            }catch(Exception e){
            	e.printStackTrace();
                System.out.println("Error in sending email");
            }
    	}
        return values;
    }
	
	
	// For SenseChangeInTime.java
//	@RequestMapping("/vessels/{firstBthgDt}")
//	public String getFirstBthgDt(@PathVariable ResponseEntity<Vessel> vessel) {
//		return vesselService.getFirstBthgDt(vessel);
//	}
//	
//	@RequestMapping("/vessels/{BthgDt}")
//	public String getBthgDt(@PathVariable ResponseEntity<Vessel> vessel) {
//		return vesselService.getBthgDt(vessel);
//	}
//	
//	@RequestMapping("/vessels/{Count}")
//	public int getCount(@PathVariable ResponseEntity<Vessel> vessel) {
//		return vesselService.getCount(vessel);
//	}
//	@RequestMapping("/vessels/{DisplayColor}")
//	public String getDisplayColor(@PathVariable ResponseEntity<Vessel> vessel) {
//		return vesselService.getDisplayColor(vessel);
//	}
//	
//	@RequestMapping("/vessels/{UnbthgDt}")
//	public String getUnbthgDt(@PathVariable ResponseEntity<Vessel> vessel) {
//		return vesselService.getUnbthgDt(vessel);
//	}
//	
//	// increment count works as a setter for count that does count +=1
//	@RequestMapping(method=RequestMethod.PUT, value="/vessels/{incrementCount}")
//	public void incrementCount(@PathVariable ResponseEntity<Vessel> vessel) {
//		vesselService.incrementCount(vessel);
//	}
}
