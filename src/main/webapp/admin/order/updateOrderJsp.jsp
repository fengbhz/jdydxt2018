<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String order=request.getAttribute("orderL").toString();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'addOrder.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>jcss/styles.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<style>
	  li{
	  line-height: 200%
	  }
	</style>
  </head>
  <body  style="background-color: white">
<form  action="platform/updateOrderJsp.html" id="from1"  method="post"  enctype="multipart/form-data" >
<input type="hidden" name="id"  id="id"  value="${orderL.id}" >
<input type="hidden" name="status"  id="status"  value="${status}" >
  <div id="model" >
			<div class="modal_tilte">
			<c:if test="${status==-1}">修改订单</c:if>
			<c:if test="${status==6}">申请退款</c:if>
			<c:if test="${status==7}">订单核销</c:if>
			<c:if test="${status==2}">订单作废</c:if>
			</div>
			<div class="modal_content">
				<div class="modal_content_title">
					基本类型
				</div>
				<div class="modal_content_list">
					<ul>
						<li>房&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：           ${orderL.roomName}</li>
						<li>所属酒店：   ${orderL.hotelName}</li>
						<li>入住时间：
						<c:if test="${status==-1}">
						<input class="Wdate" type="text" id="checkInDate"   value="${orderL.checkInDate}"   name="checkInDate" onfocus="WdatePicker({minDate:new Date()})"  style=" border-radius: 5px" />
						</c:if>
						<c:if test="${status!=-1}">
						   ${orderL.checkInDate}
						</c:if>
						</li>
						
						<li>离店时间：
							<c:if test="${status==-1}">
						<input class="Wdate" type="text" id="checkOutDate"         value="${orderL.checkOutDate}"    name="checkOutDate" onfocus="WdatePicker({minDate:new Date()})"  style=" border-radius: 5px" />
						</c:if>
						<c:if test="${status!=-1}">
							    ${orderL.checkOutDate}
					   </c:if>
						</li>
						
						<li>游客姓名： 
							<c:if test="${status==-1}">
						     <input style='width:160px'  type="text" name="name"     value="${orderL.name}" id="name">
						  </c:if>
						  	<c:if test="${status!=-1}">
						  	  ${orderL.name}
						  	</c:if>
						</li>
						<li>手机号码： 
						
						
						 <c:if test="${status==-1}">
						<input style='width:160px'   type="text" name="mobile" value="${orderL.mobile}"   id="mobile">
						</c:if>
						<c:if test="${status!=-1}">
						    ${orderL.mobile}
						</c:if>
						</li>
						
						<li>预订数量：
						 <c:if test="${status==-1}">
						    <input style='width:160px' type="text" name="roomNum"      value="${orderL.roomNum}"  id="roomNum">
						 </c:if>
						 <c:if test="${status!=-1}">
						    ${orderL.roomNum}
						 </c:if>
						</li>
						
						<li>支付总额：${orderL.sum} </li>
						
						 <c:if test="${status==6}">
						<li style="padding-left: 20px;"><font style="color:red;padding-right: 10px" >*</font>退款总额：
						    <input style='width:160px' type="text" name="refuNum"      value="${orderL.sum}"  id="refuNum">
						</li>
						 </c:if>
						
						<li >支付类型：
						  <c:if test="${orderL.payWay==0}">代客下单 </c:if>
						  <c:if test="${orderL.payWay==1}">微信支付 </c:if>
						  <c:if test="${orderL.payWay==2}">度假币支付 </c:if>
						</li>
					</ul>
					<div class="textarea">
						<span id="textarea_title">
							说明：
						</span>
						<textarea name="remark"  id="remark"  value="${orderL.remark}"  rows="5" cols="40"></textarea>
					</div>
					
				</div>
				<div class="footer">
					<input class="cancellation"  type="button"  onclick="sub()"  value="确定"   style="font-size: 14px">
							<c:if test="${status==6}">
					<input class="cancellation" type="button"   onclick="sub('1')" value="拒绝"  style="font-size: 14px">
					       </c:if>
				</div>
			</div>
		</div>
		</form>
  </body>
  
  <script type="text/javascript">
      function sub(num){
        if(num==1){
        $("#status").val("2");
        }
      //  $("#from1").submit();
       //     window.parent.closeWindow();
            $.ajax({
                cache: true,
                type: "POST",
                url:"<%=basePath%>platform/updateOrderJsp.html",
                data:$('#from1').serialize(),// 你的formid
                async: false,
                error: function(request) {
                     window.parent.closeWindow();
                },
                success: function(data) {
                   if(data.success=true){
                    window.parent.closeWindow();
                   }
                }
            });
            
      }
  </script>



</html>



