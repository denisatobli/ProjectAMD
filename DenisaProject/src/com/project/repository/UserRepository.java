package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.project.entity.Role;
import com.project.entity.User;
import com.project.utils.JPAUtils;

public class UserRepository {
	private static EntityManager em;
	private static EntityTransaction tx;
	private static final Logger LOGGER = LogManager.getLogger(UserRepository.class.getName());

	public User getUserByEmail(String email) {
		try {
			LOGGER.debug("Searching user by email.");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
			query.setParameter("email", email);
			tx.commit();
			LOGGER.debug("User was found.");
			return query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Error finding user : " + e.getMessage());
			return null;
		} finally {
			em.close();
		}
	}

	public User getUserById(int userId) {
		User user = null;
		try {
			LOGGER.debug("Searching user by ID.");
			em = JPAUtils.getFactory().createEntityManager();
			user = em.find(User.class, userId);
			LOGGER.debug("User was found by ID.");
			return user;
		} catch (Exception e) {
			LOGGER.error("Error finding user by ID: " + e.getMessage());
			return null;
		} finally {
			em.close();
		}
	}

	public boolean userExists(String email) {
		try {
			LOGGER.debug("Checking if this email address exists.");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<Long> query = em.createQuery("Select count (u) from User u where u.email = :email", Long.class);
			query.setParameter("email", email);
			long count = (long) query.getSingleResult();
			tx.commit();
			LOGGER.debug("Email address exists : {} .", email);
			return (count != 0);
		} catch (Exception e) {
			LOGGER.error("Error checking if email address exists : " + e.getMessage());
			return false;
		} finally {
			em.close();
		}
	}

	public boolean insertUser(String firstname, String lastname, String email, String password, String phoneNumber) {
		try {
			LOGGER.debug("Admin adding user ");
			User user = new User();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhoneNumber(phoneNumber);
			user.setRole(Role.USER);
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.merge(user);
			tx.commit();
			LOGGER.debug("User added successfully! ");
			return true;

		} catch (Exception e) {
			LOGGER.error("Error adding user : {}", e.getMessage());
			return false;
		} finally {
			em.close();
		}
	}

}
