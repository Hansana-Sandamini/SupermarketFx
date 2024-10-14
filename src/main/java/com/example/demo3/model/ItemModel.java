package com.example.demo3.model;

import com.example.demo3.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public String getNextCustomerID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT item_id FROM Customer ORDER BY customer_id DESC LIMIT  1");
        if (resultSet.next()) {
            String string = resultSet.getString(1); // C001
            String substring = string.substring(1); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIIndex = lastIdIndex + 1; // 2
            String newId = String.format("C%03d", nextIIndex); // C002
            return newId;
        }
        return "C001";
    }


    public ArrayList<String> getAllItemIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select item_id from Item");
        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()){
            itemIds.add(rst.getString(1));
        }

        return itemIds;
    }
}