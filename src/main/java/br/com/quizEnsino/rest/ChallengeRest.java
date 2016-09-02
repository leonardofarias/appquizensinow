package br.com.quizEnsino.rest;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import br.com.quizEnsino.rn.ChallengeRN;

@Path("/challenge")
public class ChallengeRest {

	@EJB
	ChallengeRN challengeRN;
	
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
			
			challengeRN.save(pontuacao, pontuacaoAdv,name,nameAdv);
							
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok")), MediaType.APPLICATION_JSON);
			
			return rb.build();	
		} catch (Exception e) {
			String msg = "Um erro inesperado aconteceu, sinto muito!";
		    rb = Response.status(500).entity(ErrorsResult.errors(500, msg));
		}
		return rb.build();
	}
	
}
