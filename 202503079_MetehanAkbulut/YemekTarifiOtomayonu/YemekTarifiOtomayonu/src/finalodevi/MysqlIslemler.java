package finalodevi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MysqlIslemler {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_final?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "";

	Connection _conn = null;

	public MysqlIslemler() {
		try {
			Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			this._conn = conn;
		} catch (SQLException e) {
			
		}
	}
	public ArrayList<YemekListe> YemekListesiAra(String Metin) {
		ArrayList<YemekListe> list = new ArrayList<YemekListe>();
		try {
			String sql = "SELECT * FROM tbl_yemekliste y INNER JOIN tbl_besinler b ON b.pId = y.pId WHERE y.yDanisan like ?";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + Metin + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				YemekListe YemekListe = new YemekListe();
				YemekListe.setbId(rs.getInt("pId"));
				YemekListe.setyId(rs.getInt("yId"));
				YemekListe.setyDanisan(rs.getString("yDanisan"));
				YemekListe.setyTarih(rs.getString("yTarih"));
				YemekListe.setbTipi(rs.getString("bTipi"));
				list.add(YemekListe);
			}
		} catch (SQLException e) {
			
		}
		return list;
	}
	public ArrayList<BesinListe> BesinListesiAra(String Metin) {
		ArrayList<BesinListe> list = new ArrayList<BesinListe>();
		try {
			String sql = "SELECT * FROM tbl_besinler y WHERE y.bAd like ?";
			PreparedStatement preparedStatement = _conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + Metin + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				BesinListe BesinListe = new BesinListe();
				BesinListe.setbID(rs.getInt("bId"));
				BesinListe.setbAd(rs.getString("bAd"));
				BesinListe.setbKalori(rs.getDouble("bKalori"));
				BesinListe.setbStok(rs.getInt("bStok"));
				BesinListe.setbTipi(rs.getString("bTipi"));
				list.add(BesinListe);
			}
		} catch (SQLException e) {
			
		}
		return list;
	}
}
