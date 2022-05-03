package mobile.gachonapp.crawling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LocalDate {

    @Test
    void 정각_시간일시_1분을_뺀다() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2022,5,3,00,00);

        LocalDateTime changedTime = null;
        //when
        if(localDateTime.getMinute() == 0 & localDateTime.getHour() == 0) {
            changedTime = localDateTime.minusMinutes(1);
        }

        //then
        Assertions.assertEquals(changedTime,LocalDateTime.of(2022,5,2,23,59));
    }
}
