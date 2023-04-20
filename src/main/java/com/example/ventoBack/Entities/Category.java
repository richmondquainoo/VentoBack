package com.example.springApp.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name ="event_category")
@NoArgsConstructor
public class Category {
    private String eventCategory;
    private Long id;
    private String image;

}
