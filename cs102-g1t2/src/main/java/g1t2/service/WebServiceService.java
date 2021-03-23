package g1t2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g1t2.entities.WebService;
import g1t2.repositories.WebServiceRepository;

@Service
public class WebServiceService {
    @Autowired
    private WebServiceRepository repository;

    public WebService saveWebservice(WebService webservice) {
    	return repository.save(webservice);
    }

    public WebService getWebserviceById(Integer id) {
    	return repository.findById(id).orElse(null);
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
