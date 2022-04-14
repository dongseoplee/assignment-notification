package mobile.gachonapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GachonappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GachonappApplication.class, args);
	}

//TODO db 쿼리 작성
//TODO 파라미터로 세션 넘어 왔을때 크롤링 가능 여부(과목 과 assignment 필터링)

//TODO 크롤링 한 데이터 db에 어떻게 연결,,,,
//TODO 기간지난 과제 여부?
//TODO 과목선택 api(update)
//TODO 과제제출여부 api(update)

//TODO 	validation 처리
//TODO 예외발생시 처리 후 response 주기
//TODO TestCode 작성

}
