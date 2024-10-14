package com.example.demo3.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String itemId;
    private String name;
    private int quantity;
    private double price;
}