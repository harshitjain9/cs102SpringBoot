package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmailServerRepository extends JpaRepository <EmailServer, Integer> {
	String deleteById(Integer id) {

    };
}
