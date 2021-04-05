package g1t2.webservice;

//import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
//import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import g1t2.entities.Vessel;
import g1t2.entities.WebService;
import g1t2.repositories.WebServiceRepository;
import g1t2.service.EmailServerService;
import g1t2.service.VesselService;
import g1t2.service.WebServiceService;

@Component
public class ScheduleDaily implements Runnable{
	
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
    
    private List<String> getDifference(Object s1, Object s2) throws IllegalAccessException {
        List<String> values = new ArrayList<>();
        for (Field field : s1.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value1 = field.get(s1);
            Object value2 = field.get(s2);
            if (value1 != null && value2 != null) {
                if (!Objects.equals(value1, value2)) {
                    values.add(String.valueOf(field.getName()+": "+value1+" -> "+value2));
                }
            }
        }
        return values;
    }
    
    public String getDailyFixedRate(int id){
        WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getDailyUpdate();
    }

    public String getApiKey(int id){
    	WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getApiKey();
    }

    public void replaceDataForDaily(List<Vessel> vesselList){
        serviceVessel.addVesselsList(vesselList);
    }

    public void reSchedule(String cronExpressionStr){
        if(taskScheduler== null){
            this.taskScheduler = new ConcurrentTaskScheduler();
        }
        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
        }
        this.scheduledFuture = this.taskScheduler.schedule(this, new CronTrigger(cronExpressionStr));
    }
    
    public String getFirstApiServerName(int id){
    	WebService webservice = service.getWebserviceByIdNonResponseEntity(id);
        return webservice.getFirstApiServerName();
    }

  // runs every once everyday.
//  @Scheduled(fixedRate = 86400000L) // 1 day in milliseconds
  @Override
  public void run() {    
      try {
          String encodedString = getApiKey(1);
          byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
          String apiKey = new String(decodedBytes);
          LocalDate now = LocalDate.now();
//          LocalDate start = now.plusDays(1);
          LocalDate end = now.plusDays(6);
          String serverName = getFirstApiServerName(1);
          URL url = new URL(serverName);
          HttpURLConnection http = (HttpURLConnection) url.openConnection();
          http.setRequestMethod("POST");
          http.setDoOutput(true);
          http.setRequestProperty("accept", "application/json");
          http.setRequestProperty("content-type", "application/json");
          http.setRequestProperty("Apikey", apiKey);

          String data = "{ \"dateFrom\": \"" + now.toString() + "\", \"dateTo\": \"" + end.toString() + "\"}";
          byte[] out = data.getBytes("utf-8");
          OutputStream stream = http.getOutputStream();
          stream.write(out);

          try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {
              StringBuilder response = new StringBuilder();
              String responseLine = null;
              while ((responseLine = br.readLine()) != null) {
                  response.append(responseLine.trim());
              }
            Gson gson = new Gson();

            JSONObject json = new JSONObject(response.toString());
            JSONArray jsonArray = json.getJSONArray("results");
            List<Vessel> vesselList = new ArrayList<>();
            for (int i = 0, size = jsonArray.length(); i < size; i++){
                JSONObject objectInArray = jsonArray.getJSONObject(i);
                Vessel newVessel= gson.fromJson(objectInArray.toString(), Vessel.class);
                Vessel existingVessel = timeDetectionService.getExistingVessel(newVessel);
                
                //flow:
                //check if there is a difference in the new vessel and the old vessel
                //if yes, update all the users who have subscribed to the vessel and update the vessel as well, save alert trigerred data in database
                //if no, just continue.
                //previous average speed:
                //update the latest avgSpeeds.
                
                List<String> diffFields = getDifference(existingVessel, newVessel);
                if (diffFields.size() != 0) {
                	timeDetectionService.emailRegardingChangeInVessel(existingVessel,diffFields);
                } else {
                	
                }
               vesselList.add(newVessel);
                
//                timeDetectionService.operationsUponBerthTimeChange(newVessel,existingVessel, vesselList);
//                timeDetectionService.toEmailIfBerthOrDepartTimeChange(newVessel,existingVessel);
            }
            System.out.println(vesselList);
//            c++;
//            System.out.println(c);
            replaceDataForDaily(vesselList);

          }
      } catch (MalformedURLException ex) {
          System.out.println("Invalid URL");
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
    	  initializeScheduler();
      }

  }

  
  @PostConstruct
  public void initializeScheduler() {
//	  this.reSchedule(3);
//      this.reSchedule(getDailyFixedRate(1));
//	  this.reSchedule("* * * ? * *");
  }
}
