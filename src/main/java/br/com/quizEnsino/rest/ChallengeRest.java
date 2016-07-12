package br.com.quizEnsino.rest;

import java.io.IOException;

import javax.annotation.security.PermitAll;
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

import br.com.quizEnsino.bd.ChallengeBD;
import br.com.quizEnsino.bd.PlayerBD;
import br.com.quizEnsino.bd.StatisticsMultiPlayerBD;
import br.com.quizEnsino.model.Challenge;
import br.com.quizEnsino.model.Player;
import br.com.quizEnsino.model.StatisticsMultiPlayer;

@Path("/challenge")
public class ChallengeRest {

	@PUT
    @PermitAll
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@QueryParam("pontuacao") String pontuacao,
			@QueryParam("pontuacaoAdv") String pontuacaoAdv, 
			@QueryParam("name") String name,
			@QueryParam("nameAdv") String nameAdv) {
			ResponseBuilder rb = null;
		
		try {
			
			Integer pont = Integer.parseInt(pontuacao);
			Integer pontAdv = Integer.parseInt(pontuacaoAdv);
			
			PlayerBD playerBD = new PlayerBD();
			ChallengeBD challengeBD = new ChallengeBD();
			StatisticsMultiPlayerBD statisticsMultiPlayerBD = new StatisticsMultiPlayerBD();
			
			Player player1 = playerBD.getByNamePlayer(name);
			Player player2 = playerBD.getByNamePlayer(nameAdv);
			StatisticsMultiPlayer sPlayer1 = new StatisticsMultiPlayer();
			StatisticsMultiPlayer sPlayer2 = new StatisticsMultiPlayer();
			
			Challenge challenge = new Challenge();
			
			challenge.setPlayerOne(player1.getNamePlayer());
			challenge.setPlayerTwo(player2.getNamePlayer());
			challenge.setScorePlayerOne(pont);
			challenge.setScorePlayerTwo(pontAdv);
			challengeBD.salvar(challenge);
			
			sPlayer1.setChallenge(challenge);
			sPlayer1.setPlayer(player1);
			sPlayer2.setChallenge(challenge);
			sPlayer2.setPlayer(player2);

			if(pont > pontAdv){
				sPlayer1.setVictory(true);
				sPlayer2.setVictory(false);
			}else{
				sPlayer1.setVictory(false);
				sPlayer2.setVictory(true);
			}
			
			statisticsMultiPlayerBD.salvar(sPlayer1);
			statisticsMultiPlayerBD.salvar(sPlayer2);
				
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok")), MediaType.APPLICATION_JSON);
			
			return rb.build();	
		} catch (Exception e) {
			String msg = "Um erro inesperado aconteceu, sinto muito!";
		    rb = Response.status(500).entity(ErrorsResult.errors(500, msg));
		}
		return rb.build();
	}
	
	private Challenge validate(String json) throws JsonParseException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, Challenge.class);
	}

}
