package com.github.moregorenine.ch04_jpa_start2;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER_CH04",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "NAME_AGE_UNIQUE",
                        columnNames = {"NAME", "AGE"})
        })
@Data
public class MemberCh04 {

    @Id
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String temp;

}
