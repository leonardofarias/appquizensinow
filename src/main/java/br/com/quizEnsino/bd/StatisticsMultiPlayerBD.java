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
import br.com.quizEnsino.model.StatisticsMultiPlayer;

/**
 * 
 * @author MacLeo
 */
@Stateless
public class StatisticsMultiPlayerBD {
    
	@PersistenceContext(name = "quizPersistenceUnit")
    private EntityManager em;
    
    public void save(StatisticsMultiPlayer statisticsMultiPlayer) {
        em.persist(statisticsMultiPlayer);
    }

    public void remove(Integer id) {
        em.remove(em.find(StatisticsMultiPlayer.class, id));
    }
    
    @SuppressWarnings("unchecked")
	public List<StatisticsMultiPlayerBD> getAll(){
        return em.createNamedQuery("StatisticsMultiPlayer.findAll").getResultList();
    }
    
	public Integer findQtdMatchesWinsLosses(Player player, Boolean victory){
        Query query = em.createNamedQuery("StatisticsMultiPlayer.findAnswersCorrect");
        query.setParameter("player", player);
        query.setParameter("victory", victory);
        return query.getResultList().size();
    }

    public StatisticsMultiPlayer find(Integer id) {
    	return em.find(StatisticsMultiPlayer.class, id);
    }
    
}
