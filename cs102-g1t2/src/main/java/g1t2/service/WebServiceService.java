package g1t2.service;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import g1t2.entities.Vessel;
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
    
    public WebService getWebserviceByIdNonResponseEntity(int id) {
    	return repository.findById(id);
    }
    


    public WebService replaceWebserviceInstructions(Map<Object, Object> fields){
    	WebService webservice = repository.findById(1);
    	boolean shouldHashApiKey = fields.containsKey("apiKey");
    	
		fields.forEach((k, v) -> {
		try {
		Field field = ReflectionUtils.findRequiredField(WebService.class, (String) k);
		field.setAccessible(true);
		if (!((String)k).equals("currentDayUpdate")) {
			ReflectionUtils.setField(field, webservice, String.valueOf(v));
		} else {
			ReflectionUtils.setField(field, webservice, (Integer) v);
		}
		
		} catch (IllegalArgumentException e) {
			
		} 
	});
	WebService updatedWebservice = null;
	if (shouldHashApiKey) {
		updatedWebservice = repository.save(webservice.hashingApiKey());
	} else {
		updatedWebservice = repository.save(webservice);
	}
	
	return updatedWebservice;
//    	if (webServiceExists == HttpStatus.NOT_FOUND) {
//			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		  }
//    	WebService savedWebService = repository.save(webservice.hashingApiKey());
//        return ResponseEntity.ok(savedWebService);
    }
}
