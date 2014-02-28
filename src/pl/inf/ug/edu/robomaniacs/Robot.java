package pl.inf.ug.edu.robomaniacs;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Robot {
	// aktualna pozycja robota
	private Pole poz;
	// poprzednie pole na ktorym byl robot w ruchu
	private String poprzedniePole = "";
	// stos poprzenidhc pol do wracania w przypoadku braku nowych ruchwow
	private Stack<String> poprzedniePola;

	public Pole getPoz() {
		return poz;
	}

	public void setPoz(Pole poz) {
		this.poz = poz;
	}

	// jazda na polnoc
	public boolean gora(Pole[][] plansza) {
		if (poz.isN()) {
			this.setPoz(plansza[poz.getX()][poz.getY() - 1]);
			return true;
		} else {
			System.out.println("nie mozesz isc do gory");
			return false;
		}
	}

	// jazda na poludnie
	public boolean dol(Pole[][] plansza) {
		if (poz.isS()) {
			this.setPoz(plansza[poz.getX()][poz.getY() + 1]);
			return true;
		} else {
			System.out.println("nie mozesz isc w dol");
			return false;
		}
	}

	// jazda na zachod
	public boolean lewo(Pole[][] plansza) {
		if (poz.isW()) {
			this.setPoz(plansza[poz.getX() - 1][poz.getY()]);
			return true;
		} else {
			System.out.println("nie mozesz isc w lewo");
			return false;
		}
	}

	// jazda na wschod
	public boolean prawo(Pole[][] plansza) {
		if (poz.isE()) {
			this.setPoz(plansza[poz.getX() + 1][poz.getY()]);
			return true;
		} else {
			System.out.println("nie mozesz isc w prawo");
			return false;
		}
	}

	// DEBUG
	public void pokazPozycje() {
		System.out.println("+-----+-----+-----+-----+-----+");
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if (poz.getX() == i && poz.getY() == j)
					System.out.print("| *   ");
				else
					System.out.print("|     ");
			}
			System.out.println("|");
			System.out.println("+-----+-----+-----+-----+-----+");
		}
	}

	// glowny algorytm wychodzenia z labiryntu
	public void algorytmWersja1(Pole[][] plansza) throws InterruptedException {

		if (!poz.isOdw()) 
			poz.setOdw(true);

		// dozwolone ruchy w tym momecie - wykluczajac juz wybrane wczesniej
		ArrayList<String> wyborSciezki = new ArrayList<String>();

		if (poz.isN() && !poz.getSciezki().contains("n")) {
			wyborSciezki.add("n");
			System.out.println("Moge wybrac sciezke n");
		}

		if (poz.isE() && !poz.getSciezki().contains("e")) {
			wyborSciezki.add("e");
			System.out.println("Moge wybrac sciezke e");
		}

		if (poz.isS() && !poz.getSciezki().contains("s")) {
			wyborSciezki.add("s");
			System.out.println("Moge wybrac sciezke s");
		}

		if (poz.isW() && !poz.getSciezki().contains("w")) {
			wyborSciezki.add("w");
			System.out.println("Moge wybrac sciezke w");
		}

		System.out.println("poprzedniePole wynosi = " + poprzedniePole);

		//jeżeli masz wiecej niz jeden wybor to nie cofaj sie NEVER BACK DOWN
		if (wyborSciezki.size() > 1 && !poprzedniePole.equals(null)) {
			wyborSciezki.remove(poprzedniePole);
			System.out.println("Usunalem poprzedniePole");
		}

		// JEZELI SIE ZGUBILES TO ZROB TO PANIKA MODE
		while (wyborSciezki.size() == 0) {
			System.out.println("Zgubilem sie POMOCY!");
			if (poz.isN() && !poz.getSciezki().contains("n")) {
				wyborSciezki.add("n");
				System.out.println("Moge wybrac sciezke n");
			}

			if (poz.isE() && !poz.getSciezki().contains("e")) {
				wyborSciezki.add("e");
				System.out.println("Moge wybrac sciezke e");
			}

			if (poz.isS() && !poz.getSciezki().contains("s")) {
				wyborSciezki.add("s");
				System.out.println("Moge wybrac sciezke s");
			}

			if (poz.isW() && !poz.getSciezki().contains("w")) {
				wyborSciezki.add("w");
				System.out.println("Moge wybrac sciezke w");
			}
			
			if (wyborSciezki.size() > 1 && !poprzedniePole.equals(null)) {
				wyborSciezki.remove(poprzedniePole);
				System.out.println("Usunalem poprzedniePole");
			}
			
			switch (poprzedniePola.pop()) {
			case "n":
				this.gora(plansza);
				break;
			case "s":
				this.dol(plansza);
				break;
			case "w":
				this.lewo(plansza);
				break;
			case "e":
				this.prawo(plansza);
			}
			drukujStak();
			this.pokazPozycje();		
			Thread.sleep(50);
		}
		// --------------- KONCZ
		

		// losowanie nastepnego ruchu
		Random rand = new Random();
		int wybrana = rand.nextInt(wyborSciezki.size());
		poz.getSciezki().add(wyborSciezki.get(wybrana));
		System.out.println("Wybralem " + wyborSciezki.get(wybrana));
		boolean czyUdanyRuch = false;
		// nastepny ruch
		switch (wyborSciezki.get(wybrana)) {
		case "n":
			czyUdanyRuch = this.gora(plansza);
			this.setPoprzedniePole("s");
			break;
		case "s":
			czyUdanyRuch = this.dol(plansza);
			this.setPoprzedniePole("n");
			break;
		case "w":
			czyUdanyRuch = this.lewo(plansza);
			this.setPoprzedniePole("e");
			break;
		case "e":
			czyUdanyRuch = this.prawo(plansza);
			this.setPoprzedniePole("w");
		}
		if (!poprzedniePole.equals("") && czyUdanyRuch)
			getPoprzedniePola().push(poprzedniePole);
		drukujStak();
	}

	public Robot() {
		super();
		poprzedniePola = new Stack<String>();
	}

	public Stack<String> getPoprzedniePola() {
		return poprzedniePola;
	}

	public void setPoprzedniePola(Stack<String> poprzedniePola) {
		this.poprzedniePola = poprzedniePola;
	}

	public String getPoprzedniePole() {
		return poprzedniePole;
	}

	public void setPoprzedniePole(String poprzedniePole) {
		this.poprzedniePole = poprzedniePole;
	}
	public void drukujStak(){
		System.out.print("Stack: ");
		for(String e: poprzedniePola){
			System.out.print(e + ", ");
		}
		System.out.println();
	}
}