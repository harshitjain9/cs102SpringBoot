package g1t2.util;

public class AuthenticationResponse {
	private String jwt;
	private MyUserDetails user;
	
	
	public AuthenticationResponse(String jwt, MyUserDetails user) {
		this.jwt = jwt;
		this.user = user;
	}

	public String getJwt() {
		return jwt;
	}
	
	public MyUserDetails getUser() {
		return user;
	}
	
}
