package g1t7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import g1t7.entities.Alert;
import g1t7.entities.AlertTriggered;
import g1t7.service.AlertTriggeredService;

@CrossOrigin()
@RestController
public class AlertTriggeredController {
	
	@Autowired
	AlertTriggeredService aTService;
	
	//GET
	@GetMapping("/alertsTriggered")
	public ResponseEntity<List<AlertTriggered>> getAllAlerts() {
		return aTService.getAllAlerts();
	}
	
	@GetMapping("/alertsTriggered/{email}")
	public ResponseEntity<List<AlertTriggered>> getAlertsAccordingToUser(@PathVariable String email) {
		return aTService.getAlertsAccordingToUser(email);
	}
	
	@PostMapping("/alertsTriggered")
	public AlertTriggered saveAlertInDB(@RequestBody AlertTriggered alertTriggered) {
		return aTService.saveAlertInDB(alertTriggered);
	}
	
}
