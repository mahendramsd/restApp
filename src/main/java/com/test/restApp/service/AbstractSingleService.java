package com.test.restApp.service;



import com.test.restApp.common.CommonDAO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractSingleService<E extends Serializable> {

	public abstract CommonDAO<E> getDAO();
	@Transactional
	public Serializable save(E valueObject) throws Exception{
		try {
			Serializable id=getDAO().save(valueObject);
				return id;	
		} catch (Exception e) {			
			throw new Exception("Add fail, because "+e.getMessage());
		}
	}
	@Transactional
	public String add(E valueObject) throws Exception{
		try {
				getDAO().saveOrUpdate(valueObject);
				return "Update Successful.";	
		} catch (Exception e) {		
			/*throw new Exception(e);*/
			throw new Exception("Add fail, because "+e.getMessage());
		}
	}
	@Transactional
	public String delete(Serializable id) throws Exception{
		try {
			getDAO().deleteById(id);
			return "Delete Successful.";
		}catch (DataIntegrityViolationException e) {
			if(e.getCause()!=null && (e.getCause() instanceof ConstraintViolationException)){
				throw (ConstraintViolationException)e.getCause();
			}else{
				throw new Exception("Delete fail, because "+e.getMessage());
			}			
		} catch (Exception e) {			
			throw new Exception("Delete fail, because "+e.getMessage());
		}
	}
	@Transactional
	public E get(Serializable id) throws Exception{
		try {
			return getDAO().get(id);
		} catch (Exception e) {
		throw new Exception("Get data fail,because"+e.getMessage());
		}
	}
	@Transactional
	public List<E> getAll() {
		try {
			return getDAO().getAll();
		} catch (Exception e) {
		System.out.println(e);
			return null;
		}
	}



}
