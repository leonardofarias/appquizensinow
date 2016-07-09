package br.com.quizEnsino.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-08T21:58:20.997-0300")
@StaticMetamodel(Player.class)
public class Player_ {
	public static volatile SingularAttribute<Player, String> email;
	public static volatile SingularAttribute<Player, String> password;
	public static volatile SingularAttribute<Player, String> namePlayer;
	public static volatile ListAttribute<Player, StatisticsOnePlayer> statisticsOnePlayerList;
	public static volatile ListAttribute<Player, StatisticsMultiPlayer> statisticsMultiPlayerList;
}
