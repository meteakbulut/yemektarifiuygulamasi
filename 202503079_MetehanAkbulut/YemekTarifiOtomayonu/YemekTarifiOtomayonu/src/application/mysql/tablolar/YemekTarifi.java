package application.mysql.tablolar;

import javafx.scene.image.Image;

public class YemekTarifi {
	private int Id;
	private String Baslik;
	private String Aciklama;
	private String ResimUrl;
	private int KullaniciId;
	private String KullaniciAdiSoyadi;
	private Image Image;
	
	public YemekTarifi() {
		super();
	}
	public YemekTarifi(int id, String baslik, String aciklama, String resimUrl, int kullaniciId) {
		super();
		Id = id;
		Baslik = baslik;
		Aciklama = aciklama;
		ResimUrl = resimUrl;
		KullaniciId = kullaniciId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getBaslik() {
		return Baslik;
	}
	public void setBaslik(String baslik) {
		Baslik = baslik;
	}
	public String getAciklama() {
		return Aciklama;
	}
	public void setAciklama(String aciklama) {
		Aciklama = aciklama;
	}
	public String getResimUrl() {
		return ResimUrl;
	}
	public void setResimUrl(String resimUrl) {
		ResimUrl = resimUrl;
		setImage(new Image(resimUrl));
	}
	public int getKullaniciId() {
		return KullaniciId;
	}
	public void setKullaniciId(int kullaniciId) {
		KullaniciId = kullaniciId;
	}
	public String getKullaniciAdiSoyadi() {
		return KullaniciAdiSoyadi;
	}
	public void setKullaniciAdiSoyadi(String kullaniciAdiSoyadi) {
		KullaniciAdiSoyadi = kullaniciAdiSoyadi;
	}
	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		this.Image = image;
	}
}
