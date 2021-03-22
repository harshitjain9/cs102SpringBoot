package g1t2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import g1t2.entities.Account;
import g1t2.service.AccountService;


@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/accounts")
	public List<Account> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@RequestMapping("/accounts/{email}")
	public Account getAccount(@PathVariable String email) {
		return accountService.getAccountByEmail(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/accounts")
	public String addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/accounts/{email}")
	public String updateAccount(@RequestBody Account account, @PathVariable String email) {
		return accountService.updateAccount(email, account);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/accounts/{email}")
	public void deleteTopic(@PathVariable String email) {
		accountService.deleteAccount(email);
	}
}
