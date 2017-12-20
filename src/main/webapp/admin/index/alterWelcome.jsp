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

		<title>欢迎语维护</title>

		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<style type="text/css">
		<!--
		.STYLE3 {
			font-size: 16px
		}
		-->
		</style>
		<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
	</head>
	<body>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<form action="AlterWelcomeAction" method="post">
			<%
				DBManager dbm = new DBManager();
				Connection conn = dbm.getConnection();
				String sql = "select id, title, content from hotelwelcome where id = 'welcome'";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				rs.next();
			%>
			<table width="70%" border="0" align="center" cellspacing="1"
				   style="margin-top: 8px">
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							欢迎语标题：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="title" type="text" id="title" value='<%=rs.getString("title")%>'>
						</label>
					</td>
				</tr>
				
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							欢迎语内容：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<textarea name="content" id="content" style="width: 600px; height: 250px;"><%=rs.getString("content")%></textarea>
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
