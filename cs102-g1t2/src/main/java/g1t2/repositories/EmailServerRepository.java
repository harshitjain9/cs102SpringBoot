package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t2.entities.EmailServer;

@Repository
public interface EmailServerRepository extends JpaRepository <EmailServer, String> {
	void deleteById(String id);
}
