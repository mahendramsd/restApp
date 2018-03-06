package com.test.restApp.common;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class HibernateTemplateImpl {

	@Autowired
    SessionFactory sessionFactory;

	public HibernateTemplateImpl() {
	}

	public HibernateTemplateImpl(SessionFactory sessionFactory) {
		// super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	/*
	 * public HibernateTemplateImpl(SessionFactory sessionFactory, boolean
	 * allowCreate) { super(sessionFactory, allowCreate); }
	 */

	public void close() {
		getSession().close();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session;
		try {
			session = getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = getSessionFactory().openSession();

		}

		return session;
	}

	public Serializable save(Serializable vo) {
		Serializable id = getSession().save(vo);
		return id;
	}

	public void update(Serializable vo) {
		getSession().update(vo);
	}

	public void delete(Serializable vo) {
		getSession().delete(vo);
	}

	public <E extends Serializable> void deleteAll(List<E> vos) {
		// Session sess = getSession();
		for (E vo : vos) {
			delete(vo);
		}
	}

	public void merge(Serializable vo) {
		Session sess = getSession();
		// Transaction trans = sess.beginTransaction();
		sess.merge(vo);

		// trans.commit();
	}

	public void saveOrUpdate(Serializable vo) {
		Session sess = getSession();
		// Transaction trans = sess.beginTransaction();
		sess.merge(vo);

		// trans.commit();
	}

	public <E extends Serializable> E get(Class cls, Serializable id) {
		Session sess = getSession();
		E vo = (E) sess.get(cls, id);
		return vo;
	}

	public <E extends Serializable> List<E> loadAll(Class cls) {
		Session sess = getSession();
		List<E> list = (List<E>) sess.createCriteria(cls).list();
		// sess.close();
		return list;
	}

	public <E extends Serializable> List<E> find(String query) {
		Session sess = getSession();
		List<E> list = (List<E>) sess.createQuery(query).list();
		return list;
	}

	/*
	 * public <E extends Serializable> List<E> find(String query,boolean close)
	 * { Session sess = getSession(); List<E> list=(List<E>)
	 * sess.createQuery(query).list(); if(close) sess.close(); return list; }
	 */
	public int executeUpdate(String query) {
		Session sess = getSession();
		int i = sess.createQuery(query).executeUpdate();
		// sess.close();
		return i;
	}

	public <E extends Serializable> List<E> find(String query, Object... params) {
		Session sess = getSession();
		Query query2 = sess.createQuery(query);
		for (int i = 0; i < params.length; i++)
			query2.setParameter(i, params[i]);
		List<E> list = query2.list();
		// sess.close();
		return list;
	}

	// @Transactional
	public <T> T execute(HibernateCallback<T> callback) {
		/*
		 * Transaction trans=null; try { //HibernateTemplate
		 * 
		 * Session sess = getSession(); trans=sess.beginTransaction();
		 * 
		 * T t=callback.doInHibernate(sess); sess.flush(); trans.commit();
		 * return t; } catch (Exception e) { try { trans.rollback(); } catch
		 * (Exception e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } } return null;
		 */
		Session sess = getSession();
		try {
			return callback.doInHibernate(sess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createQuery(String query) {
		/*
		 * Transaction trans=null; try{ Session sess = getSession();
		 * trans=sess.beginTransaction();
		 * sess.createSQLQuery(query).executeUpdate(); sess.flush();
		 * trans.commit(); sess.close(); }catch(HibernateException h){ try {
		 * trans.rollback(); } catch (Exception e) { }
		 * 
		 * }
		 */
		Session sess = getSession();
		sess.createSQLQuery(query).executeUpdate();

	}

	public void commit() {

	}

	public Object getQuaryObject(String query) {
		Object ob = null;
		try {
			Session sess = getSession();
			ob = sess.createQuery(query).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ob;
	}

}
