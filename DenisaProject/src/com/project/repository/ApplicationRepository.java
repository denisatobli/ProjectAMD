package com.project.repository;

import java.util.ArrayList;
import java.sql.Date;
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

	public  List<Application> getUserApplications(Integer userId) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			List<Application> applications = new ArrayList<>();
			TypedQuery<Application> query = em.createQuery("Select a from Application a where a.user.userId=:userId", Application.class);
			query.setParameter("userId", userId);
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

	public boolean addApplication(java.util.Date startDate, java.util.Date finishDate, String description, User user,
			Type type) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Date sql_StartDate = new java.sql.Date(startDate.getTime() );
			Date sql_FinishDate = new java.sql.Date( finishDate.getTime() );
			Application application = new Application();
			application.setStartDate(sql_StartDate);
			application.setFinishDate(sql_FinishDate);
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

	public boolean updateApplication(Application application) {
		try {
			em = JPAUtils.getFactory().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.merge(application);
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
