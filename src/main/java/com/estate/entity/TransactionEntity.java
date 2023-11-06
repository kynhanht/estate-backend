package com.estate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "transactions")
@Getter
@Setter
public class TransactionEntity extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;


}
