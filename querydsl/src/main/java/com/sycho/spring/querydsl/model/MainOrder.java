package com.sycho.spring.querydsl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"activeSubOrderList", "deActivatedSubOrderList", "fromNameLikeCList"})
@Table(name = "main_order")
public class MainOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate orderDate = LocalDate.now();

    @OneToMany(mappedBy = "mainOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "active = true")
    private List<SubOrder> activeSubOrderList;

    @OneToMany(mappedBy = "mainOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "active = false")
    private List<SubOrder> deActivatedSubOrderList;

    @OneToMany(mappedBy = "mainOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "from_name LIKE 'C%'")
    private List<SubOrder> fromNameLikeCList;

}
