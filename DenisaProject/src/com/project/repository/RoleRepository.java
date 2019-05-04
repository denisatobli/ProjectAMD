package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.project.entity.Role;
import com.project.utils.JPAUtils;

public class RoleRepository {
	
	private static EntityManager em;
	private static EntityTransaction tx;

	public static Role getRole() {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
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
