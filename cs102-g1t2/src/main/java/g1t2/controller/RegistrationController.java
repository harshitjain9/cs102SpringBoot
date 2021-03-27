package g1t2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import g1t2.service.RegistrationService;
import g1t2.entities.Registration;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService RService;
	
	//GET
	//@RequestMapping("/getAllEmailSuffix")
	@GetMapping("/getAllEmailSuffix")
	public ResponseEntity<List<Registration>> getAllEmailSuffix() {
		return RService.getAllEmailSuffix();
	}
	
	//POST
	//	@RequestMapping(method=RequestMethod.POST, value="/createNewEmailSuffix")
	@PostMapping("/createNewEmailSuffix")
	public ResponseEntity<Registration> createNewEmailSuffix(@PathVariable Registration registration) {
		return RService.saveEmailSuffixInDB(registration);
	}
	
	
	//DELETE
	@DeleteMapping("/deleteEmailSuffix/{emailSuffix}")
    public ResponseEntity<Void> deleteEmailSuffix(@PathVariable String emailSuffix){
        return RService.deleteEmailSuffix(emailSuffix);
	}
	
	
	

}
