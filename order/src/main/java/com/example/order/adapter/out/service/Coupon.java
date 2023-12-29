package com.example.order.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coupon {

    private Long id;

    private Long createAdminId;

    private int salePercent;

    private String name;

    private LocalDateTime creatAt;

    private String aggregateIdentifier;
}
