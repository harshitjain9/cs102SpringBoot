package g1t2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import g1t2.entities.Alert;
import g1t2.entities.AlertTriggered;
import g1t2.repositories.AlertTriggeredRepository;

public class AlertTriggeredService {
	@Autowired
	private AlertTriggeredRepository repository;

	public List<AlertTriggered> getAlertsTriggeredAccordingToUser(String email) {
		return repository.findAllById(email);
	}
	
	public AlertTriggered createNewAlertTriggered(AlertTriggered alertTriggered) {
    	return repository.save(alertTriggered);
    }


	
}
