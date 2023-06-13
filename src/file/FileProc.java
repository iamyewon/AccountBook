package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

import dto.AccountBookDto;
import singleton.Singleton;
import util.DateUtil;

public class FileProc {
	private File file = null;
	Singleton st = Singleton.getInstance1();
	
	// 파일 생성 
	public FileProc(String fileName) {
		file = new File("/Users/yeni/Desktop/"+fileName+".txt");
		
		try {
			if(file.createNewFile()) {
				System.out.println("파일이 생성되었습니다. ");
			}else {
				System.out.println("파일이 생성되지 않았습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	////////////////////////////////////////////////////////////
	// 파일 작성 
	public void writeFile() {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for(int i=0; i<st.accountList.size(); i++) {
				AccountBookDto item = st.accountList.get(i);
				pw.println(item.writeFile());
			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("파일에 저장되었습니다.");
	}
	
	public void read() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str = "";
			try {
				while((str = br.readLine()) != null) {
					String split[] = str.split("//");
					AccountBookDto dto = new AccountBookDto(
										DateUtil.stringToLocalDate(split[0]),
										split[1], 
										split[2], 
										Integer.parseInt(split[3]), 
										split[4]
										);
					st.accountList.add(dto);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
