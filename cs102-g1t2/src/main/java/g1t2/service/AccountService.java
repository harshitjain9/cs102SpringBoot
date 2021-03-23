package g1t2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g1t2.entities.Account;
import g1t2.repositories.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll()
		.forEach(accounts::add);
		return accounts;
	}
	
	public Account getAccountByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
	public String addAccount(Account account) {
		if (getAccountByEmail(account.getEmail()) != null) {
			return "Account creation unsuccessful. Account already exists.";
		}
		accountRepository.save(account);
		return "Account successfully created.";
	}
	
	public String updateAccount(String email, Account account) {
		accountRepository.save(account);
		return "Account update successful.";
	}
	
	public void deleteAccount(String email) {
		accountRepository.deleteById(email);
	}

}
