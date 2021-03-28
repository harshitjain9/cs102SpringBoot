package g1t2.webservice;

//import java.util.Base64;
//import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.ScheduledFuture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;

import g1t2.entities.Vessel;
import g1t2.service.VesselService;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class ScheduleDaily {
	
	@Autowired
    private VesselService serviceVessel;

	@Autowired
    private SenseChangeInTime senseChangeInTimeService;
    
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



  public void replaceDataForDaily(List<Vessel> vesselList){
	  serviceVessel.addVesselsList(vesselList);
	  
  }
  // runs every once everyday.
  @Scheduled(fixedRate = 86400000L) // 1 day in milliseconds
  public void run() {
      String apiKey = "8f765e3bf8534243bceeb5341a78f5f2";
      LocalDate now = LocalDate.now();
      LocalDate start = now.plusDays(1);
      LocalDate end = now.plusDays(6);
      String command = "curl -X POST \"https://api.portnet.com/vsspp/pp/bizfn/berthingSchedule/retrieveByBerthingDate/v1.2\" -H \"accept: application/json\" -H \"Apikey: " + apiKey + "\" -H \"content-type: application/json\" -d \"{ \\\"dateFrom\\\":\\\"" + start.toString() + "\\\", \\\"dateTo\\\":\\\"" + end.toString() + "\\\"}\\\"";
      String readLine = null;
      StringBuffer response = new StringBuffer();
      Gson gson = new Gson();

      try(BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()))) {
          while ((readLine = reader.readLine()) != null) {
              response.append(readLine);
          }
          JSONObject json = new JSONObject(response.toString());
          JSONArray jsonArray = json.getJSONArray("results");
          List<Vessel> vesselList = new ArrayList<>();
          for (int i = 0, size = jsonArray.length(); i < size; i++){
              JSONObject objectInArray = jsonArray.getJSONObject(i);
              Vessel newVessel= gson.fromJson(objectInArray.toString(), Vessel.class);
              ResponseEntity<Vessel> existingVessel = senseChangeInTimeService.getExistingVessel(newVessel);
              senseChangeInTimeService.operationsUponBerthTimeChange(newVessel,existingVessel, vesselList);
              senseChangeInTimeService.toEmailIfBerthOrDepartTimeChange(newVessel,existingVessel);
          }
//          System.out.println(vesselList.toString());
          replaceDataForDaily(vesselList);

      } catch (JSONException e) {
          System.out.println("Api link is currently down");
//          System.out.printf("Error");
      } catch (Exception e){
          e.printStackTrace();
      }
      finally {
          //initializeScheduler();
      }
  }
}
