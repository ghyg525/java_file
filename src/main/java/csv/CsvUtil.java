package csv;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class CsvUtil {
	
	/**
	 * 解析
	 * @author YangJie [2016年10月26日 上午10:42:56]
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static List<List<String>> pares(String fileName) throws Exception{
		CSVParser parser = CSVParser.parse(new File(fileName), Charset.defaultCharset(), CSVFormat.DEFAULT);
		List<List<String>> resultList = new ArrayList<List<String>>();
		for(CSVRecord record : parser.getRecords()){
			List<String> recordList = new ArrayList<String>();
			for(int i=0; i<record.size(); i++){
				recordList.add(record.get(i));
			}
			resultList.add(recordList);
		}
		parser.close();
		return resultList;
	}
	
	/**
	 * 创建
	 * @author YangJie [2016年10月26日 上午10:46:51]
	 * @param fileName
	 * @param contentList
	 * @return
	 * @throws Exception
	 */
	public static boolean create(String fileName, List<List<String>> contentList) throws Exception{
		CSVPrinter printer = new CSVPrinter(new FileWriter(fileName), CSVFormat.DEFAULT);
		for(List<String> recordList : contentList){
			for(String str : recordList){
				printer.print(str);
			}
			printer.println();
		}
		printer.flush();
		printer.close();
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
		String path = ClassLoader.getSystemResource("").getPath();
		System.out.println(path);
		System.out.println(CsvUtil.pares(path + "1.csv"));
		
		CsvUtil.create(path + "2.csv", CsvUtil.pares(path + "1.csv"));
	}
	
}
