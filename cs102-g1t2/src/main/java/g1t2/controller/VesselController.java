package g1t2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import g1t2.entities.Vessel;
import g1t2.service.VesselService;

@RestController
public class VesselController {
	
	@Autowired
	private VesselService vesselService;
	
	@RequestMapping("/vessels")
	public ResponseEntity<List<Vessel>> getAllVessels() {
		return vesselService.getAllVessels();
	}
	
	@RequestMapping("/vessels/{abbrVslM}/{inVoyN}")
	public ResponseEntity<Vessel> getVessel(@PathVariable String abbrVslM, @PathVariable String inVoyN) {
		return vesselService.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/vessels")
	public ResponseEntity<Vessel> addVessel(@RequestBody Vessel vessel) {
		return vesselService.addVessel(vessel);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/vessels/{abbrVslM}/{inVoyN}/{outVoyN}")
	public ResponseEntity<Vessel> updateVessel(@PathVariable String abbrVslM, @PathVariable String inVoyN, @PathVariable String outVoyN, @RequestBody Vessel vessel) {
		return vesselService.updateVessel(abbrVslM, inVoyN, outVoyN, vessel);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/vessels/{abbrVslM}/{inVoyN}/{outVoyN}")
	public ResponseEntity<Void> deleteVessel(@PathVariable String abbrVslM, @PathVariable String inVoyN, @PathVariable String outVoyN) {
		return vesselService.deleteVessel(abbrVslM, inVoyN, outVoyN);
	}
	// For SenseChangeInTime.java
	@RequestMapping("/vessels/{firstBthgDt}")
	public String getFirstBthgDt(@PathVariable ResponseEntity<Vessel> vessel) {
		return vesselService.getFirstBthgDt(vessel);
	}
	
	@RequestMapping("/vessels/{BthgDt}")
	public String getBthgDt(@PathVariable ResponseEntity<Vessel> vessel) {
		return vesselService.getBthgDt(vessel);
	}
	
	@RequestMapping("/vessels/{Count}")
	public int getCount(@PathVariable ResponseEntity<Vessel> vessel) {
		return vesselService.getCount(vessel);
	}
	@RequestMapping("/vessels/{DisplayColor}")
	public String getDisplayColor(@PathVariable ResponseEntity<Vessel> vessel) {
		return vesselService.getDisplayColor(vessel);
	}
	
	@RequestMapping("/vessels/{UnbthgDt}")
	public String getUnbthgDt(@PathVariable ResponseEntity<Vessel> vessel) {
		return vesselService.getUnbthgDt(vessel);
	}
	
	// increment count works as a setter for count that does count +=1
	@RequestMapping(method=RequestMethod.PUT, value="/vessels/{incrementCount}")
	public void incrementCount(@PathVariable ResponseEntity<Vessel> vessel) {
		vesselService.incrementCount(vessel);
	}
}
