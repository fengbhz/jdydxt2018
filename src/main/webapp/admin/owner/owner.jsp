<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*,org.apache.commons.lang3.StringEscapeUtils"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>业主管理</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/vailjs/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/vailjs/messages_zh.js"></script>
		<script type="text/javascript" src="<%=basePath%>laypage/laypage.js"></script>
		<style>
form.cmxform label.error,label.error {
	color: red;
	font-style: italic
}
</style>
		<script type="text/javascript">
		 var  url1="<%=basePath%>";
		 var pages =0;
	   </script>
	  <script type="text/javascript" src="<%=basePath%>admin/js/owner.js"></script>
	</head>
	<body leftmargin="8" topmargin="8">
	     <input id="pages" name="pages"  type="hidden">
		<form  id="from1"  action="admin/owner/owner.jsp" method="post">
			<div style="text-align: left; margin-top: 5px; width:100%; margin-right: auto;margin-left: 5px;">
		    	<input id="addOwnerBtn" type="button" onclick="addOwner()" value="添加" style="width:80px; height:30px; background:url(images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;"/>
		   	   &nbsp;&nbsp;&nbsp;&nbsp; <input id="resetBtn" type="button" onclick="resetCoin()" value="一键清零" 
		    	style="width:80px; height:30px; background-color:red; font-size:14px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;"/>
		    </div>
			<table width='98%' border='0' cellpadding='1' cellspacing='1'
				bgcolor='#CBD8AC' align="center" style="margin-top: 8px">
				<tr bgcolor='#EEF4EA'  >
					<td align='center'>
						<table width="90%" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129' style='text-align:right;'>
									业主姓名：
								</td>
								<td width='170'>
									<input type='text' name='queryName'  id='queryName' value='' style='width: 150px' />
								</td>
								<td width='129' style='text-align:right;'>
									手机：
								</td>
								<td width='170'>
									<input type='text' name='queryMobile'  id='queryMobile' value='' style='width: 150px' />
								</td>
								<td width='129' style='text-align:right;'>
									状态：
								</td>
								<td width='170'>
									<select name="queryStatus" id="queryStatus">
										<option>
											正常
										</option>
										<option >
											停用
										</option>
									</select>
								</td>
								<td width='86'>
									<input name="sub"  id="sub"    type="button" border="0" class="np" value='查询' /> &nbsp;&nbsp; | &nbsp;&nbsp;
									<input name="imageField"  id="export" type="button" border="0" class="np" value='导出' />
								</td>
								<td width="84">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<!--  快速转换位置按钮  -->
		<!--  内容列表   -->
		<form name="form2">
			<div id="printTable">
				<table width="98%" border="0" cellpadding="2" cellspacing="1"
					bgcolor="#3333FF" align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
					<tr bgcolor="#E7E7E7" >
						<td height="24" colspan="10" background="admin/images/tbg.gif">
							&nbsp;业主列表&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22"   id="tr01">
						<td width="5%">
							排序
						</td>
						<td width="8%">
							业主名称
						</td>
						<td width="8%">
							账号
						</td>
						<td width="10%">
							手机号码
						</td>
						<td width="10%">
							度假币余额
						</td>
						<td width="15%">
							身份证
						</td>
						<td width="8%">
							所属分类
						</td><!--
						<td width="5%">
							状态
						</td>
						--><td width="8%">
							录入时间
						</td>
						<td width="31%">
							操作
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align: left; margin-top: 5px; width:100%; margin-right: auto;margin-left: 5px;">
		    </div>
		</form>
		
		
		<div id="addOwnerDialog" style="display:none;">
			<form id="addOwnerForm" action="user/hotelUserAdd.html" method="post">
				<input name="owerid" type="hidden" id="owerid" >
				<input name="uid" type="hidden" id="uid" >
				<table width="100%" align="center">
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								所属分类：
							</div>
						</td>
						<td width="30%" bgcolor="#FFFFFF">
							<select name="hotelKind"  class="selector"  id="hotelKind">
								<%
									Connection conn1 = DBManager.getConnection();
									
									String sql1 = "SELECT id, jiudianKind FROM hotelsupplierkind WHERE jiudianStatus = '0'";
									PreparedStatement pstmt1 = conn1.prepareStatement(sql1);
									ResultSet rs1 = pstmt1.executeQuery();
									while (rs1.next()) {
								%>
								<option  value="<%=rs1.getString("jiudianKind")%>">
									<%=rs1.getString("jiudianKind")%>
								</option>
								<%
									}
									if (rs1 != null)
										rs1.close();
									if (pstmt1 != null)
										pstmt1.close();
									if (conn1 != null)
										conn1.close();
								%>
								
							</select>
						</td>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								所属酒店：
							</div>
						</td>
						<td width="30%" bgcolor="#FFFFFF">
							<select name="hotelName"  class="selector2" id="hotelName">
								<%
									Connection conn2 = DBManager.getConnection();
									
									String sql2 = "SELECT id, hotelName FROM hotelsupplier WHERE status = '0'";
									
									
									PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
									ResultSet rs2 = pstmt2.executeQuery();
			
									while (rs2.next()) {
										
								%>
								<option value="<%=rs2.getString("hotelName")%>">
									<%=rs2.getString("hotelName")%>
								</option>
								<%
									}
									if (rs2 != null)
										rs2.close();
									if (pstmt2 != null)
										pstmt2.close();
									if (conn2 != null)
										conn2.close();
								%>
								
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								业主姓名：
							</div>
						</td>
						<td width="30%" bgcolor="#FFFFFF">
							<label>
								<input name="ownerName" type="text" id="ownerName">
							</label>
						</td>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								手机号码：
							</div>
						</td>
						<td width="30%" bgcolor="#FFFFFF">
							<label>
								<input name="mobile" type="text" id="mobile">
							</label>
						</td>
					</tr>
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								身份证：
							</div>
						</td>
						<td width="80%" bgcolor="#FFFFFF" colspan=3>
							<input name="idCard" type="text" id="idCard" style="width:100%">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		
		
		<div id="coinManageDialog" style="display:none;">
			<form id="coinManageForm" action="CoinManageAction" method="post">
				<table width="100%" align="center">
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								业主姓名：
							</div>
						</td>
						<td width="30%" bgcolor="#FFFFFF">
							<label id="ownerNameLabel">
							</label>
							<input name="ownerId" type="hidden" id="ownerId" value=''>
						</td>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								度假币余额：
							</div>
						</td>
						<td width="30%" bgcolor="#FFFFFF">
							<label id="coinLabel">
							</label>
						</td>
					</tr>
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								操作类型：
							</div>
						</td>
						<td width="80%" bgcolor="#FFFFFF" colspan=3>
							<select name="opType" id="opType"   style="width:80px">
								<option value="0">充值</option>
								<option value="1">扣款</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								金额：
							</div>
						</td>
						<td width="80%" bgcolor="#FFFFFF" colspan=3>
							<input name="coin" type="text" id="coin" style="width:30%"   >
						</td>
					</tr>
					<tr>
						<td width="20%" bgcolor="#FFFFFF">
							<div align="right" class="STYLE3">
								备注：
							</div>
						</td>
						<td width="80%" bgcolor="#FFFFFF" colspan=3>
							<input name="remark" type="text" id="remark" style="width:100%">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="fasDialog" style="display:none;margin-top: 20px">
			<form id="approveForm" action="ApproveOwnerAction" method="post">
				<ul style="margin-top: 10px;">
				    <li>
				    	<label style="width:20%; text-align: right;">审核结果：</label>
				    	<input name="ownerId1" type="hidden" id="ownerId1" value="">
				    		<input name="account" type="hidden" id="account" value="">
				    	<input id="tempApprove" type="radio" name="aType" value="" style="margin-top: 10px;"/>通过&nbsp;&nbsp;&nbsp;
						<input type="radio" name="aType" value="4" />不通过
				    </li>
				</ul>
			</form>
		</div>


						<div id="QyDialog" style="display: none;text-align: center;">
		                      	<form id="QyForm" action="user/qy.html" method="post">
		                      	   <input type="hidden" id="orderId"   name="orderId" >
							      <textarea  id="qy"  name="qy"  style="width:95%;height: 95%" rows="5" cols="50"  required></textarea>
			                  </form>
		                </div>
	      

         
              <div id="biuuu_city"></div>
	</body>
	   <script type="text/javascript">
	
	     $("#export").on("click", function () {
	       window.location.href="<%=basePath%>yz/exportExcel.html";  
	    });
	    
	    
	     $("#sub").on("click", function () {
	          fycx();
	          test();
	    });
       
//测试数据
    fycx();
var nums = 15; //每页出现的数量

//调用分页
laypage({
    cont: 'biuuu_city',
    pages: pages,
    jump: function(obj){
       fycx(obj.curr); 
    }
})
</script>
	
</html>