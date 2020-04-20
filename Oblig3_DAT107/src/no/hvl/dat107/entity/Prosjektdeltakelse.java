package no.hvl.dat107.entity;


import javax.persistence.*;



@Entity
@Table(name = "prosjektdeltakelse", schema = "oblig3")
public class Prosjektdeltakelse {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektdeltakelseID;
	private int arbeidstimer;
	private String rolle;
	
	
	
	@ManyToOne
	@JoinColumn(name = "ansattID", referencedColumnName = "ansattID")
	private Ansatt ansatt;
	
	
	@ManyToOne
	@JoinColumn(name = "prosjektID", referencedColumnName = "prosjektID")
	private Prosjekt prosjekt;
	
	
	public Prosjektdeltakelse() {
		
	}
	
	
	public Prosjektdeltakelse(int arbeidstimer, String rolle, Ansatt ansatt, Prosjekt prosjekt) {
		this.arbeidstimer = arbeidstimer;
		this.rolle = rolle;
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
	}
	
	public void leggTilTimer(int arbeidstimer) {
		arbeidstimer = arbeidstimer;
	}
	
	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}
	
	public Ansatt getAnsatt() {
		return ansatt;
	}
	
	public void setArbeidstimer(int arbeidstimer) {
		this.arbeidstimer = arbeidstimer;
	}
	
	public int getArbeidstimer() {
		return arbeidstimer;
	}
	
	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}
	
	public Prosjekt getProsjekt() {
		return prosjekt;
	}
	
	public void setProsjektDeltakelseId(int prosjektdeltakelseId) {
		this.prosjektdeltakelseID = prosjektdeltakelseId;
	}
	
	public int getProsjektDeltakelseId() {
		return prosjektdeltakelseID;
	}
	
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	
	public String getRolle() {
		return rolle;
	}
	
	
	@Override
	public String toString() {
		return "AnsattID: " + ansatt.getAnsattID() + "ProsjektID: " + prosjekt.getProsjektID() + ". Timer: " + arbeidstimer + ". Rolle: " + rolle; 
 	}
}
