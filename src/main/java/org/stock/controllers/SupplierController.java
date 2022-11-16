package org.stock.controllers;

import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import org.stock.models.Product;
import org.stock.models.Supplier;
import org.stock.panemu.DaoBase;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    @FXML
    public NumberColumn<Supplier,Integer> id;
    @FXML
    public TextColumn<Supplier> email;
    @FXML
    public TextColumn<Supplier> tel;
    @FXML
    public TextColumn<Supplier> adresse;
    @FXML
    public TextColumn<Supplier> type;
    @FXML
    public TableControl<Supplier> tableSupplier;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tableSupplier.setController(new TableController<Supplier>() {
            @Override
            public TableData<Supplier> loadData(int i, List<TableCriteria> list, List<String> list1, List<TableColumn.SortType> list2, int i1) {
                return new DaoBase<Supplier>(Supplier.class).fetch(i,list,list1,list2,i1);
            }
        });
        this.tableSupplier.setRecordClass(Supplier.class);
        this.id.setNumberType(Integer.class);
        this.tableSupplier.reload();

    }
}
