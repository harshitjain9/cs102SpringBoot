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
      HttpStatus webServiceExists = getWebserviceById(webservice.getId()).getStatusCode();
      if (webServiceExists == HttpStatus.OK) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
      }
      WebService savedWebService = repository.save(webservice.hashingApiKey());
      return ResponseEntity.status(HttpStatus.CREATED).body(savedWebService);

    }

    public ResponseEntity<WebService> getWebserviceById(int id) {
    	WebService webservice = repository.findById(id);
    	if (webservice == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	return ResponseEntity.ok(webservice);
    }

    public ResponseEntity<WebService> replaceWebserviceInstructions(WebService webservice){
    	HttpStatus webServiceExists = getWebserviceById(webservice.getId()).getStatusCode();
    	if (webServiceExists == HttpStatus.NOT_FOUND) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  }
    	WebService savedWebService = repository.save(webservice.hashingApiKey());
        return ResponseEntity.ok(savedWebService);
    }
}
