package com.benjamin.feedr.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.benjamin.feedr.models.User;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
public class UserDAO implements IUserDAO {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;

	/**
	 * Constructor to create UserDAO object
	 */
	public UserDAO() {
		this.emf = Persistence.createEntityManagerFactory("feedr_v3");
		this.em = this.emf.createEntityManager();
		this.et = this.em.getTransaction();
	}

	/**
	 * Adds the given user to the database
	 * 
	 * @param user : User
	 */
	public void addUser(User user) {
		et.begin();
		em.persist(user);
		et.commit();
	}

	/**
	 * Returns the user with the given Id
	 * 
	 * @param id : int
	 * @return user : User
	 */
	public User getUserById(int id) {
		return em.find(User.class, id);
	}

	/**
	 * Returns the user with the given username
	 * 
	 * @param username : String
	 * @return user : User
	 */
	public User getUserByUsername(String username) {
		String sql = "SELECT u FROM User u WHERE u.username LIKE :username";
		Query query = em.createQuery(sql, User.class);
		return (User) query.setParameter("username", username).getSingleResult();
	}

	/**
	 * Deletes the user with the given Id from the database
	 * 
	 * @param id : int
	 */
	public void deleteUser(int id) {
		et.begin();
		em.remove(em.find(User.class, id));
		et.commit();
	}

}
