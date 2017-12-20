<%@ page language="java"
	import="java.util.*,java.sql.*,com.vdong.commons.db.*"
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
		<title>酒店分类修改</title>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
			<!--
			.STYLE3 {
				font-size: 16px
			}
			-->
		</style>
	</head>
	<body>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<form action="ModSupplierKindAction" method="post">
			<%
				Connection conn = DBManager.getConnection();
				String id = request.getParameter("id");
				String sql = "select id, jiudianKind, jiudianStatus, jiudianOrder, date_format(jiudianDate, '%Y-%m-%d') as jiudianDate from hotelsupplierkind where id = '" + id + "'";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				rs.next();
			%>
			<table width="80%" border="0" align="center" cellspacing="1" >
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							酒店分类：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="jiudianKind" type="text" id="jiudianKind"
								value='<%=rs.getString("jiudianKind")%>'>
							<input name="id" type="hidden" id="id" value='<%=id%>'>
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							序号：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="jiudianOrder" type="text" id="jiudianOrder" value='<%=rs.getString("jiudianOrder")%>'>
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
						<label>
							<div align="center">
								<input type="button" name="Submit" onclick="save();" value="提交">
								<input type="reset" name="Submit2" value="重置">
							</div>
						</label>
					</td>
				</tr>
			</table>
			<%
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			%>
		</form>
		<script type="text/javascript">
		
			function save() {
				document.forms[0].submit();
			}
		</script>
	</body>
</html>
