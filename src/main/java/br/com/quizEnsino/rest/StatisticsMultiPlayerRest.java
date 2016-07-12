package br.com.quizEnsino.rest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
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

import br.com.quizEnsino.bd.PlayerBD;
import br.com.quizEnsino.bd.StatisticsMultiPlayerBD;
import br.com.quizEnsino.model.Player;
import br.com.quizEnsino.model.StatisticsOnePlayer;

@Path("/statistics-multi-player")
public class StatisticsMultiPlayerRest {

	@GET
    @PermitAll
    @Path("/get")
    @Produces("application/json")
	public Response get(@QueryParam("namePlayer") String namePlayer) {
			ResponseBuilder rb = null;
		
		try {
			
			PlayerBD playerBD = new PlayerBD();
			Player player = playerBD.getByNamePlayer(namePlayer);
			
			StatisticsMultiPlayerBD statisticsOnePlayerBD = new StatisticsMultiPlayerBD();
			int wins = statisticsOnePlayerBD.buscarQtdPartidas(player, true);
			int losses = statisticsOnePlayerBD.buscarQtdPartidas(player, false);
			
			List<Integer> listaAcertosErros = new ArrayList<Integer>();
			listaAcertosErros.add(wins);
			listaAcertosErros.add(losses);
			
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok", listaAcertosErros)), MediaType.APPLICATION_JSON);
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
