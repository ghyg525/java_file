package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * 读取文件
 * @author YangJie
 * @createTime 2014年12月31日 下午5:53:17
 */
public class ReaderFile {
	
	private static FileReader fReader;
	private static BufferedReader bReader;
	

	public static void main(String[] args) {

		try {
			
			fReader = new FileReader("src/file/data.txt");
			bReader = new BufferedReader(fReader);

			System.out.println(bReader.readLine());
			System.out.println(bReader.readLine());
			
			bReader.close();
			fReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
