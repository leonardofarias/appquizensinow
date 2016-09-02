/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quizEnsino.bd;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.quizEnsino.model.Challenge;

/**
 * 
 * @author MacLeo
 */
@Stateless
public class ChallengeBD {
	
	@PersistenceContext(name = "quizPersistenceUnit")
    private EntityManager em;
    
    public void save(Challenge challenge) {
        em.persist(challenge);
    }

    public void remove(Integer id) {
        em.remove(em.find(Challenge.class, id));
    }
    
    @SuppressWarnings("unchecked")
	public List<Challenge> getAll(){
        return em.createNamedQuery("Challenge.findAll").getResultList();
    }
    
    public Challenge find(Integer id) {
    	return em.find(Challenge.class, id);
    }
    
}
