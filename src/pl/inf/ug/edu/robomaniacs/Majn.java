package pl.inf.ug.edu.robomaniacs;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Majn {

	public static void main(String args[]) throws InterruptedException {
		System.out.println("*************************************************************");
		int rozmX = 5;
		int rozmY = 5;
		int udane = 0;

		Pole[][] plansza = new Pole[rozmX][rozmY];

		for (int i = 0; i < rozmX; i++) {
			for (int j = 0; j < rozmY; j++) {
				plansza[i][j] = new Pole();
				plansza[i][j].setX(i);
				plansza[i][j].setY(j);
			}
		}

		// robienie planszy
		plansza[0][0].zmienPole(false, true, true, false);
		plansza[1][0].zmienPole(false, true, false, true);
		plansza[2][0].zmienPole(false, false, true, true);
		plansza[3][0].zmienPole(false, false, true, false);
		plansza[4][0].zmienPole(false, false, true, false);
		plansza[0][1].zmienPole(true, true, true, false);
		plansza[1][1].zmienPole(false, false, false, true);
		plansza[2][1].zmienPole(true, false, true, false);
		plansza[3][1].zmienPole(true, true, true, false);
		plansza[4][1].zmienPole(true, true, false, true);
		plansza[0][2].zmienPole(true, true, false, false);
		plansza[1][2].zmienPole(false, true, true, true);
		plansza[2][2].zmienPole(true, false, false, true);
		plansza[3][2].zmienPole(true, false, true, false);
		plansza[4][2].zmienPole(false, false, true, false);
		plansza[0][3].zmienPole(false, true, false, false);
		plansza[1][3].zmienPole(true, true, false, true);
		plansza[2][3].zmienPole(false, true, true, true);
		plansza[3][3].zmienPole(true, false, true, true);
		plansza[4][3].zmienPole(true, false, true, false);
		plansza[0][4].zmienPole(false, true, false, false);
		plansza[1][4].zmienPole(false, true, false, true);
		plansza[2][4].zmienPole(true, true, false, true);
		plansza[3][4].zmienPole(true, true, false, true);
		plansza[4][4].zmienPole(true, false, false, true);
		plansza[4][1].setMeta(true); // ustaw mete <------------

		Robot robot = new Robot();
		robot.setPoz(plansza[1][1]); // ustaw roba

		robot.pokazPozycje();

		while (true) {
			while (!robot.getPoz().isMeta()) {
				try {
					robot.algorytmWersja1(plansza); // seksi
				} catch (Exception e) {
					// wyczyść pamięć i zacznij od nowa!
					// to jest jak reset, tylko bez restartowania pozycji zeby
					// nie bylo ze czit
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							plansza[i][j].getSciezki().clear();
						}
					}
					robot.getPoprzedniePola().clear();

				}
				robot.pokazPozycje();
				Thread.sleep(50);
			}

			System.out.println("META!!!");
			udane++;
			System.out.println("UDAŁO SIE " + udane + " razy ! ha");
			try {
				File log = new File("log.txt");
				PrintWriter udaneWriter = new PrintWriter(new FileWriter(log,true));
				udaneWriter.println(udane + ", " + robot.iloscKrokow );
				udaneWriter.close();
			} catch (Exception s) {
				System.out.println("das Auto");
			}
			reset(plansza, robot);
		}
	}

	public static void reset(Pole[][] plansza, Robot robot) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				plansza[i][j].getSciezki().clear();
			}
		}
		robot.setPoz(plansza[1][1]);
		robot.getPoprzedniePola().clear();
		robot.iloscKrokow = 0;
	}
}
