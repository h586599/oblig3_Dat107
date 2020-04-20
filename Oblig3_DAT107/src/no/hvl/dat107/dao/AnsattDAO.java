package no.hvl.dat107.dao;


import no.hvl.dat107.entity.*;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;



public class AnsattDAO {
	
	
	private EntityManagerFactory emf;
	
	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3Persistence");
	}
	
	public Ansatt finnAnsattMedId(int id) {
		Ansatt a = null;
		EntityManager em = emf.createEntityManager();
		
		try {
			a = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
		return a;
	}
	
	
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
		Ansatt a = null;
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn LIKE :brukernavn", Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			a = query.getSingleResult();
		} finally {
			em.close();
		}
		return a;
	}
	
	
	public List<Ansatt> finnAlleAnsatte() {
		EntityManager em = emf.createEntityManager();
		
		List<Ansatt> ansatte = null;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
			ansatte = query.getResultList();
		} finally {
			em.close();
		}
		return ansatte;
	}
	
	
	public void oppdaterAnsattStillingLonn(int ansattID, String stilling, BigDecimal lonn) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			ts.begin();
			Ansatt a = em.find(Ansatt.class, ansattID);
			
			if(stilling != null) {
				a.setStilling(stilling);
			}
			if(lonn != null) {
				a.setMaanedslonn(lonn);
			}
			ts.commit();
			
		} catch(Throwable e) {
			
			if(ts.isActive()) {
				ts.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	
	public void leggTilAnsatt(Ansatt a) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			ts.begin();
			em.persist(a);
			
			Avdeling avd = a.getAvdeling();
			avd = em.merge(avd);
			avd.leggTilAnsatt(a);
			
			ts.commit();
			
		} catch(Throwable e) {
			
			if(ts.isActive()) {
				ts.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	
	public void oppdaterAnsattAvdeling(Ansatt ans, Avdeling avd) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			ts.begin();
			
			ans = em.merge(ans);
			avd = em.merge(avd);
			
			Avdeling gammelAvd = ans.getAvdeling(); //fjerner den gamle avdelingen 
			gammelAvd = em.merge(gammelAvd);
			
			gammelAvd.fjernAnsatt(ans);
			avd.leggTilAnsatt(ans);
			ans.setAvdeling(avd);
			
			ts.commit();
			
		} catch(Throwable e) {
			
			if(ts.isActive()) {
				ts.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public void timerPaaProsjekt(int ansattID, int prosjektID, int timer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		String queryStr = "SELECT pd FROM Prosjektdeltakelse pd WHERE pd.ansatt.ansattID = :ansattID AND pd.prosjekt.prosjektID = :prosjektID";
		
		try {
			ts.begin();
			
			TypedQuery<Prosjektdeltakelse> query = em.createQuery(queryStr, Prosjektdeltakelse.class);
			query.setParameter("ansattID", ansattID);
			query.setParameter("prosjektID", prosjektID);
			
			Prosjektdeltakelse pd = query.getSingleResult();
			
			pd.leggTilTimer(timer);
			
			ts.commit();
			
		} catch(Throwable e) {
			
			if(ts.isActive()) {
				ts.rollback();
			}
		} finally {
			em.close();
		}
	}
}
