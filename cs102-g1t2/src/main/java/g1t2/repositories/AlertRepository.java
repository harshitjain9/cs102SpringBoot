package g1t2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t2.entities.Alert;
import g1t2.entities.AlertComposite;

@Repository
public interface AlertRepository extends JpaRepository<Alert, AlertComposite> {
	//INHERITING THEN OVERLOADING
	List<Alert> findByEmail(String email);
    List<Alert> findByAbbrVslMAndInVoyN(String abbrVslM, String inVoyN);
	
}


