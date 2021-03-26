package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import g1t2.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	public Account findByEmail(String email);
}
