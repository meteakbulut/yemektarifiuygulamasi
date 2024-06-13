package finalodevi;

public class YemekListe {
	private int yId;
	private int bId;
	private String yDanisan;
	private String yTarih;
	private int yToplam;
	private String bTipi;

	public YemekListe() {
		super();
	}
	public int getyId() {
		return yId;
	}
	public void setyId(int yId) {
		this.yId = yId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getyDanisan() {
		return yDanisan;
	}
	public void setyDanisan(String yDanisan) {
		this.yDanisan = yDanisan;
	}
	public String getyTarih() {
		return yTarih;
	}
	public void setyTarih(String yTarih) {
		this.yTarih = yTarih;
	}
	public int getyToplam() {
		return yToplam;
	}
	public void setyToplam(int yToplam) {
		this.yToplam = yToplam;
	}
	public String getbTipi() {
		return bTipi;
	}
	public void setbTipi(String bTipi) {
		this.bTipi = bTipi;
	}
	
}
