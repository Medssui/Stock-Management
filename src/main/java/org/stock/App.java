

package org.stock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.stock.models.Client;
import org.stock.models.Order;
import org.stock.models.User;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static boolean isauth = false;
    public final static EntityManager  entityManager =  Persistence.createEntityManagerFactory( "org.stock.models" ).createEntityManager();

    @Override
    public void start(Stage stage) throws IOException {
        try{
            scene = new Scene(loadFXML( isauth ?"dashboard":"login"), 800, 500);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ioException){
            System.err.println(ioException.getMessage());
        }

    }

   public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

      var p = new   com.sun.javafx.scene.ParentHelper();

        launch();
    }

}