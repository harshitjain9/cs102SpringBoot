package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import g1t2.entities.Vessel;

public interface VesselRepository extends JpaRepository<Vessel, String>{
	public Vessel findByAbbrVoy(String abbrVslM, String VoyN);
	public Vessel deleteByAbbrVoy(String abbrVslM, String VoyN);
}
