package com.example.demo3.controller;

import com.example.demo3.db.DBConnection;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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
    private Button btnGenerateAllCustomerReport;

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

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$"; // Phone pattern (10 digits)

//        Pattern compile = Pattern.compile(namePattern);
//        System.out.println(compile.matcher(name).matches());

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        txtName.setStyle(txtName.getStyle()+";-fx-border-color: #7367F0;");
        txtNic.setStyle(txtNic.getStyle()+";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: #7367F0;");

        if (!isValidName){
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidNic){
            txtNic.setStyle(txtNic.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidEmail){
            txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidPhone){
            txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: red;");
        }

        if (isValidName && isValidNic && isValidEmail && isValidPhone){
            CustomerDTO customerDTO = new CustomerDTO(id, name, nic, email, phone);

            boolean isSaved = customerModel.saveCustomer(customerDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
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
    void generateAllCustomerOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/all_customers.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("today", LocalDate.now().toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,connection);
            JasperViewer.viewReport(jasperPrint,false);
           // connection.close();
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException {
        String id = lblCust1.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$"; // Phone pattern (10 digits)

//        Pattern compile = Pattern.compile(namePattern);
//        System.out.println(compile.matcher(name).matches());

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        txtName.setStyle(txtName.getStyle()+";-fx-border-color: #7367F0;");
        txtNic.setStyle(txtNic.getStyle()+";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: #7367F0;");

        if (!isValidName){
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidNic){
            txtNic.setStyle(txtNic.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidEmail){
            txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidPhone){
            txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: red;");
        }

        if (isValidName && isValidNic && isValidEmail && isValidPhone){
            CustomerDTO customerDTO = new CustomerDTO(id, name, nic, email, phone);

            boolean isUpdated = customerModel.updateCustomer(customerDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
            }
        }
    }

}
