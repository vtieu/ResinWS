package rlpResinWS.login;

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rlpResinWS.db.DBLogin;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/login")  
public class Login {  
	@GET  
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleStringResponse() {  
		return "Login successful";  
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResult getWebProcessesInfo(
			@FormParam("loginRequest") String loginRequest)
	{
		ObjectMapper om = new ObjectMapper();
		LoginResult result = new LoginResult();
		LoginRequest request = new LoginRequest();
		try {
			request = om.readValue(loginRequest, LoginRequest.class);
		} catch (IOException e) {
			result.error = e.getLocalizedMessage();
			return result;
		}
		
		if (DBLogin.login(request.getUserName(), request.getPassword()))
		{
			result.sessionId = UUID.randomUUID().toString();
		}
		else
		{
			result.error = "Invalid user name or password";
		}

		return result;
	}
}  
