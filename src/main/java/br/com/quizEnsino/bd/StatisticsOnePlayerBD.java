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
import javax.persistence.Query;

import br.com.quizEnsino.model.Player;
import br.com.quizEnsino.model.StatisticsOnePlayer;

/**
 * 
 * @author MacLeo
 */
@Stateless
public class StatisticsOnePlayerBD {
	
	@PersistenceContext(name = "quizPersistenceUnit")
    private EntityManager em;

    public void save(StatisticsOnePlayer statisticsOnePlayer) {
        em.persist(statisticsOnePlayer);
    }

    public void remove(Integer id) {
        em.remove(em.find(StatisticsOnePlayer.class, id));
    }
    
    @SuppressWarnings("unchecked")
	public List<StatisticsOnePlayer> getAll(){
        return em.createNamedQuery("StatisticsOnePlayer.findAll").getResultList();
    }
    
	public Integer findByQtdQuestions(Player player, Boolean correct){
        Query query = em.createNamedQuery("StatisticsOnePlayer.findAnswersCorrect");
        query.setParameter("player", player);
        query.setParameter("correct", correct);
        return query.getResultList().size();
    }

    public StatisticsOnePlayer find(Integer id) {
    	return em.find(StatisticsOnePlayer.class, id);
    }
    
}
