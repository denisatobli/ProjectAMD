package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.project.entity.Role;

public class Test {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbamd");
	private static EntityManager em;

	public static void main(String[] args) {
		em = emf.createEntityManager();
		//Role role = new Role();
		//role.setRole("Role");
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		TypedQuery<Role> userQuery = em.createQuery("Select r from Role r", Role.class);

		List<Role> roles = userQuery.getResultList();
		
		for(Role r: roles) {
			System.out.println(r);
		}	
			
		//System.out.println(user.getRole());
		
		//em.persist(role);
		tx.commit();
		em.close();
	}
}
