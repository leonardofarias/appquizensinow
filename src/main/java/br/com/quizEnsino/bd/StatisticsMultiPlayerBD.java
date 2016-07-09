/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quizEnsino.bd;

import br.com.quizEnsino.model.Player;
import br.com.quizEnsino.model.StatisticsMultiPlayer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * 
 * @author MacLeo
 */
public class StatisticsMultiPlayerBD {
    private EntityManager em;
    StatisticsMultiPlayer statisticsMultiPlayer = new StatisticsMultiPlayer();

    public StatisticsMultiPlayerBD() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appquizensinowildfly");
        em = emf.createEntityManager();
    }
    
    public void salvar(StatisticsMultiPlayer statisticsMultiPlayer) {
        em.getTransaction().begin();
        em.merge(statisticsMultiPlayer);
        em.getTransaction().commit();
    }

    public void excluir(StatisticsMultiPlayer statisticsMultiPlayer) {
        em.getTransaction().begin();
        em.remove(em.find(StatisticsMultiPlayerBD.class, statisticsMultiPlayer.getIdMultiPlayer()));
        em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
	public List<StatisticsMultiPlayerBD> listarTudo(){
        Query query = em.createNamedQuery("StatisticsMultiPlayer.findAll");
        return query.getResultList();
    }
    
	public Integer buscarQtdPartidas(Player player, Boolean victory){
        Query query = em.createNamedQuery("StatisticsMultiPlayer.findAnswersCorrect");
        query.setParameter("player", player);
        query.setParameter("victory", victory);
        return query.getResultList().size();
    }

    public StatisticsMultiPlayerBD get(Integer id) {
    	Query query = em.createNamedQuery("StatisticsMultiPlayer.findByIdStatisticsMultiPlayer");
    	query.setParameter("id_StatisticsMultiPlayer",id);
    	return (StatisticsMultiPlayerBD) query.getSingleResult();
    }
    
}
