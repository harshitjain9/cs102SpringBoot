package g1t7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import g1t7.entities.Registration;
import g1t7.service.RegistrationService;

@CrossOrigin()
@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService RService;
	

	@GetMapping("/emailSuffix")
	public ResponseEntity<List<Registration>> getAllEmailSuffix() {
		return RService.getAllEmailSuffix();
	}
	
	@PostMapping("/emailSuffix")
	public ResponseEntity<Registration> createNewEmailSuffix(@RequestBody Registration registration) {
		return RService.saveEmailSuffixInDB(registration);
	}

	@DeleteMapping("/emailSuffix/{emailSuffix}")
    public ResponseEntity<Void> deleteEmailSuffix(@PathVariable String emailSuffix){
        return RService.deleteEmailSuffix(emailSuffix);
	}
	

}
