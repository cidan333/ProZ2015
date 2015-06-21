import java.util.ArrayList;

import Chess.Database;
import Chess.MainFrame;
import Chess.Partia;
import Chess.Turniej;
import Chess.Zawodnik;


public class Main {
	
	public static void main(String[] args) 
	{
		MainFrame mainFrame = new MainFrame(); 
		mainFrame.setVisible(true);
		
		try 
		{
			Database database  = new Database("localhost", "projekt", "root", "");
			//database.parse("GigaBaza.pgn");
			
			// test szukania turniejow
			ArrayList<Turniej> turnieje = database.searchTournament("MPJ 2009 C10", "leba", null).getList();
			
			for ( Turniej t : turnieje )
			{
				System.out.println(t.getMiejscowosc());
			}
			
			//test szukania zawodnikow
			ArrayList<Zawodnik> zawodnicy = database.searchPlayers(null, null, 0, Database.EQUALS).getList();
			
			
			for ( Zawodnik p : zawodnicy )
			{
				System.out.println(p.getNazwisko());
			}
			
			
			//TEST SZUKANIA PARTII
			ArrayList<Partia> partie = database.searchGames(1, -1, -1, -1,null, null, null, null).getList();
			
			for ( Partia p : partie )
			{
				System.out.println(p.getRuchyBialego());
			}
			
			// zamykamy polaczenie z baza
			database.close();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
}
