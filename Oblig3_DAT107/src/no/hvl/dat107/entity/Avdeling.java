package no.hvl.dat107.entity;


import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;


@Entity
@Table(name = "avdeling", schema = "oblig3")
public class Avdeling {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdelingsID;
	private String avdelingsnavn;
	
	
	@OneToOne
	@JoinColumn(name = "sjefID", referencedColumnName = "ansattID")
	private Ansatt sjef;
	
	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private List<Ansatt> ansatte;
	
	public Avdeling() {
		
	}
	
	public Avdeling(String navn, Ansatt sjef) {
		avdelingsnavn = navn;
		ansatte = new ArrayList<Ansatt>();
		this.sjef = sjef;
	}

	public int getAvdelingsID() {
		return avdelingsID;
	}

	public void setAvdelingsnavn(String avdelingsnavn) {
		this.avdelingsnavn = avdelingsnavn;
	}
	
	public String getAvdelingsnavn() {
		return avdelingsnavn;
	}
	
	public List<Ansatt> getAnsatte() {
		return ansatte;
	}

	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}
	
	public Ansatt getSjef() {
		return sjef;
	}
	
	public void leggTilAnsatt(Ansatt a) {
		ansatte.add(a);
	}
	
	public void fjernAnsatt(Ansatt a) {
		ansatte.remove(a);
	}
	
	
	@Override
	public String toString() {
		return "Avdeling: " + avdelingsnavn + " - ID: " + avdelingsID;	
	}
}
