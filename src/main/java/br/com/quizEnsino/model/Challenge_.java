package br.com.quizEnsino.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-08T23:12:17.180-0300")
@StaticMetamodel(Challenge.class)
public class Challenge_ {
	public static volatile SingularAttribute<Challenge, Integer> idChallenge;
	public static volatile SingularAttribute<Challenge, String> playerOne;
	public static volatile SingularAttribute<Challenge, String> playerTwo;
	public static volatile SingularAttribute<Challenge, Integer> scorePlayerOne;
	public static volatile SingularAttribute<Challenge, Integer> scorePlayerTwo;
	public static volatile ListAttribute<Challenge, StatisticsMultiPlayer> statisticsMultiPlayerList;
}
