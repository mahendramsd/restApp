package com.test.restApp.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public class CommonDAO<E extends Serializable> {

	@Autowired
	private HibernateTemplateImpl hibernateTemplateImpl = null;

	public HibernateTemplateImpl getHibernateTemplateImpl() {
		return hibernateTemplateImpl;
	}

	public void setHibernateTemplateImpl(HibernateTemplateImpl hibernateTemplateImpl) {
		this.hibernateTemplateImpl = hibernateTemplateImpl;
	}

	Class<E> voClass;

	public CommonDAO(Class<E> voClass) {
		this.voClass = voClass;
	}

	public List<E> getAll() {
		List<E> list = hibernateTemplateImpl.loadAll(this.voClass);
		return list;
	}

	
	public void add(Serializable vo) {
		hibernateTemplateImpl.save(vo);
		
	}

	// @Transactional
	public void update(Serializable vo) {
		try {
			hibernateTemplateImpl.update(vo);
			// hibernateTemplateImpl.flush();
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	// @Transactional
	public Serializable save(Serializable vo) {
		return hibernateTemplateImpl.save(vo);
		// hibernateTemplateImpl.commit();
		// hibernateTemplateImpl.flush();
	}

	// @Transactional
	public void saveOrUpdate(Serializable vo) {
		hibernateTemplateImpl.saveOrUpdate(vo);
		// hibernateTemplateImpl.commit();
		// hibernateTemplateImpl.flush();
	}

	public void delete(Serializable vo) {
		hibernateTemplateImpl.delete(vo);
		// hibernateTemplateImpl.flush();
	}

	// @Transactional
	public void deleteAll(List<E> vos) {
		hibernateTemplateImpl.deleteAll(vos);
		// hibernateTemplateImpl.flush();
	}

	public E get(Serializable id) {
		return hibernateTemplateImpl.get(this.voClass, id);

	}
	


	// @Transactional
	public void deleteById(Serializable id) {
		Serializable vo = this.get(id);
		hibernateTemplateImpl.delete(vo);
		// hibernateTemplateImpl.flush();
	}

	// //@Transactional
	public int executeUpdate(String hql) {
		return hibernateTemplateImpl.executeUpdate(hql);
		// hibernateTemplateImpl.flush();
	}

	/*
	 * public void evict(Serializable entity){
	 * hibernateTemplateImpl.evict(entity); } public void clear(){
	 * hibernateTemplateImpl.clear(); }
	 */

	public void createQuery(String query) {
		hibernateTemplateImpl.createQuery(query);
	}
	
	public Object createQueryObject(String query) {
		return hibernateTemplateImpl.getQuaryObject(query);
	}

	public void close() {
		hibernateTemplateImpl.close();
	}

}
