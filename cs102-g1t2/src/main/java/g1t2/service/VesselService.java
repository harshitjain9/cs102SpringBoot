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
	
	public ResponseEntity<List<Vessel>> addVesselsList(List<Vessel> vesselList) {
		vesselRepository.saveAll(vesselList);
		return ResponseEntity.ok(vesselList);
	}

 
	public ResponseEntity<Vessel> findByAbbrVslMAndInVoyN(String abbrVslM, String inVoyN){
		Vessel vessel = vesselRepository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
		if (vessel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(vessel);
	}
	
	// for SenseChangeInTime.java
	public String getFirstBthgDt(ResponseEntity<Vessel> vessel) {
		return vesselRepository.getFirstBthgDt(vessel);
	}
	
	public String getBthgDt(ResponseEntity<Vessel> vessel) {
		return vesselRepository.getBthgDt(vessel);
	}
	
	public String getDisplayColor(ResponseEntity<Vessel> vessel) {
		return vesselRepository.getDisplayColor(vessel);
	}
	
	public int getCount(ResponseEntity<Vessel> vessel) {
		return vesselRepository.getCount(vessel);
	}
	
	public String getUnbthgDt(ResponseEntity<Vessel> vessel) {
		return vesselRepository.getUnbthgDt(vessel);
	}
	
	// save count+1 ? 
	// error thrown here: no property called increment count
	public void incrementCount(ResponseEntity<Vessel> vessel) {
//        int current_count = vesselRepository.getCount(vessel);
//        current_count += 1;
        vesselRepository.incrementCount(vessel);
    }
	
	
	//end
 
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