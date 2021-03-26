package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import g1t2.entities.Vessel;

public interface VesselRepository extends JpaRepository<Vessel, String>{

}
