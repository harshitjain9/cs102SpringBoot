package g1t2.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public EmailServer createNewEmailServer(EmailServer emailServer) {
		return repository.save(emailServer);
	}

    public EmailServer getEmailServer(String id) {
        return repository.findById(id).orElse(null);
    }

    public void updateEmailServer(EmailServer emailServer){
        repository.deleteById(emailServer.getId());
        repository.save(emailServer);
    }
    public void sendEmail(String fromEmail, String toEmail, String subject, String message) {

        var mailMessage = new SimpleMailMessage();
        
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
}