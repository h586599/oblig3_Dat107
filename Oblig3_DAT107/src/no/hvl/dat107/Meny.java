package no.hvl.dat107;


import no.hvl.dat107.dao.*;
import no.hvl.dat107.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;



public class Meny {
	
	private boolean ferdig;
	private AnsattDAO ansDAO;
	private AvdelingDAO avdDAO;
	private ProsjektDAO proDAO;
	private Scanner leser;
	
	
	public Meny() {
		ferdig = false;
		ansDAO = new AnsattDAO();
		avdDAO = new AvdelingDAO();
		proDAO = new ProsjektDAO();
		leser = new Scanner(System.in);
	}
	
	
	public void start() {
		
		System.out.println("PROGRAMMET STARTER");
		skrivValgAlternativ();
		
		String valgStr;
		int valgVerdi;
		
		
		while(!ferdig) {
			System.out.println("\nSkriv inn et tall mellom 0 og 8. 0 for å avslutte programmet");
			
			try {
				valgStr = leser.nextLine();
				valgVerdi = Integer.parseInt(valgStr);
				valgAlternativ(valgVerdi);
			} catch(NumberFormatException e) {
				System.out.println("Det ble ikke skrevet inn et tall. Prøv på nytt.");
			}
		}	
	}
	
	
	private void valgAlternativ(int valg) {
		String input = "";
		int id;
		Ansatt ans = null;
		Avdeling avd = null;
		Prosjekt p = null;
		
		switch(valg) {
		
		
		case 0:
			ferdig = true;
			System.out.println("\nPROGRAMMET AVSLUTTER");
			leser.close();
			break;
			
			
		case 1:
			System.out.println("\nSøker etter ansatt med ID" + "\nSkriv inn ansatt-ID");
			id = Integer.parseInt(leser.nextLine());
			ans = ansDAO.finnAnsattMedId(id);
			System.out.println("\n" + ans);
			break;
			
			
		case 2:
			System.out.println("\nSøker etter ansatt med brukernavn" + "\nSkriv inn brukernavn");
			input = leser.nextLine();
			ans = ansDAO.finnAnsattMedBrukernavn(input);
			System.out.println("\n" + ans);
			break;
			
			
		case 3:
			List<Ansatt> ansatte = ansDAO.finnAlleAnsatte();
			
			if(ansatte != null) {
				System.out.println("\nAlle ansatte i databasen");
				
				for(Ansatt a : ansatte) {
					System.out.println(a);
				}
			} else {
				System.out.println("\nIngen ansatte");
			}
			break;
			
			
		case 4:
			System.out.println("\nOppdater ansattinfo");
			System.out.println("Skriv inn ID til ansatt som skal oppdateres");
			id = Integer.parseInt(leser.nextLine());
			System.out.println("Oppdatere lønn: Ja eller Nei");
			BigDecimal lonn = null;
			
			if(input.equalsIgnoreCase("Ja")) {
				System.out.println("Skriv inn ny lønn:");
				input = leser.nextLine();
				lonn = BigDecimal.valueOf(Double.parseDouble(input));
			}
			
			System.out.println("Oppdatere stilling: Ja eller Nei");
			input = leser.nextLine();
			String stilling = null;
			
			if(input.equalsIgnoreCase("Ja")) {
				System.out.println("Skriv inn ny stilling:");
				stilling = leser.nextLine();
			}
			ansDAO.oppdaterAnsattStillingLonn(id, stilling, lonn);
			break;
			
			
		case 5:
			ans = lesInnAnsatt();
			System.out.println("\nNy ansatt er lagt inn i databasen");
			ansDAO.leggTilAnsatt(ans);
			break;
			
			
		case 6:
			System.out.println("\nSøker etter avdeling med tilsavrende ID");
			System.out.println("Skriv inn avdelingsID");
			id = Integer.parseInt(leser.nextLine());
			System.out.println(avdDAO.finnAvdelingMedId(id));
			break;
			
		
		case 7:
			System.out.println("\nSkriv ut alle ansatte i en avdeling");
			System.out.println("Skriv inn avdelingsID");
			id = Integer.parseInt(leser.nextLine());
			avd = avdDAO.finnAvdelingMedId(id);
			System.out.println(avd + "\nSjef:\n" + avd.getSjef() + "\nAnsatte: ");
			List<Ansatt> aListe = avd.getAnsatte();
			
			for(Ansatt a : aListe) {
				System.out.println(a);
			}
			break;
		
		
		case 8:
			System.out.println("\nOppdater avdelingen for en ansatt");
			System.out.println("Skriv inn ID til ansatt som skal oppdateres:");
			int ansID = Integer.parseInt(leser.nextLine());
			ans = ansDAO.finnAnsattMedId(ansID);
			
			if(ans == null) {
				System.out.println("Fant ikke ansatt");
				break;
			}
			
			if(ans.erSjef()) {
				System.out.println("Den ansatte er sjef, kan ikke oppdatere avdelingen.");
				break;
			}
			
			System.out.println("Skriv inn ID-en til den nye avdelingen til en ansatte:");
			int avdID = Integer.parseInt(leser.nextLine());
			avd = avdDAO.finnAvdelingMedId(avdID);
			
			ansDAO.oppdaterAnsattAvdeling(ans, avd);
			break;
			
		case 9:
			avd = lesInnAvdeling();
			System.out.println("\nNy ansatt er lagt inn i databasen");
			avdDAO.leggTilAvdeling(avd);
			break;
			
		}
	}
	
