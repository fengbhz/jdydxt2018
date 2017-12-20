<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*" pageEncoding="UTF-8"%>
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

		<title>代客下单</title>

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
		<form action="AddOrderAction" method="post">
			<table width="70%" border="0" align="center" cellspacing="1"
				   style="background-color:#d2d2da;line-height: 250%;">
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							所属酒店：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF" >
							<%
								Connection conn = DBManager.getConnection();
								String id = request.getParameter("id");//测试房型
								String pid = request.getParameter("pid");//测试酒店
								System.out.println("pid="+pid);
								System.out.println("id111111111111+++="+id);
								String sqlRoom = "SELECT r.*,s.* FROM hotelroom r LEFT JOIN hotelsupplier s ON r.pid = s.id where r.id="+id;
								PreparedStatement pstmRoom = conn.prepareStatement(sqlRoom);
								ResultSet rsRoom = pstmRoom.executeQuery();
								rsRoom.next();
								System.out.println("SQL="+sqlRoom);
							%>
							<%=rsRoom.getString("hotelName")%>
							<input type="hidden" name="hotelId" value="<%=pid%>" />
					</td>
				</tr>
				<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">房型：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
						<%=rsRoom.getString("roomName")%>
						<input type="hidden" name="itemId" value="<%=id%>" />
					</td>
			</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">
							游客：
						</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<input name="name" type="text" id="name">
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 手机号： </div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label><input name="mobile" type="text" id="mobile"></label>
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 身份证： </div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label><input name="idCard" type="text" id="idCard"></label>
					</td>
				</tr>
				
				<tr>
					<td bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 房间数： </div>
					</td>
					<td bgcolor="#FFFFFF">
						<label>
						<input name="roomNum" type="text" id="roomNum"></label>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						<div align="right" class="STYLE3"> 预定日期： </div>
					</td>
					<td bgcolor="#FFFFFF">
						<label><input name="checkInDate" type="text" id="checkInDate"></label> 
					
					</td>
				</tr>
				<tr>
					<td width="28%" bgcolor="#FFFFFF">
						<div align="right" class="STYLE3">离店日期：</div>
					</td>
					<td width="72%" bgcolor="#FFFFFF">
						<label><input name="checkOutDate" type="text" id="checkOutDate"></label>
					</td>
				</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">金额：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
					<label><input name="sum" type="text" id="sum"> </label> 
	
				</td>
			</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">支付状态：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF">
					<label><select name="payStatus">
						<option value="0">已支付</option>
						<option value="1">未支付</option></select></label> 
					<%
 	if (rsRoom != null) {
 		rsRoom.close();
	}
	if (pstmRoom != null) {
		pstmRoom.close();
	}
 	if (conn != null) {
 		conn.close();
 	}
 %>
				</td>
			</tr>
			<tr>
				<td width="28%" bgcolor="#FFFFFF">
					<div align="right" class="STYLE3">支付方式：</div>
				</td>
				<td width="72%" bgcolor="#FFFFFF"><select name="payWay">
						<option value="0">前台支付</option>
						<option value="1">微信支付</option>
						<option value="2">度假币支付</option>
				</select></td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 订单状态： </div>
				</td>
				<td bgcolor="#FFFFFF">
					<select name="orderStatus">
						<option value="0">有效</option>
						<option value="1">失效</option>
						<option value="2">删除</option>
						<option value="3">退款</option>
				</select>
				</td>
			</tr>
			<!-- <tr>
				<td bgcolor="#FFFFFF">
					<div align="right" class="STYLE3"> 下单时间： </div>
				</td>
				<td bgcolor="#FFFFFF">
				<label><input name="orderTime" type="text" id="orderTime"> </label> 
				</td>
			</tr> -->
				<tr>
					<td colspan="2" bgcolor="#FFFFFF">
						<label>
							<div align="center">
								<input type="submit" name="Submit" value="提交">
								<input type="reset" name="Submit2" value="重置">
							</div></label>
						
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
