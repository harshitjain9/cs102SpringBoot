package g1t2.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import g1t2.entities.*;
import g1t2.repositories.*;

@Service
public class RegistrationService {

	@Autowired
    private RegistrationRepository repository;

    public Registration saveEmailSuffixInDB(Registration registration) {
    	return repository.save(registration);
    }

    public List<Registration> getAllEmailSuffix(){
        return repository.findAll();
    }

    public void deleteEmailSuffix(String emailSuffix){
        repository.deleteById(emailSuffix);
    }
}
