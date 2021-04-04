package g1t2.entities;

import java.io.Serializable;
import java.util.Objects;


import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

public class AlertComposite implements Serializable {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String fullVslM;

    @NotNull
    private String inVoyN;
    
    public AlertComposite() {
    	
    }
    
    public AlertComposite(String email, String fullVslM, String inVoyN) {
    	this.email = email;
    	this.fullVslM = fullVslM;
    	this.inVoyN = inVoyN;
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
        if (!(o instanceof AlertComposite)) return false;
        AlertComposite that = (AlertComposite) o;
        return email.equals(that.email) &&
        		fullVslM.equals(that.fullVslM) &&
                inVoyN.equals(that.inVoyN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, fullVslM, inVoyN);
    }
}
