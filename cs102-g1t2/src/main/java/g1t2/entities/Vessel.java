package g1t2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(VesselComposite.class)
@Table(name = "VESSEL_TBL")
public class Vessel {
	
	@Id
	private String abbrVslM;
	
	@Id
	private String inVoyN;
	
	private String fullVslM;
	private String fullInVoyN;
	private String outVoyN;
	private String bthgDt;
	private String unbthgDt;
	private String berthN;
	private String status;
	
	// count refers to the number of times the timing has been updated
	// used for SenseChangeInTime.java
	private int count;
    private String firstBthgDt;
    private String displayColor;
	
	public Vessel() {
		
	}
	public Vessel(String fullVslM, String abbrVslM, String inVoyN, String fullInVoyN, String outVoyN, String btrDt, String unbthgDt, String berthN, String status) {
		this.fullVslM = fullVslM;
		this.abbrVslM = abbrVslM;
		this.inVoyN = inVoyN;
		this.fullInVoyN = fullInVoyN;
		this.outVoyN = outVoyN;
		this.bthgDt = btrDt;
		this.unbthgDt = unbthgDt;
		this.berthN = berthN;
		this.status = status;
	}
	
	public String getFullVslM() {
		return fullVslM;
	}
	public void setFullVslM(String fullVslM) {
		this.fullVslM = fullVslM;
	}
	public String getAbbrVslM() {
		return abbrVslM;
	}
	public void setAbbrVslM(String abbrVslM) {
		this.abbrVslM = abbrVslM;
	}
	public String getInVoyN() {
		return inVoyN;
	}
	public void setInVoyN(String inVoyN) {
		this.inVoyN = inVoyN;
	}
	public String getFullInVoyN() {
		return fullInVoyN;
	}
	public void setFullInVoyN(String fullInVoyN) {
		this.fullInVoyN = fullInVoyN;
	}
	public String getOutVoyN() {
		return outVoyN;
	}
	public void setOutVoyN(String outVoyN) {
		this.outVoyN = outVoyN;
	}
	public String getBthgDt() {
		return bthgDt;
	}
	public void setBthgDt(String btrDt) {
		this.bthgDt = btrDt;
	}
	public String getUnbthgDt() {
		return unbthgDt;
	}
	public void setUnbthgDt(String unbthgDt) {
		this.unbthgDt = unbthgDt;
	}
	public String getBerthN() {
		return berthN;
	}
	public void setBerthN(String berthN) {
		this.berthN = berthN;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	// below getters and setters are for SenseChangeInTime.java
	
	public int getCount() {
		return count;
	}
	public String getFirstBthgDt() {
		return firstBthgDt;
	}
	public void setCount(int count) {
        this.count = count;
    }

    public void setFirstBthgDt(String firstBthgDt) {
        this.firstBthgDt = firstBthgDt;
    }
    
    public void incrementCount() {
        this.count++;
    }
	
    
    public String getDisplayColor() {
		return displayColor;
	}
    
	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}
	
	
}
