package com.rideboard.data.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAccessManagerImpl implements DataAccessManager {
	private static Logger logger = LoggerFactory.getLogger(DataAccessManagerImpl.class);
	Session session = null;

	@Override
	public <T> int insert(T obj) {
		Number id = null;
		org.hibernate.Transaction trans = null;
		if (obj == null || sessionFactory == null) {
			logger.error("\nEither object or sessionFactory is null: object=" + obj + ", f=" + sessionFactory);
		} else {
			try {
				logger.debug("\nOpen Session by: " + sessionFactory);
				openSession();
				trans = session.beginTransaction();
				id = (Number) session.save(obj);
				if (autoCommit)
					trans.commit();
			} catch (Exception e) {
				logger.error("Failed to insert object into " + obj.getClass().getName(), e);
				e.printStackTrace();
				rollback();
			}
			return id == null ? -1 : id.intValue();
		}
		return -1;
	}

	@Override
	public <T> int update(T obj) {
		org.hibernate.Transaction trans = null;
		if (obj == null) {
			logger.error("\nEither object is null: object=" + obj);
			return 0;
		} else {
			try {
				logger.debug("\nOpen Session by: " + sessionFactory);
				openSession();
				trans = session.beginTransaction();
				session.update(obj);
				if (autoCommit)
					trans.commit();
				return 1;
			} catch (Exception e) {
				logger.error("Failed to update from " + obj.getClass().getName(), e);
				e.printStackTrace();
				rollback();
			}
		}
		return 0;
	}

	@Override
	public <T> int delete(T obj) {
		org.hibernate.Transaction trans = null;
		if (obj == null) {
			logger.error("\nEither object is null: object=" + obj);
			return 0;
		} else {
			try {
				logger.debug("\nOpen Session by: " + sessionFactory);
				openSession();
				trans = session.beginTransaction();
				session.delete(obj);
				if (autoCommit)
					trans.commit();
				return 1;
			} catch (Exception e) {
				logger.error("Failed to delete from " + obj.getClass().getName(), e);
				e.printStackTrace();
				rollback();
			}
		}
		return 0;
	}

	@Override
	public <T, G> T equalOne(Class<T> c, String col, G val) {
		T retVal = null;
		if (c == null || sessionFactory == null) {
			logger.error("\nEither class or sessionFactory is null: c=" + c + ", f=" + sessionFactory);
			return null;
		} else {
			logger.debug("\nOpen Session by: " + sessionFactory);
			try {
				openSession();
				javax.persistence.criteria.CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<T> criteria = builder.createQuery(c);
				Root<T> root = criteria.from(c);
				criteria.where(builder.equal(root.get(col).as(val.getClass()), val));
				retVal = session.createQuery(criteria).getSingleResult();
			} catch (javax.persistence.NoResultException nre) {
				logger.error("Failed to search from " + c.getName() + " on " + col + " = " + val, nre);
			} catch (Exception e) {
				logger.error("Failed to search from " + c.getName() + " on " + col + " = " + val, e);
				e.printStackTrace();
			}
		}
		return retVal;
	}

	@Override
	public <T, G> List<T> equalMore(Class<T> c, String col, G val) {
		List<T> list = null;
		if (c == null || sessionFactory == null) {
			logger.error("\nEither class or sessionFactory is null: c=" + c + ", f=" + sessionFactory);
			return null;
		} else {
			logger.debug("\nOpen Session by: " + sessionFactory);
			try {
				openSession();
				javax.persistence.criteria.CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<T> criteria = builder.createQuery(c);
				Root<T> root = criteria.from(c);
				criteria.where(builder.equal(root.get(col).as(val.getClass()), val));
				list = session.createQuery(criteria).getResultList();
			} catch (Exception e) {
				logger.error("Failed to search from " + c.getName() + " on " + col + " = " + val, e);
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public <T> List<T> like(Class<T> c, String col, String val) {
		List<T> list = null;
		if (c == null || sessionFactory == null) {
			logger.error("\nEither class or sessionFactory is null: c=" + c + ", f=" + sessionFactory);
			return null;
		} else {
			logger.debug("\nOpen Session by: " + sessionFactory);
			try {
				openSession();
				javax.persistence.criteria.CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<T> criteria = builder.createQuery(c);
				Root<T> root = criteria.from(c);
				criteria = criteria.where(builder.like(root.get(col).as(String.class), "%" + val + "%"));
				list = session.createQuery(criteria).getResultList();
			} catch (Exception e) {
				logger.error("Failed to search from " + c.getName() + " on " + col + " ~ " + val, e);
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public <T> void execute(Class<T> c, String sql) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getFirst(Class<T> c, String keyColumn) {
		T retVal = null;
		if (keyColumn == null || sessionFactory == null) {
			if (logger != null)
				logger.error("\nEither keyColumn or sessionFactory is null: keyColumn=" + keyColumn + ", f="
						+ sessionFactory);
			return null;
		}
		try {
			openSession();
			javax.persistence.criteria.CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(c);
			criteria.orderBy(builder.asc(criteria.from(c).get(keyColumn)));
			Query<T> query = session.createQuery(criteria);
			query.setMaxResults(1);
			retVal = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Failed to get last from " + c.getName(), e);
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public <T> T getLast(Class<T> c, String keyColumn) {
		T retVal = null;
		if (keyColumn == null || sessionFactory == null) {
			if (logger != null)
				logger.error("\nEither keyColumn or sessionFactory is null: keyColumn=" + keyColumn + ", f="
						+ sessionFactory);
			return null;
		}
		try {
			openSession();
			javax.persistence.criteria.CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(c);
			criteria.orderBy(builder.desc(criteria.from(c).get(keyColumn)));
			Query<T> query = session.createQuery(criteria);
			query.setMaxResults(1);
			retVal = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Failed to get last from " + c.getName(), e);
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public <T> List<T> list(Class<T> c) {
		List<T> list = null;
		if (c == null || sessionFactory == null) {
			logger.error("\nEither class or sessionFactory is null: c=" + c + ", f=" + sessionFactory);
			return null;
		}
		logger.debug("\nOpen Session by: " + sessionFactory);
		try {
			openSession();
			Query<T> query = session.createQuery("from " + c.getSimpleName(), c);
			list = query.getResultList();
		} catch (Exception e) {
			logger.error("Failed to get all from " + c.getName(), e);
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public void commit() {
		if (session != null)
			session.getTransaction().commit();
	}

	@Override
	public void rollback() {
		if (session != null)
			session.getTransaction().rollback();
	}

	@Override
	public void closeSession() {
		if (session != null)
			session.close();
	}

	@Override
	public boolean isAutoCommit() {
		return autoCommit;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	@Override
	public Session openSession() {
		if (sessionFactory == null) {
			logger.warn("\nFailed to return Session because SessionFactory is null.");
		} else {
			if(session == null || !session.isOpen()) {
				logger.info("open session");
				session = sessionFactory.openSession();
			} else {
				logger.info("use current session");
			}
		}
		return session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionfactory) {
		sessionFactory = sessionfactory;
	}

	protected void finalize() throws Throwable {
		closeSession();
	}

	private boolean autoCommit = false;
	private SessionFactory sessionFactory;
}
