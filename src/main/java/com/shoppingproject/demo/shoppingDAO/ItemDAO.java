package com.shoppingproject.demo.shoppingDAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDAO {
    @Id
    @GeneratedValue
    private int id;
    private String itemName;
    private double itemPrice;
    private String itemUnit;
    private String itemImgUrl;

    @JsonIgnore
    @ManyToMany
    private List<OrderDAO> orderDAOList;
}
