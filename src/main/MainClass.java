package main;

import java.util.Scanner;

import dao.AccountBookDao;
import file.FileProc;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountBookDao aDao = new AccountBookDao(); 
		FileProc fp = new FileProc("AccountBook");
		fp.read();
		
		
		boolean onOff = true;
		while(onOff) {
			System.out.println("===== 메뉴 =====");
			System.out.println("1. 가계부 내역 작성 ");
			System.out.println("2. 가계부 내역 삭제 ");
			System.out.println("3. 가계부 내역 수정 ");
			System.out.println("4. 가계부 내역 검색 ");
			System.out.println("5. 가계부 내역 출력 ");
			System.out.println("6. 가계부 파일 저장 ");
			System.out.println("7. 가계부 종료 ");
			System.out.println("=== 메뉴 번호를 선택하세요 ===");
			int menu = sc.nextInt();
			
			switch(menu) {
				case 1:
					aDao.insert();
					break;
				case 2:
					aDao.delete();
					break;
				case 3:
					aDao.update();
					break;
				case 4:
					aDao.search();
					break;
				case 5:
					aDao.printList();
					break;
				case 6:
					fp.writeFile();
					break;
				case 7:
					System.out.println("가계부 프로그램을 종료합니다.");
					onOff = false;
					break;
			}
		}	
	}
}
