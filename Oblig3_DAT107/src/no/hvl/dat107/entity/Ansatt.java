package no.hvl.dat107.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "ansatt", schema = "oblig3")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattID;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate tilsettDato;
	private String stilling;
	private BigDecimal maanedslonn;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "avdelingsID", referencedColumnName = "avdelingsID")
	private Avdeling avdeling; 
	
	
	@OneToMany(mappedBy = "ansatt", fetch = FetchType.EAGER)
	private List<Prosjektdeltakelse> deltakere;
	
	
	public Ansatt() {
		
	}
		
	public Ansatt(String fornavn, String etternavn, LocalDate tilsettDato, String stilling, BigDecimal maanedslonn, Avdeling avdeling) {
		this.brukernavn = validerBrukernavn(fornavn, etternavn);
		this.fornavn = fornavn; 
		this.etternavn = etternavn; 
		this.tilsettDato = tilsettDato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling = avdeling;
		deltakere = new ArrayList<Prosjektdeltakelse>();
	}
	
	private String validerBrukernavn(String fornavn, String etternavn) {
		if(fornavn == null || etternavn == null || fornavn.length() == 0 || etternavn.length() == 0 || (fornavn.length() == 1 && etternavn.length() == 1)) {
			return "N/A";
			//throw some error
		} else if(fornavn.length() < 2 && etternavn.length() < 1) {
			return "" + fornavn.charAt(0) + etternavn.charAt(0) + etternavn.charAt(1);
			
		} else if(fornavn.length() > 1 && etternavn.length() < 2) {
			return "" + fornavn.charAt(0) + fornavn.charAt(1) + etternavn.charAt(0);
			
		} else {
			return "" + fornavn.charAt(0) + fornavn.charAt(1) + etternavn.charAt(0) + etternavn.charAt(1);
			
		}
	}
	
	public boolean erSjef() {
		return this.ansattID == avdeling.getSjef().getAnsattID();
	}
	
	public void leggTilProsjektDeltakelse(Prosjektdeltakelse pd) {
		deltakere.add(pd);
	}
	
	public void fjernProsjektDeltakelse(Prosjektdeltakelse pd) {
		deltakere.remove(pd);
	}
	
	
	public void setAnsattID(int ansattID) {
		this.ansattID = ansattID;
	}
	
	
	public int getAnsattID() {
		return ansattID;
	}
	

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	
	
	public String getBrukernavn() {
		return brukernavn;
	}

	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	
	
	public String getEtternavn() {
		return etternavn;
	}


	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	
	
	public String getFornavn() {
		return fornavn;
	}


	public void setMaanedslonn(BigDecimal maanedslonn) {
		this.maanedslonn = maanedslonn;
	}
	
	
	public BigDecimal getMaanedslonn() {
		return maanedslonn;
	}

	
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	
	
	public String getStilling() {
		return stilling;
	}
	
	
	public void setTilsettDato(LocalDate tilsettDato) {
		this.tilsettDato = tilsettDato;
	}
	
	
	public LocalDate getTilsettDato() {
		return tilsettDato;
	}
	
	
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	public Avdeling getAvdeling() {
		return avdeling;
	}

	
	@Override
	public String toString() {
		return "{ID: " + ansattID + ", " + brukernavn + ". " + fornavn + " " + etternavn + ". Tilsett: " + tilsettDato.toString() + ". Stilling: " + stilling + ". Månedslønn: " + maanedslonn.toString() + ". Avdeling: " + avdeling.getAvdelingsnavn() + "}";  
	}
}
