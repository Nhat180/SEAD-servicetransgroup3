package com.example.servicetransgroup3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTrans {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long serviceTransId;

    private String customer;

    private String address;

    private String mechanic;

    private String serviceName;

    private Integer totalCost;

    private String startDate;

    private String endDate;
}
