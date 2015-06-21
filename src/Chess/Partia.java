package Chess;

public class Partia {
	private int id;
	private int idTurnieju;
	private int idBialego;
	private int idCzarnego;
	private String runda;
	private String kodDebiutowy;
	private String ruchy;
	private boolean kWiezowa;
	private int liczbaFigurNaKoniec;

	public Partia(int id, int idTurnieju, int idBialego, int idCzarnego,
			String runda, String kodDebiutowy, String ruchy, boolean kWiezowa,
			int liczbaFigurNaKoniec) {
		super();
		this.id = id;
		this.idTurnieju = idTurnieju;
		this.idBialego = idBialego;
		this.idCzarnego = idCzarnego;
		this.runda = runda;
		this.kodDebiutowy = kodDebiutowy;
		this.ruchy = ruchy;
		this.kWiezowa = kWiezowa;
		this.liczbaFigurNaKoniec = liczbaFigurNaKoniec;
	}

	public Partia() {
	}

	public int getId() {
		return id;
	}

	public int getIdTurnieju() {
		return idTurnieju;
	}

	public void setIdTurnieju(int idTurnieju) {
		this.idTurnieju = idTurnieju;
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

	public String getRunda() {
		return runda;
	}

	public void setRunda(String runda) {
		this.runda = runda;
	}

	public String getKodDebiutowy() {
		return kodDebiutowy;
	}

	public void setKodDebiutowy(String kodDebiutowy) {
		this.kodDebiutowy = kodDebiutowy;
	}

	public String getRuchy() {
		return ruchy;
	}

	public void setRuchy(String ruchy) {
		this.ruchy = ruchy;
	}

	public boolean getkWiezowa() {
		return kWiezowa;
	}

	public void setkWiezowa(boolean kWiezowa) {
		this.kWiezowa = kWiezowa;
	}

	public int getLiczbaFigurNaKoniec() {
		return liczbaFigurNaKoniec;
	}

	public void setLiczbaFigurNaKoniec(int liczbaFigurNaKoniec) {
		this.liczbaFigurNaKoniec = liczbaFigurNaKoniec;
	}

	@Override
	public String toString() {
		return "Partia [id=" + id + ", idTurnieju=" + idTurnieju
				+ ", idBialego=" + idBialego + ", idCzarnego=" + idCzarnego
				+ ", runda=" + runda + ", kodDebiutowy=" + kodDebiutowy
				+ ", ruchy=" + ruchy + ", kWiezowa=" + kWiezowa
				+ ", liczbaFigurNaKoniec=" + liczbaFigurNaKoniec + "]";
	}

}