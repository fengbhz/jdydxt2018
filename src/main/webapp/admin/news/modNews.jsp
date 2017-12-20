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

		<title>新闻信息修改</title>

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
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<form action="ModNewsAction" method="post">
			<%
				Connection conn = DBManager.getConnection();
				String id = request.getParameter("id");
				String sql = "select n.*,s.dmmc  from news  n   left join  t_systemcode s   on  n.type=s.dmz where s.type='NEW' and  n.id='" + id + "'    ";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				rs.next();
			%>
			<table width="70%" border="0" align="center" cellspacing="1"
			style="background-color:#d2d2da;line-height: 250%;">
			  <tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							类别：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
								<%=rs.getString("dmmc")==null?"":rs.getString("dmmc")%>
					</td>
				</tr>
			
			
			
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							标题：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="title" type="text" id="title"
								value='<%=rs.getString("title")%>'>
							<input name="id" type="hidden" id="id" value='<%=id%>'>
						</label>
					</td>
				</tr>
				 
				
				 <tr>
					<td bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							 内容：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
						<textarea rows="10" cols="80"  name="info"><%=rs.getString("info").replaceAll("<br>","\r\n").replaceAll("&nbsp;"," ")%></textarea>
					</td>
				</tr>
				
				 <tr>
					<td bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							 图片：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
						<img  name="pic"  src="<%=basePath%><%=rs.getString("pic")%>"  ></img>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
						<label>
							<div align="center">
								<input type="submit" name="Submit" value="提交">
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
	</body>
</html>
