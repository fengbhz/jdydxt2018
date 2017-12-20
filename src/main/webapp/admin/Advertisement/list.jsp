<%@ page language="java"
         import="java.util.*,java.sql.*,com.vdong.commons.db.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告管理</title>
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
</head>
<body leftmargin="8" topmargin="8">
	<!--  快速转换位置按钮  -->
	<!--  搜索表单  -->
	<form action="admin/Advertisement/list.jsp" method="post">
		<table width='98%' border='0' cellpadding='1' cellspacing='1'
			   bgcolor='#CBD8AC' align="center" style="margin-top: 8px">
			<tr bgcolor='#EEF4EA'>
				<td align='center'>
					<table width="469" border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td width='129'>请输入广告标题：</td>
							<td width='170'><input type='text' name='queryName' value=''
												   style='width: 150px' /></td>
							<td width='86'><input name="imageField" type="submit"
												  border="0" class="np" value='查询' /></td>
							<td width="84">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<!--  内容列表   -->
	<form name="form2">
		<div id="printTable">
			<table width="98%" border="0" cellpadding="2" cellspacing="1"
				bgcolor="#3333FF" align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
				<tr bgcolor="#E7E7E7">
					<td height="24" colspan="10" background="admin/images/tbg.gif">
						&nbsp;广告信息列表&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">ID</td>
					<td width="15%">发现分类</td>
					<td width="15%">发现标题</td>
					<td width="15%">首图</td>

					<td width="15%">发布日期</td>
					<td width="5%">状态</td>
					<td width="15%">操作</td>
				</tr>
				<%
					DBManager dbm = new DBManager();
					Connection conn = dbm.getConnection();
					String queryName = request.getParameter("queryName");
					String sql = "select * from hotelad";
					if (queryName != null) {
						sql = "select * from hotelad where adTitle like '%" + queryName + "%'";
					}
					System.out.println(sql + "-----");
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						String id = rs.getString("id");
						String status = rs.getString("adStatus");
				%>
				<tr align='center' bgcolor="#FFFFFF"
					onMouseMove="javascript:this.bgColor='#FCFDEE';"
					onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td><%=id%></td>
					<td><%=rs.getString("adKind")%></td>
					<td align="left">
						<div align="center">
							<%
								if (rs.getString("adTitle") != null) {
							%>
							<%=rs.getString("adTitle")%>
							<%
								}
							%>
						</div>
					</td>
					<td align="left">
						<div align="center">
							<img alt="" width="120px" height="70px"
								src="<%=rs.getString("adPic")%>">
						</div>
					</td>

					<td align="left">
						<div align="center">
							<%
								if (rs.getString("adSubmitDate") != null) {
							%>
							<%=rs.getString("adSubmitDate")%>
							<%
								}
							%>
						</div>
					</td>
					<td align="left">
						<div align="center">
							<%
								if (status.equals("0")) {
							%>
							<img alt="" width="25px" height="25px" src="upload/effective.png">
							<%
								} else {
							%>
							<img alt="" width="25px" height="25px" src="upload/invalid.png">
							<%
								}
							%>
						</div>
					</td>
					<td><a href="admin/Advertisement/modAdvertisement.jsp?id=<%=id%>">编辑</a>
						| <a href="DelAdvertisementAction?id=<%=id%>&status=1">删除</a> | 
						<% if (status.equals("1")) { %>
						<a href="DelAdvertisementAction?id=<%=id%>&status=2">启用</a>
						<% } else if (status.equals("0")) { %> 
						<a href="DelAdvertisementAction?id=<%=id%>&status=3">停用</a></td>
					<% } %>
				</tr>
				<%
					}
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				%>
				<tr bgcolor="#FAFAF1">
					<td height="28" colspan="10">&nbsp; 
					<a href="admin/Advertisement/addAdvertisement.jsp"><strong>添加广告</strong></a>
					</td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>