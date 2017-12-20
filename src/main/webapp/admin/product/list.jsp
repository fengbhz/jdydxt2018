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
		<title>产品列表</title>
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/css/ext-all.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ext_icon.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/ext_patch_firefox.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/extjs/ux/css/ux-all.css"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ext-all.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ux/ux-all.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ux/Toast.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ux/uploadpanel/js/swfupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ux/uploadpanel/js/uploadPanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/extjs/ui/ext-ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/publicExj.js"></script>
		<script type="text/javascript" src="<%=basePath%>laypage/laypage.js"></script>

	</head>
	<script type="text/javascript">
        var url='<%=basePath%>';
        var  openW;
        var pages =0;
	</script>
	<script type="text/javascript" src="<%=basePath%>admin/js/product.js"></script>
	<body leftmargin="8" topmargin="8" style="margin-bottom: 50px">
		<!--  搜索表单  -->
		<form action="admin/product/list.jsp" method="post">
			<table width='98%'    border='0' cellpadding='1' cellspacing='1'   align="center" style="margin-top: 8px;background-color:#d2d2da">
				<tr bgcolor='#EEF4EA'>
					<td align='center'>
						<table width="469" border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td width='129'>
									请输入产品名：
								</td>
								<td width='170'>
									<input type='text' name='queryName' value=''
										style='width: 150px' />
								</td>
								<td width='86'>
									<input name="imageField" type="submit" border="0" class="np"
										value='查询' />
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
				align="center" style="margin-top: 8px;background-color:#d2d2da;line-height: 200%;margin-bottom: 50px">
					<tr bgcolor="#E7E7E7">
						<td height="24" colspan="10" background="admin/images/tbg.gif">
							&nbsp;产品列表&nbsp;
						</td>
					</tr>
					<tr align="center" bgcolor="#FAFAF1" height="22"  id="tr01">
						<td width="10%">
							排序
						</td>
						<td width="30%">
							酒店名称
						</td>
						<td width="15%">
							产品名称
						</td>
						<td width="10%">
							度假币(平常)
						</td>
						<td width="10%">
							实体货币(平常)
						</td>
					<!--  	<td width="10%">
							支付方式
						</td>-->
						<td width="5%">
							价格设置
						</td>
						<td width="15%">
							操作
						</td>
					</tr>

					<tr bgcolor="#FAFAF1">
						<td height="28" colspan="10">
							&nbsp;
							<a href="product/addproduct.html"><strong>添加</strong>
							</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
			<div ></div>
			<div style="  margin-top: 20px; margin-left: 20px;" id="biuuu_city"></div>

	<script type="text/javascript">
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


		    function updatePrice(id){
		      var url1="product/priceManager.html";
	         p_View.showAppInfo(url1,id,"");
		    }

	    </script>

	</body>
</html>