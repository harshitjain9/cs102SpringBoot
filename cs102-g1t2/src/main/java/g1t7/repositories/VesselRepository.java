package g1t7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import g1t7.entities.Vessel;
import g1t7.entities.VesselComposite;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, VesselComposite>{
	List<Vessel> findByBthgDtContaining(String date);
    String deleteByBthgDtContaining(String date);
    Vessel findByFullVslMAndInVoyN(String fullVslM, String inVoyN);
    void deleteByFullVslMAndInVoyN(String abbrVslM, String inVoyN);
    void deleteByFullVslMAndOutVoyN(String abbrVslM, String outVoyN);
    
//    @Query("SELECT c.name, p.productName FROM CUSTOMER c JOIN c.products p")
//    @Query"(SELECT a.avgSpeed FROM VESSEL v JOIN v.averageSpeeds a where v.avgSpeed = :avgSpeed")
//    List<Integer> findAverageSpeedsBy
    
    
    
    // for SenseChangeInTime.java
//    String getFirstBthgDt(ResponseEntity<Vessel> vessel);
//    String getBthgDt(ResponseEntity<Vessel> vessel);
//    int getCount(ResponseEntity<Vessel> vessel);
//    void incrementCount(ResponseEntity<Vessel> vessel);
//	String getDisplayColor(ResponseEntity<Vessel> vessel);
//	String getUnbthgDt(ResponseEntity<Vessel> vessel);

}
