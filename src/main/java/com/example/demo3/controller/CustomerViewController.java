package com.example.demo3.controller;

import com.example.demo3.dto.CustomerDTO;
import com.example.demo3.model.CustomerModel;
import com.example.demo3.tm.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    @FXML
    private AnchorPane customerContent;

    @FXML
    private Label lblCustomer;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnGenerateReport;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerId;

    @FXML
    private TableColumn<CustomerTM, String> colEmail;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private TableColumn<CustomerTM, String> colNic;

    @FXML
    private TableColumn<CustomerTM, String> colPhone;

    @FXML
    private Label lblCust1;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblPhone;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhone;

    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws SQLException {
        String id = lblCust1.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        CustomerDTO customerDTO = new CustomerDTO(id, name, nic, email, phone);

        boolean isSaved = customerModel.saveCustomer(customerDTO);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Customer saved...!").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fail to save customer...!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        try {
            refreshTable();

            String nextCustomerID = customerModel.getNextCustomerID();
            System.out.println(nextCustomerID);
            lblCust1.setText(nextCustomerID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();

        String nextCustomerID = customerModel.getNextCustomerID();
        System.out.println(nextCustomerID);
        lblCust1.setText(nextCustomerID);

        txtName.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtPhone.setText("");

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void refreshTable() throws SQLException {
        ArrayList<CustomerDTO> customerDTOS = customerModel.getAllCustomer();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customerDTO:customerDTOS){
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getId(),
                    customerDTO.getName(),
                    customerDTO.getNic(),
                    customerDTO.getEmail(),
                    customerDTO.getPhone()
            );
            customerTMS.add(customerTM);
        }
        tblCustomer.setItems(customerTMS);
    }


    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            lblCust1.setText(selectedItem.getId());
            txtName.setText(selectedItem.getName());
            txtNic.setText(selectedItem.getNic());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCust1.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES){

            boolean isDeleted = customerModel.deleteCustomer(customerId);

            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer deleted...!").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete customer...!").show();
            }
        }
    }

    @FXML
    void btnResetCustomerOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException {
        String id = lblCust1.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        CustomerDTO customerDTO = new CustomerDTO(id, name, nic, email, phone);

        boolean isUpdate = customerModel.updateCustomer(customerDTO);

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Customer updated...!").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fail to update customer...!").show();
        }
    }

}
