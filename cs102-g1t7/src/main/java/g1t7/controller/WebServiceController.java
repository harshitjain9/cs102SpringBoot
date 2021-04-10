package g1t7.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g1t7.entities.WebService;
import g1t7.service.WebServiceService;
import g1t7.webservice.ScheduleCurrentDay;
import g1t7.webservice.ScheduleDaily;

@CrossOrigin()
@RestController
public class WebServiceController {
	
	@Autowired
	private WebServiceService webServiceService;
	
	@Autowired
  private ScheduleDaily scheduleDaily;

  @Autowired
  private ScheduleCurrentDay scheduleCurrentDay;
	
	
	@RequestMapping("/webservice/{id}")
	public ResponseEntity<WebService> getWebserviceById(@PathVariable int id) {
		return webServiceService.getWebserviceById(id);
	}
		
	@PostMapping("/webservice")
    public ResponseEntity<WebService> saveWebserviceController(@RequestBody WebService webservice){
        return webServiceService.saveWebservice(webservice);
    }
	
	@PutMapping("/webservice")
	public WebService replaceWebserviceInstructions(@RequestBody Map<Object, Object> fields) {
		WebService updatedWebservice =  webServiceService.replaceWebserviceInstructions(fields);
		  scheduleCurrentDay.initializeScheduler();
		  scheduleDaily.initializeScheduler();
		  return updatedWebservice;
		
	}
	
	
	


}
