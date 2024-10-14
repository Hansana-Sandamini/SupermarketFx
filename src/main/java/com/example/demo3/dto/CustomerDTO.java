package com.example.demo3.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerDTO {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;
}
