package g1t7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import g1t7.entities.Alert;
import g1t7.entities.AlertTriggered;
import g1t7.repositories.AlertTriggeredRepository;

@Service
public class AlertTriggeredService {
	
	@Autowired
	AlertTriggeredRepository repository;
	
	public ResponseEntity<List<AlertTriggered>> getAllAlerts() {
		List<AlertTriggered> alerts = repository.findAll();
		return ResponseEntity.ok(alerts);
	}
	
	
	public ResponseEntity<List<AlertTriggered>> getAlertsAccordingToUser(String email) {
		List<AlertTriggered> alerts = repository.findByEmail(email);
		return ResponseEntity.ok(alerts);
	}
	
	public AlertTriggered saveAlertInDB(AlertTriggered alertTriggered) {
		return repository.save(alertTriggered);	
	}
	
	public List<AlertTriggered> saveAlertsInDB(List<AlertTriggered> alertList) {
		return repository.saveAll(alertList);
	}

}
