package no.hvl.dat107.dao;



import no.hvl.dat107.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ProsjektdeltakelseDAO {
	

	
	private EntityManagerFactory emf; 
	
	public ProsjektdeltakelseDAO() {
		
		emf = Persistence.createEntityManagerFactory("oblig3Persistence");
			
			
		}
	
	public void leggTilDeltager (Prosjektdeltakelse PD) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			
			tx.begin();
			em.persist(PD);
			tx.commit();
			
		}catch (Throwable e) {
			
			e.printStackTrace();
			tx.rollback();
			
		} finally {
			em.close();
		}
		
		
	
	}
}
