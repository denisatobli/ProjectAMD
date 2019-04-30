package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.project.entity.User;
import com.project.utils.JPAUtils;

public class UserRepository {
	private static EntityManager em;
	private static EntityTransaction tx;

	public static User getUserByEmail(String email) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
			query.setParameter("email", email);
			tx.commit();
			System.out.println("repoooo "+query.getSingleResult());
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx.isActive() && tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void main(String [] args) {
		getUserByEmail("denisa@tobli.com");
	}
	public User getUserById(int userId) {
		User user = null;
		try {
			em = JPAUtils.getFactory().createEntityManager();
			user = em.find(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}
	public boolean userExists(String email) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<Long> query = em.createQuery("Select count (u) from User u where u.email = :email", Long.class);
			query.setParameter("email", email);
			long count = (long) query.getSingleResult();
			tx.commit();
			return (count != 0);
		} catch (Exception e) {
			if (tx.isActive() && tx != null) {
				tx.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}

}
