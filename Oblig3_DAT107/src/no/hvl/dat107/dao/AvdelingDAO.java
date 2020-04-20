package no.hvl.dat107.dao;


import no.hvl.dat107.entity.*;


import javax.persistence.*;


public class AvdelingDAO {

	private EntityManagerFactory emf;
	
	
	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3Persistence");
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