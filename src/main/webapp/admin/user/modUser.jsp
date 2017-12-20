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

<title>信息修改</title>

<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<style type="text/css">
<!--
.STYLE3 {
	font-size: 16px
}
-->
</style>
</head>

<body>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<form action="ModUserAction" method="post">
		<%
			Connection conn = DBManager.getConnection();
			String id = request.getParameter("id");
		    System.out.println("id==========" +id);
			String sql = "select t.id,t2.name,t.password,t.gender,t.mobile,t.role from hoteluser t  left join   hotelowner  t2  on t.account=t2.account where t.id='" + id + "'";
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			rs.next();
		%>
		<table width="39%" border="0" align="center" cellspacing="1"
			bordercolor="#000000" bgcolor="#0000CC">
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">姓名：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF"><label> <input
						name="name" type="text" id="name"
						value='<%=rs.getString("name")%>'> <input name="id"
						type="hidden" id="name" value='<%=id%>'>
				</label></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">密码：</div>
				</td>
				<td bgcolor="#FFFFFF"><input name="password" type="text"
					id="password" value='<%=rs.getString("password")%>'></td>
			</tr>

			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">性别：</div>
				</td>
				<td bgcolor="#FFFFFF"><select name="gender" id="gender">
						<%
							String gender = rs.getString("gender");
						%>
						<option value="1" <%if (gender.equals("1")) {%> selected="selected"
							<%}%>>男</option>
						<option value="2" <%if (gender.equals("2")) {%> selected="selected"
							<%}%>>女</option>
				</select></td>
			</tr>

			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">手机：</div>
				</td>
				<td bgcolor="#FFFFFF"><input name="mobile" type="text"
					id="mobile" value='<%=rs.getString("mobile")%>'></td>
			</tr>

			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">会员等级：</div>
				</td>
				<td bgcolor="#FFFFFF"><select name="role" id="role">

						<%
							String type = rs.getString("role");
						%>
						<option value="7" <%=type.equals("7") ? "selected" : ""%>>A类会员(7折)</option>
						<option value="8" <%=type.equals("8") ? "selected" : ""%>>B类会员(8折)</option>
						<option value="9" <%=type.equals("9") ? "selected" : ""%>>C类会员(9折)</option>
				</select></td>
			</tr>

			<tr>
				<td colspan="2" bgcolor="#FFFFFF"><label>
						<div align="center">
							<input type="submit" name="Submit" value="提交"> <input
								type="reset" name="Submit2" value="重置">
						</div>
				</label></td>
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
</body>
</html>
