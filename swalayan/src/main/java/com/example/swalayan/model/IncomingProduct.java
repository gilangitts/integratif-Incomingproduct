package com.example.swalayan.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "incoming_product")
public class IncomingProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private int quantity;
    private Date date;

}
