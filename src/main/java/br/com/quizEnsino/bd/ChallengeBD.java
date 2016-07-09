/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quizEnsino.bd;

import br.com.quizEnsino.model.Challenge;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * 
 * @author MacLeo
 */
public class ChallengeBD {
    private EntityManager em;
    Challenge challenge = new Challenge();

    public ChallengeBD() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appquizensinowildfly");
        em = emf.createEntityManager();
    }
    
    public void salvar(Challenge challenge) {
        em.getTransaction().begin();
        em.merge(challenge);
        em.getTransaction().commit();
    }

    public void excluir(Challenge challenge) {
        em.getTransaction().begin();
        em.remove(em.find(Challenge.class, challenge.getIdChallenge()));
        em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
	public List<Challenge> listarTudo(){
        Query query = em.createNamedQuery("Challenge.findAll");
        return query.getResultList();
    }
    
    public Challenge get(Integer id) {
    	Query query = em.createNamedQuery("Challenge.findByIdChallenge");
    	query.setParameter("id_Challenge",id);
    	return (Challenge) query.getSingleResult();
    }
    
}
