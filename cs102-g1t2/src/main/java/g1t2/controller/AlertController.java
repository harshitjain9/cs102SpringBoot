package g1t2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import g1t2.entities.Alert;
import g1t2.entities.AlertComposite;
import g1t2.service.AlertService;

@CrossOrigin()
@RestController
public class AlertController {
	
	@Autowired
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
	
	@GetMapping("/getAlertsAccordingToVesselId/{fullVslM}/{inVoyN}")
	public ResponseEntity<List<Alert>> getAlertsAccordingToVesselId(@PathVariable String fullVslM, @PathVariable String inVoyN) {
		return alertService.getAlertsAccordingToVesselId(fullVslM, inVoyN);
	}
	
	
	//POST
	@PostMapping("/saveAlertInDB")
	public ResponseEntity<Alert> saveAlertInDB(@RequestBody Alert alert) {
		return alertService.saveAlertInDB(alert);
	}
	
	@PostMapping("/saveAlertsInDB")
	public ResponseEntity<List<Alert>> saveAlertsInDB(@RequestBody List<Alert> alertList) {
		return alertService.saveAlertsInDB(alertList);
	}
	
	
	//DELETE
	@DeleteMapping("/deleteAlert")
    public ResponseEntity<Void> deleteAlert(@RequestBody AlertComposite alertComposite){
        return alertService.deleteAlert(alertComposite);
    }
}
