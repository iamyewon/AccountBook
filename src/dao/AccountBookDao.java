package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dto.AccountBookDto;
import file.FileProc;
import singleton.Singleton;
import util.DateUtil;

public class AccountBookDao {
	Scanner sc = new Scanner(System.in);
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	AccountBookDto aDto = new AccountBookDto();
	Singleton st = Singleton.getInstance1();
	//FileProc fp = new FileProc("saveAccountBook");
	
	
	public AccountBookDao() {
		super();
		// init();
		// fp.read();
	}



	// 테스트용 
	/*
	public void init() {
		st.accountList.add(new AccountBookDto(LocalDate.now(), "쇼핑", "지출", 20000, "티셔츠"));
		st.accountList.add(new AccountBookDto(LocalDate.of(2022,6,5), "쇼핑", "지출", 57800, "바지"));
		st.accountList.add(new AccountBookDto(LocalDate.of(2022,6,6), "식비", "지출", 12300, "분식"));
		st.accountList.add(new AccountBookDto(LocalDate.of(2023,4,6), "월급", "수입", 4000000, "월급"));
		st.accountList.add(new AccountBookDto(LocalDate.of(2023,5,6), "월급", "수입", 3000000, "월급"));
		st.accountList.add(new AccountBookDto(LocalDate.of(2023,6,6), "월급", "수입", 3000000, "월급"));
		st.accountList.add(new AccountBookDto(LocalDate.of(2023,6,7), "식비", "지출", 15000, "치킨"));
		
		printList();
	}
	*/
		

	
	
	// 가계부 내역 저장
	public void insert() {
		// TODO : validation - 사용자 입력값 검증 
		System.out.println("가계부를 작성합니다.");

		System.out.println("날짜 입력(yyyyMMdd) >>> ");
		String date = sc.next();

		System.out.println("용도 입력(간단하게) >>> ");
		String purpose = sc.next();

		System.out.println("수입/지출 선택 >>> ");
		String inOut = sc.next();

		System.out.println("금액 입력(숫자만 입력) >>> ");
		int money = sc.nextInt();

		System.out.println("상세 내역 입력 >>> ");
		String detail = sc.next();
		
		// String -> LocalDate
		LocalDate localDate = DateUtil.stringToLocalDate(date);
		
		
		st.accountList.add(new AccountBookDto(localDate, purpose, inOut, money, detail ));
		printList();
		

	}

	// 가계부 내역 삭제
	public void delete() {
		printList();// 삭제를 위한 목록 보여주기
		
		
		System.out.println("삭제할 내역의 번호를 입력해주세요 >>> ");
		int number = sc.nextInt();
		
		if(number > st.accountList.size() || number<=0) {
			System.out.println("올바른 가계부 내역의 번호를 입력해주세요.");
		}else if(number > 0 && number <= st.accountList.size()) {
			st.accountList.remove(number-1);
			System.out.println("삭제 완료");
		}
	}

	// 가계부 내역 수정
	public void update() {		
		// 수정을 위한 목록 보여주기 
		printList();
		
		System.out.println("수정할 내역의 번호를 입력해주세요 >>> ");
		int number = sc.nextInt();
		
		AccountBookDto updateItem = st.accountList.get(number-1);
		System.out.println(updateItem);
		
		System.out.println("용도 변경 >>> ");
		String purpose = sc.next();
		System.out.println("수입/지출 변경 >>> ");
		String inOut = sc.next();
		System.out.println("금액 변경 >>> ");
		int money = sc.nextInt();
		System.out.println("상세 내역 변경 >>> ");
		String detail = "";
		try {
			detail = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updateItem.update(purpose, inOut, money, detail);
		System.out.println("수정 완료");
	}

	// 가계부 내역 검색 
	public void search() {
		// 월별조회, 기간별조회, 용도별검색 
		System.out.println("===== 검색 메뉴 =====");
		System.out.println("1. 월별 조회 ");
		System.out.println("2. 기간별 조회 ");
		System.out.println("3. 용도별 검색 ");
		
		System.out.println("메뉴 번호 선택 >>> ");
		int menu = sc.nextInt();
		
		switch(menu) {
			case 1:
				searchByMonth();
				break;
			case 2:
				searchByPeriod();
				break;
			case 3:
				searchByPurpose();
				break;
		}
	}
	
	// (년도+)월별 검색 
	public void searchByMonth() {
		System.out.println("조회할 년도 입력 >>> ");
		int year = sc.nextInt();
		
		System.out.println("조회할 월 입력 >>> ");
		int month = sc.nextInt();
		
		boolean isAccord = false;
		
		for(AccountBookDto item : st.accountList) {
			if(item.getDate().getYear()== year) {
				if(item.getDate().getMonthValue() == month) {
					System.out.println(item);
					isAccord = true;
				}
			}
			
		}
		if(!isAccord) {
			System.out.println("일치하는 기간의 가계부 내역이 없습니다.");
		}

		
	}
	
	// 지정 기간별 검색 
	public void searchByPeriod() {
		System.out.println("조회 시작일 >>> ");
		String from = sc.next();
		
		System.out.println("조회 종료일 >>> ");
		String end = sc.next();
		
		LocalDate fromDate = DateUtil.stringToLocalDate(from);
		LocalDate endDate = DateUtil.stringToLocalDate(end);
		
		boolean isAccord = false;
		for(AccountBookDto item : st.accountList) {
			LocalDate itemDate = item.getDate();
			
			if(itemDate.isAfter(fromDate.minusDays(1)) && itemDate.isBefore(endDate.plusDays(1))) {
				System.out.println(item);
				isAccord = true;
			}
		}
		if(!isAccord) {
			System.out.println("일치하는 기간의 가계부 내역이 없습니다.");
		}
		
	}
	
	// 간단한 용도별 검색
	public void searchByPurpose(){
		System.out.println("검색 할 단어(용도 검색) >>> ");
		sc.nextLine();
		String purpose = sc.nextLine();
		System.out.println(purpose);
		
		boolean isAccord = false;
		for(AccountBookDto item : st.accountList) {
			if(item.getPurpose().contains(purpose)) {
				System.out.println(item);
				isAccord = true;
			}
		}
		if(!isAccord) {
			System.out.println("일치하는 가계부 내역이 없습니다.");
		}
	}
	
	
	// 전체 내역 출력
	public void printList() {
		if (st.accountList == null) return;
		for(int i=0; i<st.accountList.size(); i++) {
			//.toString() 생략해도 호출됨.
			System.out.println(i+1 + "번 = " + st.accountList.get(i));
		}
	}

}
