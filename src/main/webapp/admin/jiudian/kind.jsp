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
		<title>酒店分类</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
</script>
		<script charset="utf-8" src="kindeditor/kindeditor-min.js">
</script>
	
	</head>
	<body leftmargin="8" topmargin="8"'>
		<!--  搜索表单  -->
		<form action="admin/jiudian/kind.jsp" method="post">
			<table width='98%' border='0' cellpadding='1' cellspacing='1'
				bgcolor='#CBD8AC' align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
				<tr bgcolor='#EEF4EA'>
					<td align='center'>

						<table width="469" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129'>
									请输入分类：
								</td>
								<td width='170'>
									<input type='text' name='queryName' value=''
										style='width: 150px' />
								</td>
								<td width='86'>
									<input name="imageField" type="submit" border="0" class="np" value='查询' />
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
							&nbsp;酒店分类&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22">
						<td width="10%">
							排序
						</td>
						<td width="35%">
							分类名称
						</td>
						<td width="15%">
							状态
						</td>
						<td width="20%">
							录入时间
						</td>
						<td width="20%">
							操作
						</td>
					</tr>


					<%
						DBManager dbm = new DBManager();
						Connection conn = dbm.getConnection();
						String queryName = request.getParameter("queryName");
						String sql = "select id, jiudianKind, jiudianStatus, jiudianOrder, date_format(jiudianDate, '%Y-%m-%d') as jiudianDate from hotelsupplierkind order by jiudianOrder";
						if (queryName != null) {
							sql = "select id, jiudianKind, jiudianStatus, jiudianOrder, date_format(jiudianDate, '%Y-%m-%d') as jiudianDate from hotelsupplierkind where jiudianKind like '%" + queryName
									+ "%' order by jiudianOrder";
						}
						PreparedStatement pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();

						while (rs.next()) {
							String id = rs.getString("id");
							String status = rs.getString("jiudianStatus");
					%>

					<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						<td>
							<%=rs.getString("jiudianOrder")%>
						</td>
						<td>
							<%=rs.getString("jiudianKind")%>
						</td>
						<td>
							<div align="center">
							<%
								if(status.equals("0")){
							%>
								<img alt="" width="25px" height="25px" src="upload/effective.png">
							<% 
								}else{
							%>
								<img alt="" width="25px" height="25px" src="upload/invalid.png">
							<% 
								}
							%>
							</div>
						</td>
						<td align="center">
							<%=rs.getString("jiudianDate")%>
						</td>
						<td>
							<a href="admin/jiudian/modKind.jsp?id=<%=id%>">修改</a> |
							<%
								if(status.equals("0")){
							%>
								<a href="DelSupplierKindAction?id=<%=id%>&status=1">停用</a>
							<% 
								}else{
							%>
								<a href="DelSupplierKindAction?id=<%=id%>&status=0">启用</a>
							<% 
								}
							%>
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

					<tr bgcolor="#FAFAF1">
						<td height="28" colspan="10">
							<a href="admin/jiudian/addKind.jsp"><strong>添加分类</strong>
							</a>
						</td>
					</tr>

				</table>
			</div>
		</form>
	</body>
</html>