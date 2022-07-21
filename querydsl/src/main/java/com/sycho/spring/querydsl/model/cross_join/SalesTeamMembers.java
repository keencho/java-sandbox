package com.sycho.spring.querydsl.model.cross_join;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SALES_TEAM_MEMBERS")
public class SalesTeamMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", foreignKey = @ForeignKey(name = "FK_SALES_TEAM_MEMBERS___COMPANY"))
    private Company company;

    public SalesTeamMembers(String name, Company company) {
        this.name = name;
        this.company = company;
    }
}
