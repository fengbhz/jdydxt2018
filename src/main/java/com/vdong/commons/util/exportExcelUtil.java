package com.vdong.commons.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 二维码导出工具类
 * 
 * @author lk
 * 
 */
public class exportExcelUtil {/*

	*//**
	 * 
	 * 通过传过来的数据生成Excel表格（带图片）
	 * 
	 * @param list
	 * @return Map<String,String>
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, String> exportQM(List list,String type, HttpServletRequest request, HttpServletResponse response) {
		FileOutputStream fileOut = null;
		BufferedImage bufferImg = null;
		Map remap = new HashMap();
		// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet1 = wb.createSheet("报表导出");
			sheet1.setDefaultColumnWidth(15);
			sheet1.setDefaultRowHeight((short) 500);
			// 根据类型来生成不同的excel表格
			if(type.equals("ORDER")){
				excelHandleForOrder(sheet1, list);
			}else if(type.equals("YZ")){
				excelHandleForYz(sheet1, list);
			}else if(type.equals("FRIEND")){
				excelHandleForFriend(sheet1, list);
			}else if(type.equals("MONEY")){
				excelHandleForMoney(sheet1, list);
			}else if(type.equals("COIN")){
			excelHandleForCoin(sheet1, list);
		}
			
			String realpath = request.getSession().getServletContext()
					.getRealPath("/excelUpload")
					+ "/";
			Date data = new Date();
			SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName = format2.format(data) + ".xls ";// 对文件进行重命名
			System.out.println("realpath" + realpath + fileName);
			fileOut = new FileOutputStream(realpath + fileName);
			// 写入excel文件
			wb.write(fileOut);
			System.out.println("----Excle文件已生成------");
			remap.put("path", realpath);
			remap.put("fileName", fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return remap;
	}

	public static void excelHandleForFriend(HSSFSheet sheet1,List list) {
		HSSFRow row2= sheet1.createRow((short) 0);
		HSSFCell cell1 = row2.createCell((short) 0);// 第一列
		HSSFCell cell2 = row2.createCell((short) 1);
		HSSFCell cell3 = row2.createCell((short) 2);
		HSSFCell cell4 = row2.createCell((short) 3);
		HSSFCell cell5 = row2.createCell((short) 4);
		cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入数据
/*		序号	亲友名称	手机号码	度假币余额	领取时间*/
		cell1.setCellValue("序号");
		cell2.setCellValue("亲友名称");
		cell3.setCellValue("手机号码");
		cell4.setCellValue("度假币余额");
		cell5.setCellValue("领取时间");

