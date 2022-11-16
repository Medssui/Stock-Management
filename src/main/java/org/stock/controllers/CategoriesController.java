package org.stock.controllers;

import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.stock.models.Category;
import org.stock.models.Client;
import org.stock.panemu.DaoBase;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable  {
    @FXML
    public TableControl tableCategories;
    @FXML
    public NumberColumn id;
    @FXML
    public TextColumn code;
    @FXML
    public TextColumn lable;

    private TableController<Category> controller = new TableController<Category>() {
        @Override
        public TableData<Client> loadData(int i, List list, List list1, List list2, int i1) {
            return new DaoBase<Category>(Category.class).fetch(i,list,list1,list2,i1);
        }

        @Override
        public List<Category> update(List<Category> records) {
            DaoBase<Category> daoBase = new DaoBase<Category>(Category.class);
            List<Category> result = daoBase.update(records);
            return result;
        }

        @Override
        public List<Category> insert(List<Category> newRecords) {
            DaoBase<Category> daoBase = new DaoBase<Category>(Category.class);
            tableCategories.refresh();
            return daoBase.insert(newRecords);
        }

        @Override
        public void delete(List<Category> records) {
            DaoBase<Category> daoBase = new DaoBase<Category>(Category.class);
            daoBase.delete(records);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableCategories.setRecordClass(Category.class);
        tableCategories.setController(this.controller);
        tableCategories.setAgileEditing(true); // add this line
        tableCategories.reload();
    }

}
