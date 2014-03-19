package com.resinWS;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/hello")  
public class Hello {  
	@GET  
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleStringResponse() {  
		return "Hello, world!";  
	}

	@POST
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public HelloResult getWebProcessesInfo(
			@FormParam("helloRequest") String helloRequest)
	{
		ObjectMapper om = new ObjectMapper();
		HelloResult result = new HelloResult();
		HelloRequest request = new HelloRequest();
		try {
			request = om.readValue(helloRequest, HelloRequest.class);
		} catch (IOException e) {
			request.setName("ERROR");
		}

		result.helloResult = "Hello, " + request.getName();
		return result;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("dbTest")
	public String dbTest() {
		MySQLTest access = new MySQLTest();
		
		String name = "";
		try {
			name = access.readDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "First country name = " + name;
	}
}  

class HelloRequest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class HelloResult {
	String helloResult;
}