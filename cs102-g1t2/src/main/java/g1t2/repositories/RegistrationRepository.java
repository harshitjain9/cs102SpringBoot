package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import g1t2.entities.*;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
	void deleteById(String emailSuffix);
}
