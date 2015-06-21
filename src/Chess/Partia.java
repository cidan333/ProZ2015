package Chess;

public class Partia {
	private int id;
	private int idBialego;
	private int idCzarnego;
	private double runda;
	private String kodDebiutowy;
	private String komentator;
	private String ruchyBialego;
	private String ruchyCzarnego;
	
	public Partia(int id, int idBialego, int idCzarnego, double runda,
			String kodDebiutowy, String ruchyBialego,
			String ruchyCzarnego) {
		super();
		this.id = id;
		this.idBialego = idBialego;
		this.idCzarnego = idCzarnego;
		this.runda = runda;
		this.kodDebiutowy = kodDebiutowy;
		this.ruchyBialego = ruchyBialego;
		this.ruchyCzarnego = ruchyCzarnego;
	}

	public Partia() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBialego() {
		return idBialego;
	}

	public void setIdBialego(int idBialego) {
		this.idBialego = idBialego;
	}

	public int getIdCzarnego() {
		return idCzarnego;
	}

	public void setIdCzarnego(int idCzarnego) {
		this.idCzarnego = idCzarnego;
	}

	public double getRunda() {
		return runda;
	}

	public void setRunda(double runda) {
		this.runda = runda;
	}

	public String getKodDebiutowy() {
		return kodDebiutowy;
	}

	public void setKodDebiutowy(String kodDebiutowy) {
		this.kodDebiutowy = kodDebiutowy;
	}

	public String getKomentator() {
		return komentator;
	}

	public void setKomentator(String komentator) {
		this.komentator = komentator;
	}

	public String getRuchyBialego() {
		return ruchyBialego;
	}

	public void setRuchyBialego(String ruchyBialego) {
		this.ruchyBialego = ruchyBialego;
	}

	public String getRuchyCzarnego() {
		return ruchyCzarnego;
	}

	public void setRuchyCzarnego(String ruchyCzarnego) {
		this.ruchyCzarnego = ruchyCzarnego;
	}
	
	
	
	
}
