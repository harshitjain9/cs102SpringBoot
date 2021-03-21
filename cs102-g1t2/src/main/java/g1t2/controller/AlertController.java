package g1t2.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import g1t2.entities.Alert;
import g1t2.service.AlertService;

public class AlertController {
	private AlertService alertService;

	//GET
	@GetMapping("/getAllAlerts")
	public List<Alert> getAllAlerts() {
		return alertService.getAllAlerts();
	}
	
	@GetMapping("/getAlertsAccordingToUser/{email}")
	public List<Alert> getAlertsAccordingToUser(@PathVariable String email) {
		return alertService.getAlertsAccordingToUser(email);
	}
	
	@GetMapping("/getAlertsAccordingToVesselId/{abbrVslM}/{inVoyN}")
	public List<Alert> getAlertsAccordingToVesselId(String abbrVslM, String inVoyN) {
		return alertService.getAlertsAccordingToVesselId(abbrVslM, inVoyN);
	}
	
	
	//POST
	@PostMapping("/saveAlertInDB/{alert}")
	public Alert saveAlertInDB(@PathVariable Alert alert) {
		return alertService.saveAlertInDB(alert);
	}
	
	@PostMapping("/saveAlertInDB/{alertList}")
	public List<Alert> saveAlertsInDB(@PathVariable List<Alert> alertList) {
		return alertService.saveAlertsInDB(alertList);
	}
	
	
	
	//DELETE
	@DeleteMapping("/deleteAlert/{email}/{abbrVslM}/{inVoyN}")
    public void deleteAlert(@PathVariable String email, @PathVariable String abbrVslM, @PathVariable String inVoyN){
        alertService.deleteAlert(email, abbrVslM, inVoyN);
	}
}
