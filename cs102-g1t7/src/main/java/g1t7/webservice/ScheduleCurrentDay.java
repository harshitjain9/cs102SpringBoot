package g1t7.webservice;

import java.util.Base64;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.sun.mail.iap.ProtocolException;

import g1t7.entities.Vessel;
import g1t7.entities.WebService;
import g1t7.repositories.WebServiceRepository;
import g1t7.service.EmailServerService;
import g1t7.service.VesselService;
import g1t7.service.WebServiceService;

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
    private SenseChangeInFields timeDetectionService;
    
    public int getCurrentDayRate(int id){
        WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getCurrentDayUpdate();
    }

    public String getApiKey(int id){
    	WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getApiKey();
    }
    
    public String getSecondApiServerName(int id){
    	WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getSecondApiServerName();
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
    
	public boolean dueArriveInThreeDays(String dateString) {
		if (dateString == null || dateString.equals("")) {
			return false;
		}
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		LocalDate givenDate = LocalDate.parse(dateString, inputFormatter);
		return givenDate.isAfter(LocalDate.now()) && givenDate.isBefore(LocalDate.now().plusDays(3)); 
	}

  @Override
  public void run() { 
	  List<Vessel> vessels = serviceVessel.getAllVesselsNonResponseEntity();
	  
	  for (Vessel vessel: vessels) {	
		  
		  if (!dueArriveInThreeDays(vessel.getBthgDt())) {
			  continue;
		  }
		  
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
	          String encodedString = getApiKey(1);
	          byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
	          String apiKey = new String(decodedBytes);
	          LocalDate now = LocalDate.now();
//	          LocalDate start = now.plusDays(1);
	          LocalDate end = now.plusDays(6);
	          String serverName = getSecondApiServerName(1);
	          URL url = new URL(serverName+"/?vslvoy=" + vslVoy);
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
					Vessel updatedVessel = serviceVessel.updateVesselPartial(vessel, map);
					serviceVessel.updateAverageSpeed(updatedVessel);
					System.out.println("PRINT:" + map);
				}
	          }
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
	  this.reSchedule(getCurrentDayRate(1));
  }
}
