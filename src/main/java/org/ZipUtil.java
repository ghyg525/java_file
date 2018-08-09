package org;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 * @author YangJie [2018年8月9日 下午5:25:24]
 */
public class ZipUtil {

	/**
	 * 将文件夹打包成zip文件
	 * @param path 目标文件夹
	 * @throws Exception
	 */
	public static String zip(String path) throws Exception {
		return zip(path, path + ".zip");
	}
	
	/**
	 * 将文件夹打包成zip文件
	 * @param path 目标文件夹
	 * @param name zip文件名
	 * @throws Exception
	 */
	public static String zip(String path, String name) throws Exception {
		String dir = path.substring(0, path.lastIndexOf("/")); // zip文件目录
		File zipFile = new File(name);
		zipFile.createNewFile(); // 创建zip文件
		FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
		ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream, Charset.forName("GBK"));
		zip(new File(path), "", zipOutputStream); // 递归压缩文件
		zipOutputStream.close();
		fileOutputStream.close();
		return dir + File.separator + name;
	}

	/**
	 * 向zip包中递归写入文件
	 * @param file 压缩目录或文件
	 * @param parent 上级目录
	 * @param zipOutputStream zip输出流
	 * @throws Exception
	 */
	private static void zip(File file, String parent, ZipOutputStream zipOutputStream) throws Exception {
		if (file.isDirectory()) { // 如果是文件夹
			parent += file.getName() + File.separator;
			File[] files = file.listFiles();
			for (File f : files) {
				zip(f, parent, zipOutputStream);
			}
		} else { // 如果是文件
			FileInputStream fileInputStream = new FileInputStream(file);
			ZipEntry zipEntry = new ZipEntry(parent + file.getName());
			zipOutputStream.putNextEntry(zipEntry);
			byte[] content = new byte[1024]; // 缓冲数组
			int length; // 将文件写入zip包
			while ((length = fileInputStream.read(content)) != -1) {
				zipOutputStream.write(content, 0, length);
				zipOutputStream.flush();
			}
			fileInputStream.close();
		}
	}

	 
	/**
	 * 解压缩
	 * @param file 压缩文件
	 * @throws Exception
	 */
    public static void unzip(String file) throws Exception {
    	unzip(new File(file)); // 默认解压到同目录
    }
    
    /**
     * 解压缩
     * @param file 压缩文件
     * @throws Exception
     */
    public static void unzip(File file) throws Exception {
    	unzip(file, file.getParent()); // 默认解压到同目录
    }
    
    /**
     * 解压缩
     * @param file 压缩文件
     * @param path 指定解压目录
     * @throws Exception
     */
    public static void unzip(File file, String path) throws Exception {
    	ZipFile zipFile = new ZipFile(file, Charset.forName("GBK"));
    	Enumeration<? extends ZipEntry> entrys = zipFile.entries();
    	while (entrys.hasMoreElements()) {
    		ZipEntry zipEntry = entrys.nextElement();
    		String fileName = zipEntry.getName();
    		System.out.println(fileName);
    		if (zipEntry.isDirectory()) {
    			new File(path + fileName).mkdirs();
    		}else {
    			dir(path, fileName); // 创建文件上级目录
    			fileName = fileName.contains("/") ? fileName.substring(fileName.lastIndexOf("/") + 1) : fileName;
    			File outputFile = new File(path + fileName);
    			OutputStream outputStream = new FileOutputStream(outputFile);
    			InputStream inputStream = zipFile.getInputStream(zipEntry);
    			int len;
    			byte[] buffer = new byte[10240];
    			while (-1 != (len = inputStream.read(buffer))) {
    				outputStream.write(buffer, 0, len);
    			}
    			outputStream.close();
    			inputStream.close();
    		}
    		zipEntry.clone();
    	}
    	zipFile.close();
    }
	
    /**
     * 创建解压目录
     * @param path
     * @param name
     */
	private static void dir(String path, String name) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		if(Objects.nonNull(name) && !name.trim().isEmpty()) {
			name = name.replace('\\', '/');
			name = name.substring(0, name.lastIndexOf("/"));
			for(String s : name.split("/")) {
				file = new File(path + File.separator + s);
				if (!file.exists()) {
					file.mkdir();
				}
				path += File.separator + s;
			}
		}
	}

}
