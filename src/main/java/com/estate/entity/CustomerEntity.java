package com.estate.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity extends AbstractEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "demand")
    private String demand;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "status")
    private Integer status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignment_customer",
            joinColumns = @JoinColumn(name = "customer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staff_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<TransactionEntity> transactions = new ArrayList<>();

}
