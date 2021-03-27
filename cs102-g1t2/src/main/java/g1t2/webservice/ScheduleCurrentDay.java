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

import com.google.gson.Gson;

import g1t2.entities.Vessel;

public class ScheduleCurrentDay {

  public void replaceDataForCurrentDay(List<Vessel> vesselList){
      //some code
  }

  public void run() {
      String apiKey = "8f765e3bf8534243bceeb5341a78f5f2";
      LocalDate date = LocalDate.now();
      String command = "curl -X POST \"https://api.portnet.com/vsspp/pp/bizfn/berthingSchedule/retrieveByBerthingDate/v1.2\" -H \"accept: application/json\" -H \"Apikey: " + apiKey + "\" -H \"content-type: application/json\" -d \"{ \\\"dateFrom\\\":\\\"" + date.toString() + "\\\", \\\"dateTo\\\":\\\"" + date.toString() + "\\\"}\\\"";
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
              //Vessel existingVessel = timeDetectionService.getExistingVessel(newVessel);
              //timeDetectionService.operationsUponBerthTimeChange(newVessel,existingVessel, vesselList);
              //timeDetectionService.toEmailIfBerthOrDepartTimeChange(newVessel,existingVessel);
          }
//          System.out.println(vesselList.toString());
          replaceDataForCurrentDay(vesselList);;

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
