<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*"
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
		<title>充值与扣款</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">


		</script>
	</head>
	<body leftmargin="8" topmargin="8">
		<!--  搜索表单  -->
		<form  id="from1"  action="admin/yuding/coinManager.jsp" method="post">
			<table width='98%' border='0' cellpadding='1' cellspacing='1'
				bgcolor='#CBD8AC' align="center" style="margin-top: 8px">
				<tr bgcolor='#EEF4EA'>
					<td align='center'>

						<table width="100%" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129' style='text-align:right;'>
									账号：
								</td>
								<td width='170'>
									<input type='text' name='account' id='account'   value='' style='width: 150px' />
								</td>

								<td width='129' style='text-align:right;'>
									操作状态：
								</td>
								<td width='170'>
									<select name="type" id="type" style="width: 100px">
										<option name=""></option>
										<option name="0">充值</option>
										<option name="1">扣款</option>
									</select>
								</td>


								<td width='86'>
									<input name="imageField" type="submit" border="0" class="np" value='查询' /> &nbsp;&nbsp; | &nbsp;&nbsp;
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
					<tr bgcolor="#E7E7E7">
						<td height="24" colspan="10" background="admin/images/tbg.gif">
							&nbsp;操作列表&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22">
						   <td width="8%">
							   序号
						   </td>
							<td width="8%">
					      	 账号
						</td>
						<td width="10%">
							操作状态
						</td>
						<td width="15%">
							金额
						</td>
						<td width="8%">
							操作后余额
						</td>

						<td width="8%">
							操作时间
						</td>

						<td width="8%">
							备注
						</td>
					</tr>


					<%
						Connection conn = DBManager.getConnection();
						String account = request.getParameter("account");
						String type = request.getParameter("type");
						System.out.println("type------>"+type);

						String sql = "";
						String condition = "";
						if (account != null && !account.equals("")) {
							condition += " and tu.account = '" + account + "' ";
						}

						if (type != null && !type.equals("")) {
						      type=type.equals("充值")?"0":"1";
							condition += " and tu.type = '" + type + "' ";
						}
						sql = "select *  from   t_log  tu   where   1=1  ";
						if(condition.equals("")){

						}else{
							sql=sql+ condition ;
						}
						   System.out.print("sql----->"+sql);
						PreparedStatement pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
                           int i=0;
						while (rs.next()) {
						   i++;
					%>

					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						<td>
							<%=i%>
						</td>
						<td>
							<%=rs.getString("account")==null?"/":rs.getString("account")%>
						</td>
						<td>
							<%=rs.getString("type").equals("0")?"充值":"扣款"%>
						</td>
						<td>
							<%=rs.getString("coin")==null?"0":rs.getString("coin")%>
						</td>

						<td>
							<%=rs.getString("originalCoin")==null?"0":rs.getString("originalCoin")%>
						</td>

						<td>
							<%=rs.getString("creattime")==null?"0":rs.getString("creattime")%>
						</td>

						<td>
							<%=rs.getString("remark")==null?"/":rs.getString("remark")%>
						</td>


						
					</tr>
					<%
						}
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					%>

				</table>
			</div>
		</form>
		<script type="text/javascript">
		 $("#export").on("click", function () {
	       window.location.href="<%=basePath%>coin/exportExcel.html";
            });
		</script>
	</body>
</html>