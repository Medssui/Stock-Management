package org.stock.components;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.stock.controllers.OrdersController.ProductNode;
import org.stock.controllers.OrdersController;
import org.stock.models.ProductOrder;

import java.util.List;

public class ProductItem extends HBox implements EventTarget {

    private ProductNode selectedProduct;
    private VBox vBox;
    private Label label;
    private ComboBox<ProductNode> comboBox;
    private VBox vBox0;
    private Label label0;
    private Spinner<Integer> spinner;
    private VBox vBox1;
    private Button button;
    private DropShadow dropShadow;

    public Long getProductId() {
        return comboBox.getSelectionModel().getSelectedItem().getId();
    }

    public Integer getProductQuantity() {
        return spinner.getValue();
    }

    public ProductItem(ProductNode selectedProduct, Integer quantity, List<ProductNode> lables) {

        this.selectedProduct = selectedProduct;
        vBox = new VBox();
        label = new Label();
        comboBox = new ComboBox<ProductNode>();
        vBox0 = new VBox();
        label0 = new Label();
        spinner = new Spinner<Integer>(1, 100, quantity);
        vBox1 = new VBox();
        button = new Button();
        dropShadow = new DropShadow();


        spinner.setEditable(true);
        setAlignment(javafx.geometry.Pos.CENTER);
        setSpacing(10.0);
        getStyleClass().add("productelement");

        vBox.setPrefWidth(100.0);

        label.setText("Product");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        comboBox.setEditable(false);
        comboBox.getItems().addAll(lables);
        comboBox.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        comboBox.setPrefWidth(150.0);
        comboBox.setPromptText("product");
        comboBox.setValue(selectedProduct);

        comboBox.getSelectionModel().selectedItemProperty().addListener(this::valueChanged);
        spinner.valueProperty().addListener(this::valueChanged);
        vBox.setOpaqueInsets(new Insets(0.0));

        vBox0.setPrefWidth(100.0);

        label0.setText("Quantity");

        spinner.setEditable(true);

        vBox1.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);

        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-color: #d83121;");
        button.setText("remove");
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(this::removeNode);
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        setPadding(new Insets(5.0, 10.0, 10.0, 10.0));

        setEffect(dropShadow);

        vBox.getChildren().add(label);
        vBox.getChildren().add(comboBox);
        getChildren().add(vBox);
        vBox0.getChildren().add(label0);
        vBox0.getChildren().add(spinner);
        getChildren().add(vBox0);
        vBox1.getChildren().add(button);
        getChildren().add(vBox1);

    }


    private void removeNode(MouseEvent mouseEvent) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setContentText("Are you sure you want to continue this action ?");
        a.show();
        a.setOnCloseRequest(e->{
            if (((Alert) e.getSource()).getResult() == ButtonType.OK)
                OrdersController.listProductItems.remove(this);
        });


    }

    private void valueChanged(ObservableValue<? extends Object> observableValue, Object integer, Object t1) {
        int index = OrdersController.listProductItems.indexOf(this);
        OrdersController.listProductItems.set(index, this);
    }
}
