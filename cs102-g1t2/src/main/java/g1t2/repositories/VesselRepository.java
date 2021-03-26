package g1t2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t2.entities.Vessel;
import g1t2.entities.VesselComposite;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, VesselComposite>{
	List<Vessel> findByBthgDtContaining(String date);
    String deleteByBthgDtContaining(String date);
    Vessel findByAbbrVslMAndInVoyN(String abbrVslM, String inVoyN);
    void deleteByAbbrVslMAndInVoyN(String abbrVslM, String inVoyN);
    void deleteByAbbrVslMAndOutVoyN(String abbrVslM, String outVoyN);
}
