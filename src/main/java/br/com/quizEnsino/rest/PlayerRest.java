package br.com.quizEnsino.rest;


import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import br.com.quizEnsino.rn.PlayerRN;
import br.com.quizEnsino.dto.PlayerDTO;
import br.com.quizEnsino.model.Player;

@Path("/player")
public class PlayerRest {

	@EJB
	PlayerRN playerRN;
	
	@PUT
    @PermitAll
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String json) {
			ResponseBuilder rb = null;
		
		try {
			
			Player player = validate(json);
			
			PlayerDTO playerResult = playerRN.save(player);
			
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok", playerResult)), MediaType.APPLICATION_JSON);
			
			return rb.build();	
		} catch (Exception e) {
		    rb = Response.status(500).entity(ErrorsResult.errors(500, e.getMessage()));
		}
		return rb.build();
	}
	
	@GET
    @PermitAll
    @Path("/get")
    @Produces("application/json")
	public Response get(@QueryParam("email") String email, 
			@QueryParam("password") String password) {
			ResponseBuilder rb = null;
		
		try {
						
			PlayerDTO playerResult = playerRN.getUser(email, password);
						
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok", playerResult)), MediaType.APPLICATION_JSON);
			return rb.build();	
		} catch (Exception e) {
		    rb = Response.status(500).entity(ErrorsResult.errors(500, e.getMessage()));
		}
		return rb.build();
	}
	
	private Player validate(String json) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, Player.class);
	}

}
