package mobile.gachonapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GachonappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GachonappApplication.class, args);
	}

//TODO db 쿼리 작성
//TODO 크롤링 한 데이터 db에 어떻게 연결 (dto를 사용해야하나 아니면 도메인을 바로 넘겨야하나??  하고 영속)
//TODO 과목선택 api(update)
//TODO 과제제출여부 api(update) -
//TODO 미제출 기본페이지     제출한 숙제 OR  (시간이 지난)  따로 보여준다.
	
//TODO 쿼리 공부 및 조인 , 캐스케이트 , 변경감지
//TODO 도메인에서 비즈니스 로직 처리 고민


//TODO 추가 사항
//TODO 자동로그인
//TODO TestCode 작성
//TODO 기간지난 과제 여부? -> 과제 제출기한 과 현재시간을 비교해서 넘어갔으면 db에 저장

}
