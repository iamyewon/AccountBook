package singleton;

import java.util.ArrayList;

import dto.AccountBookDto;

public class Singleton {
	
	private static Singleton singleton = null;
	
	public ArrayList<AccountBookDto> accountList = null;
	
	private Singleton() {
		accountList = new ArrayList<AccountBookDto>();
	}
	
	public static Singleton getInstance1() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
}
