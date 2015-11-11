package zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import file.CreateFile;

/**
 * 创建zip工具类
 * @author YangJie
 */
public class CreateZip {

	
	/**
	 * 将文件夹打包成zip文件
	 * @param filePath	目标文件夹
	 * @return zip文件相对路径
	 * @throws Exception 
	 */
	public static String createZip(String filePath) throws Exception {
		
		String zipDir = filePath.substring(0, filePath.lastIndexOf("/"));		// zip文件目录
		String zipName = filePath.substring(filePath.lastIndexOf("/")+1) + ".zip";		// zip文件与文件夹同名
		File zipFile = CreateFile.createFile(zipDir, zipName);	 // 创建zip文件
		
		FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        zipOutputStream.setEncoding("gbk");		// 解决中文文件名乱码问题
        
        writeZip(new File(filePath), "", zipOutputStream);	// 递归压缩文件
        
        zipOutputStream.close();
        fileOutputStream.close();
        
		return zipDir+"/"+zipName;
	}
	
	
	
	/**
	 * 向zip包中递归写入文件
	 * @param file	压缩目录或文件
	 * @param parentPath	上级目录
	 * @param zipOutputStream zip输出流	
	 * @throws Exception 
	 */
	private static void writeZip(File file, String parentPath, ZipOutputStream zipOutputStream) throws Exception {
		if(file.isDirectory()){	// 如果是文件夹
    	   parentPath += file.getName()+File.separator;
            File[] files = file.listFiles();
            for(File f : files){
            	writeZip(f, parentPath, zipOutputStream);
            }
        }else{	// 如果已经是文件
            FileInputStream fileInputStream = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(parentPath + file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            byte [] content = new byte[1024];	// 缓冲数组
            int length;		// 将文件写入zip包
            while((length = fileInputStream.read(content)) != -1){
            	zipOutputStream.write(content, 0, length);
            	zipOutputStream.flush();
            }
            fileInputStream.close();
        }
	}
	
	
	public static void main(String[] args) throws Exception {
		createZip("c:/temp");
	}
	
}
