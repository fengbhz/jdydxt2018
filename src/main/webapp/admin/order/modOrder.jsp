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
		<title>订单信息变更</title>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
      	<script type="text/javascript" src="<%=path%>/js/popup.js"></script>
		<script type="text/javascript">
			function up() {
				var pop = new Popup( {
					contentType : 1,
					isReloadOnClose : false,
					width : 400,
					height : 200
				});
				pop.setContent("contentUrl", "<%=path%>/upload/upload.jsp");
				pop.setContent("title", "文件上传");
				pop.build();
				pop.show();
			}
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
		<form action="ModHotelAction" method="post">
			<%
				Connection conn = DBManager.getConnection();
				String id = request.getParameter("id");
				String sql = "select * from hotelorder where id='" + id + "'";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				rs.next();
			%>
			<table width="70%" border="0" align="center" cellspacing="1"
				bordercolor="#000000" bgcolor="#0000CC">
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							酒店名称：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="itemName" type="text" id="itemName" value='<%=rs.getString("itemName")%>'>
							<input name="id" type="hidden" id="id" value='<%=id%>'>
						</label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							房间数：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label>
							<input name="roomNum" type="text" id="roomNum" value='<%=rs.getString("roomNum")%>'>
							<input name="id" type="hidden" id="id" value='<%=id%>'>
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

var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="info"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist' ]
	});
});

function save() {
    
	//editor.sync();
	
	document.forms[0].submit();
	

}
</script>
	</body>
</html>
