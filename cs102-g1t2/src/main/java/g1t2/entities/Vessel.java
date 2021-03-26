package g1t2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vessel {
	
	@Id
	private String abbrVslM;
	
	@Id
	private String inVoyN;
	
	private String fullVslM;
	private String fullInVoyN;
	private String outVoyN;
	private String btrDt;
	private String unbthgDt;
	private String berthN;
	private String status;
	
	public Vessel() {
		
	}
	public Vessel(String fullVslM, String abbrVslM, String inVoyN, String fullInVoyN, String outVoyN, String btrDt, String unbthgDt, String berthN, String status) {
		this.fullVslM = fullVslM;
		this.abbrVslM = abbrVslM;
		this.inVoyN = inVoyN;
		this.fullInVoyN = fullInVoyN;
		this.outVoyN = outVoyN;
		this.btrDt = btrDt;
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
	public String getBtrDt() {
		return btrDt;
	}
	public void setBtrDt(String btrDt) {
		this.btrDt = btrDt;
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
	
	
	
}
