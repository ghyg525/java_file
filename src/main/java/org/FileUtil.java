package org;

import java.io.File;
import java.io.IOException;

/**
 * 文件压缩工具类
 * @author YangJie [2018年8月9日 下午5:25:24]
 */
public class FileUtil {

	/**
	 * 创建文件
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

	/**  
	 *  根据路径删除指定的目录或文件，无论存在与否  
	 *@param sPath  要删除的目录或文件  
	 *@return 删除成功返回 true，否则返回 false。  
	 */  
	public static boolean deleteFolder(String sPath) {   
	    boolean flag = false;   
	    File file = new File(sPath);   
	    // 判断目录或文件是否存在   
	    if (!file.exists()) {  // 不存在返回 false   
	        return flag;   
	    } else {   
	        // 判断是否为文件   
	        if (file.isFile()) {  // 为文件时调用删除文件方法   
	            return deleteFile(sPath);   
	        } else {  // 为目录时调用删除目录方法   
	            return deleteDirectory(sPath);   
	        }   
	    }   
	} 
	
	
	/**  
	 * 删除单个文件  
	 * @param   sPath    被删除文件的文件名  
	 * @return 单个文件删除成功返回true，否则返回false  
	 */  
	public static boolean deleteFile(String sPath) {   
	    boolean flag = false;   
	    File file = new File(sPath);   
	    // 路径为文件且不为空则进行删除   
	    if (file.isFile() && file.exists()) {   
	        file.delete();   
	        flag = true;   
	    }   
	    return flag;   
	}  

	/**  
	 * 删除目录（文件夹）以及目录下的文件  
	 * @param   sPath 被删除目录的文件路径  
	 * @return  目录删除成功返回true，否则返回false  
	 */  
	public static boolean deleteDirectory(String sPath) {   
	    File dirFile = new File(sPath);   
	    //如果dir对应的文件不存在，或者不是一个目录，则退出   
	    if (!dirFile.exists() || !dirFile.isDirectory()) {   
	        return false;   
	    }   
	    boolean flag = true;   
	    //删除文件夹下的所有文件(包括子目录)   
	    File[] files = dirFile.listFiles();   
	    for (int i = 0; i < files.length; i++) {   
	        //删除子文件   
	        if (files[i].isFile()) {   
	            flag = deleteFile(files[i].getAbsolutePath());   
	            if (!flag) break;   
	        } //删除子目录   
	        else {   
	            flag = deleteDirectory(files[i].getAbsolutePath());   
	            if (!flag) break;   
	        }   
	    }   
	    if (!flag) return false;   
	    //删除当前目录   
	    if (dirFile.delete()) {   
	        return true;   
	    } else {   
	        return false;   
	    }   
	}  
	
}
