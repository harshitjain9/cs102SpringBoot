package g1t2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import g1t2.entities.Vessel;
import g1t2.repositories.VesselRepository;

@Service
public class VesselService {

	private VesselRepository vesselRepository;
	
	public List<Vessel> getAllVessels() {
		List<Vessel> vessels = new ArrayList<>();
		vesselRepository.findAll().forEach(vessels::add);
		return vessels;
	}
	
	public Vessel getVesselByKey(String abbrVslM, String inVoyN, String outVoyN) {
		if (outVoyN == null) {
			return vesselRepository.findByAbbrVoy(abbrVslM, inVoyN);
		}
		return vesselRepository.findByAbbrVoy(abbrVslM, outVoyN);
	}
	
	public String addVessel(Vessel vessel) {
		if (getVesselByKey(vessel.getAbbrVslM(), vessel.getInVoyN(), vessel.getOutVoyN()) != null) {
			return "Adding vessel unsuccessful. Vessel already exists.";
		}
		vesselRepository.save(vessel);
		return "Vessel added successfully.";
	}
	
	public String updateVessel(Vessel vessel) {
		vesselRepository.save(vessel);
		return "Vessel successfully updated.";
	}
	
	public String deleteVessel(String abbrVslM, String inVoyN, String outVoyN) {
		if (getVesselByKey(abbrVslM, inVoyN, outVoyN) != null) {
			if (outVoyN == null) {
				vesselRepository.deleteByAbbrVoy(abbrVslM, inVoyN);
			} else {
				vesselRepository.deleteByAbbrVoy(abbrVslM, outVoyN);
			}
			return "Vessel successfully deleted.";
		}
		return "Vessel deletion unsuccessful. Vessel doesn't exist.";
	}
}
