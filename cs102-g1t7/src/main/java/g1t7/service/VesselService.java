package g1t7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

import g1t7.entities.Vessel;
import g1t7.repositories.VesselRepository;

@Service
public class VesselService {

	@Autowired
	private VesselRepository vesselRepository;
 
	public ResponseEntity<List<Vessel>> getAllVessels() {
		List<Vessel> vessels = new ArrayList<>();
		vesselRepository.findAll().forEach(vessels::add);
		return ResponseEntity.ok(vessels);
	}
	
	public List<Vessel> getAllVesselsNonResponseEntity() {
		List<Vessel> vessels = new ArrayList<>();
		vesselRepository.findAll().forEach(vessels::add);
		return vessels;
	}
	
	public ResponseEntity<List<Vessel>> addVesselsList(List<Vessel> vesselList) {
		vesselRepository.saveAll(vesselList);
		return ResponseEntity.ok(vesselList);
	}

 
	public ResponseEntity<Vessel> findByFullVslMAndInVoyN(String fullVslM, String inVoyN){
		Vessel vessel = vesselRepository.findByFullVslMAndInVoyN(fullVslM, inVoyN);
		if (vessel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(vessel);
	}
	
	public Vessel findByFullVslMAndInVoyNNNonResponseEntity(String fullVslM, String inVoyN) {
		Vessel vessel = vesselRepository.findByFullVslMAndInVoyN(fullVslM, inVoyN);
		return vessel;
	}
	 
	public ResponseEntity<Vessel> addVessel(Vessel vessel) {
		HttpStatus vesselExists = findByFullVslMAndInVoyN(vessel.getFullVslM(), vessel.getInVoyN()).getStatusCode();
		if (vesselExists == HttpStatus.OK) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		vesselRepository.save(vessel);
		return ResponseEntity.status(HttpStatus.CREATED).body(vessel);
	}
 
	public ResponseEntity<Vessel> updateVessel(String fullVslM, String inVoyN, String outVoyN, Vessel vessel) {
		HttpStatus vesselExists = findByFullVslMAndInVoyN(vessel.getFullVslM(), vessel.getInVoyN()).getStatusCode();
		if (vesselExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		vesselRepository.save(vessel);
		return ResponseEntity.ok(vessel);
	}
	
	public ResponseEntity<Vessel> updateVesselPartial(String fullVsLm, String inVoyN, Map<Object, Object> fields) {
		Vessel vessel = vesselRepository.findByFullVslMAndInVoyN(fullVsLm, inVoyN);
		if (vessel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		fields.forEach((k, v) -> {
			try {
			Field field = ReflectionUtils.findRequiredField(Vessel.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field, vessel, String.valueOf(v));
			
			} catch (IllegalArgumentException e) {
				
			}
		});
		Vessel updatedVessel = vesselRepository.save(vessel);
		return ResponseEntity.ok(updatedVessel);
		
	}
	
	public Vessel updateVesselPartial(Vessel givenVessel, Map<Object, Object> fields) {
		Vessel vessel = vesselRepository.findByFullVslMAndInVoyN(givenVessel.getFullVslM(), givenVessel.getInVoyN());
		if (vessel == null) {
			return vesselRepository.save(givenVessel);
		}
		fields.forEach((k, v) -> {
			try {
			Field field = ReflectionUtils.findRequiredField(Vessel.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field, vessel, String.valueOf(v));
			
			} catch (IllegalArgumentException e) {
				
			}
		});
		Vessel updatedVessel = vesselRepository.save(vessel);
		return updatedVessel;
		
	}

	public void updateAverageSpeed(Vessel givenVessel) {
		givenVessel.setThirdLastAvgSpeed(givenVessel.getSecondLastAvgSpeed());
		givenVessel.setSecondLastAvgSpeed(givenVessel.getCurrentAvgSpeed());
		givenVessel.setCurrentAvgSpeed(givenVessel.getAVG_SPEED());
		vesselRepository.save(givenVessel);
		return;
	}
 
	public ResponseEntity<Void> deleteVessel(String fullVslM, String inVoyN, String outVoyN) {
		HttpStatus vesselExists = findByFullVslMAndInVoyN(fullVslM, inVoyN).getStatusCode();
		HttpStatus vesselExists2 = findByFullVslMAndInVoyN(fullVslM, outVoyN).getStatusCode();
		if (vesselExists == HttpStatus.NOT_FOUND) {
			if (vesselExists2 == HttpStatus.NOT_FOUND) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			vesselRepository.deleteByFullVslMAndOutVoyN(fullVslM, outVoyN);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		vesselRepository.deleteByFullVslMAndInVoyN(fullVslM, inVoyN);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	

	
	

}