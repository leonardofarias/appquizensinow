package br.com.quizEnsino.rest;


import java.io.IOException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
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
import br.com.quizEnsino.rn.StatisticsMultiPlayerRN;

@Path("/statistics-multi-player")
public class StatisticsMultiPlayerRest {

	@EJB
	StatisticsMultiPlayerRN statisticsMultiPlayerRN;
	
	@GET
    @PermitAll
    @Path("/get")
    @Produces("application/json")
	public Response get(@QueryParam("namePlayer") String namePlayer) {
			ResponseBuilder rb = null;
		
		try {
			
			List<Integer> listMatchesWinsLosses = statisticsMultiPlayerRN.findQtdMatchesWinsLosses(namePlayer);
			
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok", listMatchesWinsLosses)), MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			String msg = "Um erro inesperado aconteceu, sinto muito!";
		    rb = Response.status(500).entity(ErrorsResult.errors(500, msg));
		}
		return rb.build();
	}
	
	private StatisticsOnePlayer validate(String json) throws JsonParseException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, StatisticsOnePlayer.class);
	}

}
