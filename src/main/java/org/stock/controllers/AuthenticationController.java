package org.stock.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Criteria;
import org.stock.App;
import org.stock.models.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;

public class AuthenticationController  {

    @FXML
    private TextField username;
    @FXML
    private  PasswordField password;



    @FXML
    public void Authenticate() throws IOException {
       if( App.isauth = (Long) App.entityManager
                        .createNamedQuery("authenticate")
                        .setParameter("username",this.username.getText())
                        .setParameter("password",this.password.getText())
                        .getSingleResult() == 1 )
           App.setRoot("dashboard");


        int x= 5;

    }



}
