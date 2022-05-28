package mobile.gachonapp.crawling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Test
    void 삭제_리스트_비교() {
        List<String> oldList = Arrays.asList("1","2","3","4","5");
        List<String> newList = Arrays.asList("1","2","3","6","7","8");

        List<String> resultOldList = oldList.stream()
                .filter(old -> newList.stream().noneMatch(Predicate.isEqual(old)))
                .collect(Collectors.toList());

        System.out.println("resultOldList = " + resultOldList); //삭제
    }

    @Test
    void 추가_리스트_비교() {
        List<String> find = Arrays.asList("1","2","3","4","5");
        List<String> cral = Arrays.asList("1","2","3","6","7","8");

        List<String> resultNewList = cral.stream()
                .filter(list -> find.stream().noneMatch(Predicate.isEqual(list)))
                .collect(Collectors.toList());

        System.out.println("resultOldList = " + resultNewList); //삭제
    }
}
