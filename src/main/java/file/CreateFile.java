package file;

import java.io.File;
import java.io.IOException;

/**
 * 创建文件及目录
 * @author YangJie
 * @createTime 2014年12月31日 下午5:52:37
 */
public class CreateFile {

	
	/**
	 * 创建文件及目录
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static File createFile(String filePath, String fileName) throws IOException{
		
		// 创建目录
		File dir = new File(filePath);
		if (!dir.exists()) {	// 如果目录不存在, 创建
			dir.mkdirs();
		}
		
		// 创建文件
		if(fileName != null){
			File file = new File(dir, fileName);
			if (!file.exists()) {	// 如果文件不存在, 创建
				file.createNewFile();	// 创建文件
			}
			return file;
		}
		
		// 只创建目录
		return dir;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		createFile("c:/temp", "test.test");
		
//		createFile("c:/temp", null);	// 只创建目录

	}

}
