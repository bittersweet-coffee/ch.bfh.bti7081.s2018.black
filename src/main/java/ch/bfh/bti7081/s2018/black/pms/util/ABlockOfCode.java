package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;

//Source: http://www.copypasteisforword.com/notes/lambda-expressions-in-java
//Author: enrique
//Date: 16.05.2018
public interface ABlockOfCode<T> {
	  T execute(EntityManager em);
	}