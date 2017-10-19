package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public abstract class DaoGenerico<T> {

	private EntityManager entityManager;

	protected DaoGenerico() {
		entityManager = getEntityManager();
	}

	protected EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("faculdade");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public T searchForID(Class<T> clazz, int id) {
		return (T) entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> searchAll(Class<T> clazz) {
		Query test = entityManager.createQuery("FROM " + clazz.getName());
		return test.getResultList();
	}

	public void toPersist(T t) {
		try {

			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			// faz com que a transação volte um ponto antes caso ocorra algum
			// erro
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void update(T t) {
		try {

			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			// faz com que a transação volte um ponto antes caso ocorra algum
			// erro
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}

	}

	public void remove(T t) {
		try {

			entityManager.getTransaction().begin();
			entityManager.remove(t);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			// faz com que a transação volte um ponto antes caso ocorra algum
			// erro
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(Class<T> clazz, int id) {
		try {
			T t = searchForID(clazz, id);
			remove(t);
		} catch (Exception ex) {
			// faz com que a transação volte um ponto antes caso ocorra algum
			// erro
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}
