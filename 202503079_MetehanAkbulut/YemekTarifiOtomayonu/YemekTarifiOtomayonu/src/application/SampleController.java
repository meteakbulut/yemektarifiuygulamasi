/**
 * Sample Skeleton for 'Sample.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import application.mysql.tablolar.Kullanici;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SampleController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnGirisYap"
    private Button btnGirisYap; // Value injected by FXMLLoader

    @FXML // fx:id="btnKayitOl"
    private Button btnKayitOl; // Value injected by FXMLLoader

    @FXML // fx:id="txtEposta"
    private TextField txtEposta; // Value injected by FXMLLoader

    @FXML // fx:id="txtSifre"
    private TextField txtSifre; // Value injected by FXMLLoader

    @FXML
    void txt_danisanOnKeyPressed(ActionEvent event) {
    	MysqlBaglanti db = new MysqlBaglanti();
    	String danisan = txtDanisan.getText();
    	Kullanici girisYapan = db.GirisKontrol(Eposta, Sifre);
    	if (girisYapan != null) {
    		Main.GirisYapanKullanici = girisYapan;
    		try {
        		Stage stage1=new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));                
    			AnchorPane pane1 = (AnchorPane)loader.load();
    			AnaSayfaController anasayfaController = loader.getController();
    			anasayfaController.setUSER_ID(girisYapan.getId());
    			Scene scene = new Scene(pane1,1115,705);
    			stage1.setScene(scene);
    			stage1.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Hata");
			alert.setContentText("Giri� bilgileri yanl��!");
			alert.show();
		}
    }

    @FXML
    void btnKayitOlOnClick(ActionEvent event) {
    	try {
    		Stage stage1=new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("KayitOl.fxml"));
			Scene scene = new Scene(pane1,400,400);
			stage1.setScene(scene);
			stage1.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnGirisYap != null : "fx:id=\"btnGirisYap\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnKayitOl != null : "fx:id=\"btnKayitOl\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtEposta != null : "fx:id=\"txtEposta\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtSifre != null : "fx:id=\"txtSifre\" was not injected: check your FXML file 'Sample.fxml'.";

    }

}
