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
    private String abbrVslM;

    @NotNull
    private String inVoyN;

    public String getEmail() {
        return email;
    }

    public String getAbbrVslM() {
        return abbrVslM;
    }

    public String getInVoyN() {
        return inVoyN;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AlertComposite)) return false;
        AlertComposite that = (AlertComposite) o;
        return email.equals(that.email) &&
                abbrVslM.equals(that.abbrVslM) &&
                inVoyN.equals(that.inVoyN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, abbrVslM, inVoyN);
    }
}
