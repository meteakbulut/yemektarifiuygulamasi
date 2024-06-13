/**
 * Sample Skeleton for 'Kesfet.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.mysql.MysqlBaglanti;
import application.mysql.tablolar.YemekTarifi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class KesfetController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="ancKesfet"
    private AnchorPane ancKesfet; // Value injected by FXMLLoader

    @FXML // fx:id="listviewKesfet"
    private ListView<YemekTarifi> listviewKesfet; // Value injected by FXMLLoader

    @FXML
    void listviewKesfetOnClick(MouseEvent event) throws IOException {
    	FXMLLoader yemekTarifiDetayLoader = new FXMLLoader(getClass().getResource("YemekTarifiDetay.fxml"));
		AnchorPane pane1 = (AnchorPane) yemekTarifiDetayLoader.load();
		YemekTarifiDetayController yemekTarifiDetayController = yemekTarifiDetayLoader.getController();
		YemekTarifi selectedItem = listviewKesfet.getSelectionModel().getSelectedItem();
		
		yemekTarifiDetayController.setYemekTarifiId(selectedItem.getId());	
		
		ancKesfet.getChildren().clear();
		ancKesfet.getChildren().add(pane1);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	ObservableList<YemekTarifi> items = FXCollections.observableArrayList();
    	MysqlBaglanti db = new MysqlBaglanti();
		for (YemekTarifi item : db.YemekTarifiListesi()) {
			items.add(item);
		}
		listviewKesfet.setItems(items);
		listviewKesfet.setCellFactory(param -> new ListCell<YemekTarifi>() {
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

}
