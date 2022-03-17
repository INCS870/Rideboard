package com.rideboard.data.dao;

import java.util.List;

public interface ICommonDAO
{
    public void insert(Object obj);

    public void update(Object obj);

    public void delete(Object obj);

    public void insert(Object obj, Object importSession);

    public void update(Object obj, Object importSession);

    public void delete(Object obj, Object importSession);
    
    public Object searchByKey(int i, Class<?> c);
    
    public List<?> searchByValue(String value, String col, Class<?> c);
    
    public Object searchUniqueByValue(String value, String col, Class<?> c);
    
    public List<?> searchByCondition(String cond, Class<?> c);
    
    public List<?> searchByRange(String col, double s, double e, Class<?> c);
    
    public List<?> searchByRange(String col, int s, int e, Class<?> c);
    
    public List<?> searchByRange(String col, long s, long e, Class<?> c);
    
    public List<?> searchByRange(String col, float s, float e, Class<?> c);
    
    public Object getLast(Class<?> c);
    
    public <T> T getLast(Class<T> c, String keyColumn);

    public List<?> getAll(Class<?> c);

    public List<?> getTop(Class<?> c, int i);
    
    public java.util.List<?> getMaxValue(Class<?> c, String column);
    
    public java.util.List<?> execute(String sql);

    public void commit(Object importSession);
    
	public void rollback(Object importSession);
	
    public boolean isAutoCommit();
    
	public void setAutoCommit(boolean autoCommit);
    
    public boolean hasValue(String key, Class<?> c);
    
    public org.hibernate.Session getSession();
    
    public void close(Object importSession);
}
