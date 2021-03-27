package g1t2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import g1t2.entities.Vessel;
import g1t2.service.VesselService;

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
	
}
