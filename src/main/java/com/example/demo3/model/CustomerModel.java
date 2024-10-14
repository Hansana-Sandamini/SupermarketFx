package com.example.demo3.model;

import com.example.demo3.Util.CrudUtil;
import com.example.demo3.db.DBConnection;
import com.example.demo3.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public String getNextCustomerID() throws SQLException {
        // C001
        // C002
        //Connection connection = DBConnection.getInstance().getConnection();
        //String sql = "SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT  1";
        //PreparedStatement pst = connection.prepareStatement(sql);

        //ResultSet rst = pst.executeQuery();
        ResultSet resultSet = CrudUtil.execute("SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT  1");
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

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        //Connection connection = DBConnection.getInstance().getConnection();
        //String sql = "INSERT INTO Customer VALUES (?,?,?,?,?)";
        //PreparedStatement pst = connection.prepareStatement(sql);

        //pst.setString(1, customerDTO.getId());
        //pst.setString(2, customerDTO.getName());
        //pst.setString(3, customerDTO.getNic());
        //pst.setString(4, customerDTO.getEmail());
        //pst.setString(5, customerDTO.getPhone());

        //int i = pst.executeUpdate();

        //return i > 0;

        boolean isSaved = CrudUtil.execute(
                "INSERT INTO Customer VALUES (?,?,?,?,?)",
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone()
        );
        return isSaved;
    }

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return CrudUtil.execute(
                "update Customer set name=?,nic=?,email=?,phone=? where customer_id = ?",
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone(),
                customerDTO.getId()
        );
    }

    public boolean deleteCustomer(String customerId) throws SQLException {
        return CrudUtil.execute("delete from Customer where customer_id=?",customerId);
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customer_id from Customer");
        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()){
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    public CustomerDTO findByCustomerId(String selectedCustomerId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from Customer where customer_id=?"
                ,selectedCustomerId
        );

        if (rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );

            return customerDTO;
        }
        return null;
    }
}
