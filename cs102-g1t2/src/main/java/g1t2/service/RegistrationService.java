package g1t2.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import g1t2.entities.Registration;
import g1t2.repositories.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
    private RegistrationRepository repository;

    public ResponseEntity<Registration> saveEmailSuffixInDB(Registration registration) {
    	Registration duplicate = repository.findById(registration.getEmailSuffix()).orElse(null);
    	if (duplicate != null) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	}
    	Registration savedRegistration = repository.save(registration);
    	return ResponseEntity.status(HttpStatus.CREATED).body(savedRegistration);
    }

    public ResponseEntity<List<Registration>> getAllEmailSuffix(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Void> deleteEmailSuffix(String emailSuffix){
        repository.deleteById(emailSuffix);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
