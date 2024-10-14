package com.example.demo3.controller;

import com.example.demo3.dto.CustomerDTO;
import com.example.demo3.model.CustomerModel;
import com.example.demo3.model.ItemModel;
import com.example.demo3.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersViewController extends AnchorPane {

    @FXML
    private Label lblOrders;

    @FXML
    private AnchorPane ordersContent;

    @FXML
    private TextField txtQty;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> ColItemId;

    @FXML
    private TableView<?> tblOrders;

    private final OrderModel orderModel= new OrderModel();
    private final CustomerModel customerModel = new CustomerModel();
    private final ItemModel itemModel = new ItemModel();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load data..!").show();
        }
    }

    private void refreshPage() throws SQLException {
        lblOrderId.setText(orderModel.getNextOrderId());
//        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderDate.setText(LocalDate.now().toString());

        loadCustomerIds();
        loadItemId();
    }

    private void loadItemId() throws SQLException {
        ArrayList<String> itemIds = itemModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbItemId.setItems(observableList);
    }

    private void loadCustomerIds() throws SQLException {
        ArrayList<String> customerIds = customerModel.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmbCustomerId.setItems(observableList);
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) throws SQLException {
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        CustomerDTO customerDTO = customerModel.findByCustomerId(selectedCustomerId);
        lblCustomerName.setText(customerDTO.getName());
    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {

    }
}


