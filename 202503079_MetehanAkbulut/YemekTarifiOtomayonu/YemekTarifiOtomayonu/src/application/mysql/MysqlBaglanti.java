package application.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;
import application.mysql.tablolar.Kullanici;
import application.mysql.tablolar.YemekTarifi;
import application.yardimcifonksiyonlar.Sifreleme;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MysqlBaglanti {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/yemektarifiotomasyonu?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "";

	Connection _conn = null;

	public MysqlBaglanti() {
		try {
			Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			this._conn = conn;
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Hata");
				alert.setHeaderText("Mysql Baðlantý Hatasý");
				alert.setContentText(e.getMessage());
				alert.show();
			}
		}
	}


	public Kullanici GirisKontrol(String Eposta, String Sifre) {
		try {
			String sql = "SELECT * FROM kullanici WHERE Eposta = ? and sifre = ?";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, Eposta);
			preparedStatement.setString(2, Sifreleme.MetniMd5Sifrele(Sifre));
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Kullanici user = new Kullanici();
				user.setId(rs.getInt("Id"));
				user.setEposta(rs.getString("Eposta"));
				user.setAdiSoyadi(rs.getString("AdiSoyadi"));
				user.setSifre(rs.getString("Sifre"));
				user.setAdmin(rs.getInt("Admin"));
				return user;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return null;
	}

	public void KullaniciEkle(String Eposta, String AdiSoyadi, String Sifre) {
		try {
			String sql = "INSERT INTO kullanici VALUES(null, ?, ?, ?)";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, Eposta);
			preparedStatement.setString(2, AdiSoyadi);
			preparedStatement.setString(3, Sifreleme.MetniMd5Sifrele(Sifre));
			preparedStatement.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public void SifreDegistir(String EskiSifre, String YeniSifre) {
		try {
			String sql = "SELECT * FROM kullanici WHERE Id = ?";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setInt(1, Main.GirisYapanKullanici.getId());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String sifreMD5 = Sifreleme.MetniMd5Sifrele(EskiSifre);
				if (rs.getString("Sifre").equals(sifreMD5)) {
					sql = "UPDATE kullanici SET sifre = ? WHERE Id = ?";
					preparedStatement = _conn.prepareStatement(sql);
					preparedStatement.setString(1, Sifreleme.MetniMd5Sifrele(YeniSifre));;
					preparedStatement.setInt(2, Main.GirisYapanKullanici.getId());
					preparedStatement.execute();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Baþarýlý");
					alert.setHeaderText("Baþarýlý");
					alert.setContentText("Ýþlem baþarý ile gerçekleþmiþtir.");
					alert.show();
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Hata" + Sifreleme.MetniMd5Sifrele(EskiSifre));
					alert.setHeaderText("Hata" + rs.getString("Sifre"));
					alert.setContentText("Girdiðiniz þifre eski þifreniz ile uyumuþuyor!");
					alert.show();
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public ArrayList<YemekTarifi> YemekTarifiListesi() {
		ArrayList<YemekTarifi> list = new ArrayList<YemekTarifi>();
		try {
			String sql = "SELECT * FROM yemektarifi y INNER JOIN kullanici k on y.KullaniciId= k.Id ";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				YemekTarifi yemekTarifi = new YemekTarifi();
				yemekTarifi.setId(rs.getInt("Id"));
				yemekTarifi.setBaslik(rs.getString("Baslik"));
				yemekTarifi.setAciklama(rs.getString("Aciklama"));
				yemekTarifi.setResimUrl(rs.getString("ResimUrl"));
				yemekTarifi.setKullaniciId(rs.getInt("KullaniciId"));
				yemekTarifi.setKullaniciAdiSoyadi(rs.getString("AdiSoyadi"));
				list.add(yemekTarifi);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return list;
	}
	
	public YemekTarifi YemekTarifiListesiId(int Id) {
		try {
			String sql = "SELECT * FROM yemektarifi y INNER JOIN kullanici k on y.KullaniciId= k.Id WHERE y.Id = ? ";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setInt(1, Id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				YemekTarifi yemekTarifi = new YemekTarifi();
				yemekTarifi.setId(rs.getInt("Id"));
				yemekTarifi.setBaslik(rs.getString("Baslik"));
				yemekTarifi.setAciklama(rs.getString("Aciklama"));
				yemekTarifi.setResimUrl(rs.getString("ResimUrl"));
				yemekTarifi.setKullaniciId(rs.getInt("KullaniciId"));
				yemekTarifi.setKullaniciAdiSoyadi(rs.getString("AdiSoyadi"));
				return yemekTarifi;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return null;
	}
	
	public ArrayList<YemekTarifi> YemekTarifiListesiKullaniciId(int KullaniciId) {
		ArrayList<YemekTarifi> list = new ArrayList<YemekTarifi>();
		try {
			String sql = "SELECT * FROM yemektarifi y INNER JOIN kullanici k on y.KullaniciId= k.Id WHERE y.KullaniciId = ? ";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setInt(1, KullaniciId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				YemekTarifi yemekTarifi = new YemekTarifi();
				yemekTarifi.setId(rs.getInt("Id"));
				yemekTarifi.setBaslik(rs.getString("Baslik"));
				yemekTarifi.setAciklama(rs.getString("Aciklama"));
				yemekTarifi.setResimUrl(rs.getString("ResimUrl"));
				yemekTarifi.setKullaniciId(rs.getInt("KullaniciId"));
				yemekTarifi.setKullaniciAdiSoyadi(rs.getString("AdiSoyadi"));
				list.add(yemekTarifi);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return list;
	}
	public void YemekTarifiEkle(String Baslik, String Aciklama, String ResimUrl) {
		try {
			String sql = "INSERT INTO yemektarifi VALUES(null, ?, ?, ?,?)";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, Baslik);
			preparedStatement.setString(2, Aciklama);
			preparedStatement.setString(3, ResimUrl);
			preparedStatement.setInt(4, Main.GirisYapanKullanici.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public void YemekTarifiDuzenle(int Id, String Baslik, String Aciklama, String ResimUrl) {
		try {
			String sql = "UPDATE yemektarifi SET Baslik = ?, Aciklama = ?, ResimUrl = ? WHERE Id = ?";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, Baslik);
			preparedStatement.setString(2, Aciklama);
			preparedStatement.setString(3, ResimUrl);
			preparedStatement.setInt(4, Id);
			preparedStatement.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public void YemekTarifiSil(int Id) {
		try {
			String sql = "DELETE FROM yemektarifi WHERE Id= ?";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setInt(1, Id);
			preparedStatement.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public ArrayList<YemekTarifi> YemekTarifiListesiAra(String Metin) {
		ArrayList<YemekTarifi> list = new ArrayList<YemekTarifi>();
		try {
			String sql = "SELECT * FROM yemektarifi y INNER JOIN kullanici k on y.KullaniciId= k.Id WHERE y.Baslik like ? or y.Aciklama like ? ";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + Metin + "%");
			preparedStatement.setString(2, "%" + Metin + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				YemekTarifi yemekTarifi = new YemekTarifi();
				yemekTarifi.setId(rs.getInt("Id"));
				yemekTarifi.setBaslik(rs.getString("Baslik"));
				yemekTarifi.setResimUrl(rs.getString("ResimUrl"));
				yemekTarifi.setKullaniciId(rs.getInt("KullaniciId"));
				yemekTarifi.setKullaniciAdiSoyadi(rs.getString("AdiSoyadi"));
				list.add(yemekTarifi);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return list;
	}
}
