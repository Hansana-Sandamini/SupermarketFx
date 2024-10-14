package com.example.demo3.controller;

import com.example.demo3.tm.ItemTM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemViewController implements Initializable {

    @FXML
    private AnchorPane itemcontent;

    @FXML
    private Label lblItem;

    @FXML
    private TableColumn<ItemTM, String> ColItemId;

    @FXML
    private TableColumn<ItemTM, String> colName;

    @FXML
    private TableColumn<ItemTM, String> colPrice;

    @FXML
    private TableColumn<ItemTM, Integer> colQuantity;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblItemId;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtItemQty;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();

        if (itemId != null) {
            // Confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
//                boolean isDeleted = itemModel.deleteItem(itemId);
//
//                if (isDeleted) {
//                    new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully!").show();
//                    refreshPage();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Failed to delete item!").show();
//                }
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    private void loadTableData() throws SQLException {
//        ArrayList<ItemDTO> itemDTOS = itemModel.getAllItems();
//        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();
//
//        for (ItemDTO itemDTO : itemDTOS) {
//            ItemTM itemTM = new ItemTM(
//                    itemDTO.getItemId(),
//                    itemDTO.getName(),
//                    itemDTO.getQuantity(),
//                    itemDTO.getPrice()
//            );
//
//            itemTMS.add(itemTM);
//        }
//
//        tblItem.setItems(itemTMS);
    }

    private void loadNextItemId() throws SQLException {
//        String nextItemId = itemModel.getNextItemId();
//        lblItemId.setText(nextItemId);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();
        String name = txtItemName.getText();
        String quantityString = txtItemQty.getText();
        String priceString = txtItemPrice.getText();

        String quantityPattern = "^[0-9]+$";
        String pricePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name != null;
        boolean isValidQuantity = quantityString.matches(quantityPattern);
        boolean isValidPrice = priceString.matches(pricePattern);

        System.out.println(isValidQuantity + " / " + quantityString);

        resetStyles();

        if (!isValidName) {
            txtItemName.setStyle(txtItemName.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidQuantity) {
            txtItemQty.setStyle(txtItemQty.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidPrice) {
            txtItemPrice.setStyle(txtItemPrice.getStyle() + "; -fx-border-color: red;");
        }

        if (isValidName && isValidQuantity && isValidPrice) {
            resetStyles();

            int quantity = Integer.parseInt(quantityString);
            double price = Double.parseDouble(priceString);

//            ItemDTO itemDTO = new ItemDTO(itemId, name, quantity, price);
//
//            boolean isSaved = itemModel.saveItem(itemDTO);
//
//            if (isSaved) {
//                new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!").show();
//                refreshPage();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to save item!").show();
//            }
        }
    }

    private void refreshPage() throws SQLException {
        loadNextItemId();
        loadTableData();
        resetStyles();

        txtItemName.setText("");
        txtItemQty.setText("");
        txtItemPrice.setText("");
        tblItem.getSelectionModel().clearSelection();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void resetStyles() {
        txtItemName.setStyle(txtItemName.getStyle() + "; -fx-border-color: #7367F0;");
        txtItemQty.setStyle(txtItemQty.getStyle() + "; -fx-border-color: #7367F0;");
        txtItemPrice.setStyle(txtItemPrice.getStyle() + "; -fx-border-color: #7367F0;");
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();
        String name = txtItemName.getText();
        String quantityString = txtItemQty.getText();
        String priceString = txtItemPrice.getText();

        String quantityPattern = "^[0-9]+$";
        String pricePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name != null;
        boolean isValidQuantity = quantityString.matches(quantityPattern);
        boolean isValidPrice = priceString.matches(pricePattern);

        resetStyles();

        if (!isValidName) {
            txtItemName.setStyle(txtItemName.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidQuantity) {
            txtItemQty.setStyle(txtItemQty.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidPrice) {
            txtItemPrice.setStyle(txtItemPrice.getStyle() + "; -fx-border-color: red;");
        }

        if (isValidName && isValidQuantity && isValidPrice) {
            resetStyles();

            int quantity = Integer.parseInt(quantityString);
            double price = Double.parseDouble(priceString);

//            ItemDTO updatedItemDTO = new ItemDTO(itemId, name, quantity, price);
//
//            boolean isSaved = itemModel.updateItem(updatedItemDTO);
//
//            if (isSaved) {
//                new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
//                refreshPage();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to update item!").show();
//            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            refreshPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ItemTM itemTM = tblItem.getSelectionModel().getSelectedItem();
        if (itemTM != null) {
            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

            lblItemId.setText(itemTM.getItemId());
            txtItemName.setText(itemTM.getName());
            txtItemQty.setText(String.valueOf(itemTM.getQuantity()));
            txtItemPrice.setText(String.valueOf(itemTM.getPrice()));
        }
    }
}
