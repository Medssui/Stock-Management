package org.stock.controllers;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.control.LookupFieldController;
import com.panemu.tiwulfx.table.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import org.stock.models.Category;
import org.stock.models.Client;
import org.stock.models.Product;
import org.stock.panemu.DaoBase;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsControllers implements Initializable {

    @FXML
    public AnchorPane anPan;
    @FXML
    public TableControl<Product> productTable;
    @FXML
    public TextColumn<Product> clmRef;
    @FXML
    public LookupColumn<Product, Category> clmCategory;
    //public LookupColumn<Product, Category> clmCategory = new LookupColumn<>("category", "lable");
    @FXML
    public NumberColumn<Product, Float> price;
    @FXML
    public NumberColumn<Product, Integer> quantity;
    @FXML
    public TextColumn clmlable;
    @FXML
    private NumberColumn<Product, Integer> clmId;
    @FXML
    private TickColumn<Product> clmTick;

    private TableController<Product> controller = new TableController<Product>() {
        @Override
        public TableData<Product> loadData(int i, List list, List list1, List list2, int i1) {
            return new DaoBase<Product>(Product.class).fetch(i,list,list1,list2,i1);

        }

        @Override
        public List<Product> update(List<Product> records) {
            DaoBase<Product> daoBase = new DaoBase<Product>(Product.class);
            List<Product> result = daoBase.update(records);

            return result;
        }

        @Override
        public List<Product> insert(List<Product> newRecords) {
            DaoBase<Product> daoBase = new DaoBase<Product>(Product.class);
            productTable.refresh();
            return daoBase.insert(newRecords);
        }

        @Override
        public void delete(List<Product> records) {
            DaoBase<Product> daoBase = new DaoBase<Product>(Product.class);
            daoBase.delete(records);
        }
    };

    public ProductsControllers() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("product controller init");
        this.productTable.setController(this.controller);
        this.productTable.setRecordClass(Product.class);
        this.quantity.setNumberType(Integer.class);

        this.clmId.setNumberType(Integer.class);

        this.clmCategory.setLookupController(new LookupFieldController<>(Category.class) {
            @Override
            public String[] getColumns() {
                return new String[]{"id","code","lable"} ;
            }

            @Override
            protected TableData<Category> loadData(int i, List<TableCriteria> list, List<String> list1, List<TableColumn.SortType> list2, int i1) {
                return new DaoBase<Category>(Category.class).fetch(i,list,list1,list2,i1);
            }
        });
        this.productTable.reload();

    }
}
