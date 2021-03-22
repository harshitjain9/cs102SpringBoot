package g1t2.repositories;

import org.springframework.data.repository.CrudRepository;

import g1t2.entities.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
	public Account findByEmail(String email);
}
