package batch.transaction.module.event;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Event {

    private String id;

    private String eventData;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    private LocalDateTime createAt;

    private String status;

    public void updateStatus(EventStatus eventStatus){
        this.eventStatus = eventStatus;
        this.status = eventStatus.getToString();
    }
}
