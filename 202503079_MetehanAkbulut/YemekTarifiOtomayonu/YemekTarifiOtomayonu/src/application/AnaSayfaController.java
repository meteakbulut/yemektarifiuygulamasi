/**
 * Sample Skeleton for 'AnaSayfa.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

public class AnaSayfaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ancIcerik"
    private AnchorPane ancIcerik; // Value injected by FXMLLoader

    @FXML // fx:id="ancMenu"
    private AnchorPane ancMenu; // Value injected by FXMLLoader

    @FXML
    private Button btnAdmin;
    
    @FXML // fx:id="btnCikisYap"
    private Button btnCikisYap; // Value injected by FXMLLoader

    @FXML // fx:id="btnKesfet"
    private Button btnKesfet; // Value injected by FXMLLoader

    @FXML // fx:id="btnSifreDegistir"
    private Button btnSifreDegistir; // Value injected by FXMLLoader

    @FXML // fx:id="btnYemekTariflerim"
    private Button btnYemekTariflerim; // Value injected by FXMLLoader
    
    private int USER_ID;
    
    @FXML
    void btnAramaOnClick(ActionEvent event) {
    	try {
			AnchorPane pane1 = (AnchorPane) FXMLLoader.load(getClass().getResource("Arama.fxml"));
			ancIcerik.getChildren().setAll(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    void btnCikisYapOnClick(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Çýkýþ yapmkmak istediðinize Emin misiniz?", ButtonType.YES, ButtonType.NO);
    	alert.setTitle("Çýkýþ Yap");
    	alert.setHeaderText("Çýkýþ Yap");
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
    	}
    }

    @FXML
    void btnKesfetOnClick(ActionEvent event) {
    	try {
			AnchorPane pane1 = (AnchorPane) FXMLLoader.load(getClass().getResource("Kesfet.fxml"));
			ancIcerik.getChildren().setAll(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnSifreDegistirOnClick(ActionEvent event) {
    	try {
			AnchorPane pane1 = (AnchorPane) FXMLLoader.load(getClass().getResource("SifreDegistir.fxml"));
			ancIcerik.getChildren().setAll(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnYemekTariflerimOnClick(ActionEvent event) {
    	try {
            FXMLLoader yemekTariflerimLoader = new FXMLLoader(getClass().getResource("YemekTariflerim.fxml"));
			AnchorPane pane1 = (AnchorPane) yemekTariflerimLoader.load();
			YemekTariflerimController yemekTariflerimController = yemekTariflerimLoader.getController();
			yemekTariflerimController.setUSER_ID(USER_ID);
			ancIcerik.getChildren().setAll(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnAdminOnclick(ActionEvent event) {
    	try {
            FXMLLoader yemekTariflerimLoader = new FXMLLoader(getClass().getResource("Admin.fxml"));
			AnchorPane pane1 = (AnchorPane) yemekTariflerimLoader.load();
			YemekTariflerimController yemekTariflerimController = yemekTariflerimLoader.getController();
			yemekTariflerimController.setUSER_ID(USER_ID);
			ancIcerik.getChildren().setAll(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
	}
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	if (Main.GirisYapanKullanici.getAdmin() == 1) {
			btnAdmin.setVisible(true);
		} 
    }

}
