<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>新闻信息添加</title>

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
		<form action="msg/addmsg.html" method="post" enctype="multipart/form-data">
			<table width="70%" border="0" align="center" cellspacing="1"
			style="background-color:#d2d2da;line-height: 250%;">
				
				<tr>
					<td bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							 类别：
						</div>
					</td>
					<td bgcolor="#FFFFFF">
					<select   name="type"  id="type" style="height: 25px;width: 160px;">
					<option value="0">我的消息</option>
					<option value="1">关于我们</option>
					<option value="2">常见问题</option>
					</select>
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
							<input  style="width: 67%;height: 25px;" name="title" type="text" id="title">
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
						<textarea rows="10" cols="80"  name="info"></textarea>
					</td>
				</tr>
				
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							图片：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input type="file" name="pic" id="pic">
						</label>
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
		</form>
	</body>
</html>
