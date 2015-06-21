package Chess;
import java.sql.Date;


public class Turniej {
	private int id;
	private String nazwa;
	private String miejscowosc;
	private Date data;
	
	public Turniej(int id, String nazwa, String miejscowosc, Date data) {
		super();
		this.id = id;
		this.nazwa = nazwa;
		this.miejscowosc = miejscowosc;
		this.data = data;
	}

	public Turniej() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
