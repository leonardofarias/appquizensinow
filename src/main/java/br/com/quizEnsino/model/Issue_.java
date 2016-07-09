package br.com.quizEnsino.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-08T23:12:06.256-0300")
@StaticMetamodel(Issue.class)
public class Issue_ {
	public static volatile SingularAttribute<Issue, Integer> idIssue;
	public static volatile SingularAttribute<Issue, String> area;
	public static volatile SingularAttribute<Issue, String> answer;
	public static volatile SingularAttribute<Issue, String> asking;
	public static volatile ListAttribute<Issue, Option> optionList;
}
