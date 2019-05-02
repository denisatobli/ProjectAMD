package com.project.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.project.entity.*;
import com.project.utils.JPAUtils;

public class ApplicationRepository {
	private static EntityManager em;
	private static EntityTransaction tx;

	public List<Application> getAllApplications() {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Application> applications = new ArrayList<>();
			TypedQuery<Application> query = em.createQuery("Select a from Application a", Application.class);
			applications = query.getResultList();
			tx.commit();
			return applications;
		} catch (Exception e) {
			if (tx.isActive() && tx != null) {
				tx.rollback();
			}
			System.out.print("Something went wrong ");
			return null;
		} finally {
			em.close();
		}
	}

	public List<Type> getAllApplicationTypes() {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Type> types = new ArrayList<>();
			TypedQuery<Type> query = em.createQuery("Select t from Type t", Type.class);
			types = query.getResultList();
			tx.commit();
			return types;
		} catch (Exception e) {
			if (tx.isActive() && tx != null) {
				tx.rollback();
			}
			System.out.print("Something went wrong ");
			return null;
		} finally {
			em.close();
		}
	}

	public boolean addApplication(Date startDate, Date finishDate, String description, User user, Type type) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Application application = new Application();
			application.setStartDate(startDate);
			application.setFinishDate(finishDate);
			application.setDescription(description);
			application.setApproved(false);
			application.setUser(user);
			application.setType(type);
			em.merge(application);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive() && tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public boolean updateApplicationApproval(Integer applicationId, boolean isApproved) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			Application application = em.find(Application.class, applicationId);
			tx.begin();
			application.setApproved(isApproved);
			;
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean deleteApplication(int applicationId) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			Application application = em.find(Application.class, applicationId);
			tx = em.getTransaction();
			tx.begin();
			em.remove(application);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive() && tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}

	public boolean updateApplication(Integer applicationId, Date startDate, Date finishDate, String description) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			Application application = em.find(Application.class, applicationId);
			tx.begin();
			application.setStartDate(startDate);
			application.setFinishDate(finishDate);
			application.setDescription(description);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}

}
