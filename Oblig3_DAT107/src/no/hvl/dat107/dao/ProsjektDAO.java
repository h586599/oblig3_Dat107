package no.hvl.dat107.dao;


import no.hvl.dat107.entity.*;


import javax.persistence.*;


public class ProsjektDAO {
	
	private EntityManagerFactory emf;
	
	
	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3Persistence");
	}
	
	
	public void leggTilNyttProsjekt(Prosjekt p) {
	}
}