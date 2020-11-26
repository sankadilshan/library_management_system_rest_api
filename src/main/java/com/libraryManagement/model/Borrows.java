package com.libraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrows")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ISBN;
    private int userId;
    private Date checkoutDate= new Date();
    private Date returnDate;
    private int fine=0;
    private String publisherId;

}
