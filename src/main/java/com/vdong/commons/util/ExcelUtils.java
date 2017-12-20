package com.vdong.commons.util;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件上传的公共方法
 * 
 * @author lk
 */
public class ExcelUtils {/*
*//**
 * 
 * @param request
 * @param response
 * @param realPath   文件上传的全部路径 
 * @return Map,  flag代表上传后的状态,path   上传后的文件路径名  ,msg代表上传的信息 
Map<String,String>
 *//*
	public static Map<String, String> uploadExcel(HttpServletRequest request,HttpServletResponse response,String realPath,MultipartFile myfiles) {
		Map<String, String> remap = new HashMap<String, String>();
		Date data = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");
		format2.format(data);
		String originalFilename = null;
		if (myfiles.isEmpty()) {
			remap.put("flag", "false");
			remap.put("msg", "上传失败,请选择文件后上传");
		} else {
			originalFilename = myfiles.getOriginalFilename();
			System.out.println("文件原名: " + originalFilename);
			String filename = format2.format(data) + ".xls ";// 对文件进行重命名
			try {
				FileUtils.copyInputStreamToFile(myfiles.getInputStream(),
						new File(realPath, filename));
				remap.put("flag", "true");
				remap.put("msg", "上传成功！");
				System.out.println("文件名+文件路径为" + realPath + "\\" + filename);
				remap.put("realPath", realPath + "\\" + filename);
			} catch (IOException e) {
				System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
				e.printStackTrace();
				remap.put("flag", "false");
				remap.put("msg", "文件上传失败，请重试！！！");
			}
		}
		return remap;
	}
	
	 *//**

     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行

     * @param file 读取数据的源Excel

     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1

     * @return 读出的Excel中数据的内容

     * @throws FileNotFoundException

     * @throws IOException

     *//*

    public static String[][] getData(String path, int ignoreRows)
           throws FileNotFoundException, IOException {
    	File file1=new File(path);
       List<String[]> result = new ArrayList<String[]>();
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
    		   file1));
       // 打开HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
           HSSFSheet st = wb.getSheetAt(sheetIndex);
           // 第一行为标题，不取
           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
              HSSFRow row = st.getRow(rowIndex);
              if (row == null) {
                  continue;
              }
              int tempRowSize = row.getLastCellNum() + 1;
              if (tempRowSize > rowSize) {
                  rowSize = tempRowSize;
              }
              String[] values = new String[rowSize];
              Arrays.fill(values, "");
              boolean hasValue = false;
              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                  String value = "";
                  cell = row.getCell(columnIndex);
                  if (cell != null) {
                     switch (cell.getCellType()) {
                     case HSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue();
                         break;
                     case HSSFCell.CELL_TYPE_NUMERIC:
                         if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            } else {
                                value = "";
                            }
                         } else {
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                         }
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y": "N");
							break;
						default:
							value = "";
						}
                  }
                  if (columnIndex == 0 && value.trim().equals("")) {
                     break;
                  }
                  values[columnIndex] = value.trim();
                  hasValue = true;
              }
              if (hasValue) {
                  result.add(values);
              }
           }
       }
       in.close();

       String[][] returnArray = new String[result.size()][rowSize];

       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = (String[]) result.get(i);
       }
       System.out.println("返回的参数是 ："+returnArray);
       return returnArray;
    }

	*//**
	 * 
	 * 去重
	 * 
	 * @param a
	 *            二维数组
	 * @return list 里面装有报错信息以及正确的数据
	 *//*
	private List deleteRe(String[][] a) {
		List<Map> list = new ArrayList<Map>();
		StringBuffer str = new StringBuffer();
		for (int j = 0; j < a.length; j++) {
			System.out.println("这是第" + j + "轮");
			
			Map<String, String> remap = new HashMap<String, String>();
			String yjrjgdm = a[j][0]; // 引荐人机构代码
			String yjrdm = a[j][1]; // 引荐人代码
			String username = a[j][2]; // 引荐人机构代码
			remap.put("yjrdm", yjrdm);
			remap.put("yjrjgdm", yjrjgdm);
			remap.put("username", username);
			list.add(remap);
			System.out.println("yjrdm " + yjrdm + "yjrjgdm"+yjrjgdm);
		}
		Map<String, String> strmap = new HashMap<String, String>();
		strmap.put("msg", str.toString());
		list.add(strmap); // list 最后一个长度放的是报错信息
		return list;
	}
     
     *//**
      * 
      * 去重
      *  @param a   二维数组
      * @return  list  里面装有报错信息以及正确的数据
      *//*
      private   List deleteRe2(String[][] a){
     	 List<Map> list=new ArrayList<Map>();
     	 Map<String,String> pxmap=new HashMap<String,String>();//去重以后的map
     	 StringBuffer str=new StringBuffer(); 
     	 for (int j = 0; j < a.length; j++) {
     			  System.out.println("这是第"+j+"轮");
     			  Map<String,String> remap=new HashMap<String,String>();
     						  String name =a[j][0]; //姓名
     						  String groupname2=a[j][1];  //  群组
     						  String phone=a[j][2]; 
     						  String ghnum=a[j][3]; 
     						  if(null!=name&&!"".equals(name)){
     						  if(pxmap.containsKey(name)){//  如果有重复
     							  String hs=pxmap.get(name);//  行数
     							  str.append("第"+j+"行和第"+hs+"行姓名重复，无法插入!");
     						  }else{// 如果没有重复，则往里面插入
     						  pxmap.put(name, ""+j);
     						  remap.put("name", name);
     						  remap.put("groupname2", groupname2);
     						  remap.put("phone", phone);
     						  remap.put("ghnum", ghnum);
 				}
 			}
     						  list.add(remap);
     	 }
     	 Map<String,String> strmap=new HashMap<String,String>();
     	 strmap.put("msg", str.toString());
     	 list.add(strmap); // list   最后一个长度放的是报错信息
 		return list;
 	} 
 
     
	*//**
	 * 
	 * 对二维码中的数据获取和去重进行整合
	 * 
	 * @param realPath
	 *            路径
	 * @param ignore
	 *            忽视的行数
	 * @return List
	 *//*
	@SuppressWarnings("unchecked")
	public static List quickMark(String realPath, int ignore) {
		List<Map> list = new ArrayList<Map>();
		try {
			ExcelUtils excelutils = new ExcelUtils();

			list = excelutils.deleteRe(getData(realPath, ignore));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
     
     
     
     
     
     
     
     
    public static void main(String[] args) {
	
	try {
	String [][] a=	getData("F:/mole.xls",2);
	for (int j = 0; j < a.length; j++) {
		  System.out.println("这是第"+j+"轮");
		  for (int i = 0; i < a[j].length; i++) {
			  if(null!=a[j][i]&&!"".equals(a[j][i])){
				  System.out.println(a[j][i]);
			  }
			
		}
	}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	 
}
*/}
