package org.stock.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.stock.models.Client;
import org.stock.models.Product;
import org.stock.panemu.DaoBase;

public class ClientsController implements Initializable {

    @FXML
    public TableControl<Client> tableClient;
    @FXML
    public NumberColumn<Client, Long> id;
    @FXML
    public TextColumn<Client> firstname;
    @FXML
    public TextColumn<Client> lastname;
    @FXML
    public TextColumn<Client> tel;
    @FXML
    public TextColumn<Client> email;

    private TableController<Client> controller = new TableController<Client>() {
        @Override
        public TableData<Client> loadData(int i, List list, List list1, List list2, int i1) {
            return new DaoBase<Client>(Client.class).fetch(i,list,list1,list2,i1);
        }

        @Override
        public List<Client> update(List<Client> records) {
            DaoBase<Client> daoBase = new DaoBase<Client>(Client.class);
            List<Client> result = daoBase.update(records);
            return result;
        }

        @Override
        public List<Client> insert(List<Client> newRecords) {
            DaoBase<Client> daoBase = new DaoBase<Client>(Client.class);
            tableClient.refresh();
            return daoBase.insert(newRecords);
        }

        @Override
        public void delete(List<Client> records) {
            DaoBase<Client> daoBase = new DaoBase<Client>(Client.class);
            daoBase.delete(records);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableClient.setRecordClass(Client.class);
        tableClient.setController(this.controller);
        tableClient.setAgileEditing(true); // add this line
        tableClient.reload();
    }





}
