package g1t7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t7.entities.EmailServer;

@Repository
public interface EmailServerRepository extends JpaRepository <EmailServer, Integer> {
	void deleteById(int id);
}
