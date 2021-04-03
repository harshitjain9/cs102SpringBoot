package g1t2.webservice;

//import java.util.Base64;
//import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sun.mail.iap.ProtocolException;

import g1t2.entities.Vessel;
import g1t2.entities.WebService;
import g1t2.repositories.WebServiceRepository;
import g1t2.service.EmailServerService;
import g1t2.service.VesselService;
import g1t2.service.WebServiceService;

@Component
public class ScheduleCurrentDay implements Runnable{
	
	@SuppressWarnings("rawtypes")
	ScheduledFuture scheduledFuture;
	TaskScheduler taskScheduler;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private int c = 0;
	
    @Autowired
    private VesselService serviceVessel;

    @Autowired
    private WebServiceService service;

    @Autowired
    private EmailServerService emailServerService;

    @Autowired
    private WebServiceRepository repository;

    @Autowired
    private SenseChangeInTime timeDetectionService;
    
    public int getCurrentDayRate(int id){
        WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getCurrentDayUpdate();
    }

    public String getApiKey(int id){
    	WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getApiKey();
    }

    public void replaceDataForDaily(List<Vessel> vesselList){
        serviceVessel.addVesselsList(vesselList);
    }

    public void reSchedule(int interval){
        if(taskScheduler== null){
            this.taskScheduler = new ConcurrentTaskScheduler();
        }
        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
        }
        this.scheduledFuture = taskScheduler.scheduleAtFixedRate(this::run, interval);
    }

  // runs every once everyday.
//  @Scheduled(fixedRate = 86400000L) // 1 day in milliseconds
  @Override
  public void run() { 
	  List<Vessel> vessels = serviceVessel.getAllVesselsNonResponseEntity();
	  
	  for (Vessel vessel: vessels) {
		  
		  try {
			  Thread.sleep(1000);
		  } catch (InterruptedException ie) {
	    	  System.out.println("Thread Interrupted.");
	      } 
		  String fullVsIM = vessel.getFullVslM();
		  String inVoyN  = vessel.getInVoyN();
		  fullVsIM = fullVsIM.replaceAll("\\s+","");
		  fullVsIM = fullVsIM.replace("/", "");
		  
		  inVoyN = inVoyN.replaceAll("\\s+","");
		  inVoyN = inVoyN.replace("/", "");
		  
		  try {
	    	  String vslVoy = fullVsIM + inVoyN;
	          String apiKey = getApiKey(1);
	          LocalDate now = LocalDate.now();
//	          LocalDate start = now.plusDays(1);
	          LocalDate end = now.plusDays(6);
	          URL url = new URL("https://api.portnet.com/extapi/vessels/predictedbtr/?vslvoy=" + vslVoy);
	          HttpURLConnection http = (HttpURLConnection) url.openConnection();
	          http.setRequestProperty("accept", "application/json");
	          http.setRequestProperty("Apikey", apiKey);
	          try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {
	              StringBuilder response = new StringBuilder();
	              String responseLine = null;
	              while ((responseLine = br.readLine()) != null) {
	                  response.append(responseLine.trim());
	              }
	              Gson gson = new Gson();
				ErrorOutput e = gson.fromJson(response.toString(), ErrorOutput.class);
				if (e.getError() != null) {
					System.out.println(e.getError());
				} else {
					ObjectMapper mapper = new ObjectMapper();
					Map<Object, Object> map = mapper.readValue(response.toString(), Map.class);
					serviceVessel.updateVesselPartial(vessel.getFullVslM(), vessel.getInVoyN(), map);
					System.out.println("PRINT:" + map);
				}
	          }
	          
//	          Thread.sleep(1000);
	      } catch (MalformedURLException ex) {
	          System.out.println("Invalid URL");
	      } catch (Exception e) {
	          e.printStackTrace();
	      } finally {
	    	  initializeScheduler();
	      }
		  
		  
	  }
      

  }
  

  
  @PostConstruct
  public void initializeScheduler() {
//	  this.reSchedule(getCurrentDayRate(1));
  }
}
