package Chess;
import java.util.ArrayList;

public class ListaPartii {
	private ArrayList<Partia> list = new ArrayList<Partia>();
	
	public void add(Partia p){
		list.add(p);
	}
	
	public ArrayList<Partia> getList(){
		return list;
	}
}
