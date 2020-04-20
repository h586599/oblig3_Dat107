package no.hvl.dat107.entity;


import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "prosjekt", schema = "oblig3")
public class Prosjekt {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektID;
	private String prosjektNavn;
	private String prosjektBeskrivelse;
	
	
	@OneToMany(mappedBy = "prosjekt", fetch = FetchType.EAGER)
	private List<Prosjektdeltakelse> deltakere;
	
	
	public Prosjekt() {
		
	}
	
	
	public Prosjekt(String prosjektNavn, String prosjektBeskrivelse) {
		this.prosjektNavn = prosjektNavn;
		this.prosjektBeskrivelse = prosjektBeskrivelse;
		deltakere = new ArrayList<Prosjektdeltakelse>();
	}
	
	
	public void leggTilProsjektDeltakelse(Prosjektdeltakelse pd) {
		deltakere.add(pd);
	}
	
	public void fjernProsjektDeltakelse(Prosjektdeltakelse pd) {
		deltakere.remove(pd);
	}
	
	public int getProsjektID() {
		return prosjektID;
	}
	
	public void setProsjektNavn(String prosjektNavn) {
		this.prosjektNavn = prosjektNavn;
	}
	
	public String getProsjektNavn() {
		return prosjektNavn;
	}
	
	public void setProsjektBeskrivelse(String prosjektBeskrivelse) {
		this.prosjektBeskrivelse = prosjektBeskrivelse;
	}
	
	public String getProsjektBeskrivele() {
		return prosjektBeskrivelse;
	}
	
	
	@Override
	public String toString() {
		String ut = "ID: " + prosjektID + " - " + prosjektNavn + ": " + prosjektBeskrivelse + "\nTilsette:\n";
		int totalTimer = 0;
		
		for(Prosjektdeltakelse pd : deltakere) {
			Ansatt a = pd.getAnsatt();
			ut = a.getFornavn() + " " + a.getEtternavn() + " - Rolle: " + pd.getRolle() + " - Timer: " + pd.getArbeidstimer() + "\n";
			totalTimer = pd.getArbeidstimer();
		}
		
		ut = "Timer totalt: " + Integer.toString(totalTimer);
		return ut;	
	}
}
