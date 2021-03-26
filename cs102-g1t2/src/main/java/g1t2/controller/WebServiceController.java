package g1t2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import g1t2.entities.WebService;
import g1t2.service.WebServiceService;

public class WebServiceController {
	@Autowired
    private ScheduleTaskDaily scheduleTaskDaily;

    @Autowired
    private ScheduleTaskCurrentDay scheduleTaskCurrentDay;

    @Autowired
    private WebServiceService service;

    @PostMapping("/addWebservice/")
    public WebService saveWebserviceController(@RequestBody WebService webservice){
        return service.saveWebservice(webservice.hashingApiKey());
    }

    @PostMapping("/updateWebservice/")
    public String replaceWebserviceInstructionsController(@RequestBody WebService webservice){
        if(webservice.getApiKey() == "" || webservice.getDailyUpdate() == "" || webservice.getCurrentDayUpdate() == 0){
            WebService webserviceFromDB = getWebserviceById(1);
            if(webservice.getApiKey() == ""){
                String api = webserviceFromDB.getApiKey();
                webservice.setApiKey(api);
            }
            if(webservice.getDailyUpdate() == ""){
                String daily = webserviceFromDB.getDailyUpdate();
                webservice.setDailyUpdate(daily);
            }
            if(webservice.getCurrentDayUpdate() == 0){
                int currentDay = webserviceFromDB.getCurrentDayUpdate();
                webservice.setCurrentDayUpdate(currentDay);
            }
            service.replaceWebserviceInstructions(webservice);
            scheduleTaskCurrentDay.initializeScheduler();
            scheduleTaskDaily.initializeScheduler();
            return "Data replaced successfully.";
        } else{
            service.replaceWebserviceInstructions(webservice.hashingApiKey());
            scheduleTaskDaily.initializeScheduler();
            return "Data replaced successfully.";
        }

    }

    @GetMapping("/getById/{id}")
    public WebService getWebserviceById(@PathVariable int id) {
    	return service.getWebserviceById(id);
    }
}
