<%@ page language="java"
import="java.util.*,java.sql.*,com.vdong.commons.db.*, java.text.*,com.vdong.commons.util.*,org.apache.commons.lang3.StringUtils"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		      String  ids= "";
		      String  roleCode= "";
			try{
	            ids= null==request.getAttribute("id").toString()?request.getParameter("id"):request.getAttribute("id").toString();  // 后台传过来的ID
                roleCode= null==request.getAttribute("roleCode").toString()?request.getParameter("roleCode"):request.getAttribute("roleCode").toString();  
			}catch(Exception e){
			    ids=request.getParameter("id");
			    roleCode=request.getParameter("roleCode");
			}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ext_icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ext_patch_firefox.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/ux/css/ux-all.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>laypage/laypage.js"></script>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ux/ux-all.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ux/Toast.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ux/uploadpanel/js/swfupload.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ux/uploadpanel/js/uploadPanel.js"></script>
<script type="text/javascript" src="<%=basePath%>js/extjs/ui/ext-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/publicExj.js"></script>
<script type="text/javascript">
	var url='<%=basePath%>';
	var  openW;
	var roleCode='<%=roleCode%>';
		 var pages =0;
</script>
<script type="text/javascript" src="<%=basePath%>admin/js/order.js"></script>
</head>
<body leftmargin="8" topmargin="8">
	<!--  搜索表单  -->
	<form  id="from1"   action="platform/OrderList.html" method="post">
		<table width='98%' border='0' cellpadding='1' cellspacing='1'
		 align="center"  style="margin-top: 8px; line-height: 200%;background-color: #eef4ea; padding-left: 20px;">
		    <input id="id"  value="<%=ids%>" name="id" type="hidden">
		     <input id="roleCode"  value="<%=roleCode%>" name="roleCode" type="hidden">
		     <input id="pages" name="pages"  type="hidden">
			<tr>
				<td style="width: 6%"> 状态：</td>
				<td style="width: 30%">
				<select name="orderStatus"  id="orderStatus" style="width: 150px"><!--
							<option value="">订单状态</option>
				      <c:forEach  items="${_dlist}" var="v" >
	                        <option value="${v.DMZ}">${v.DMMC}</option>
                      </c:forEach>
					-->
						    <option value="">订单状态</option>
				      
	                        <option value="0">已下单(未付款)</option>
                      
	                        <option value="1">取消</option>
                      
	                        <option value="2">已付款</option>
                      
	                        <option value="3">等待入住</option>
                      
	                        <option value="4">无房（退款）</option>
                      
	                        <option value="5"> 退款中</option>
                      
	                        <option value="6"> 退款成功</option>
                      
	                        <option value="7">已入住</option>
                      
	                        <option value="8">未住过期</option>
					
					
					
					</select>
					
					<select name="payStatus"  id="payStatus"  style="width: 150px">
							<option value="">支付状态</option>
							<option value="1">已支付</option>
							<option value="0">未支付</option>
					</select>
					
					<select  id="payWay" name="payWay"  style="width: 150px">
							<option value="">支付方式</option>
							<option value="1">微信支付</option>
							<option value="2">度假币支付</option>
					</select>
				</td>
				</tr>
			<tr>
			<td  style="width: 4%">查询条件： </td>
		    <td><input type='text'   name='phoneNum'  id='phoneNum' value='' placeholder="手机号" style='width: 150px' />
		    
		      <input type='text'  name='orderNum'   id='orderNum' value='' placeholder="订单号" style='width: 150px' />
		    
		      <input type='text' name='hotelName' id='hotelName'  value='' placeholder="酒店名称" style='width: 150px' /></td>
			
			</tr>
			<tr>
				<td>入离店时间：</td>
				<td  colspan="1" style=" width: 30%;">
				<input class="Wdate" type="text" id="checkInDate"   name="checkInDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style=" border-radius: 5px" />
				-<input class="Wdate" type="text" id="checkOutDate"    name="checkOutDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style=" border-radius: 5px" />
		<!--	</td>
				</tr>
			<tr>
			-->


				</td>
				<td style="width:30%">
					<label style="padding-left: 88px">核销时间：</label>
				     	<input class="Wdate" type="text" id="VerifitimeStart"    name="VerifitimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style=" border-radius: 5px" />
				        -<input class="Wdate" type="text" id="VerifitimeEnd"     name="VerifitimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style=" border-radius: 5px" />
				</td>
				<td>
				<input name="sub"  id="sub"  type="button" border="0" class="np"  style="  width: 40px;"  value='查询' /> &nbsp;&nbsp;|&nbsp;&nbsp;
				<input name="export" type="button" id="export" border="0" class="np"  style="  width: 40px;"  value='导出' /></td>
			</tr>
		</table>
	</form>
	<!--  快速转换位置按钮  -->
	<!--  内容列表   -->
	<form name="form2" >
		<div id="printTable">
			<table width="98%" border="0" cellpadding="2" cellspacing="1"
				align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 250%; ">
				<tr bgcolor="#E7E7E7">
					<td height="24" colspan="16" background="admin/images/tbg.gif">
						&nbsp;订单信息列表&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22"  id="tr01">
					<td width="6%">订单号</td>
					<td width="6%">所属酒店</td>
					<td width="6%">房型</td>
					<td width="4%">游客</td>
					<td width="6%">手机号</td>
					<td width="3%">数量</td>
					<td width="6%">预定日期</td>
					<td width="6%">离店日期</td>
					<td width="3%">金额</td>
					<td width="5%">支付状态</td>
					<td width="5%">支付方式</td>
					<td width="6%">订单状态</td>
					<td width="6%">下单时间</td>
					<td width="10%">操作</td>
				</tr>
			</table>
		</div>
	</form>
	   <div ></div>
	   <div style="  margin-top: 20px; margin-left: 20px;" id="biuuu_city"></div>
</body>
<script type="text/javascript">

	     $("#export").on("click", function () {
	          window.location.href= url+"order/exportExcel.html";
            });
            
	    $("#sub").on("click", function () {
	          fycx();
	          test();
	    });

//测试数据
    fycx();
var nums = 15; //每页出现的数量

//调用分页
laypage({
    cont: 'biuuu_city',
    pages: pages,
    jump: function(obj){
       fycx(obj.curr); 
    }
})
</script>

</html>