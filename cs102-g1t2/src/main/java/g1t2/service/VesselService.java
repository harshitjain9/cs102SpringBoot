package g1t2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

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
	
	public List<Vessel> getAllVesselsNonResponseEntity() {
		List<Vessel> vessels = new ArrayList<>();
		vesselRepository.findAll().forEach(vessels::add);
		return vessels;
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
	
	public Vessel findByAbbrVslMAndInVoyNNonResponseEntity(String abbrVslM, String inVoyN) {
		return vesselRepository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
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
	
	public Vessel updateVesselPartial(String abbrVslM, String inVoyN, Map<Object, Object> fields) {
		Vessel vessel = vesselRepository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
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