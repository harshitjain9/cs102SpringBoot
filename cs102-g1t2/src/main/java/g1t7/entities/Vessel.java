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
	
////	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, ma)
//	@OneToMany(mappedBy = "vessel")
////	@JoinColumns({
////		@JoinColumn(name = "vas_inVoyN", referencedColumnName = "inVoyN"),
////		@JoinColumn(name = "vas_fullVslM", referencedColumnName = "fullVslM")
////	})
//	private List<AverageSpeed> averageSpeeds;
	
//	@OneToMany(mappedBy = "vessel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	
	
	
	

	// count refers to the number of times the timing has been updated
	// used for SenseChangeInTime.java
	private int count;
    private String firstBthgDt;
    private String displayColor;
	
	public Vessel() {
		
	}

	public Vessel(String abbrVslM, String inVoyN, String fullVslM, String fullInVoyN, String outVoyN, String shiftSeqN,
			String bthgDt, String unbthgDt, String berthN, String status, String aVG_SPEED, String dISTANCE_TO_GO,
			String iS_PATCHING_ACTIVATED, String mAX_SPEED, String pATCHING_PREDICTED_BTR, String pREDICTED_BTR,
			String vESSEL_NAME, String vOYAGE_CODE_INBOUND, String vSL_VOY) {
		super();
		this.abbrVslM = abbrVslM;
		this.inVoyN = inVoyN;
		this.fullVslM = fullVslM;
		this.fullInVoyN = fullInVoyN;
		this.outVoyN = outVoyN;
		this.shiftSeqN = shiftSeqN;
		this.bthgDt = bthgDt;
		this.unbthgDt = unbthgDt;
		this.berthN = berthN;
		this.status = status;
		AVG_SPEED = aVG_SPEED;
		DISTANCE_TO_GO = dISTANCE_TO_GO;
		IS_PATCHING_ACTIVATED = iS_PATCHING_ACTIVATED;
		MAX_SPEED = mAX_SPEED;
		PATCHING_PREDICTED_BTR = pATCHING_PREDICTED_BTR;
		PREDICTED_BTR = pREDICTED_BTR;
		VESSEL_NAME = vESSEL_NAME;
		VOYAGE_CODE_INBOUND = vOYAGE_CODE_INBOUND;
		VSL_VOY = vSL_VOY;
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
