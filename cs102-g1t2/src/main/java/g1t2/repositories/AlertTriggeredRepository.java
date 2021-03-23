package g1t2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import g1t2.entities.Alert;
import g1t2.entities.AlertTriggered;

public interface AlertTriggeredRepository extends JpaRepository<AlertTriggered, Integer> {
	public void deleteById(Alert alert, String dateTime);
	List<AlertTriggered> findAllById(String email);
}
