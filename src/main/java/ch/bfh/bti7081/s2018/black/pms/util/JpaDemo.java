package ch.bfh.bti7081.s2018.black.pms.util;

import javax.persistence.EntityManager;

import ch.bfh.bti7081.s2018.black.pms.model.UserModel;

public class JpaDemo {
	
	public static void test() {
		EntityManager entityManager = JpaUtility.getEntityManager();	
		entityManager.getTransaction().begin();
		UserModel user = new UserModel("Mahesh", "Varanasi");
		UserModel user1 = new UserModel("test", "test");
		entityManager.persist(user);
		entityManager.persist(user1);
		entityManager.getTransaction().commit();
		//entityManager.close();
		//JpaUtility.close();		
		System.out.println("Entity saved.");
		
		entityManager = JpaUtility.getEntityManager();	
		UserModel user_test = entityManager.find(UserModel.class, new Integer(2));
		System.out.println("Name:"+ user_test.getName()+", Password:"+ user_test.getPassword());
		JpaUtility.close();		
		System.out.println("Done");
	}

	public static void main(String[] args) {
		

	}

}
