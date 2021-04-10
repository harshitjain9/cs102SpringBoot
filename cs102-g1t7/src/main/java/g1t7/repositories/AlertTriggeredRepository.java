package g1t7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t7.entities.AlertTriggered;
import g1t7.entities.AlertTriggeredComposite;

@Repository
public interface AlertTriggeredRepository extends JpaRepository<AlertTriggered, AlertTriggeredComposite>{
	List<AlertTriggered> findByEmail(String email);
}
