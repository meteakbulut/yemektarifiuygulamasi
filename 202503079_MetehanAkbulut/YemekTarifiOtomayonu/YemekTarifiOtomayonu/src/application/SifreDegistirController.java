/**
 * Sample Skeleton for 'SifreDegistir.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SifreDegistirController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSifreDegistir"
    private Button btnSifreDegistir; // Value injected by FXMLLoader

    @FXML // fx:id="txtEskiSifre"
    private TextField txtEskiSifre; // Value injected by FXMLLoader

    @FXML // fx:id="txtYeniSifre"
    private TextField txtYeniSifre; // Value injected by FXMLLoader

    @FXML
    void btnSifreDegistirOnClick(ActionEvent event) {
    	if (txtEskiSifre.getText() == "" || txtYeniSifre.getText() == "") {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Hata");
			alert.setHeaderText("Hata");
			alert.setContentText("Lütfen gerekli alanlarý doldurunuz!");
			alert.show();
		}else {
			MysqlBaglanti db = new MysqlBaglanti();
			db.SifreDegistir(txtEskiSifre.getText(), txtYeniSifre.getText());
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSifreDegistir != null : "fx:id=\"btnSifreDegistir\" was not injected: check your FXML file 'SifreDegistir.fxml'.";
        assert txtEskiSifre != null : "fx:id=\"txtEskiSifre\" was not injected: check your FXML file 'SifreDegistir.fxml'.";
        assert txtYeniSifre != null : "fx:id=\"txtYeniSifre\" was not injected: check your FXML file 'SifreDegistir.fxml'.";

    }

}
