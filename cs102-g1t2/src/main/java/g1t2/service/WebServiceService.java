package g1t2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import g1t2.entities.WebService;
import g1t2.repositories.WebServiceRepository;

@Service
public class WebServiceService {
    @Autowired
    private WebServiceRepository repository;

    public ResponseEntity<WebService> saveWebservice(WebService webservice) {
    	HttpStatus webServiceExists = getWebServiceById(webservice.getId()).get;
    	
    	return repository.save(webservice);
    }

    public ResponseEntity<WebService> getWebserviceById(int id) {
    	WebService webservice = repository.findById(id);
    	if (webservice == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	return ResponseEntity.ok(webservice);
    }

    public String replaceWebserviceInstructions(WebService webservice){
        if(getWebserviceById(webservice.getId()) == null){
            return "Failure to find id";
        }
        repository.deleteById(webservice.getId());
        repository.save(webservice);
        return "Data replaced successfully";
    }
}
