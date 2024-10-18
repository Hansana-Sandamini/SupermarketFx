package com.example.demo3.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter                 // @Getter: Automatically generates getter methods for all fields
@Setter                 // @Setter: Automatically generates setter methods for all fields
@AllArgsConstructor     // @AllArgsConstructor: Generates a constructor with all fields as parameters
@NoArgsConstructor      // @NoArgsConstructor: Generates a no-argument constructor
@ToString               // @ToString: Automatically generates a string representation of the object
public class OrderDTO {

    private String orderId;
    private String customerId;
    private Date orderDate;

    // @orderDetailsDTOS: A list of OrderDetailsDTO objects, each representing an item in the order
    private ArrayList<OrderDetailsDTO> orderDetailsDTOS;

}