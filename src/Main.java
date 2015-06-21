
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import Chess.Database;
import Chess.MainFrame;
import Chess.Partia;
import Chess.Turniej;
import Chess.Zawodnik;



public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{		
		Runnable r = new Runnable() {


            public void run() {
                MainFrame cb = null ;
                try {
                    cb = new MainFrame();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                JFrame f = new JFrame("ChessChamp");
                
                f.add(cb.getGui());                
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.pack();

                f.setMinimumSize(f.getSize());
                //f.setSize(1500,1000);
                f.setVisible(true);
                
                
                
            }
        };
        SwingUtilities.invokeLater(r);
    
		
		try 
		{
			Database database  = new Database("localhost", "projekt", "root", "");
			//database.parse("../GigaBaza.pgn");
			
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
				System.out.println(p.getRuchy());
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
