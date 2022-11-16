package org.stock.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.stock.App;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ChartsController implements Initializable {

    @FXML
    public HBox chart;
    @FXML
    public PieChart pieChartProduct;
    @FXML
    public Label pieChartProductQuantity;
    @FXML
    public LineChart <Number, Number> lineChartOrderYear;
    @FXML
    public Label clientcount;
    @FXML
    public Label ordercount;
    @FXML
    public Label productcount;
    @FXML
    public Label suppliercount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //PieChart Product and quantity
        var products = (List<PieChart.Data>) App.entityManager.createQuery("select p.lable, p.quantity from Product p ")
                .getResultList().stream().map(
                        o -> {
                            Object[] o1 = (Object[]) o;
                            return new PieChart.Data((String) o1[0], (Integer) o1[1]);
                        }
                ).collect(Collectors.toList());

        clientcount.setText(""+App.entityManager.createQuery("select count(c.id)  from Client c").getSingleResult());
        ordercount.setText(""+App.entityManager.createQuery("select count(o.id) from Order o").getSingleResult());
        productcount.setText(""+products.size());
        suppliercount.setText(""+App.entityManager.createQuery("select count(s.id) from Supplier s").getSingleResult());



        //Create date
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(products);

        //Create PieChart chart Object
        pieChartProduct.setData(pieChartData);
        pieChartProduct.setTitle("Nombre de produit en stock");
        pieChartProduct.setPrefWidth(400.0);
        pieChartProduct.setPrefHeight(400.0);

        /*pieChartProductQuantity.setTextFill(Color.BLACK);
        pieChartProductQuantity.setStyle("-fx-font: 20 arial;");
        pieChartProductQuantity.setText("test");*/

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 12 arial;");

        for (final PieChart.Data data : pieChartProduct.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    /*pieChartProductQuantity.setTranslateX(e.getX());
                    pieChartProductQuantity.setTranslateY(e.getY());
                    System.out.println(data.getPieValue());
                    pieChartProductQuantity.setText(String.valueOf(data.getPieValue()));*/
                    System.out.println(data.getPieValue());
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }

        //Line Chart nbr order in Year

        //defining a series
        //XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

        /*var orderYear = (List<LineChart.Data>) App.entityManager.createQuery(" select COUNT(o.id), COUNT(o.id) FROM orders o GROUP BY o.id ")
                .getResultList().stream().map(
                        o -> {
                            Object[] o1 = (Object[]) o;
                            return series.getData().add(new XYChart.Data((Integer)o1[0], (Integer)o1[1]));
                        }
                ).collect(Collectors.toList());*/


        //Create PieChart chart Object
        //series.setName("My portfolio");
        //populating the series with data
        /*series.getData().add(new XYChart.Data<Number, Number>(1, 23));
        series.getData().add(new XYChart.Data<Number, Number>(2, 14));
        series.getData().add(new XYChart.Data<Number, Number>(3, 15));
        series.getData().add(new XYChart.Data<Number, Number>(4, 24));
        series.getData().add(new XYChart.Data<Number, Number>(5, 34));*/

        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series series = new XYChart.Series();
        series.setName("les vente annuellle");

        var orderYear = (List<XYChart.Data>) App.entityManager.createQuery(" select year(o.applicationDate) as ye, count(o.id) as cu  FROM Order o GROUP BY ye ")
        .getResultList().stream().map(
                o -> {
                    Object[] o1 = (Object[]) o;
                    series.getData().add(new XYChart.Data(((Integer)o1[0]).toString(), (Long)o1[1]));
                    return 1;
                }
        ).collect(Collectors.toList());

        lineChartOrderYear.getData().add(series);
    }


}
