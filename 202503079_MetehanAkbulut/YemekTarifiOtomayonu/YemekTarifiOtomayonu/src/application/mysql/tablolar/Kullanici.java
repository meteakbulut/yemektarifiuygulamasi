package application.mysql.tablolar;

public class Kullanici {
	private int Id;
	private String Eposta;
	private String Sifre;
	private String AdiSoyadi;
	private int Admin;

	
	
	public Kullanici() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kullanici(int id, String eposta, String sifre, String adiSoyadi, int admin) {
		super();
		Id = id;
		Eposta = eposta;
		Sifre = sifre;
		AdiSoyadi = adiSoyadi;
		Admin = admin;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getEposta() {
		return Eposta;
	}
	public void setEposta(String eposta) {
		Eposta = eposta;
	}
	public String getSifre() {
		return Sifre;
	}
	public void setSifre(String sifre) {
		Sifre = sifre;
	}
	public String getAdiSoyadi() {
		return AdiSoyadi;
	}
	public void setAdiSoyadi(String adiSoyadi) {
		AdiSoyadi = adiSoyadi;
	}
	public int getAdmin() {
		return Admin;
	}
	public void setAdmin(int admin) {
		Admin = admin;
	}
}
