package br.com.quizEnsino.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-08T21:58:20.999-0300")
@StaticMetamodel(StatisticsOnePlayer.class)
public class StatisticsOnePlayer_ {
	public static volatile SingularAttribute<StatisticsOnePlayer, Integer> idOnePlayer;
	public static volatile SingularAttribute<StatisticsOnePlayer, Player> player;
	public static volatile SingularAttribute<StatisticsOnePlayer, Issue> issue;
	public static volatile SingularAttribute<StatisticsOnePlayer, Boolean> correct;
}
