package g1t2.webservice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import g1t2.entities.Alert;
import g1t2.entities.Vessel;
import g1t2.service.AlertService;
import g1t2.service.EmailServerService;
import g1t2.service.VesselService;
public class SenseChangeInTime {

	
    @Autowired
    private VesselService vesselService;

    @Autowired
    private  EmailServerService emailServerService;

    @Autowired
    private AlertService alertService;

    public ResponseEntity<Vessel> getExistingVessel(Vessel newVessel) {
        return vesselService.findByAbbrVslMAndInVoyN(newVessel.getAbbrVslM(), newVessel.getInVoyN());
    }

    public boolean hasTimeChanged(String oldTime, String newTime) {
        return !oldTime.equals(newTime);
    }

    public boolean hasBerthOrDepartTimeChanged(String oldBthgDt, String newBthgDt, String oldUnbthgDt, String newUnbthgDt) {
        return hasTimeChanged(oldBthgDt,newBthgDt) || hasTimeChanged(oldUnbthgDt, newUnbthgDt);
    }
    
    
    public String getFirstBthgDt(ResponseEntity<Vessel> newVessel) {
        return vesselService.getFirstBthgDt(newVessel);
    }



    public ResponseEntity<List<Alert>> getAlertListForVessel (Vessel newVessel){
        return alertService.getAlertsAccordingToVesselId(newVessel.getAbbrVslM(), newVessel.getInVoyN());
    }
    
    public List<Alert> getAlertListVessel(Vessel newVessel){
        return alertService.getAlertsAccordingToVesselIdNonResponseEntity(newVessel.getAbbrVslM(), newVessel.getInVoyN());
    }

    public void emailAllSubscribers(String vesselName, List<Alert> subList, String oldBthgDt, String newBthgDt, String
    oldUnbthgDt, String newUnbthgDt){
        for (Alert subscription : subList) {
            String sendTo =  subscription.getEmail();
            String subject = vesselName+": Berthing/Departure time change";
            String msg = "The berthing time has updated from "+oldBthgDt+" to "+newBthgDt
                    +"\nThe departure time has updated from "+oldUnbthgDt+" to "+newUnbthgDt;
            try{
                emailServerService.sendEmail(sendTo,subject,msg);
            }catch(Exception e){
                System.out.println("Failed to send email");
            }
        }
    }


    public void toEmailIfBerthOrDepartTimeChange(Vessel newVessel, ResponseEntity<Vessel> existingVessel){
        System.out.println("retrieved existing vessel");
        if (existingVessel != null) {
            String vesselName = newVessel.getAbbrVslM() + " " + newVessel.getInVoyN();
            String oldBthgDt = vesselService.getBthgDt(existingVessel);
            String oldUnbthgDt = vesselService.getUnbthgDt(existingVessel);
            String newBthgDt = newVessel.getBthgDt();
            String newUnbthgDt = newVessel.getUnbthgDt();

            if (hasBerthOrDepartTimeChanged(oldBthgDt, newBthgDt, oldUnbthgDt, newUnbthgDt)) {
                emailAllSubscribers(vesselName, getAlertListVessel(newVessel), oldBthgDt, newBthgDt, oldUnbthgDt, newUnbthgDt);
            }
        }
        		
    }

    public void operationsUponBerthTimeChange(Vessel newVessel, ResponseEntity<Vessel> existingVessel, List<Vessel> vesselList ) throws ParseException {
    	
        if (existingVessel != null) { //if it is an existing vessel
        	 String firstBerthTimeString = vesselService.getFirstBthgDt(existingVessel);
        	 String oldBerthTimeString = vesselService.getBthgDt(existingVessel);
            String newBerthTimeString = newVessel.getBthgDt();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//2021-03-27T11:41:00

            if (hasTimeChanged(oldBerthTimeString, newBerthTimeString)) {//if there is a change in berthing time
                Date firstBerthTime = format.parse(firstBerthTimeString);
                Date newBerthTime = format.parse(newBerthTimeString);
                
                // Error thrown here - no property called increment count
                vesselService.incrementCount(existingVessel); 
                newVessel.setCount(vesselService.getCount(existingVessel));
                long diff = Math.abs(firstBerthTime.getTime() - newBerthTime.getTime()); //Difference in old and new berthing time in milliseconds
                long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff); //Difference in old and new berthing time in minutes
                if (diffInMinutes >= 60) { // new berthing time changed by more than 60mins (huge change)
                    newVessel.setDisplayColor("red");
                } else if (diffInMinutes < 60 && diffInMinutes > 0) { // new berthing time changed by less than 60 mins
                    newVessel.setDisplayColor("yellow");
                } else if (diffInMinutes == 0) { // new berthing time same as old berthing time
                    newVessel.setDisplayColor("white");
                }

            }else{
                newVessel.setDisplayColor(vesselService.getDisplayColor(existingVessel));
                newVessel.setCount(vesselService.getCount(existingVessel));
                
            }
            newVessel.setFirstBthgDt(vesselService.getFirstBthgDt(existingVessel));
            vesselList.add(newVessel);

        } else {//if vessel returned is new
            newVessel.setFirstBthgDt(newVessel.getBthgDt());
            newVessel.setDisplayColor("white");
            newVessel.setCount(0);
            vesselList.add(newVessel);
        }

    }
	
}
