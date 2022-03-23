package com.rideboard.data.dao;

import java.util.List;

public interface DataAccessManager {
	
    public <T> int insert(T obj);
    public <T> int update(T obj);
    public <T> int delete(T obj);

    public <T,G> T equalOne(Class<T> c, String col, G val);
    public <T,G> List<T> equalMore(Class<T> c, String col, G val);
    public <T> List<T> like(Class<T> c, String col, String val);    
    public <T> void execute(Class<T> c, String sql);

    public <T> T getFirst(Class<T> c, String keyColumn);
    public <T> T getLast(Class<T> c, String keyColumn);
    public <T> List<T> list(Class<T> c);
    
	public org.hibernate.Session openSession();
	
    public void closeSession();
    public void commit();    
	public void rollback();

    public boolean isAutoCommit();    
	public void setAutoCommit(boolean autoCommit);
}
