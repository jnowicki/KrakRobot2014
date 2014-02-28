package pl.inf.ug.edu.robomaniacs;

import java.util.ArrayList;

public class Pole {

	private int x, y;
	private boolean n, s, w, e, meta, odw;

	// wybrane sciezki w poprzenich ruchach
	private ArrayList<String> sciezki = new ArrayList<String>();

	public void zmienPole(boolean n, boolean e, boolean s, boolean w) {
		this.setN(n);
		this.setS(s);
		this.setW(w);
		this.setE(e);
	}

	public boolean isOdw() {
		return odw;
	}

	public void setOdw(boolean odw) {
		this.odw = odw;
	}

	public ArrayList<String> getSciezki() {
		return sciezki;
	}

	public void setSciezki(ArrayList<String> sciezki) {
		this.sciezki = sciezki;
	}

	public boolean isMeta() {
		return meta;
	}

	public void setMeta(boolean meta) {
		this.meta = meta;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isN() {
		return n;
	}

	public void setN(boolean n) {
		this.n = n;
	}

	public boolean isS() {
		return s;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	public boolean isW() {
		return w;
	}

	public void setW(boolean w) {
		this.w = w;
	}

	public boolean isE() {
		return e;
	}

	public void setE(boolean e) {
		this.e = e;
	}
}