package com.shoppingproject.demo.shoppingDAO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDAO {
    @Id
    @GeneratedValue
    private int id;
    @ManyToMany(mappedBy = "orderDAOList")
    private List<ItemDAO>  itemDAOList;
}
