package br.com.quizEnsino.rest;


import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import br.com.quizEnsino.dto.IssueDTO;
import br.com.quizEnsino.rn.IssueRN;

@Path("/issues")
public class IssueRest {	

	@EJB
	IssueRN issueRN;
	
	@GET
    @PermitAll
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
			
		ResponseBuilder rb = null;
		
		try {
			
			IssueDTO issueResult = issueRN.getRandomIssue();
			
			rb = Response.ok(new Gson().toJson(SuccessResult.success(200, "ok", issueResult)), MediaType.APPLICATION_JSON);
			
			return rb.build();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
