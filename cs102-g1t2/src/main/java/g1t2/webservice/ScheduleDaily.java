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
//import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledFuture;

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

  // runs every once everyday.
//  @Scheduled(fixedRate = 86400000L) // 1 day in milliseconds
  @Override
  public void run() {    
      try {
          String apiKey = getApiKey(1);
          LocalDate now = LocalDate.now();
//          LocalDate start = now.plusDays(1);
          LocalDate end = now.plusDays(6);
          URL url = new URL("https://api.portnet.com/vsspp/pp/bizfn/berthingSchedule/retrieveByBerthingDate/v1.2");
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
                timeDetectionService.operationsUponBerthTimeChange(newVessel,existingVessel, vesselList);
                timeDetectionService.toEmailIfBerthOrDepartTimeChange(newVessel,existingVessel);
            }
//            System.out.println(vesselList);
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
