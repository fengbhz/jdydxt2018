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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>会员管理</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<script language="javascript">
		
function doPrint() {	
        
		LODOP.PRINT_INIT("打印");
		LODOP.ADD_PRINT_HTM(88,20,700,600,document.getElementById("printTable").innerHTML);
		LODOP.PREVIEW();	
	};
	

			function deleteHy(id) {
				if (confirm("确定要删除此会员吗？")) {
				 window.location.href="<%=basePath%>platform/deleteUser.html?id="+id;
				}
			}
		</script>
	
	</head>
	<body leftmargin="8" topmargin="8"'>
		<!--  快速转换位置按钮  -->
		<!--  内容列表   -->

	<!--  搜索表单  -->
	<form action="admin/user/list.jsp" method="post">

		<table width='98%' border='0' cellpadding='1' cellspacing='1'
			   bgcolor='#CBD8AC' align="center" style="margin-top: 8px">
			<tr bgcolor='#EEF4EA'>
				<td align='center'>

					<table width="469" border='0' cellpadding='0' cellspacing='0'>
						<tr>
							<td width='129'>
								请输入会员名称：
							</td>
							<td width='170'>
								<input type='text' name='queryName' value=''
									   style='width: 150px' />
							</td>
							<td width='86'>
								<input name="imageField" type="submit" border="0" class="np"
									   value='查询' />
							</td>
							<td width="84">&nbsp;

							</td>
						</tr>
					</table>

				</td>
			</tr>
		</table>
	</form>
		<form name="form2">
			<div id="printTable">
				<table width="98%" border="0" cellpadding="2" cellspacing="1"
					bgcolor="#3333FF" align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
					<tr bgcolor="#E7E7E7">
						<td height="24" colspan="10" background="admin/images/tbg.gif">
							&nbsp;会员信息列表&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22">
						
						<td width="14%">
							姓名
						</td>
						<td width="10%">
							昵称
						</td>

						<td width="10%">
						          性别
						</td>
						<td width="10%">
						   手机
						</td>
						<td width="10%">
							等级
						</td>
						

					<%--	<td width="10%">
							操作
						</td>--%>
					</tr>

					<%
						Connection conn = DBManager.getConnection();
						String queryName = request.getParameter("queryName");
						String sql = "select * from hoteluser  where userKind='0'  ";
						if (queryName != null) {
							sql += "and name like '%" + queryName
									+ "%'";
						}
						PreparedStatement pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();

						while (rs.next()) {
							String id = rs.getString("id");
					%>

					<tr align='center' bgcolor="#FFFFFF"
						onMouseMove="javascript:this.bgColor='#FCFDEE';"
						onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						 
						<td><%=rs.getString("name")%>

						</td>
						
						<td align="left">
							<div align="center">
								<%=rs.getString("nickName")%>

							</div>
						</td>
						

						
						<td align="left">
							<div align="center">
								<%if(null!=rs.getString("gender")&&rs.getString("gender").equals("1")){%>
									男
								<%} else if(null!=rs.getString("gender")&&rs.getString("gender").equals("2")) { %>
									女
								<%} %>
							</div>
						</td>
						
						<td align="left">
							<div align="center">
								<%=rs.getString("mobile")%>

							</div>
						</td>
						
						<td>
							<%
							   String type=rs.getString("role");
							   if(null!=type&&type.equals("7")){
							     out.print("A类会员(7折)");
							   }
							   if(null!=type&&type.equals("8")){
							     out.print("B类会员(8折)");
							   }
							   if(null!=type&&type.equals("9")){
							   out.print("C类会员(9折)");
							   }
							   
							 %>
						</td>
					 
					<%--	<td>
							<a href="admin/user/modUser.jsp?id=<%=id %>">编辑</a> |
							<a   onclick="deleteHy('<%=id %>')" href="javascript:void(0);">删除</a>
						</td>--%>
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
							&nbsp;
						
							  
					</tr>

				</table>
			</div>
		</form>


	</body>
</html>