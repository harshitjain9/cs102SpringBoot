package g1t2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import g1t2.entities.AlertTriggered;
import g1t2.service.AlertTriggeredService;

@RestController
public class AlertTriggeredController {
	
	@Autowired
	private AlertTriggeredService alertTriggered;
	
	//GET
	@GetMapping("/getAlertsTriggeredAccordingToUser")
	public List<AlertTriggered> getAlertsTriggeredAccordingToUser(@PathVariable String email) {
		return alertTriggered.getAlertsTriggeredAccordingToUser(email);
	}
	
	//POST
	@PostMapping("/createNewAlertTriggered")
	public AlertTriggered createNewAlertTriggered(@PathVariable AlertTriggered alertTriggered) {
    	return this.alertTriggered.createNewAlertTriggered(alertTriggered);
    }

}
