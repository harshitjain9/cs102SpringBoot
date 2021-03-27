package g1t2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import g1t2.entities.EmailServer;
import g1t2.repositories.EmailServerRepository;

@Service
public class EmailServerService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailServerRepository repository;
	
	
	
	public EmailServerService (JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
	
	public ResponseEntity<EmailServer> createNewEmailServer(EmailServer emailServer) {
		EmailServer savedEmailServer = repository.save(emailServer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmailServer);
	}

    public ResponseEntity<Optional<EmailServer>> getEmailServer(String id) {
    	Optional<EmailServer> emailServer = repository.findById(id);
        if (emailServer.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(emailServer);
        
    }

    public ResponseEntity<EmailServer> updateEmailServer(EmailServer emailServer){
        repository.deleteById(emailServer.getId());
		EmailServer savedEmailServer = repository.save(emailServer);
		return ResponseEntity.ok(savedEmailServer);
    }
    public void sendEmail(String toEmail, String subject, String message) {

        var mailMessage = new SimpleMailMessage();
        
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
    
    //done
}