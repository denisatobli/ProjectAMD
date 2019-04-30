package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.project.entity.Role;

public class RoleRepository {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbamdd");
	private static EntityManager em;

	public static Role getRole() {
		try {
		em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		TypedQuery<Role> query = em.createQuery("Select r from Role r where r.id=:id", Role.class);
		query.setParameter("id", 1);
		tx.commit();
		return query.getSingleResult();
		
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			em.close();
		}
		
	}

}
