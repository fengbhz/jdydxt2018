<%@ page language="java" import="java.util.*,java.sql.*,com.vdong.commons.db.*"
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
		<title>业主管理</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	     <style>
	     input{
	         font-size: 15px;
	     }
	     #u6136_div {
    position: absolute;
    left: 0px;
    top: 0px;
    width: 77px;
    height: 39px;
    background: inherit;
    background-color: rgba(25, 158, 216, 1);
    border: none;
    border-radius: 4px;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    font-family: 'PingFangTC-Regular', 'PingFang TC';
    font-weight: 400;
    font-style: normal;
    font-size: 14px;
}
	    /*  table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;} */
	     </style>
		<script type="text/javascript">
            var url='<%=basePath%>';
			 function  exportExcel(){
			  document.location.href="<%=basePath%>";  
			 }
		</script>
		<script type="text/javascript" src="<%=basePath%>laypage/laypage.js"></script>

	</head>

	<script type="text/javascript" src="<%=basePath%>admin/js/HolidayMoney.js"></script>
	<body leftmargin="8" topmargin="8">
		<!--  搜索表单  -->
		<form  id="from1" action="admin/owner/HolidayMoney.jsp" method="post">
			<table width='98%' border='0' cellpadding='1' cellspacing='1'
				bgcolor='#CBD8AC' align="center" style="margin-top: 8px">
				<tr bgcolor='#EEF4EA'>
					<td align='center'>

						<table width="100%" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129' style='text-align:right;'>
									业主姓名：
								</td>
								<td width='170'>
									<input type='text' name='queryName' id="queryName" value='' style='width: 150px' />
								</td>
								<td width='129' style='text-align:right;'>
									亲友姓名：
								</td>
								<td width='170'>
									<input type='text' name='queryFriend'  id="queryFriend" value='' style='width: 150px' />
								</td>
								<td width='86'>
								<div style="text-align: left; margin-top: 5px; width:100%; margin-right: auto;margin-left: 5px;">
		    	                  <input id="resetBtn" type="button"  value="搜索" style="width:80px; height:30px; background:url(images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;"/>
		                        </div>
								</td>
								<td width='86'>
								<div style="text-align: left; margin-top: 5px; width:100%; margin-right: auto;margin-left: 5px;">
		    	                  <input id="export" type="button"  value="导出excel" style="width:80px; height:30px; background:url(images/btnbg.png) no-repeat; font-size:14px;font-weight:bold;font-family:'Microsoft YaHei';color:#fff; cursor:pointer;"/>
		                        </div>
								</td>
								<td width="84">
									&nbsp; 
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<!--  快速转换位置按钮  -->
		<!--  内容列表   -->
		<form name="form2">
			<div id="printTable">
				<table width="98%" border="0" cellpadding="2" cellspacing="1"
					bgcolor="#3333FF" align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%">
					<tr align="center" bgcolor="#FAFAF1" height="22"  id="tr01">
						<td width="8%">
						          流水号
						</td>
						<td width="10%">
							业主名称
						</td>
						<td width="10%">
							亲友名称
						</td>
						<td width="15%">
							赠送额度
						</td>
						<td width="8%">
							赠送时间
						</td>
						<td width="5%">
						      领取时间
						</td>
					</tr>
				</table>
			</div>
			
		</form>
		</form>
		<div ></div>
		<div style="  margin-top: 20px; margin-left: 20px;" id="biuuu_city"></div>

		<script type="text/javascript">
            $("#resetBtn").on("click", function () {
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


	    $("#export").on("click", function () {
	        window.location.href="<%=basePath%>money/exportExcel.html";
	   //$("#from1").submit();
           });


	</script>
	</body>
</html>