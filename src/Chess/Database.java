package Chess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
	// **** SETTINGS ***
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	protected String host; 
	protected String database; 
	protected String user; 
	protected String password;
	
	// **** VARIABLES *****
	public static final char LESS_THAN = '<'; 
	public static final char EQUALS = '='; 
	public static final char GREATER_THAN = '>'; 
	
	public enum Koncowka {WIEZOWA};
	
	private Connection connection = null;	
	
	public Database(String host, String database, String user, String password) throws Exception
	{
		this.host = host;
		this.database = database; 
		this.user = user;
		this.password = password; 
		
		try 
		{			
			Class.forName(JDBC_DRIVER);
			
			connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);			
		} 
		catch (Exception e)
		{
			throw e;
		}		
		
	}
	
	public ListaPartii searchGames(int idTurnieju, int idBialego, int idCzarnego, double runda, String eco, String ruchyBialego, String ruchyCzarnego, Koncowka koncowka) throws Exception
	{
		ListaPartii partie = new ListaPartii();
		
		String query = "SELECT * FROM partie WHERE partiaid > 0 ";
		
		if ( idTurnieju >= 0 )
			query += " AND turniejid = " + idTurnieju; 
		
		if ( idBialego >= 0 )
			query += " AND bialyid = " + idBialego; 
		
		if ( idCzarnego >= 0 )
			query += " AND czarnyid = " + idCzarnego; 
		
		if ( koncowka != null )
		{
			switch (koncowka)
			{
			case WIEZOWA: 
				query += " and k_wiezowa = 1 "; 
			}
		}
		
		try 
		{
			Statement statement = connection.createStatement();
			
			statement.executeQuery(query); 
			ResultSet rs = statement.getResultSet();
			
			while ( rs.next() )
			{
				
				partie.add(new Partia(
							rs.getInt("partiaid"),
							rs.getInt("turniejid"),
							rs.getInt("bialyid"), 
							rs.getInt("czarnyid"), 
							rs.getString("RUNDA"),
							rs.getString("DEBIUT"), 
							rs.getString("ruchy"),
							rs.getBoolean("kWiezowa"), 
							rs.getInt("liczbaFigurNaKoniec")
						 ));
			}
			
			statement.close();
			rs.close();
		}
		catch (Exception e)
		{
			throw e;
		}	
		
		return partie; 
	}
	
	public ListaZawodnikow searchPlayers(String imie, String nazwisko, int ranking, char condition) throws Exception
	{
		ListaZawodnikow players = new ListaZawodnikow(); 
		
		String query = "SELECT * FROM zawodnicy WHERE zawodnikid > 0 ";
		
		if ( imie != null && !imie.isEmpty() ) 
			query += " AND imie LIKE '%" + imie + "%' ";
		
		if ( nazwisko != null && !nazwisko.isEmpty() ) 
			query += " AND miejscowosc LIKE '%" + nazwisko + "%' ";  
		
		if ( ranking >= 0 ) 
		{
			query += " AND ranking " + condition + " " + ranking; 
		}
		
		try 
		{
			Statement statement = connection.createStatement();
			
			statement.executeQuery(query); 
			ResultSet rs = statement.getResultSet();
			
			while ( rs.next() )
			{
				players.add(new Zawodnik(rs.getInt("zawodnikid"), rs.getString("imie"), rs.getString("Nazwisko"), rs.getInt("ranking") ));
			}
			
			statement.close();
			rs.close();
		}
		catch (Exception e)
		{
			throw e;
		}	
		
		return players;
	}
	
	public ListaTurniejow searchTournament(String nazwa, String miejscowosc, String data) throws Exception
	{
		ListaTurniejow tournaments = new ListaTurniejow();
		
		String query = "SELECT * FROM turnieje WHERE turniejid > 0 ";
		
		if ( nazwa != null && !nazwa.isEmpty() ) 
			query += " AND nazwa LIKE '%" + nazwa + "%' ";  
		
		if ( miejscowosc != null && !miejscowosc.isEmpty() ) 
			query += " AND miejscowosc LIKE '%" + miejscowosc + "%' ";  
		
		if ( data != null && !data.isEmpty() ) 
			query += " AND data='" + data + "' ";  
		
		try 
		{
			Statement statement = connection.createStatement();
			
			statement.executeQuery(query); 
			ResultSet rs = statement.getResultSet();
			
			while ( rs.next() )
			{
				tournaments.add(new Turniej(rs.getInt("turniejid"), rs.getString("nazwa"), rs.getString("miejscowosc"), rs.getDate("data")));
			}
			
			statement.close();
			rs.close();
		}
		catch (Exception e)
		{
			throw e;
		}		
		
		return tournaments; 
	}
	
	public void close() throws Exception 
	{
		try 
		{
			if (connection != null) 
				connection.close();	
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	public void parse(String filepath) {
		// listy z danymi
		ListaZawodnikow zawodnicy = new ListaZawodnikow();
		ListaTurniejow turnieje = new ListaTurniejow();
		ListaPartii partie = new ListaPartii();

		// odczyt pliku PGN
		String line = new String();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(-1);
		}
		BufferedReader inbr = new BufferedReader(new InputStreamReader(fin));
		try {
			// WCZYTANIE ZAWODNIKOW zmienne
			String imie, nazwisko, imie2, nazwisko2 = new String();
			imie = null;
			nazwisko = null;
			imie2 = null;
			nazwisko2 = null;
			String imieNazwisko, imieNazwisko2 = new String(); // pomocniczy
																// przed
																// rozdzieleniem
			int ranking = 0;
			int ranking2 = 0;
			int idZ = 0;
			String wynik[] = null; // pomocnicza tablica do podzielenia wierszy
			// WCZYTYWANIE TURNIEJOW zmienne
			String nazwa, miejscowosc = new String();
			nazwa = null;
			miejscowosc = null;
			Date data = null;
			int rok, miesiac, dzien = 0;
			String datePom = null;
			int idT = 0;
			while ((line = inbr.readLine()) != null) {
				if (line.length() > 6)
					switch (line.substring(0, 7)) {
					case "[White ": {
						wynik = line.split("\"");
						imieNazwisko = wynik[1].replace("'", " ");
						wynik = imieNazwisko.split(", ");
						if (wynik.length < 2)
							wynik = imieNazwisko.split(" ");
						if (wynik.length != 1)
							imie = wynik[1];
						nazwisko = wynik[0];
					}
						break;
					case "[WhiteE": {
						wynik = line.split("\"");
						ranking = Integer.parseInt(wynik[1]);
						if (zawodnicy.checkZawodnik(new Zawodnik(idZ, imie,
								nazwisko, ranking))) {
							zawodnicy.add(new Zawodnik(idZ, imie, nazwisko,
									ranking));
							idZ++;
						}
					}
						break;
					case "[Black ": {
						wynik = line.split("\"");
						imieNazwisko2 = wynik[1].replace("'", " ");
						wynik = imieNazwisko2.split(", ");
						if (wynik.length < 2)
							wynik = imieNazwisko2.split(" ");
						if (wynik.length != 1)
							imie2 = wynik[1];
						nazwisko2 = wynik[0];
					}
						break;
					case "[BlackE": {
						wynik = line.split("\"");
						ranking2 = Integer.parseInt(wynik[1]);
						if (zawodnicy.checkZawodnik(new Zawodnik(idZ, imie2,
								nazwisko2, ranking2))) {
							zawodnicy.add(new Zawodnik(idZ, imie2, nazwisko2,
									ranking2));
							idZ++;
						}
					}
						break;
					case "[Event ": {
						wynik = line.split("\"");
						nazwa = wynik[1].replace("'", " ");
					}
						break;
					case "[Site \"": {
						wynik = line.split("\"");
						miejscowosc = wynik[1];
					}
						break;
					case "[Date \"": {
						wynik = line.split("\"");
						datePom = wynik[1];
						wynik = datePom.split("[.]");
						if (wynik[0].equals("????"))
							wynik[0] = "2000";
						if (wynik[1].equals("??"))
							wynik[1] = "1";
						if (wynik[2].equals("??"))
							wynik[2] = "1";
						rok = Integer.parseInt(wynik[0]);
						miesiac = Integer.parseInt(wynik[1]);
						dzien = Integer.parseInt(wynik[2]);
						data = new Date(rok - 1900, miesiac, dzien - 30);
						if (turnieje.checkTurniej(new Turniej(idT, nazwa,
								miejscowosc, data))) {
							turnieje.add(new Turniej(idT, nazwa, miejscowosc,
									data));
							idT++;
						}
					}
						break;
					}
			}

			// WCZYTANIE PARTII
			// zmienne
			int idP = 0;
			int idTurnieju = 0;
			int idBialego = 0;
			int idCzarnego = 0;
			String runda = new String();
			String kodDebiutowy = new String();
			String ruchy = new String();

			fin.getChannel().position(0);
			inbr = new BufferedReader(new InputStreamReader(fin));
			while ((line = inbr.readLine()) != null) {
				if (line.length() > 6)
					switch (line.substring(0, 7)) {
					case "[Event ": {
						wynik = line.split("\"");
						nazwa = wynik[1].replace("'", " ");
						idTurnieju = turnieje.returnID(nazwa);
					}
						break;
					case "[White ": {
						wynik = line.split("\"");
						imieNazwisko = wynik[1].replace("'", " ");
						wynik = imieNazwisko.split(", ");
						if (wynik.length < 2)
							wynik = imieNazwisko.split(" ");
						if (wynik.length != 1)
							imie = wynik[1];
						nazwisko = wynik[0];
						idBialego = zawodnicy.returnID(imie, nazwisko);
					}
						break;
					case "[Black ": {
						wynik = line.split("\"");
						imieNazwisko = wynik[1].replace("'", " ");
						wynik = imieNazwisko.split(", ");
						if (wynik.length < 2)
							wynik = imieNazwisko.split(" ");
						if (wynik.length != 1)
							imie = wynik[1];
						nazwisko = wynik[0];
						idCzarnego = zawodnicy.returnID(imie, nazwisko);
					}
						break;
					case "[Round ": {
						wynik = line.split("\"");
						runda = wynik[1];
					}
						break;
					case "[ECO \"A":
					case "[ECO \"B":
					case "[ECO \"C":
					case "[ECO \"D":
					case "[ECO \"E": {
						wynik = line.split("\"");
						kodDebiutowy = wynik[1];
					}
						break;
					}
				if (line.length() > 1 && line.substring(0, 2).equals("1.")) {
					ruchy = ruchy.concat(line);
					while ((line = inbr.readLine()).length() > 1) {
						ruchy = ruchy.concat(line);
					}
					partie.add(new Partia(idP, idTurnieju, idBialego,
							idCzarnego, runda, kodDebiutowy, ruchy, true, 99));
					ruchy = "";
					idP++;
				}
			}
		} catch (IOException e) {
			System.out.println("Input/output error.");
		}

		if (connection == null) {
			System.exit(-1);
		}
		System.out.println("Po³¹czenie z baz¹ danych otwarte!");

		// zapis zawodnikow i turniejow do bazy
		try {
			String query = new String();
			Statement stm = (Statement) connection.createStatement();
			stm.executeUpdate("use projekt;");
			stm.executeUpdate("delete from zawodnicy;");
			stm.executeUpdate("delete from turnieje;");
			for (Zawodnik z : zawodnicy.getList()) {
				query = "INSERT INTO Zawodnicy VALUES(" + z.getId() + ",'"
						+ z.getImie() + "','" + z.getNazwisko() + "',"
						+ z.getRanking() + ");";
				System.out.println(query);
				stm.executeUpdate(query);
			}
			for (Turniej t : turnieje.getList()) {
				query = "INSERT INTO Turnieje VALUES(" + t.getId() + ",'"
						+ t.getNazwa() + "','" + t.getMiejscowosc() + "','"
						+ t.getData() + "')";
				System.out.println(query);
				stm.executeUpdate(query);
			}
			int myIntKWiezowa;
			for (Partia p : partie.getList()) {
				myIntKWiezowa = (p.getkWiezowa()) ? 1 : 0;
				query = "INSERT INTO Partie VALUES(" + p.getId() + ",'"
						+ p.getIdTurnieju() + "','" + p.getIdBialego() + "','"
						+ p.getIdCzarnego() + "','" + p.getRunda() + "','"
						+ p.getKodDebiutowy() + "','" + p.getRuchy() + "','"
						+ myIntKWiezowa + "','" + p.getLiczbaFigurNaKoniec()
						+ "')";
				System.out.println(query);
				stm.executeUpdate(query);
			}
		} catch (SQLException e) {
			System.out.println("Blad przy dodawaniu rekrodow: " + e.toString());
		}

		// zamkniecie polaczenia z baza danych
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("B³¹d przy zamykaniu po³¹czenia: " + e);
			System.exit(-1);
		}
		System.out.println("Po³¹czenie zamkniête!");
	}
}