		if (null != list && list.size() > 0) {
			for (int i = 1; i < list.size() + 1; i++) {
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				Map map = (Map) list.get(i - 1);
				HSSFRow row = sheet1.createRow((short) i);
				row.setHeight((short) 500);
				HSSFCell cel1 = row.createCell((short) 0);// 第一列
				HSSFCell cel2 = row.createCell((short) 1);
				HSSFCell cel3 = row.createCell((short) 2);
				HSSFCell cel4 = row.createCell((short) 3);
				HSSFCell cel5 = row.createCell((short) 4);
				cel1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel1.setCellValue(i);
				cel2.setCellValue(map.get("friendName").toString());
				cel3.setCellValue(map.get("mobile").toString());
				cel4.setCellValue((map.get("sum")==null?"0":map.get("sum")).toString());
				cel5.setCellValue(String.valueOf(map.get("receiveTime")==null?"未领取":map.get("receiveTime")));
			}
		}

	}
	
	
	public static void excelHandleForMoney(HSSFSheet sheet1,List list) {
		HSSFRow row2= sheet1.createRow((short) 0);
		HSSFCell cell1 = row2.createCell((short) 0);// 第一列
		HSSFCell cell2 = row2.createCell((short) 1);
		HSSFCell cell3 = row2.createCell((short) 2);
		HSSFCell cell4 = row2.createCell((short) 3);
		HSSFCell cell5 = row2.createCell((short) 4);
		HSSFCell cell6 = row2.createCell((short) 5);
		HSSFCell cell7 = row2.createCell((short) 6);
		cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入数据
		cell1.setCellValue("序号");
		cell2.setCellValue("流水号");
		cell3.setCellValue("业主名称");
		cell4.setCellValue("亲友名称");
		cell5.setCellValue("赠送额度");
		cell6.setCellValue("赠送时间");
		cell7.setCellValue("领取时间");

		if (null != list && list.size() > 0) {
			for (int i = 1; i < list.size() + 1; i++) {
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				Map<String, String> map = (Map<String, String>) list.get(i - 1);
				HSSFRow row = sheet1.createRow((short) i);
				row.setHeight((short) 500);
				HSSFCell cel1 = row.createCell((short) 0);// 第一列
				HSSFCell cel2 = row.createCell((short) 1);
				HSSFCell cel3 = row.createCell((short) 2);
				HSSFCell cel4 = row.createCell((short) 3);
				HSSFCell cel5 = row.createCell((short) 4);
				HSSFCell cel6 = row.createCell((short) 5);
				HSSFCell cel7 = row.createCell((short) 6);
				cel1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel1.setCellValue(i);
				cel2.setCellValue(map.get("id"));
				cel3.setCellValue(map.get("yzName"));
				cel4.setCellValue(map.get("friendName"));
				cel5.setCellValue(map.get("sum"));
				cel6.setCellValue(String.valueOf(map.get("giveTime")));
				cel7.setCellValue(String.valueOf(map.get("receiveTime")));
			}
		}

	}

	public static void excelHandleForCoin(HSSFSheet sheet1,List list) {
		HSSFRow row2= sheet1.createRow((short) 0);
		HSSFCell cell1 = row2.createCell((short) 0);// 第一列
		HSSFCell cell2 = row2.createCell((short) 1);
		HSSFCell cell3 = row2.createCell((short) 2);
		HSSFCell cell4 = row2.createCell((short) 3);
		HSSFCell cell5 = row2.createCell((short) 4);
		HSSFCell cell6 = row2.createCell((short) 5);
		HSSFCell cell7 = row2.createCell((short) 6);
		cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入数据
			/*账号	操作状态	金额	操作后余额	操作时间	备注*/
		cell1.setCellValue("序号");
		cell2.setCellValue("账号");
		cell3.setCellValue("操作状态");
		cell4.setCellValue("金额");
		cell5.setCellValue("操作后余额");
		cell6.setCellValue("操作时间");
		cell7.setCellValue("备注");

		if (null != list && list.size() > 0) {
			for (int i = 1; i < list.size() + 1; i++) {
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				Map<String, String> map = (Map<String, String>) list.get(i - 1);
				HSSFRow row = sheet1.createRow((short) i);
				row.setHeight((short) 500);
				HSSFCell cel1 = row.createCell((short) 0);// 第一列
				HSSFCell cel2 = row.createCell((short) 1);
				HSSFCell cel3 = row.createCell((short) 2);
				HSSFCell cel4 = row.createCell((short) 3);
				HSSFCell cel5 = row.createCell((short) 4);
				HSSFCell cel6 = row.createCell((short) 5);
				HSSFCell cel7 = row.createCell((short) 6);
				cel1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel1.setCellValue(i);
				cel2.setCellValue(map.get("account"));
				cel3.setCellValue(map.get("type").equals("0")?"充值":"扣款");
				cel4.setCellValue(map.get("coin"));
				cel5.setCellValue(map.get("originalCoin"));
				cel6.setCellValue(String.valueOf(map.get("creattime")));
				cel7.setCellValue(String.valueOf(map.get("remark")));
			}
		}

	}

	public static void excelHandleForYz(HSSFSheet sheet1,List list) {
		HSSFRow row2= sheet1.createRow((short) 0);
		HSSFCell cell1 = row2.createCell((short) 0);// 第一列
		HSSFCell cell2 = row2.createCell((short) 1);
		HSSFCell cell3 = row2.createCell((short) 2);
		HSSFCell cell4 = row2.createCell((short) 3);
		HSSFCell cell5 = row2.createCell((short) 4);
		HSSFCell cell6 = row2.createCell((short) 5);
		HSSFCell cell7 = row2.createCell((short) 6);
		cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入数据
		cell1.setCellValue("序号");
		cell2.setCellValue("业主名称");
		cell3.setCellValue("手机号码");
		cell4.setCellValue("度假币余额");
		cell5.setCellValue("身份证");
		cell6.setCellValue("所属分类");
		cell7.setCellValue("录入时间");

		if (null != list && list.size() > 0) {
			for (int i = 1; i < list.size() + 1; i++) {
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				Map map = (Map) list.get(i - 1);
				HSSFRow row = sheet1.createRow((short) i);
				row.setHeight((short) 500);
				HSSFCell cel1 = row.createCell((short) 0);// 第一列
				HSSFCell cel2 = row.createCell((short) 1);
				HSSFCell cel3 = row.createCell((short) 2);
				HSSFCell cel4 = row.createCell((short) 3);
				HSSFCell cel5 = row.createCell((short) 4);
				HSSFCell cel6 = row.createCell((short) 5);
				HSSFCell cel7 = row.createCell((short) 6);
				cel1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel1.setCellValue(i);
				cel2.setCellValue(map.get("name").toString());
				cel3.setCellValue(map.get("mobile").toString());
				cel4.setCellValue(map.get("virtualCoin").toString()==null?"0":map.get("virtualCoin").toString());
				cel5.setCellValue(map.get("idCard").toString());
				cel6.setCellValue(map.get("kind").toString());
				cel7.setCellValue(String.valueOf(map.get("inputDate")));
			}
		}

	}
	
	
	//订单预处理项
	public static void excelHandleForOrder(HSSFSheet sheet1,List list) {
		HSSFRow row2= sheet1.createRow((short) 0);
		HSSFCell cell1 = row2.createCell((short) 0);// 第一列
		HSSFCell cell2 = row2.createCell((short) 1);
		HSSFCell cell3 = row2.createCell((short) 2);
		HSSFCell cell4 = row2.createCell((short) 3);
		HSSFCell cell5 = row2.createCell((short) 4);
		HSSFCell cell6 = row2.createCell((short) 5);
		HSSFCell cell7 = row2.createCell((short) 6);
		HSSFCell cell8 = row2.createCell((short) 7);
		HSSFCell cell9 = row2.createCell((short) 8);
		HSSFCell cell10 = row2.createCell((short) 9);
		HSSFCell cell11 = row2.createCell((short) 10);
		HSSFCell cell12 = row2.createCell((short) 11);
		HSSFCell cell13 = row2.createCell((short) 12);
		HSSFCell cell14 = row2.createCell((short) 13);
		HSSFCell cell15 = row2.createCell((short) 14);
		
		
		cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
		
		// 在单元格中输入数据
		cell1.setCellValue("序号");
		cell2.setCellValue("订单号");
		cell3.setCellValue("所属酒店");
		cell4.setCellValue("房型");
		cell5.setCellValue("游客");
		cell6.setCellValue("手机号");
		cell7.setCellValue("数量");
		cell8.setCellValue("预定日期");
		cell9.setCellValue("离店日期");
		cell10.setCellValue("金额");
		cell11.setCellValue("支付状态");
		cell12.setCellValue("支付方式");
		cell13.setCellValue("订单状态");
		cell14.setCellValue("下单时间");
		cell15.setCellValue("预订账号");

		if (null != list && list.size() > 0) {
			for (int i = 1; i < list.size() + 1; i++) {
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				Map map = (Map) list.get(i - 1);
				HSSFRow row = sheet1.createRow((short) i);
				row.setHeight((short) 500);
				HSSFCell cel1 = row.createCell((short) 0);// 第一列
				HSSFCell cel2 = row.createCell((short) 1);
				HSSFCell cel3 = row.createCell((short) 2);
				HSSFCell cel4 = row.createCell((short) 3);
				HSSFCell cel5 = row.createCell((short) 4);
				HSSFCell cel6 = row.createCell((short) 5);
				HSSFCell cel7 = row.createCell((short) 6);
				HSSFCell cel8 = row.createCell((short) 7);
				HSSFCell cel9 = row.createCell((short) 8);
				HSSFCell cel10 = row.createCell((short) 9);
				HSSFCell cel11 = row.createCell((short) 10);
				HSSFCell cel12 = row.createCell((short) 11);
				HSSFCell cel13 = row.createCell((short) 12);
				HSSFCell cel14 = row.createCell((short) 13);
				HSSFCell cel15 = row.createCell((short) 14);
				
				cel1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel8.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel9.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel10.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel11.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel12.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel13.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel14.setCellType(HSSFCell.CELL_TYPE_STRING);
				cel15.setCellType(HSSFCell.CELL_TYPE_STRING);
				
				cel1.setCellValue(i);
				cel2.setCellValue(map.get("orderId").toString());
				cel3.setCellValue(map.get("hotelName").toString());
				cel4.setCellValue(map.get("roomName").toString());
				cel5.setCellValue(map.get("name").toString());
				cel6.setCellValue(map.get("mobile").toString());
				cel7.setCellValue(map.get("roomNum").toString());
				cel8.setCellValue(String.valueOf(map.get("checkInDate")));
				cel9.setCellValue(String.valueOf(map.get("checkOutDate")));
				cel10.setCellValue(map.get("sum").toString());
				cel11.setCellValue(map.get("payStatus").toString().equals("1")?"已支付":"未支付");
				cel12.setCellValue(map.get("payWay").toString().equals("1")?"微信支付":"度假币支付");
				cel13.setCellValue(map.get("os").toString());
				cel14.setCellValue(map.get("orderTimes").toString());
				cel15.setCellValue(null==map.get("uaccount")?"":map.get("uaccount").toString());
			}
		}

	}
	/**
	 * 
	 * 取网络的地址上面的图片
	 * 
	 * @param urlstr
	 * @return
	 * @throws Exception
	 *             InputStream
	 */
	private static InputStream getFile(String urlstr) throws Exception {
		InputStream is;
		URL url;
		url = new URL(urlstr);

		URLConnection con = url.openConnection();
		// 不超时
		con.setConnectTimeout(0);
		//con.setConnectTimeout(30000);
		//con.setReadTimeout(30000);
		con.setDoOutput(true);//打开写入属性
		// 不允许缓存
		con.setUseCaches(false);
		con.setDefaultUseCaches(false);
		is = con.getInputStream();

		// 先读入内存
		ByteArrayOutputStream buf = new ByteArrayOutputStream(8192);
		byte[] b = new byte[1024];
		int len;
		while ((len = is.read(b)) != -1) {
			buf.write(b, 0, len);
		}
		is = new ByteArrayInputStream(buf.toByteArray());
		return is;
	}

	/**
	 * 
	 * 
	 * @param filePath
	 *            文件路径
	 * @param response
	 * @param fileName
	 *            文件名
	 * @param fileType
	 *            文件类型
	 * @return
	 * @throws Exception
	 */
	public static boolean downLoadFile(String filePath,
			HttpServletResponse response, String fileName, String fileType)
			throws Exception {
		
		  // 下载本地文件
      //  String fileName = "Operator.doc".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream(filePath.replace("/", "\\")+fileName.toString().trim());// 文件的存放路径
        // 设置输出的格式
    	System.out.println("realpath11"+filePath.replace("/", "\\")+fileName);
        response.reset();
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "inline;filename =" + fileName.toString().trim());
      //  response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	
	
	public static void main(String[] args) {
		
		try {
			exportExcelUtil.getFile("http://pic1.nipic.com/2008-09-08/200898163242920_2.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
