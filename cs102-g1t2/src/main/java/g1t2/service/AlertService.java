package g1t2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import g1t2.entities.Alert;
import g1t2.entities.AlertComposite;
import g1t2.repositories.AlertRepository;

@Service
public class AlertService {
	
	@Autowired
	private AlertRepository repository;
	
	//GET
	public ResponseEntity<List<Alert>> getAllAlerts() {
		List<Alert> alerts = repository.findAll();
		return ResponseEntity.ok(alerts);
	}
	
	
	public ResponseEntity<List<Alert>> getAlertsAccordingToUser(String email) {
		List<Alert> alerts = repository.findByEmail(email);
		return ResponseEntity.ok(alerts);
	}
	
	public ResponseEntity<List<Alert>> getAlertsAccordingToVesselId(String abbrVslM, String inVoyN) {
		List<Alert> alerts = repository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
		return ResponseEntity.ok(alerts);
	}
	
	//POST
	public ResponseEntity<Alert> saveAlertInDB(Alert alert) {
		repository.save(alert);
		return ResponseEntity.status(HttpStatus.CREATED).body(alert);
		
	}
	
	public ResponseEntity<List<Alert>> saveAlertsInDB(List<Alert> alertList) {
		repository.saveAll(alertList);
		return ResponseEntity.status(HttpStatus.CREATED).body(alertList);
	}
	
	//DELETE
	public ResponseEntity<Void> deleteAlert(AlertComposite alertComposite) {
        repository.deleteById(alertComposite);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	
}