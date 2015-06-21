package Chess;
import java.util.ArrayList;

public class ListaTurniejow {
	private ArrayList<Turniej> list = new ArrayList<Turniej>();
	
	public void add(Turniej t){
		list.add(t);
	}
	
	public ArrayList<Turniej> getList(){
		return list;
	}
	
	public boolean checkTurniej(Turniej t){ // jezeli jest juz taki turniej to zwraca FALSE
		boolean pom = true;
		for (Turniej t2 : list){
			if(t.getNazwa().equals(t2.getNazwa())){
				pom = false;
				break;
			}
	}
		return pom;
}
}
