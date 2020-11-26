package com.libraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String ISBN;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private float price;
    @NotNull
    private long page;
    @NotNull
    private int availability;




}
