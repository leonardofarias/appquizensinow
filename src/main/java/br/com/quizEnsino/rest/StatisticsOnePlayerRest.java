package br.com.quizEnsino.rest;


import java.io.IOException;
import java.util.List;

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

import br.com.quizEnsino.model.StatisticsOnePlayer;
import br.com.quizEnsino.rn.StatisticsOnePlayerRN;

@Path("/statistics-one-player")
public class StatisticsOnePlayerRest {

	@EJB
	StatisticsOnePlayerRN statisticsOnePlayerRN;
	
	@PUT
    @PermitAll
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@QueryParam("idIssue") String idIssue,
			@QueryParam("namePlayer") String namePlayer, 
			@QueryParam("checkAnswer") String checkAnswer) {
			ResponseBuilder rb = null;
		
		try {
			
			statisticsOnePlayerRN.save(idIssue, namePlayer, checkAnswer);
				
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok")), MediaType.APPLICATION_JSON);
			
			return rb.build();	
		} catch (Exception e) {
			String msg = "Um erro inesperado aconteceu, sinto muito!";
		    rb = Response.status(500).entity(ErrorsResult.errors(500, msg));
		}
		return rb.build();
	}
	
	@GET
    @PermitAll
    @Path("/get")
    @Produces("application/json")
	public Response get(@QueryParam("namePlayer") String namePlayer) {
			ResponseBuilder rb = null;
		
		try {
			
			List<Integer> listaAcertosErros = statisticsOnePlayerRN.findQtdQuestionsRightError(namePlayer);
			
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok", listaAcertosErros)), MediaType.APPLICATION_JSON);
		} catch (Exception e) {
		    rb = Response.status(500).entity(ErrorsResult.errors(500, e.getMessage()));
		}
		return rb.build();
	}
	
	private StatisticsOnePlayer validate(String json) throws JsonParseException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, StatisticsOnePlayer.class);
	}

}
