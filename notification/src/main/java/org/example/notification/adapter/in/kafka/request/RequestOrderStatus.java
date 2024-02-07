package org.example.notification.adapter.in.kafka.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RequestOrderStatus {

    private long memberId;

    private int orderStatus;

}
