package com.rideboard.data.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonDAO implements ICommonDAO
{
	private static Logger logger = LoggerFactory.getLogger(CommonDAO.class);
	Session session = null;
	
	public void commit(Object importSession){
        session = (Session) importSession; 
		org.hibernate.Transaction transaction = getTransaction(session);
		transaction.commit();
	}
	public void rollback(Object importSession){
        session = (Session) importSession; 
		org.hibernate.Transaction transaction = getTransaction(session);
		transaction.rollback();
	}
    public boolean isAutoCommit() {
		return autoCommit;
	}
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
	public void delete(Object obj){
		delete(obj, getSession());
	}
	public void delete(Object obj, Object importSession)
    {
        if(obj == null) {
        	logger.error("\nEither object is null: object="+obj);
            return;
        } else
        {
            logger.debug("\nOpen Session by: "+sessionFactory);
            session = (Session) importSession; 
            session.delete(obj);
            org.hibernate.Transaction transaction = session.beginTransaction();
            if(autoCommit) transaction.commit();
        }
    }
    public boolean hasValue(String key, Class<?> c){
    	 return (this.searchUniqueByValue(key, this.getIdentifier(c), c)!=null);
    }
    public Object searchByKey(int i, Class<?> c)
    {
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            Object obj1 = sess.get(c, Integer.valueOf(i));
            sess.close();
            return obj1;
        }
    }
    public List<?> searchByValue(String value, String col, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            List<?> obj1 = sess.createCriteria(c).add(Restrictions.like(col, value)).list();
            sess.close();
            return obj1;
        }
    }
    public Object searchUniqueByValue(String value, String col, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            Object obj1 = sess.createCriteria(c).add(Restrictions.like(col, value)).uniqueResult();
            sess.close();
            return obj1;
        }
    }
    public List<?> searchByCondition(String cond, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            logger.debug("[CommonDAO::searchByCondition] SELECT FROM " + getEntityName(c) + 
            		" " + cond);
            List<?> obj1 = sess.createCriteria(c).add(Restrictions.sqlRestriction(cond)).list();
            sess.close();
            return obj1;
        }
    }
    public List<?> searchByRange(String col, double s, double e, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            double sl = s>e?e:s;
            double el = s>e?s:e;
            List<?> obj1 = sess.createCriteria(c).add(Restrictions.between(col, Double.valueOf(sl), Double.valueOf(el))).list();
            sess.close();
            return obj1;
        }
    }
    public List<?> searchByRange(String col, int s, int e, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            int sl = s>e?e:s;
            int el = s>e?s:e;
            List<?> obj1 = sess.createCriteria(c).add(Restrictions.between(col, Integer.valueOf(sl), Integer.valueOf(el))).list();
            sess.close();
            return obj1;
        }
    }
    public List<?> searchByRange(String col, long s, long e, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            long sl = s>e?e:s;
            long el = s>e?s:e;
            List<?> obj1 = sess.createCriteria(c).add(Restrictions.between(col, Long.valueOf(sl), Long.valueOf(el))).list();
            sess.close();
            return obj1;
        }
    }
    public List<?> searchByRange(String col, float s, float e, Class<?> c){
        if(c == null || sessionFactory == null)
        {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
            return null;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            Session sess = getSession();
            float sl = s>e?e:s;
            float el = s>e?s:e;
            List<?> obj1 = sess.createCriteria(c).add(Restrictions.between(col, Float.valueOf(sl), Float.valueOf(el))).list();
            sess.close();
            return obj1;
        }
    }
    public void insert(Object obj){
    	insert(obj, getSession());
    }
    public void insert(Object obj, Object importSession)
    {
        if(obj == null || sessionFactory == null)
        {
        	logger.error("\nEither object or sessionFactory is null: object="+obj+", f="+sessionFactory);
            return;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            session = (Session) importSession; 
            session.save(obj);
            org.hibernate.Transaction transaction = session.beginTransaction();
            if(autoCommit) transaction.commit();
        }
    }
    public void update(Object obj){
    	update(obj, getSession());
    }
    public void update(Object obj, Object importSession)
    {
        if(obj == null || sessionFactory == null) {
        	logger.error("\nEither object or sessionFactory is null: object="+obj+", f="+sessionFactory);
        	return;
        } else {
            logger.debug("\nOpen Session by: "+sessionFactory);
            session = (Session) importSession; 
            org.hibernate.Transaction transaction = session.beginTransaction();
            session.update(obj);
            if(autoCommit) transaction.commit();
        }
    }
    
    public Object getLast(Class<?> c)
    {
        if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        String key = getIdentifier(c);
        if(key==null||key.length()<1) {
        	logger.debug("\nError get Identifier for getLast()");
        	return null;
        }
        return getLast(c, key);
    }
    
    public <T> T getLast(Class<T> c, String keyColumn){
        if(keyColumn == null || sessionFactory == null) {
        	logger.error("\nEither keyColumn or sessionFactory is null: keyColumn="+keyColumn+", f="+sessionFactory);
        	return null;
        }
        session = getSession();        
        javax.persistence.criteria.CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(c);
        criteria.orderBy(builder.desc(criteria.from(c).get(keyColumn)));
        Query<T> query = session.createQuery(criteria);
        query.setMaxResults(1);
        return query.getSingleResult(); //crit.uniqueResult();
    }

    public List<?> getAll(Class<?> c)
    {
        if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        logger.debug("\nOpen Session by: "+sessionFactory);
        session = getSession();
//        Criteria crit = session.createCriteria(c);
//        List<?> list = crit.list();
        Query<?> query = session.createQuery("from " + c.getSimpleName(), c);
        List<?> list = query.getResultList();
        if(list == null || list.size() <= 0)
            return null;
        else
            return list;
    }

    public List<?> getTop(Class<?> c, int i)
    {
        if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        logger.debug("\nOpen Session by: "+sessionFactory);
        session = getSession();
//        Criteria crit = session.createCriteria(c);
//        crit.setMaxResults(i);
//        List<?> list = crit.list();
        Query<?> query = session.createQuery("from " + c.getSimpleName(), c);
        List<?> list = query.setMaxResults(i).getResultList();
        if(list == null || list.size() <= 0) {
        	logger.error("\nlist is null: list="+list);
        	return null;
        }
        return list;
    }
    
    public java.util.List<?> getMaxValue(Class<?> c, String column){
    	if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        logger.debug("\nOpen Session by: "+sessionFactory);
        session = getSession();
        String sql = "SELECT CAST(MAX(" + column + "),string) AS "+ column +" FROM " + getEntityName(c);
        logger.debug("[CommonDAO::getMaxValue] getMax sql - " + sql);
    	Query<?> query = session.createQuery(sql);
    	return query==null?null:query.list();
    }
    
    public java.util.List<?> execute(String sql){
    	if(sql == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: f="+sessionFactory);
        	return null;
        }
    	session = getSession();
    	Query<?> query = session.createQuery(sql);
    	return query==null?null:query.list();
    }

    private String getIdentifier(Class<?> c)
    {
        if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        logger.debug("\nOpen Session by: "+sessionFactory);
        ClassMetadata classmetadata = sessionFactory.getClassMetadata(c);
        if(classmetadata == null) {
        	logger.debug("\nclassmetadata is null");
        	return null;
        }
        return classmetadata.getIdentifierPropertyName();
    }

    private String getEntityName(Class<?> c)
    {
        if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        logger.debug("\nOpen Session by: "+sessionFactory);
        ClassMetadata classmetadata = sessionFactory.getClassMetadata(c);
        if(classmetadata == null) {
        	logger.debug("\nclassmetadata is null");
        	return null;
        }
        return classmetadata.getEntityName();
    }

	private String getColumnNames(Class<?> c)
    {
        if(c == null || sessionFactory == null) {
        	logger.error("\nEither class or sessionFactory is null: c="+c+", f="+sessionFactory);
        	return null;
        }
        logger.debug("\nGet Class<?> Metadata from session by: "+sessionFactory);
        ClassMetadata classmetadata = sessionFactory.getClassMetadata(c);
        if(classmetadata == null) {
        	logger.error("\nclassmetadata is null: classmetadata="+classmetadata);
        	return null;
        }
        String as[] = classmetadata.getPropertyNames();
        if(as == null){
        	logger.error("\nGetPropertyNames is null: classmetadata="+classmetadata);
        	return null;
        }
        String s = Arrays.toString(as);
        if(s == null)
            return null;
        else
            return s.substring(1, s.length() - 1);
    }
    
    private org.hibernate.Transaction getTransaction(){
    	Session se = getSession();
    	if(se == null) {
    		logger.debug("\nFailed to return transaction because Session is null.");
    		return null;
    	}
    	return se.beginTransaction();
    }

    public void close(Object importSession) {
		if(importSession != null) ((Session)importSession).close();
	}
    
	public Session getSession(){
    	if(sessionFactory == null){
    		logger.debug("\nFailed to return Session because SessionFactory is null.");
    		return null;
    	}
        return sessionFactory.openSession();
    }
    
    public org.hibernate.Transaction getTransaction(Session se){
    	if(se == null) {
    		logger.debug("\nFailed to return transaction because Session is null.");
    		return null;
    	}
    	return se.beginTransaction();
    }
    
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionfactory)
    {
        sessionFactory = sessionfactory;
    }

    public CommonDAO(SessionFactory sessionfactory)
    {
        setSessionFactory(sessionfactory);
    }
    
    protected void finalize() throws Throwable{
    	if(session!=null) session.close();
    }
    
	private boolean autoCommit = false;
    private SessionFactory sessionFactory;
}
