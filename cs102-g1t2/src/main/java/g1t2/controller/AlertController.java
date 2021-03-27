package g1t2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import g1t2.entities.Alert;
import g1t2.entities.AlertComposite;
import g1t2.service.AlertService;

@RestController
public class AlertController {
	private AlertService alertService;

	//GET
	@GetMapping("/getAllAlerts")
	public ResponseEntity<List<Alert>> getAllAlerts() {
		return alertService.getAllAlerts();
	}
	
	@GetMapping("/getAlertsAccordingToUser/{email}")
	public ResponseEntity<List<Alert>> getAlertsAccordingToUser(@PathVariable String email) {
		return alertService.getAlertsAccordingToUser(email);
	}
	
	@GetMapping("/getAlertsAccordingToVesselId/{abbrVslM}/{inVoyN}")
	public ResponseEntity<List<Alert>> getAlertsAccordingToVesselId(String abbrVslM, String inVoyN) {
		return alertService.getAlertsAccordingToVesselId(abbrVslM, inVoyN);
	}
	
	
	//POST
	@PostMapping("/saveAlertInDB/{alert}")
	public ResponseEntity<Alert> saveAlertInDB(@PathVariable Alert alert) {
		return alertService.saveAlertInDB(alert);
	}
	
	@PostMapping("/saveAlertInDB/{alertList}")
	public ResponseEntity<List<Alert>> saveAlertsInDB(@PathVariable List<Alert> alertList) {
		return alertService.saveAlertsInDB(alertList);
	}
	
	
	
	//DELETE
	@DeleteMapping("/deleteAlert")
    public ResponseEntity<Void> deleteSubscription(@RequestBody AlertComposite alertComposite){
        return alertService.deleteAlert(alertComposite);
    }
}
