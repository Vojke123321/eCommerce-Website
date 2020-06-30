package com.vojke.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vojke.bookstore.entity.Users;

public class JpaDAO<E> {
	private static EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("BookStore");
	}

	public JpaDAO() {
	}

	public E create(E entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(entity);
		em.flush();
		em.refresh(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public E update(E entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public E find(Class<E> type, Object id) {
		EntityManager em = emf.createEntityManager();
		E entity = em.find(type, id);
		if (entity != null) {
			em.refresh(entity);
		}
		em.close();
		return entity;
	}

	public void delete(Class<E> type, Object id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Object reference = em.getReference(type, id);
		em.remove(reference);
		em.getTransaction().commit();
		em.close();
	}

	public List<E> findWithNamedQuery(String queyName) {
		EntityManager em = emf.createEntityManager();
		List<E> resultList = em.createNamedQuery(queyName).getResultList();
		em.close();
		return resultList;
	}

	public long countWithNamedQuery(String queryName) {
		EntityManager em = emf.createEntityManager();
		long singleResult = (long) em.createNamedQuery(queryName).getSingleResult();
		em.close();
		return singleResult;
	}

	public List<E> findWithNamedQueryAndParamater(String queyName, String paramName, Object paramValue) {
		EntityManager em = emf.createEntityManager();
		List<E> resultList = em.createNamedQuery(queyName).setParameter(paramName, paramValue).getResultList();
		em.close();
		return resultList;
	}

	public List<E> findWithNamedQueryAndParamater(String queyName, Map<String, Object> parameterss) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery(queyName);
		Set<Entry<String, Object>> rawParameters = parameterss.entrySet();
		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		List<E> resultList = query.getResultList();
		em.close();
		return resultList;
	}

	public List<E> findWithNamedQuery(String queyName, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery(queyName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<E> resut = query.getResultList();
		em.close();
		return resut;
	}
	public List<Object[]> findWithNamedQueryObjects(String queyName, int firstResult, int maxResult) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery(queyName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<Object[]> resut = query.getResultList();
		em.close();
		return resut;
	}


	public long countWithNamedQuery(String queryName,String paramName,Object value) {
		EntityManager em = emf.createEntityManager();
		long singleResult = (long) em.createNamedQuery(queryName).setParameter(paramName, value).getSingleResult();
		em.close();
		return singleResult;
	}

	public void close() {
		if (emf != null) {
			emf.close();
		}
	}
}
