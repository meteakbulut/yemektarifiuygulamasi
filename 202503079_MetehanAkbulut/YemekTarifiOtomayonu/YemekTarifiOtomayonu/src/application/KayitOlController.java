/**
 * Sample Skeleton for 'KayitOl.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class KayitOlController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnKayitOl"
    private Button btnKayitOl; // Value injected by FXMLLoader

    @FXML // fx:id="txtAdiSoyadi"
    private TextField txtAdiSoyadi; // Value injected by FXMLLoader

    @FXML // fx:id="txtEposta"
    private TextField txtEposta; // Value injected by FXMLLoader

    @FXML // fx:id="txtSifre"
    private TextField txtSifre; // Value injected by FXMLLoader

    @FXML
    void btnKayitOlOnClick(ActionEvent event) {
    	MysqlBaglanti db = new MysqlBaglanti();
    	String Eposta = txtEposta.getText();
    	String AdiSoyadi = txtAdiSoyadi.getText();
    	String Sifre = txtSifre.getText();
    	if (Eposta == "" ||AdiSoyadi == "" ||Sifre == "" ) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Hata!");
			alert.setContentText("Alanlar boþ býrakýlamaz!");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Hata!");
			alert.setContentText("Kayýt baþarýlý!");
			alert.show();
		}
    	db.KullaniciEkle(Eposta, AdiSoyadi, Sifre);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnKayitOl != null : "fx:id=\"btnKayitOl\" was not injected: check your FXML file 'KayitOl.fxml'.";
        assert txtAdiSoyadi != null : "fx:id=\"txtAdiSoyadi\" was not injected: check your FXML file 'KayitOl.fxml'.";
        assert txtEposta != null : "fx:id=\"txtEposta\" was not injected: check your FXML file 'KayitOl.fxml'.";
        assert txtSifre != null : "fx:id=\"txtSifre\" was not injected: check your FXML file 'KayitOl.fxml'.";

    }

}
