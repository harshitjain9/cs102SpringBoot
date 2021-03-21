package g1t2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t2.entities.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
	//INHERITING THEN OVERLOADING
	void deleteById(String email, String abbrVslM, String inVoyN);
	List<Alert> findAllById(String email);
	List<Alert> findAllById(String abbrVslM, String inVoyN);
	
}


