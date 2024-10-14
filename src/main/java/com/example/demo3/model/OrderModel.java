package com.example.demo3.model;


import com.example.demo3.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public String getNextOrderId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select order_id from Orders order by order_id desc limit 1");

        if (resultSet.next()) {
            String string = resultSet.getString(1); // C001
            String substring = string.substring(1); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIIndex = lastIdIndex + 1; // 2
            String newId = String.format("O%03d", nextIIndex); // C002
            return newId;
        }
        return "O001";
    }
}