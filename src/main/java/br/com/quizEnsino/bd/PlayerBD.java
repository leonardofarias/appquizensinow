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

/**
 * 
 * @author MacLeo
 */
@Stateless
public class PlayerBD {
	
	@PersistenceContext(name = "quizPersistenceUnit")
    private EntityManager em;

	public void save(Player player) {
		em.persist(player);
	}

	public void remove(String email) {
		em.remove(em.find(Player.class, email));
	}

	@SuppressWarnings("unchecked")
	public List<Player> getAll() {
		return em.createNamedQuery("Player.findAll").getResultList();
	}

	public Player getByEmail(String email) {
		try {
			Query query = em.createNamedQuery("Player.findByEmail");
			query.setParameter("email", email);
			return (Player) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Player getByName(String namePlayer) {
		try {
			Query query = em.createNamedQuery("Player.findByNamePlayer");
			query.setParameter("namePlayer", namePlayer);
			return (Player) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Player getByUser(String email, String password) {
		try {
			Query query = em.createNamedQuery("Player.findByUser");
			query.setParameter("email", email);
			query.setParameter("password", password);
			return (Player) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
