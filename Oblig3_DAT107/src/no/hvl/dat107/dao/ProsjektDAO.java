package no.hvl.dat107.dao;

import java.util.List;

import no.hvl.dat107.entity.*;

import javax.persistence.*;



public class ProsjektDAO {

	private EntityManagerFactory emf;

	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3Persistence");
	}

	public Prosjekt finnProsjektMedID(int id) {

		EntityManager em = emf.createEntityManager();

		Prosjekt p1;

		try {

			p1 = em.find(Prosjekt.class, id);

		} finally {

			em.close();

		}

		return p1;

	}

	public List<Prosjekt> hentAlleProsjekter() {

		EntityManager em = emf.createEntityManager();
		List<Prosjekt> prosjekter = null;

		try {

			TypedQuery<Prosjekt> query = em.createNamedQuery("hentAlleProsjekt", Prosjekt.class);
			prosjekter = query.getResultList();

		} finally {
			em.close();

		}
		return prosjekter;

	}

	public void opprettProsjekt(Prosjekt p) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {

			tx.begin();
			em.persist(p);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();

		} finally {
			em.close();

		}

	}
}
