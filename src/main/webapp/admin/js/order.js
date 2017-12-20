function changeOrder(obj,id){
	   var  status=  $(obj).find("option:selected").val();
	   var  s_text=  $(obj).find("option:selected").text();
	   if(status=='-1'||status=='6'||status=='2'){//  修改退款作废订单
	    $(obj).children().eq(0).attr('selected','selected');
	     var url1="platform/updateOrderJsp.html";
	     p_View.showAppInfo(url1,id,status);
	    return ;
	   }
	   if (confirm("确定要进行 \" "+$(obj).find("option:selected").text()+"\"  吗？")) {
	  		$.ajax({
						url : url+"platform/updateOrderStatus.html",
						data : {
							"id" : id,
							"status" :status
						},
						cache : false,
						async : false,
						type : "post",
						dataType : "json",
						success : function(data) {
						if(data.success){
						alert("操作成功");
						window.location.href= url+"platform/OrderList.html";
						    }
						}
					});
	      } 
	        $(obj).children().eq(0).attr('selected','selected');
	}
	
	function closeWindow(){
	  p_View. closeWindow();
	   location.reload();
	}
	
  function  changecolor(){
		$("tr").each(function(i) {
			  if($(this).attr("rec")=='6'){
			    $(this).css("background-color","#bda5a5");
			  }else if($(this).attr("rec")!="undefined"&&$(this).attr("rec")!=undefined){
			    $(this).css("background-color","#E3FFE0");
			  }
			});
  }	
	
	
	 $(window).load(function() {
		 changecolor();
	}); 
	
	     
	     function  fycx(numstr){
			 clearOldData();
			 var pageNum ='10';  // 一页显示10个
			 if(numstr=='0'){// 初始化分页显示
				 var  curPage="1";	// 当前页
			 }else{
				 var  curPage=numstr;// 当前页
			 }
			 debugger;
		   var phoneNum=$("#phoneNum").val();
		   var orderNum=$("#orderNum").val();
		   var VerifitimeStart=$("#VerifitimeStart").val();
		   var VerifitimeEnd=$("#VerifitimeEnd").val();
		   var checkInDate=$("#checkInDate").val();
		   var checkOutDate=$("#checkOutDate").val();
		   var hotelName=$("#hotelName").val();
		   var orderStatus=$("#orderStatus").val();
		   var payStatus=$("#payStatus").val();
		   var payWay=$("#payWay").val();
		   var jsonData={"phoneNum":phoneNum,"orderNum":orderNum,"checkInDate":checkInDate,"checkOutDate":checkOutDate,"VerifitimeStart":VerifitimeStart,
				   "VerifitimeEnd":VerifitimeEnd,"hotelName":hotelName,
				   "orderStatus":orderStatus,"payStatus":payStatus,
				   "payWay":payWay,
				   "pageNum":pageNum,"curPage":curPage};
			$.ajax({
				url:url+"order/json.html",
				data:jsonData,
				cache:false,
				async: false,
				type:"post",
				dataType:"json",
				success:function(_data) {
					// 定义好赋值字段的顺序
				  pages =_data.pagecount;
				  data=_data.info;
				    var str="";	
					for(var i = 0; i < data.length;i++) {
						var json = data[i];
						 str+="<tr align='center'   id=\"tr_d\"   rec='"+json.orderStatus+"'  class=\"tr\">";	
						 str+="<input type=\"hidden\"  name=\"oid\"  id=\"oid\"  value="+json.id+" >";
						 
						 str+="	<td > "+json.orderId+" </td> <td align=\"center\">  <div align=\"center\"> "+json.hotelName;	
						 str+="</div>	</td>  	<td  align=\"left\">  <div align=\"center\">"+json.roomName;	
						 str+="	</div></td>  	<td   align=\"left\">  <div align=\"center\">"+json.name;	
						 str+="	</div></td>  	<td   align=\"left\">  <div align=\"center\">"+json.mobile;	
						 str+="	</div></td>  	<td   align=\"left\">  <div align=\"center\">"+json.roomNum;	
						 str+="</div></td>  	<td   align=\"left\"> <div align=\"center\">"+json.checkInDate.substring(5, 10).replace("-", "/");	
						 str+="</div></td>  	<td   align=\"left\"> <div align=\"center\">"+json.checkOutDate.substring(5, 10).replace("-", "/");	
						 str+="	</div></td>  	<td   align=\"left\"> <div align=\"center\">"+json.sum;	
						 str+="	</div></td>  	<td   align=\"left\"> <div align=\"center\">";	
						  if(json.payStatus=='1'){
							  str+=" 已支付  ";
						  } else if(json.payStatus=='0'){
							  str+=" 未支付  ";
						  }
						  str+=" </div></td>  	<td   align=\"left\"> <div align=\"center\">";	
						  if(json.payWay=='1'){
							  str+=" 微信支付  ";
						  } else if(json.payWay=='2'){
							  str+=" 度假币支付  ";
						  }
						 str+="	</div></td>  	<td   align=\"left\"> <div align=\"center\">"+json.os;	
						 str+="	</div></td>  	<td   align=\"left\"> <div align=\"center\">"+json.orderTimes;	;	
						 str+="	</div></td>";
						 str+="<td> <select  name=\"select\"  id=\"select\"  style=\"width:80px;text-align:center;\"  onchange=\"changeOrder(this,'"+json.id+"')\"  >";	
						 str+=" <option value=\"0\" selected=selected>操作</option>";	
					
						 if(roleCode=='JD_MANAGER'){
							 if(json.orderStatus=='2'){
								 str+="<option value=\"3\">留房</option>";	
								 str+="<option value=\"4\">无房</option>";	
							 }
							 
							 if(json.orderStatus=='3'){
								 str+=" <option value=\"7\">进店核销</option>";	
							 }
						 }else if(roleCode=='PT_MANAGER'){
							 if(json.orderStatus=='3'){
								 str+="<option value=\"1\">订单作废</option>";	
							 } 
							 if(json.orderStatus=='3'){
								 str+="<option value=\"-1\">修改订单</option>";	
							 } 
							 
							 if(json.orderStatus=='5'){
								 str+="  <option value=\"6\">退款处理</option> ";	
							 } 
						 }
						 
						   str+=" </select> </td> </tr>";		
					}
					/*if(data.length == 0){
						alert("未查询到数据，请重新检索！");
					}*/
				     $("#tr01").after(str);
				     changecolor();
				}
			});
		 }
		
		//清除
		function clearOldData() {
			$(".tr").remove();
		}    
        
		function test(){
			laypage({
			    cont: 'biuuu_city',
			    pages: pages,
			    jump: function(obj){
			       fycx(obj.curr); 
			    }
			})
	}