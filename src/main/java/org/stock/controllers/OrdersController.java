package org.stock.controllers;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.control.LookupFieldController;
import com.panemu.tiwulfx.table.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;
import org.stock.App;
import org.stock.components.ProductItem;
import org.stock.models.*;
import org.stock.panemu.DaoBase;
import org.stock.reports.OrderReport;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrdersController implements Initializable {

    public class ProductNode {
        private String lable;
        private Long Id;

        public ProductNode(String lable, Long id) {
            this.lable = lable;
            Id = id;
        }

        @Override
        public ProductNode clone()  {
            return new ProductNode(this.lable,this.Id);
        }

        @Override
        public String toString() {
            return this.lable;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }
    }


    @FXML
    public TableControl<Order> tableOrder;
    @FXML
    public NumberColumn<Order, Integer> id;
    @FXML
    public NumberColumn<Order, Float> price;
    @FXML
    public NumberColumn<Order, Float> tax;
    @FXML
    public NumberColumn<Order, Float> ttcPrice;
    @FXML
    public TypeAheadColumn  status;
    @FXML
    public DateColumn<Order> application_date;
    @FXML
    public DateColumn<Order> delivery_date;
    @FXML
    public TextColumn<Order> delivery_vendor;
    @FXML
    public LookupColumn<Order, Client> client;
    @FXML
    public TickColumn<Order> clmTick;
    @FXML
    public VBox productItemContainer;
    @FXML
    public Spinner<Integer> productItemQuantity;
    @FXML
    public ComboBox<ProductNode> productItemName;

    public Button printButton ;

    public static List<ProductOrder> OrderedProducts;
    private List<ProductNode> productsLables;

    public static ObservableList<Node> listProductItems = FXCollections.<Node>observableArrayList();


    public static int deletedNodeIndex;

    private TableController<Order> controller = new TableController<Order>() {
        @Override
        public TableData<Order> loadData(int i, List list, List list1, List list2, int i1) {
            return new DaoBase<Order>(Order.class).fetch(i, list, list1, list2, i1);

        }

        @Override
        public List<Order> update(List<Order> records) {
            DaoBase<Order> daoBase = new DaoBase<Order>(Order.class);
            List<Order> result = daoBase.update(records);

            return result;
        }

        @Override
        public List<Order> insert(List<Order> newRecords) {
            DaoBase<Order> daoBase = new DaoBase<Order>(Order.class);
            tableOrder.refresh();
            return daoBase.insert(newRecords);
        }

        @Override
        public void delete(List<Order> records) {
            DaoBase<Order> daoBase = new DaoBase<Order>(Order.class);
            daoBase.delete(records);
        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("product controller init");
        this.status.addItem("pending","pending");
        this.status.addItem("delivered","delivred");
        this.status.addItem("paused","paused");

        this.printButton = new Button("print");
        printButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    OrderReport report = new OrderReport(tableOrder.getSelectedItem().getId().intValue()) ;
                    report.showReport();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (JRException e) {
                    e.printStackTrace();
                }

            }
        });

        this.tableOrder.addButton(this.printButton);
        this.status.setEditable(false);
        this.tableOrder.setController(this.controller);
        this.tableOrder.setRecordClass(Order.class);
        this.id.setNumberType(Integer.class);
        this.client.setLookupController(new LookupFieldController<>(Client.class) {
            @Override
            public String[] getColumns() {
                return new String[]{"id", "firstname", "lastname", "tel", "email"};
            }

            @Override
            protected TableData<Client> loadData(int i, List<TableCriteria> list, List<String> list1, List<TableColumn.SortType> list2, int i1) {
                return new DaoBase<Client>(Client.class).fetch(i, list, list1, list2, i1);
            }
        });
        this.tableOrder.reload();

        this.productsLables = (List<ProductNode>) App.entityManager.
                createQuery("select p from Product p").
                getResultList().
                stream().map((p) -> new ProductNode(((Product) p).getLable(), ((Product) p).getId())).collect(Collectors.toList());


        this.productItemName.getItems().addAll(this.productsLables);

        this.tableOrder.getTableView().setOnMouseClicked(this::loadProducts);
        Bindings.bindContentBidirectional(listProductItems, this.productItemContainer.getChildren());
        listProductItems.addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> change) {
                change.next();
                if(change.getList().isEmpty()){
                    return;
                }
                App.entityManager.getTransaction().begin();
                try{
                    App.entityManager.createQuery("delete from ProductOrder po where po.orderId = :orderId").setParameter("orderId",tableOrder.getSelectedItem().getId().intValue()).executeUpdate();
                    OrderedProducts = change.getList().stream().map(node -> {
                        ProductItem pnode = (ProductItem) node;
                        ProductOrder productOrder = new ProductOrder(pnode.getProductId().intValue(), tableOrder.getSelectedItem().getId().intValue(), pnode.getProductQuantity());
                        App.entityManager.persist(productOrder);

                        return productOrder ;
                    }).collect(Collectors.toList());
                }
                catch (Exception exception){
                    App.entityManager.getTransaction().rollback();
                }
                finally {
                    App.entityManager.getTransaction().commit();
                }





            }
        });
    }

    @FXML
    public void addProductitem() {
        ProductNode selectedProduct = this.productItemName.getSelectionModel().getSelectedItem();
        System.out.println(this.productItemName.getSelectionModel().getSelectedItem().getId());
        listProductItems.add(new ProductItem(selectedProduct.clone(), this.productItemQuantity.getValue(), this.productsLables));
    }

    boolean change = false ;
    @FXML
    private void loadProducts(MouseEvent mouseEvent) {
        change = true ;
        listProductItems.retainAll();
        List<ProductItem> productsOrder = (List<ProductItem>) App.entityManager.createQuery("select po.productId ,po.orderId,po.quantity,p.lable from ProductOrder po inner join Product as p on po.productId=p.id where po.orderId = :orderid")
                .setParameter("orderid", tableOrder.getSelectedItem().getId().intValue())
                .getResultList()
                .stream().map(o -> {
                    Object[] o1 = (Object[]) o;
                    return new ProductItem(new ProductNode((String) o1[3],((Integer)o1[0]).longValue()),(Integer)o1[2],this.productsLables);
                }).collect(Collectors.toList());
        listProductItems.addAll(productsOrder);


    }


}
