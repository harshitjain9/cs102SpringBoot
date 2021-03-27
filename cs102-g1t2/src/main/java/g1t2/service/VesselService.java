package g1t2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import g1t2.entities.Vessel;
import g1t2.repositories.VesselRepository;

@Service
public class VesselService {

	@Autowired
	private VesselRepository vesselRepository;
 
	public ResponseEntity<List<Vessel>> getAllVessels() {
		List<Vessel> vessels = new ArrayList<>();
		vesselRepository.findAll().forEach(vessels::add);
		return ResponseEntity.ok(vessels);
	}

 
	public ResponseEntity<Vessel> findByAbbrVslMAndInVoyN(String abbrVslM, String inVoyN){
		Vessel vessel = vesselRepository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
		if (vessel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(vessel);
	}
 
	public ResponseEntity<Vessel> addVessel(Vessel vessel) {
		HttpStatus vesselExists = findByAbbrVslMAndInVoyN(vessel.getAbbrVslM(), vessel.getInVoyN()).getStatusCode();
		if (vesselExists == HttpStatus.OK) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		vesselRepository.save(vessel);
		return ResponseEntity.status(HttpStatus.CREATED).body(vessel);
	}
 
	public ResponseEntity<Vessel> updateVessel(String abbrVslM, String inVoyN, String outVoyN, Vessel vessel) {
		HttpStatus vesselExists = findByAbbrVslMAndInVoyN(vessel.getAbbrVslM(), vessel.getInVoyN()).getStatusCode();
		if (vesselExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		vesselRepository.save(vessel);
		return ResponseEntity.ok(vessel);
	}
 
	public ResponseEntity<Void> deleteVessel(String abbrVslM, String inVoyN, String outVoyN) {
		HttpStatus vesselExists = findByAbbrVslMAndInVoyN(abbrVslM, inVoyN).getStatusCode();
		HttpStatus vesselExists2 = findByAbbrVslMAndInVoyN(abbrVslM, outVoyN).getStatusCode();
		if (vesselExists == HttpStatus.NOT_FOUND) {
			if (vesselExists2 == HttpStatus.NOT_FOUND) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			vesselRepository.deleteByAbbrVslMAndOutVoyN(abbrVslM, outVoyN);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		vesselRepository.deleteByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}