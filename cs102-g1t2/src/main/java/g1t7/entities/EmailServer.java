package g1t7.entities;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_server")
public class EmailServer {
	@Id
	private int id;
	private String server;
	private String senderEmail;
	
	public EmailServer() {
		super();
	}
	public EmailServer(int id, String server, String senderEmail) {
		super();
		this.id = id;
		this.server = server;
		this.senderEmail = senderEmail;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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