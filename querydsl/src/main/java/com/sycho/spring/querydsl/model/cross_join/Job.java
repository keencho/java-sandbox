package com.sycho.spring.querydsl.model.cross_join;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOB")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime dtCreatedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEV_TEAM_MEMBERS_ID", foreignKey = @ForeignKey(name = "FK_JOB___DEV_TEAM_MEMBERS"))
    private DevTeamMember devTeamMembers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALES_TEAM_MEMBERS_ID", foreignKey = @ForeignKey(name = "FK_JOB___SALES_TEAM_MEMBERS"))
    private SalesTeamMembers salesTeamMembers;

    public Job(String name, DevTeamMember devTeamMembers, SalesTeamMembers salesTeamMembers) {
        if (devTeamMembers == null && salesTeamMembers == null) {
            throw new RuntimeException("Work must be assigned to one person.");
        }
        this.name = name;
        this.devTeamMembers = devTeamMembers;
        this.salesTeamMembers = salesTeamMembers;
    }
}
