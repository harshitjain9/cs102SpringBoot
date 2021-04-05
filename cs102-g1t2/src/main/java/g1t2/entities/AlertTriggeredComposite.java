package g1t2.entities;

import java.io.Serializable;
import java.util.Objects;


import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

public class AlertTriggeredComposite implements Serializable {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String fullVslM;

    @NotNull
    private String inVoyN;
    
    @NotNull
    private String time;
    
    public AlertTriggeredComposite() {
    	
    }
    
    public AlertTriggeredComposite(String email, String fullVslM, String inVoyN, String time) {
    	this.email = email;
    	this.fullVslM = fullVslM;
    	this.inVoyN = inVoyN;
    	this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public String getFullVslM() {
        return fullVslM;
    }

    public String getInVoyN() {
        return inVoyN;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AlertTriggeredComposite)) return false;
        AlertTriggeredComposite that = (AlertTriggeredComposite) o;
        return email.equals(that.email) &&
        		fullVslM.equals(that.fullVslM) &&
                inVoyN.equals(that.inVoyN) &&
                time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, fullVslM, inVoyN, time);
    }
}
