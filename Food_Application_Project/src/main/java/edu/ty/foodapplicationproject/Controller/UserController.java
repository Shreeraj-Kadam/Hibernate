package edu.ty.foodapplicationproject.Controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.ty.foodapplicationproject.model.*;
public class UserController {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	public UserController() {
		emf = Persistence.createEntityManagerFactory("food_application_project");
		em = emf.createEntityManager();
		et = em.getTransaction();
	}
	public User registerUser(User user) {
		et.begin();
		em.persist(user);
		et.commit();
		User currentUser = em.find(User.class, user.getId());
		return currentUser;
	}
	
	public User loginUser(String email , String password) {
		Query query = em.createQuery("Select user from User user"); 
		List<User> users =  query.getResultList();
		// getting user id based on email
		User user = null;
		for(User usr: users) {
			if(usr.getEmail().equalsIgnoreCase(email)) {
				user = usr;
			}
		}
		
		
		User user1 = em.find(User.class, user.getId());
		
		
		
		if(email.equalsIgnoreCase(user.getEmail())   &&  password.equalsIgnoreCase(user.getPassword()) ) {
			return user1;
		}
		else {
			return null; 	
		}
		
		
	}
}
