package g1t7.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@IdClass(VesselComposite.class)
@Table(name = "VESSEL_TBL")
public class Vessel {
	
	
	
	
	@Id
	private String inVoyN;
	@Id
	private String fullVslM;
	
	private String abbrVslM;
	private String fullInVoyN;
	private String outVoyN;
	private String shiftSeqN;
	private String bthgDt;
	private String unbthgDt;
	private String berthN;
	private String status;
	
	private String AVG_SPEED;
	private String DISTANCE_TO_GO;
	private String IS_PATCHING_ACTIVATED;
	private String MAX_SPEED;
	private String PATCHING_PREDICTED_BTR;
	private String PREDICTED_BTR;
	private String VESSEL_NAME;
	private String VOYAGE_CODE_INBOUND;
	private String VSL_VOY;
	private String CURRENT_PORT_COUNTRY;
	private String CURRENT_PORT;
	private String LAST_PORT_COUNTRY;
	private String LAST_PORT;
	private String NEXT_PORT_COUNTRY;
	private String NEXT_PORT_NAME;
	private String currentAvgSpeed;
	private String secondLastAvgSpeed;
	private String thirdLastAvgSpeed;
	
	// count refers to the number of times the timing has been updated
	// used for SenseChangeInTime.java

	public Vessel(String inVoyN, String fullVslM, String abbrVslM, String fullInVoyN, String outVoyN, String shiftSeqN, String bthgDt, String unbthgDt, String berthN, String status, String AVG_SPEED, String DISTANCE_TO_GO, String IS_PATCHING_ACTIVATED, String MAX_SPEED, String PATCHING_PREDICTED_BTR, String PREDICTED_BTR, String VESSEL_NAME, String VOYAGE_CODE_INBOUND, String VSL_VOY, String CURRENT_PORT_COUNTRY, String CURRENT_PORT, String LAST_PORT_COUNTRY, String LAST_PORT, String NEXT_PORT_COUNTRY, String NEXT_PORT_NAME, String currentAvgSpeed, String secondLastAvgSpeed, String thirdLastAvgSpeed) {
		this.inVoyN = inVoyN;
		this.fullVslM = fullVslM;
		this.abbrVslM = abbrVslM;
		this.fullInVoyN = fullInVoyN;
		this.outVoyN = outVoyN;
		this.shiftSeqN = shiftSeqN;
		this.bthgDt = bthgDt;
		this.unbthgDt = unbthgDt;
		this.berthN = berthN;
		this.status = status;
		this.AVG_SPEED = AVG_SPEED;
		this.DISTANCE_TO_GO = DISTANCE_TO_GO;
		this.IS_PATCHING_ACTIVATED = IS_PATCHING_ACTIVATED;
		this.MAX_SPEED = MAX_SPEED;
		this.PATCHING_PREDICTED_BTR = PATCHING_PREDICTED_BTR;
		this.PREDICTED_BTR = PREDICTED_BTR;
		this.VESSEL_NAME = VESSEL_NAME;
		this.VOYAGE_CODE_INBOUND = VOYAGE_CODE_INBOUND;
		this.VSL_VOY = VSL_VOY;
		this.CURRENT_PORT_COUNTRY = CURRENT_PORT_COUNTRY;
		this.CURRENT_PORT = CURRENT_PORT;
		this.LAST_PORT_COUNTRY = LAST_PORT_COUNTRY;
		this.LAST_PORT = LAST_PORT;
		this.NEXT_PORT_COUNTRY = NEXT_PORT_COUNTRY;
		this.NEXT_PORT_NAME = NEXT_PORT_NAME;
		this.currentAvgSpeed = currentAvgSpeed;
		this.secondLastAvgSpeed = secondLastAvgSpeed;
		this.thirdLastAvgSpeed = thirdLastAvgSpeed;
	}
	
