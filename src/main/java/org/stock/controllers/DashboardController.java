package org.stock.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.stock.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class DashboardController  implements Initializable {
    @FXML
    public VBox container;
    @FXML
    private BorderPane wrapper ;

    @FXML
    private void changePage(MouseEvent event) throws IOException {

        container.getChildren().forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                node.getStyleClass().removeAll("navbtnactive");
            }
        });
        Node node = (Button) event.getSource() ;
       node.getStyleClass().add("navbtnactive");

        wrapper.setCenter(App.loadFXML((String) node.getUserData()));
    }

    @FXML
    private void logout() throws IOException {
        App.isauth = false ;
        App.setRoot("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            wrapper.setCenter(App.loadFXML("charts"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
