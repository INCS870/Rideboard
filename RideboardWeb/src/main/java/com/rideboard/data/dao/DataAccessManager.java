package com.rideboard.data.dao;

import java.util.List;

public interface DataAccessManager {
	
    public <T> int insert(T obj);
    public <T> int update(T obj);
    public <T> int delete(T obj);

//    public <T> T equal(Class<T> c, int key);
    public <T,G> List<T> equal(Class<T> c, String col, G val);
    public <T> List<T> like(Class<T> c, String col, String val);
    
    public <T> void execute(Class<T> c, String sql);

    public <T> T getFirst(Class<T> c, String keyColumn);
    public <T> T getLast(Class<T> c, String keyColumn);
    public <T> List<T> getAll(Class<T> c);
    
    public void commit();    
	public void rollback();
	public void openSession();
    public void closeSession();

    public boolean isAutoCommit();    
	public void setAutoCommit(boolean autoCommit);
}
