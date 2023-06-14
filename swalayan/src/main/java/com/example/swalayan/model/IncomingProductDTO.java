package com.example.swalayan.model;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingProductDTO {
    private Long id;
    private int quantity;
    private Date date;
    private Long id_product;

}
