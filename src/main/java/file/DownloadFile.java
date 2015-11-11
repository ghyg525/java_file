package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 下载文件
 * @author YangJie
 * @createTime 2014年12月31日 下午5:52:59
 */
public class DownloadFile {

	
	/**
	 * 下载文件
	 * @param url 网络文件地址
	 * @param file 本地文件地址
	 * @throws Exception
	 */
	public static void downloadFile(String url, File file) throws Exception{

		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection(); // 创建http链接
		
		conn.setRequestMethod("GET"); 	// 设置请求方式为"GET"
		conn.setConnectTimeout(5 * 1000); // 超时响应时间为5秒
		
		InputStream input = conn.getInputStream();	// 通过网络连接获取输入流
		FileOutputStream output = new FileOutputStream(file);// 通过新文件创建输出流
		
		int length;		// 复制文件
		byte[] data = new byte[64];
		while ((length=input.read(data)) > 0) {
			output.write(data, 0, length);
		}
		
		input.close();	// 关闭资源
		output.close();
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String url = "http://www.baidu.com/img/bdlogo.png";	// 网络文件地址
		
		String filePath = "c:/temp";	// 本地文件目录
		String fileName = System.currentTimeMillis() + url.substring(url.lastIndexOf("."), url.length()); // 本地文件名称
		
		File file = CreateFile.createFile(filePath, fileName); // 创建本地文件
		
		downloadFile(url, file); // 下载文件
		
		System.out.println("成功下载文件: "+url);
		System.out.println("保存路径为: "+file);
		
	}
	
}
