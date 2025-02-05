package batch.transaction.module.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Event {

    private String id;

    private String eventData;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;
}
