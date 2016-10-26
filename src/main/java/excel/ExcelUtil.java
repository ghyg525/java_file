package excel;

import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**
 * 生成excel文件工具类
 * @author YangJie
 * @createTime 2015年4月7日 上午11:22:52
 */
public class ExcelUtil {
	
	/**
	 * 导出excel文件, 默认只有一张表名为first
	 * @param outputStream
	 * @param dataList
	 * @return
	 * @author YangJie
	 * @throws Exception 
	 * @createTime 2015年4月7日 上午11:29:51
	 */
	public static OutputStream export(OutputStream outputStream, List<List<Object>> dataList) throws Exception {
		// 创建工作簿(可读可写)
		WritableWorkbook book = Workbook.createWorkbook(outputStream);
		// 生成名为"first"的工作表，参数0表示这是第一页
		WritableSheet sheet = book.createSheet("first", 0);
		//填充文本  单元格位置是[(0,0)=(A,1)|(1,2)=(B,3)]
		for (int i = 0; i < dataList.size(); i++) {
			List<Object> list = dataList.get(i);
			for (int j = 0; j < list.size(); j++) {
				sheet.addCell(new Label(j, i, list.get(j)==null ? "" :list.get(j).toString()));
			}
		}
		book.write();
		book.close();
		return outputStream;
	}
	
}
