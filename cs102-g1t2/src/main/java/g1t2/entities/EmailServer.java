package g1t2.entities;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class EmailServer {
	@Id
	private String id;
	private String server;
	private String senderEmail;
	
	public EmailServer() {
		super();
	}
	public EmailServer(String id, String server, String senderEmail) {
		super();
		this.id = id;
		this.server = server;
		this.senderEmail = senderEmail;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	
}