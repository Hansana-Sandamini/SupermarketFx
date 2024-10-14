package com.example.demo3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnOrders;

    @FXML
    private VBox btnsVbox;

    @FXML
    private AnchorPane content;

    @FXML
    private AnchorPane layoutPane;

    @FXML
    private Label lblSuperMarket;

    @FXML
    private VBox sideVbox;

    @FXML
    void navigateToCustomerPage(ActionEvent event) {
        navigateTo("/view/CustomerView.fxml");
    }

    @FXML
    void navigateToItemPage(ActionEvent event) {
        navigateTo("/view/ItemView.fxml");
    }

    @FXML
    void navigateToOrdersPage(ActionEvent event) {
        navigateTo("/view/OrdersView.fxml");
    }

    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            content.getChildren().add(load);
        } catch (IOException e) {
            //e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/CustomerView.fxml");
    }
}