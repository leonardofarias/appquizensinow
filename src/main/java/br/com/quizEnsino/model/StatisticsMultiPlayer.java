/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quizEnsino.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacLeo
 */
@Entity
@Table(name ="statistics_multiplayer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatisticsMultiPlayer.findAll", query = "SELECT c FROM StatisticsMultiPlayer c"),
    @NamedQuery(name = "StatisticsMultiPlayer.findAnswersCorrect", query = "SELECT c FROM StatisticsMultiPlayer c"
    		+ " where c.player =:player and c.victory =:victory")})
public class StatisticsMultiPlayer implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_multiplayer")
    private Integer idMultiPlayer;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_player", nullable = false)
    private Player player;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_challenge", nullable = false)
    private Challenge challenge;
    
    @Basic(optional = false)
    @Column(name = "victory")
    private Boolean victory;
    
    public StatisticsMultiPlayer(){
        
    }
    
    public Integer getIdMultiPlayer() {
		return idMultiPlayer;
	}

	public void setIdMultiPlayer(Integer idMultiPlayer) {
		this.idMultiPlayer = idMultiPlayer;
	}
    
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public Boolean getVictory() {
		return victory;
	}

	public void setVictory(Boolean victory) {
		this.victory = victory;
	}

}
