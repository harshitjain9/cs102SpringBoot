package g1t2.entities;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WebService_table")
public class WebService {
	@Id
    private int id = 1;
    private String apiKey;
    private String dailyUpdate = "* * * ? * *";
    private int currentDayUpdate;
    private String firstApiServerName;
    private String secondApiServerName;
    
    public WebService() {
    	
    }

    public WebService(int id, String encodedString, String dailyUpdate, int currentDayUpdate, String firstApiServerName, String secondApiServerName) {
    	this.id = id;
    	apiKey = encodedString;
    	this.dailyUpdate = dailyUpdate;
    	this.currentDayUpdate = currentDayUpdate;
    	this.firstApiServerName = firstApiServerName;
    	this.secondApiServerName = secondApiServerName;
	}

	public Integer getId(){
		return id; 
	}

    public String getApiKey(){
        return apiKey;
    }

    public String getDailyUpdate(){
        return dailyUpdate;
    }

    public Integer getCurrentDayUpdate(){
        return currentDayUpdate;
    }
    
    public String getFirstApiServerName() {
    	return firstApiServerName;
    }
    
    public String getSecondApiServerName() {
    	return secondApiServerName;
    }

    public void setApiKey(String apiKey){this.apiKey = apiKey;}

    public void setDailyUpdate(String dailyUpdate){this.dailyUpdate = dailyUpdate;}

    public void setCurrentDayUpdate(int currentDayUpdate){this.currentDayUpdate = currentDayUpdate;}
    
    public void setFirstApiServerName(String firstApiServerName) {
    	this.firstApiServerName = firstApiServerName;
    }
    
    public void setSecondApiServerName(String secondApiServerName) {
    	this.secondApiServerName = secondApiServerName;
    }

    public WebService hashingApiKey(){
        String apiKeyExtract = this.getApiKey();
        String encodedString = Base64.getEncoder().encodeToString(apiKeyExtract.getBytes());
        WebService webserviceInstructions = new WebService(this.id, encodedString, this.dailyUpdate, this.currentDayUpdate, this.firstApiServerName, this.secondApiServerName);
        return webserviceInstructions;
    }
}
