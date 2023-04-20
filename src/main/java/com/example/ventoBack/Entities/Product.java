package com.example.springApp.Entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "name can not be null")
    private String eventName;
    @NotNull(message = "description can not be null")
    private String description;
    @NotNull(message = "image can not be null")
    private String eventImage;
    @NotNull(message = "price can not be null")
    private String rate;
    private String category;
    private String time;
    private String location;

}
