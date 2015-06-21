package Chess;

import java.util.ArrayList;

public class ListaZawodnikow {
	private ArrayList<Zawodnik> list = new ArrayList<Zawodnik>();

	public void add(Zawodnik z) {
		list.add(z);
	}

	public ArrayList<Zawodnik> getList() {
		return list;
	}

	public boolean checkZawodnik(Zawodnik z) { // jezeli jest juz taki zawodnik
												// to zwraca FALSE
		boolean pom = true;
		for (Zawodnik z2 : list) {
			if (z.getImie().equals(z2.getImie())
					&& z.getNazwisko().equals(z2.getNazwisko())) {
				pom = false;
				break;
			}
		}
		return pom;
	}

	public int returnID(String imie, String nazwisko) {
		int id = 0;
		for (Zawodnik z : list) {
			if (z.getImie().equals(imie) && z.getNazwisko().equals(nazwisko)) {
				id = z.getId();
				break;
			}
		}
		return id;
	}
}
