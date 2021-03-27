package g1t2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g1t2.entities.Vessel;
import g1t2.repositories.VesselRepository;

@Service
public class VesselService {

@Autowired
 private VesselRepository vesselRepository;
 
 public List<Vessel> getAllVessels() {
  List<Vessel> vessels = new ArrayList<>();
  vesselRepository.findAll().forEach(vessels::add);
  return null;
 }
 
 public Vessel findByAbbrVslMAndInVoyN(String abbrVslM, String inVoyN){
     return vesselRepository.findByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
 }
 
 public String addVessel(Vessel vessel) {
  if (findByAbbrVslMAndInVoyN(vessel.getAbbrVslM(), vessel.getInVoyN()) != null) {
   return "Adding vessel unsuccessful. Vessel already exists.";
  }
  vesselRepository.save(vessel);
  return "Vessel added successfully.";
 }
 
 public String updateVessel(String abbrVslM, String inVoyN, String outVoyN, Vessel vessel) {
  vesselRepository.save(vessel);
  return "Vessel successfully updated.";
 }
 
 public String deleteVessel(String abbrVslM, String inVoyN, String outVoyN) {
  if (findByAbbrVslMAndInVoyN(abbrVslM, inVoyN) != null) {
   if (outVoyN == null) {
    vesselRepository.deleteByAbbrVslMAndInVoyN(abbrVslM, inVoyN);
   } else {
    vesselRepository.deleteByAbbrVslMAndOutVoyN(abbrVslM, outVoyN);
   }
   return "Vessel successfully deleted.";
  }
  return "Vessel deletion unsuccessful. Vessel doesn't exist.";
 }
}