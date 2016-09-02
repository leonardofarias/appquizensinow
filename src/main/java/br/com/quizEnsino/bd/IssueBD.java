/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quizEnsino.bd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.quizEnsino.model.Issue;

/**
 * 
 * @author MacLeo
 */
@Stateless
public class IssueBD {
	
	@PersistenceContext(name = "quizPersistenceUnit")
    private EntityManager em;
    
    public void save(Issue issue) {
        em.persist(issue);
    }

    public void remove(Integer id) {
        em.remove(em.find(Issue.class, id));
    }
    
	public int getAll(){
        return em.createNamedQuery("Issue.findAll").getResultList().size();
    }

    public Issue find(Integer id) {
    	return em.find(Issue.class, id);
    }
    
}
