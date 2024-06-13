/**
 * Sample Skeleton for 'Arama.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import application.mysql.tablolar.YemekTarifi;
import finalodevi.BesinListe;
import finalodevi.MysqlIslemler;
import finalodevi.YemekListe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AramaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="ancArama"
    private AnchorPane ancArama; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnArama"
    private Button btnArama; // Value injected by FXMLLoader

    @FXML // fx:id="listviewArama"
    private ListView<YemekTarifi> listviewArama; // Value injected by FXMLLoader

    @FXML // fx:id="txtArama"
    private TextField txtArama; // Value injected by FXMLLoader

    @FXML
    void btnAramaOnClick(ActionEvent event) {
    	ObservableList<YemekTarifi> items = FXCollections.observableArrayList();
    	MysqlBaglanti db = new MysqlBaglanti();
		for (YemekTarifi item : db.YemekTarifiListesiAra(txtArama.getText())) {
			items.add(item);
		}
		listviewArama.setItems(items);
		listviewArama.setCellFactory(param -> new ListCell<YemekTarifi>() {
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

    @FXML
    void listviewAramaOnClick(MouseEvent event)  {
    	try {
    		FXMLLoader yemekTarifiDetayLoader = new FXMLLoader(getClass().getResource("YemekTarifiDetay.fxml"));
			AnchorPane ancIcerik = (AnchorPane) yemekTarifiDetayLoader.load();
			YemekTarifiDetayController yemekTarifiDetayController = yemekTarifiDetayLoader.getController();
			yemekTarifiDetayController.setYemekTarifiId(listviewArama.getSelectionModel().getSelectedItem().getId());
			ancArama.getChildren().clear();
			ancArama.getChildren().add(ancIcerik);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnArama != null : "fx:id=\"btnArama\" was not injected: check your FXML file 'Arama.fxml'.";
        assert listviewArama != null : "fx:id=\"listviewArama\" was not injected: check your FXML file 'Arama.fxml'.";
        assert txtArama != null : "fx:id=\"txtArama\" was not injected: check your FXML file 'Arama.fxml'.";

    }
    
    void editDanisanAraOnKeyPress(ActionEvent event) {
    	ObservableList<YemekListe> items = FXCollections.observableArrayList();
    	MysqlIslemler db = new MysqlIslemler();
    	
		for (YemekListe item : db.YemekListesiAra(txtArama.getText())) {
			items.add(item);
		}
		tbl1_btip.setCellValueFactory(new PropertyValueFactory<>("bTip"));
		tbl1_danisan.setCellValueFactory(new PropertyValueFactory<>("yDanisan"));
		tbl1_adet.setCellValueFactory(new PropertyValueFactory<>("bToplam"));
		tbl1_tarih.setCellValueFactory(new PropertyValueFactory<>("yTarih"));
		tbl1_toplam.setCellValueFactory(new PropertyValueFactory<>("yToplam"));
        tb1.setItems(items);
    }
    void editBesinAraOnKeyPress(ActionEvent event) {
    	ObservableList<BesinListe> items = FXCollections.observableArrayList();
    	MysqlIslemler db = new MysqlIslemler();
    	
		for (BesinListe item : db.BesinListesiAra(txtArama.getText())) {
			items.add(item);
		}
		tbl2_bAd.setCellValueFactory(new PropertyValueFactory<>("bAd"));
		tbl2_bTip.setCellValueFactory(new PropertyValueFactory<>("bTip"));
		tbl2_birimKalori.setCellValueFactory(new PropertyValueFactory<>("bKalori"));
		tbl2_stok.setCellValueFactory(new PropertyValueFactory<>("bStok"));
        tb2.setItems(items);
    }
}
