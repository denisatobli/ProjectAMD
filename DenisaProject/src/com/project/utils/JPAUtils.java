package com.project.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	private static EntityManagerFactory emf;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("dbamd");
		} catch (Exception e) {
			e.printStackTrace();
			emf = null;
		}
	}

	public static EntityManagerFactory getFactory() {
		return emf;
	}
}
