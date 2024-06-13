/**
 * Sample Skeleton for 'YemekTarifiDetay.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import application.mysql.tablolar.YemekTarifi;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class YemekTarifiDetayController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="imgResim"
    private ImageView imgResim; // Value injected by FXMLLoader

    @FXML // fx:id="lblAciklama"
    private Label lblAciklama; // Value injected by FXMLLoader

    @FXML // fx:id="lblBaslik"
    private Label lblBaslik; // Value injected by FXMLLoader
    
    private int  YEMEK_TARIFI_ID;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    }
    public void setYemekTarifiId(int YEMEK_TARIFI_ID) {
    	this.YEMEK_TARIFI_ID = YEMEK_TARIFI_ID;
    	MysqlBaglanti db = new MysqlBaglanti();
    	YemekTarifi yemekTarifi = db.YemekTarifiListesiId(this.YEMEK_TARIFI_ID);
    	imgResim.setImage(yemekTarifi.getImage());
    	lblBaslik.setText(yemekTarifi.getBaslik());
    	lblAciklama.setText(yemekTarifi.getAciklama());
    }
}
