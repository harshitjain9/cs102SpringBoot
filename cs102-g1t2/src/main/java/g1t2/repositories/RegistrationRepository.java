package g1t2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import g1t2.entities.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
	void deleteById(String emailSuffix);
	Optional<Registration> findById(String emailSuffix);
}
