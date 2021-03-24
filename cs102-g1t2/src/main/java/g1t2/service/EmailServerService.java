package g1t2.service;

@Service
public class EmailServerService<JavaMailSender> {
	@Autowired
	private EmailServerRepository repository;
	private JavaMailSender javaMailSender;
	
	public EmailServerService (JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
	
	public EmailServer createNewEmailServer(EmailServer emailServer) {
		return repository.save(emailServer);
	}

    public EmailServer getEmailServer(Integer id) {
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