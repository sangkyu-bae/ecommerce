package org.example.event;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CheckRegisteredProductEvent {

    private String createOrderId;


    private boolean isSuccess;

    private String checkRegisteredProductIdAndAmount;

    private Long userId;

    private String productAggregate;

    private List<ProductRequestCreateCommand> productRequestCreateEvents;


}
