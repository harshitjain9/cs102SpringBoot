package g1t7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t7.entities.WebService;

@Repository
public interface WebServiceRepository extends JpaRepository <WebService, Integer>{
	WebService findById(int id);
    WebService deleteById(int id);
}