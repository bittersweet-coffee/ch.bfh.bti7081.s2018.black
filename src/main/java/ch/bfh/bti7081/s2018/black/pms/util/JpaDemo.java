package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;

import ch.bfh.bti7081.s2018.black.pms.model.AddictionModel;
import ch.bfh.bti7081.s2018.black.pms.model.UserModel;

public class JpaDemo {
	
	/*public static void test() {
		EntityManager entityManager = JpaUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		UserModel user = new UserModel();
		user.setName("toni3");
		user.setPassword("test3");
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		//JpaUtility.close();		
		//System.out.println("Entity saved.");
		
		entityManager = JpaUtility.getEntityManager();	
		UserModel user_test = entityManager.find(UserModel.class, new Integer(3));
		System.out.println("Name:"+ user_test.getName()+", Password:"+ user_test.getPassword());
		entityManager.close();		
		System.out.println("Done");
	}*/
	
	public static void testUser() {	
		EntityManager entityManager = JpaUtility.getEntityManager();	
		UserModel user_test = entityManager.find(UserModel.class, new Integer(4));
		System.out.println("Name:"+ user_test.getName());
		entityManager.close();		
		System.out.println("Done");
	}
	
	public static void testAddiction() {	
		EntityManager entityManager = JpaUtility.getEntityManager();	
		AddictionModel addiction = entityManager.find(AddictionModel.class, new Integer(1));
		System.out.println("Name: "+ addiction.getName() + " " + "Descritpion: " + addiction.getDescription());
		entityManager.close();		
		//System.out.println("Done");
	}

	public static void main(String[] args) {
		

	}

}
