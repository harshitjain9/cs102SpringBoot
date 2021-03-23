package g1t2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import g1t2.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	public Account findByEmail(String email);
}
