package g1t2.service;

import org.springframework.beans.factory.annotation.Autowired;
import g1t2.repositories.AlertTriggeredRepository;

public class AlertTriggeredService {
	@Autowired
	private AlertTriggeredRepository repository;

	public AlertTriggeredRepository getRepository() {
		return repository;
	}

	public void setRepository(AlertTriggeredRepository repository) {
		this.repository = repository;
	}
	
	
	
}
