package batch.transaction.module.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {

    INIT("이벤트 생성 대기","INIT"),
    FAIL("이벤트 생성 실패","FAIL"),

    FAIL_WORK("이벤트 이전 동작 실패","FAIL_WORK"),
    SUCCESS("이벤트 생성 완료","SUCCESS")
    ;

    private final String status;
    private final String toString;
}
