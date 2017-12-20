<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>统计反馈数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
 <script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript"  src="<%=basePath%>js/fenyetoFk.js"></script>
<style>
ul{
border:0px;
padding:0px;
margin:0px;
line-height	:200%;
list-style:none;
}
.ul{
list-style:none;
background-color: rgba(53, 50, 50, 0.25);
border: solid 1px;
}

.spanxh{
float:left;
width: 10%;
height: 20px;
}
.span{
float:left;
width: 18%;
height: 20px;
}
.ewm {clear:both;width:100%;text-align:center}
.ul li{line-height: 200%;
}



*{ margin:0; padding:0; list-style:none;}
a{ text-decoration:none;}
a:hover{ text-decoration:none;}
.tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
.tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
.tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
input{height: 28px;
 border-radius: 5px;
 }
 .wrap{ width:auto; max-width:640px; margin:0 auto;}
.center{ width:103%; margin:0 auto;}
 /***************分页******************/
.fenye{ float:left; margin-top:10px;}
.fenye ul{ float:left; margin-left:32px; }
.fenye ul li{ float:left; margin-left:5px;padding: 4px 6px; border:1px solid #ccc;  font-weight:bold; cursor:pointer; color:#999;}
.fenye ul li a{ color:#999;}
.fenye ul li.xifenye{ width:38px; text-align:center; float:left; position:relative;cursor: pointer;}
.fenye ul li .xab{ float:left; position:absolute; width:39px; border:1px solid #ccc; height:123px; overflow-y: auto;overflow-x: hidden;top:-125px; background-color: #fff; display:inline;left:-1px; width:50px;}
.fenye ul li .xab ul{ margin-left:0; padding-bottom:0;}
.fenye ul li .xab ul li{ border:0; padding:4px 0px; color:#999; width:34px; margin-left:0px; text-align:center;}
</style>
<script type="text/javascript">
  var url1="<%=basePath%>";

</script>
</head>
  <body>
   <div  style="float:left;width: 100%;height: 50px;margin-top: 50px;padding-left: 20px;">
   <div style="float:left;width: 18%">
   <span>会员名称   </span><input  type="text" value="" name="name" id="name">
   </div>
   <div style="float:left;width: 26%">
   <span>时间     </span><input class="Wdate" type="text" id="starttime" name="starttime"
					onfocus="WdatePicker({minDate:'%y%M%d'})" onblur="changetime('0')"
					style="height: 28px; border-radius: 5px" />-<input class="Wdate" type="text" id="endtime" name="endtime"
					onfocus="WdatePicker({minDate:'%y%M%d'})" onblur="changetime('0')"
					style="height: 28px; border-radius: 5px" />
   </div>
  
   <div style="float:left;width: 10%"><input style="width: 100px" type="button"  value="查询" onclick="fkfycx(0)"></div>
    <div style="float:left;width: 10%">
    <input style="width: 100px"    type="button"  value="导出" onclick="exporttoExcel()"></div>
   </div>
     <div style="clear:both;width:100%;height:50px;text-align: center">
       <ul>
       <li class="spanxh">序号</li>
       <li class="span">会员昵称</li>
       <li class="span">会员账号</li>
       <li class="span">密码</li>
       <li class="span">性别</li>
       <li class="span">手机</li>
       <li class="span">等级</li>
       <li class="span">操作</li>
       </ul>
       </div>
       <div class="ewm">
       <c:forEach var="i" begin="1" end="10" varStatus="status">
       <ul class="ul" id="url<c:out value="${status.index}" />"    >
       <li id="xh<c:out value="${status.index}" />"  class="spanxh">${status.index}</li>
       <li  id="yjrdm<c:out value="${status.index}" />" class="span">${status.index}</li>
       <li  id="yjrjgdm<c:out value="${status.index}" />" class="span"></li>
       <li  id="appid<c:out value="${status.index}" />" class="span"></li>
       <li  id="addtime<c:out value="${status.index}" />" class="span"></li>
        <li  id="statue<c:out value="${status.index}" />" class="span"></li>
       <input type="hidden"  id="id<c:out value="${status.index}" />" />
       </ul>
       </c:forEach>
       </div>
 <!--下一页--->
 <div class="wrap"    style="margin-top:30px;">
    <div class="fenye">
    	<ul>
        	<li id="first" onclick="first()">首页</li>
            <li id="top" onclick="topclick()">上一页</li>
            <li class="xifenye" id="xifenye" onclick="xifenye()">
            	<a id="xiye">1</a>/<a id="mo">66</a>
                <div class="xab" id="xab" style="display:none">
                	<ul id="uljia">
                    </ul>
                </div>
            </li>
            <li id="down" onclick="downclick()">下一页</li>
            <li id="last" onclick="last()">末页</li>
        </ul>
    
    </div>
</div>

       <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript"  src="<%=basePath%>js/jquery.page.js"></script>
  </body>
  
  <script type="text/javascript">
$(function(){
fkfycx(0);
zs();
});
function  zs(){
  for ( var i = 1; i < 11; i++) {
  if($("#xh"+i).text()==''){
 $("#url"+i).css("display","none");
  }else{
  $("#url"+i).css("display","block");
   $("#url"+i).css("height","50px");
  }
}
} 

</script>
</html>
