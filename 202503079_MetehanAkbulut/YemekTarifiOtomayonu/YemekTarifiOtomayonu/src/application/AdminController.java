/**
 * Sample Skeleton for 'YemekTariflerim.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import application.mysql.tablolar.YemekTarifi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnGuncelle"
    private Button btnGuncelle; // Value injected by FXMLLoader

    @FXML // fx:id="btnSil"
    private Button btnSil; // Value injected by FXMLLoader

    @FXML // fx:id="listviewYemekTariflerim"
    private ListView<YemekTarifi> listviewYemekTariflerim; // Value injected by FXMLLoader

    @FXML // fx:id="txtId"
    private TextField txtId; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtAciklama"
    private TextArea txtAciklama; // Value injected by FXMLLoader

    @FXML // fx:id="txtBasllik"
    private TextField txtBasllik; // Value injected by FXMLLoader

    @FXML // fx:id="txtResimURL"
    private TextField txtResimURL; // Value injected by FXMLLoader

    private int USER_ID;

    @FXML
    void btnGuncelleOnClick(ActionEvent event) {
    	MysqlBaglanti db = new MysqlBaglanti();
    	int Id = Integer.parseInt(txtId.getText());
    	String Baslik = txtBasllik.getText();
    	String Aciklama = txtAciklama.getText();
    	String ResimUrl = txtResimURL.getText();
    	
    	db.YemekTarifiDuzenle(Id, Baslik, Aciklama, ResimUrl);
    	initialize();
    }

    @FXML
    void btnSilOnClick(ActionEvent event) {
    	MysqlBaglanti db = new MysqlBaglanti();
    	int Id = Integer.parseInt(txtId.getText());
    	db.YemekTarifiSil(Id);
    	initialize();
    }

    @FXML
    void listviewYemekTariflerimOnClick(MouseEvent event) {
    	if(listviewYemekTariflerim.getSelectionModel().getSelectedItem() != null) {
    		txtId.setText(listviewYemekTariflerim.getSelectionModel().getSelectedItem().getId() + "");
    		txtBasllik.setText(listviewYemekTariflerim.getSelectionModel().getSelectedItem().getBaslik());
    		txtAciklama.setText(listviewYemekTariflerim.getSelectionModel().getSelectedItem().getAciklama());
    		txtResimURL.setText(listviewYemekTariflerim.getSelectionModel().getSelectedItem().getResimUrl());
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	ObservableList<YemekTarifi> items = FXCollections.observableArrayList();
    	MysqlBaglanti db = new MysqlBaglanti();
		/*for (YemekTarifi item : db.YemekTarifiListesiKullaniciId(Main.GirisYapanKullanici.getId())) {
			items.add(item);
		}*/
    	 
    	for (YemekTarifi item : db.YemekTarifiListesi()) {
    		items.add(item);
    	}
		listviewYemekTariflerim.setItems(items);
		listviewYemekTariflerim.setCellFactory(param -> new ListCell<YemekTarifi>() {
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(YemekTarifi yemekTarifi, boolean empty) {
				super.updateItem(yemekTarifi, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					imageView.setImage(yemekTarifi.getImage());
					imageView.setFitHeight(150);
					imageView.setFitWidth(150);
					setText(yemekTarifi.getBaslik());
					setGraphic(imageView);
				}
			}
		});
    }
    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
        initialize();
    }
}