	public Vessel() {
		
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
	public String getShiftSeqN() {
		return shiftSeqN;
	}
	public void setShiftSeqN(String shiftSeqN) {
		this.shiftSeqN = shiftSeqN;
	}
	public String getAVG_SPEED() {
		return AVG_SPEED;
	}
	public void setAVG_SPEED(String aVG_SPEED) {
		AVG_SPEED = aVG_SPEED;
	}
	public String getDISTANCE_TO_GO() {
		return DISTANCE_TO_GO;
	}
	public void setDISTANCE_TO_GO(String dISTANCE_TO_GO) {
		DISTANCE_TO_GO = dISTANCE_TO_GO;
	}
	public String getIS_PATCHING_ACTIVATED() {
		return IS_PATCHING_ACTIVATED;
	}
	public void setIS_PATCHING_ACTIVATED(String iS_PATCHING_ACTIVATED) {
		IS_PATCHING_ACTIVATED = iS_PATCHING_ACTIVATED;
	}
	public String getMAX_SPEED() {
		return MAX_SPEED;
	}
	public void setMAX_SPEED(String mAX_SPEED) {
		MAX_SPEED = mAX_SPEED;
	}
	public String getPATCHING_PREDICTED_BTR() {
		return PATCHING_PREDICTED_BTR;
	}
	public void setPATCHING_PREDICTED_BTR(String pATCHING_PREDICTED_BTR) {
		PATCHING_PREDICTED_BTR = pATCHING_PREDICTED_BTR;
	}
	public String getPREDICTED_BTR() {
		return PREDICTED_BTR;
	}
	public void setPREDICTED_BTR(String pREDICTED_BTR) {
		PREDICTED_BTR = pREDICTED_BTR;
	}
	public String getVESSEL_NAME() {
		return VESSEL_NAME;
	}
	public void setVESSEL_NAME(String vESSEL_NAME) {
		VESSEL_NAME = vESSEL_NAME;
	}
	public String getVOYAGE_CODE_INBOUND() {
		return VOYAGE_CODE_INBOUND;
	}
	public void setVOYAGE_CODE_INBOUND(String vOYAGE_CODE_INBOUND) {
		VOYAGE_CODE_INBOUND = vOYAGE_CODE_INBOUND;
	}
	public String getVSL_VOY() {
		return VSL_VOY;
	}
	public void setVSL_VOY(String vSL_VOY) {
		VSL_VOY = vSL_VOY;
	}

	public String getCURRENT_PORT_COUNTRY() {
		return this.CURRENT_PORT_COUNTRY;
	}

	public void setCURRENT_PORT_COUNTRY(String CURRENT_PORT_COUNTRY) {
		this.CURRENT_PORT_COUNTRY = CURRENT_PORT_COUNTRY;
	}

	public String getCURRENT_PORT() {
		return this.CURRENT_PORT;
	}

	public void setCURRENT_PORT(String CURRENT_PORT) {
		this.CURRENT_PORT = CURRENT_PORT;
	}

	public String getLAST_PORT_COUNTRY() {
		return this.LAST_PORT_COUNTRY;
	}

	public void setLAST_PORT_COUNTRY(String LAST_PORT_COUNTRY) {
		this.LAST_PORT_COUNTRY = LAST_PORT_COUNTRY;
	}

	public String getLAST_PORT() {
		return this.LAST_PORT;
	}

	public void setLAST_PORT(String LAST_PORT) {
		this.LAST_PORT = LAST_PORT;
	}

	public String getNEXT_PORT_COUNTRY() {
		return this.NEXT_PORT_COUNTRY;
	}

	public void setNEXT_PORT_COUNTRY(String NEXT_PORT_COUNTRY) {
		this.NEXT_PORT_COUNTRY = NEXT_PORT_COUNTRY;
	}

	public String getNEXT_PORT_NAME() {
		return this.NEXT_PORT_NAME;
	}

	public void setNEXT_PORT_NAME(String NEXT_PORT_NAME) {
		this.NEXT_PORT_NAME = NEXT_PORT_NAME;
	}

	public String getCurrentAvgSpeed() {
		return this.currentAvgSpeed;
	}

	public void setCurrentAvgSpeed(String currentAvgSpeed) {
		this.currentAvgSpeed = currentAvgSpeed;
	}

	public String getSecondLastAvgSpeed() {
		return this.secondLastAvgSpeed;
	}

	public void setSecondLastAvgSpeed(String secondLastAvgSpeed) {
		this.secondLastAvgSpeed = secondLastAvgSpeed;
	}

	public String getThirdLastAvgSpeed() {
		return this.thirdLastAvgSpeed;
	}

	public void setThirdLastAvgSpeed(String thirdLastAvgSpeed) {
		this.thirdLastAvgSpeed = thirdLastAvgSpeed;
	}

}
