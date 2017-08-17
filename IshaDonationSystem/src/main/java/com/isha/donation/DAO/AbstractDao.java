package com.isha.donation.DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	  @SuppressWarnings("unchecked")
	    public AbstractDao(){
	    	System.out.println("AbstractDao->d.c");
	        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	    }
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	 @SuppressWarnings("unchecked")
	    public T getByKey(PK key) {
	    	System.out.println("AbstractDao->getByKey()");
	        return (T) getSession().get(persistentClass, key);
	    }
	 
	    public void persist(T entity) {
	    	System.out.println("AbstractDao->persist()");
	        getSession().persist(entity);
	    }
	 
	    public void update(T entity) {
	    	System.out.println("AbstractDao->update()");
	        getSession().update(entity);
	    }
	 
	    public void delete(T entity) {
	    	System.out.println("AbstractDao->delete()");
	        getSession().delete(entity);
	    }
	     
	    protected Criteria createEntityCriteria(){
	    	System.out.println("AbstractDao->createEntityCriteria()");
	        return getSession().createCriteria(persistentClass);
	    }
	
	
}