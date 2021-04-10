package g1t7.webservice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import g1t7.entities.Alert;
import g1t7.entities.Vessel;
import g1t7.service.AlertService;
import g1t7.service.EmailServerService;
import g1t7.service.VesselService;

@Component
public class SenseChangeInTime {

	
    @Autowired
    private VesselService vesselService;

    @Autowired
    private  EmailServerService emailServerService;

    @Autowired
    private AlertService alertService;

    public Vessel getExistingVessel(Vessel vessel) {
        return vesselService.findByFullVslMAndInVoyNNNonResponseEntity(vessel.getFullVslM(), vessel.getInVoyN());
    }

    public boolean hasTimeChanged(String oldTime, String newTime) {
        return !oldTime.equals(newTime);
    }

    public boolean hasBerthOrDepartTimeChanged(String oldBthgDt, String newBthgDt, String oldUnbthgDt, String newUnbthgDt) {
        return hasTimeChanged(oldBthgDt,newBthgDt) || hasTimeChanged(oldUnbthgDt, newUnbthgDt);
    }
    
    
//    public String getFirstBthgDt(ResponseEntity<Vessel> newVessel) {
//        return vesselService.getFirstBthgDt(newVessel);
//    }



    public List<Alert> getAlertListForVessel(Vessel vessel){
        return alertService.getAlertsAccordingToVesselIdNonResponseEntity(vessel.getFullVslM(), vessel.getInVoyN());
    }
    
    public void emailRegardingChangeInVessel(Vessel existingVessel, List<String> diffFields) {
    	List<Alert> alertList = getAlertListForVessel(existingVessel);
    	for (Alert alert: alertList) {
    		
    	      String vesselName = existingVessel.getFullVslM() + " " + existingVessel.getInVoyN();
    	      String subject = "Deatails of vessel " + vesselName + " have changed";
    	      
    	      StringBuffer sb = new StringBuffer();
    	      for(int i = 0; i < diffFields.size(); i++) {
    	         sb.append(diffFields.get(i));
    	      }
    	      String message = sb.toString();
    	      
    	      String toEmail = alert.getEmail();
    	      
    		try{
                emailServerService.sendEmail(subject, message, toEmail);
            }catch(Exception e){
                System.out.println("Error in sending email");
            }
    	}
    	
    }

    public void operationsUponBerthTimeChange(Vessel newVessel,Vessel existingVessel, List<Vessel> vesselList ) throws ParseException {
//      Vessel existingVessel = getExistingVessel(newVessel);
      if (existingVessel != null) { //if it is an existing vessel
          String firstBerthTimeString = existingVessel.getFirstBthgDt();
          String oldBerthTimeString = existingVessel.getBthgDt();
          String newBerthTimeString = newVessel.getBthgDt();
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//2020-11-16T13:00:00

          if (hasTimeChanged(oldBerthTimeString, newBerthTimeString)) {//if berthing time change
              Date firstBerthTime = format.parse(firstBerthTimeString);
              Date newBerthTime = format.parse(newBerthTimeString);
              existingVessel.incrementCount(); //
              newVessel.setCount(existingVessel.getCount());
              long diff = Math.abs(firstBerthTime.getTime() - newBerthTime.getTime()); //time diff in miliseconds
              long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff); //time diff in minutes
              if (diffInMinutes >= 60) {
                  newVessel.setDisplayColor("red");
              } else if (diffInMinutes < 60 && diffInMinutes > 0) {
                  newVessel.setDisplayColor("yellow");
              } else if (diffInMinutes == 0) { // when new berthing time change back to the first pulled berthing time
                  newVessel.setDisplayColor("white");
              }

          }else{
              newVessel.setDisplayColor(existingVessel.getDisplayColor());
              newVessel.setCount(existingVessel.getCount());
          }
          newVessel.setFirstBthgDt(existingVessel.getFirstBthgDt());
          vesselList.add(newVessel);

      } else {//if it is a new vessel
          newVessel.setFirstBthgDt(newVessel.getBthgDt());
          newVessel.setDisplayColor("white");
          newVessel.setCount(0);
          vesselList.add(newVessel);
      }

  }
	
}
