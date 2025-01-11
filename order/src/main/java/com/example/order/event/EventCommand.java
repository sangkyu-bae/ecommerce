package com.example.order.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.SelfValidating;

@Builder
@Getter @EqualsAndHashCode(callSuper = true)
public class EventCommand extends SelfValidating<EventCommand> {

}
