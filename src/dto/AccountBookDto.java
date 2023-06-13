package dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import singleton.Singleton;

// db에 필요한 데이터 모음 
public class AccountBookDto {
	
	private LocalDate date;
	private String purpose;
	private String inOut;
	private int money;	
	private String detail;
	
//////////////////////////////////////////
	
	public AccountBookDto() {
		super();
	}
	
	// insert 용 
	public AccountBookDto(LocalDate date, String purpose, String inOut, int money, String detail) {
		super();
		this.date = date;
		this.purpose = purpose;
		this.inOut = inOut;
		this.money = money;
		this.detail = detail;
	}

//////////////////////////////////////////
	

	public String getPurpose() {
		return purpose;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


//////////////////////////////////////////
	
	// update 용 
	public void update(String purpose, String inOut, int money, String detail) {
		this.purpose = purpose;
		this.inOut = inOut;
		this.money = money;
		this.detail = detail;
	}

//////////////////////////////////////////
	@Override
	public String toString() {
		return "[date=" + date + ", purpose=" + purpose + ", inOut=" + inOut + ", money=" + money
				+ ", detail=" + detail + "]";
	}
	
	// 파일 저장용 출력
	public String writeFile() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formattedDate = date.format(formatter);
		return formattedDate + "//" + purpose + "//" + inOut + "//" + money + "//" + detail;
	}
	
	
	
	

	
	

	
	
	
	
	
}
