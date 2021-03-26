package g1t2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g1t2.entities.Alert;
import g1t2.entities.AlertComposite;
import g1t2.repositories.AlertRepository;

@Service
public class AlertService {
	
	@Autowired
	private AlertRepository repository;
	
	//GET
	public List<Alert> getAllAlerts() {
		return repository.findAll();
	}
	
	
	public List<Alert> getAlertsAccordingToUser(String email) {
		return repository.findByEmail(email);
	}
	
	public List<Alert> getAlertsAccordingToVesselId(String abbrVslM, String inVoyN) {
		return repository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
	}
	
	//POST
	public Alert saveAlertInDB(Alert alert) {
		return repository.save(alert);
		
	}
	
	public List<Alert> saveAlertsInDB(List<Alert> alertList) {
		return repository.saveAll(alertList);
	}
	
	
	//DELETE
	public void deleteAlert(AlertComposite alertComposite) {
        repository.deleteById(alertComposite);
    }
	
}