package no.hvl.dat107.dao;


import no.hvl.dat107.entity.*;


import javax.persistence.*;


public class AvdelingDAO {

	private EntityManagerFactory emf;
	
	
	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3Persistence");
	}
	
	public void leggTilAvdeling(Avdeling avd) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			ts.begin();
			em.persist(avd);			
			ts.commit();
			
		} catch(Throwable e) {
			e.printStackTrace();
			if(ts.isActive()) {
				ts.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public Avdeling finnAvdelingMedId(int id) {
		Avdeling avd = null;
		EntityManager em = emf.createEntityManager();
		
		try {
			avd = em.find(Avdeling.class, id);
		} finally {
			em.close();
		}
		return avd;
	}
	
	
}