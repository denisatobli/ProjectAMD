package com.project.repository;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.project.entity.*;
import com.project.utils.JPAUtils;

public class ApplicationRepository {
	private static EntityManager em;
	private static EntityTransaction tx;
	private static final Logger LOGGER = LogManager.getLogger(ApplicationRepository.class.getName());

	public List<Application> getAllApplications(boolean isApproved) {
		try {
			LOGGER.debug("Getting all applications");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Application> applications = new ArrayList<Application>();
			TypedQuery<Application> query = em.createQuery("Select a from Application a where a.isApproved=:isApproved", Application.class);
			query.setParameter("isApproved", isApproved);
			applications = query.getResultList();
			tx.commit();
			LOGGER.debug("Applications retrieved : " + applications);
			return applications;
		} catch (Exception e) {
			LOGGER.error("Error getting all applications : " + e.getMessage());
			return null;
		} finally {
			em.close();
		}
	}

	public List<Application> getUserApplications(Integer userId) {
		try {
			LOGGER.debug("Getting  user applications");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Application> applications = new ArrayList<Application>();
			TypedQuery<Application> query = em.createQuery("Select a from Application a where a.user.userId=:userId and a.isApproved=:isApproved",
					Application.class);
			query.setParameter("userId", userId);
			query.setParameter("isApproved", true);
			applications = query.getResultList();
			tx.commit();
			LOGGER.debug("User applications retrieved : " + applications);
			return applications;
		} catch (Exception e) {
			LOGGER.error("Error getting user applications : " + e.getMessage());
			return null;
		} finally {
			em.close();
		}
	}

	public List<Type> getAllApplicationTypes() {
		try {
			LOGGER.debug("Getting application types");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Type> types = new ArrayList<Type>();
			TypedQuery<Type> query = em.createQuery("Select t from Type t", Type.class);
			types = query.getResultList();
			tx.commit();
			LOGGER.debug("Application types retrieved : " + types);
			return types;
		} catch (Exception e) {
			LOGGER.error("Error getting application types : " + e.getMessage());
			return null;
		} finally {
			em.close();
		}
	}

	public boolean addApplication(java.util.Date startDate, java.util.Date finishDate, String description, User user,
			Type type) {
		try {
			LOGGER.debug("User {} adding application" + user.getFirstname());
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Date sql_StartDate = new java.sql.Date(startDate.getTime());
			Date sql_FinishDate = new java.sql.Date(finishDate.getTime());
			Application application = new Application();
			application.setStartDate(sql_StartDate);
			application.setFinishDate(sql_FinishDate);
			application.setDescription(description);
			application.setApproved(false);
			application.setUser(user);
			application.setType(type);
			em.merge(application);
			tx.commit();
			LOGGER.debug("Application added successfully !");
			return true;
		} catch (Exception e) {
			LOGGER.error("Error adding application : " + e.getMessage());
			return false;
		} finally {
			em.close();
		}
	}

	public boolean updateApplicationApproval(Integer applicationId, boolean isApproved) {
		try {
			LOGGER.debug("Updating application approval !");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			Application application = em.find(Application.class, applicationId);
			tx.begin();
			application.setApproved(isApproved);
			tx.commit();
			LOGGER.debug("Application approval successfully updated!");
			return true;
		} catch (Exception e) {
			LOGGER.error("Error updating application approval : " + e.getMessage());
		} finally {
			em.close();
		}
		return false;
	}

	public boolean deleteApplication(int applicationId) {
		try {
			LOGGER.debug("Deleting application !");
			em = JPAUtils.getFactory().createEntityManager();
			Application application = em.find(Application.class, applicationId);
			tx = em.getTransaction();
			tx.begin();
			em.remove(application);
			tx.commit();
			LOGGER.debug("Application successfully deleted !");
			return true;
		} catch (Exception e) {
			LOGGER.error("Error deleting application : " + e.getMessage());
		} finally {
			em.close();
		}
		return false;
	}

	public boolean updateApplication(Application application) {
		try {
			LOGGER.debug("Updating application !");
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.merge(application);
			tx.commit();
			LOGGER.debug("Application successfully updated !");
			return true;
		} catch (Exception e) {
			LOGGER.error("Error editing application : " + e.getMessage());
		} finally {
			em.close();
		}
		return false;
	}

}