	private Ansatt lesInnAnsatt() {
		System.out.println("\nLes inn en ny ansatt");
		
		System.out.println("Skriv inn fornavn:");
		String fornavn = leser.nextLine();
		
		System.out.println("Skriv inn etternavn:");
		String etternavn = leser.nextLine();
		
		System.out.println("Skriv inn dato(yyyy-mm-dd):");
		String tilsettDatoStr = leser.nextLine();
		LocalDate dato = LocalDate.parse(tilsettDatoStr);
		
		System.out.println("Skriv inn stilling:");
		String stilling = leser.nextLine();
		
		System.out.println("Skriv inn månedslønnen:");
		String lonnStr = leser.nextLine();
		BigDecimal lonn = BigDecimal.valueOf(Double.parseDouble(lonnStr));
		
		System.out.println("Skriv inn avdelings-ID:");
		int avdID = Integer.parseInt(leser.nextLine());
		Avdeling avd = avdDAO.finnAvdelingMedId(avdID);
		
		Ansatt a = new Ansatt(fornavn, etternavn, dato, stilling, lonn, avd);
		return a;
	}
	
	private Avdeling lesInnAvdeling() {
		System.out.println("\nLes inn en ny avdeling");
		
		System.out.println("Skriv inn navn på avdelingen");
		String avdNavn = leser.nextLine();
		
		System.out.println("Skriv inn id på avdelingsleder (sjef)");
		Ansatt ansatt = ansDAO.finnAnsattMedBrukernavn(leser.nextLine());
		
		return new Avdeling(avdNavn, ansatt);
	}
	
	
	private void skrivValgAlternativ() {
		System.out.println("Valgalaternativ");
		System.out.println("1 - Søk etter ansatt med ID");
		System.out.println("2 - Søk etter ansatt med brukernavn");
		System.out.println("3 - Skriv ut alle ansatte");
		System.out.println("4 - Oppdatere en ansatt sin stilling og/eller lønn");
		System.out.println("5 - Legg inn en ny ansatt");
		System.out.println("6 - Søker etter avdeling med ID");
		System.out.println("7 - Skriv ut alle ansatte på en avdeling");
		System.out.println("8 - Oppdater hvilke avdeling en jobber i");	
		System.out.println("9 - Legg inn en ny avdeling (I ustand!)");
	}
}
