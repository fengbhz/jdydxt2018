
	     
	     function  fycx(numstr){
			 clearOldData();
			 var pageNum ='20';  // 一页显示10个
			 if(numstr=='0'){// 初始化分页显示
				 var  curPage="1";	// 当前页
			 }else{
				 var  curPage=numstr;// 当前页
			 }
		   var queryName=$("#queryName").val();

		   var jsonData={"queryName":queryName, "pageNum":pageNum,"curPage":curPage};
			$.ajax({
				url:url+"product/list.html",
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
                        var id = json.id;
                        var pid = json.pid;
                        var deleted = json.isdeleted;

                        if(deleted=="0"){
                            str+="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" class=\"tr\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\">";
						}else{
                            str+="<tr align='center' bgcolor=\"#F2F2F2\"  height=\"22\"  class=\"tr\">";
                        }
						 str+="	 <td align=\"center\">  "+json.id;
						 str+="	</td>  	<td  align=\"center\"> "+json.hotelName ;
						 str+="	 </td>	<td   align=\"center\"> "+json.roomName;
                         str+="	 </td>	<td   align=\"center\"> "+json.virtualCoin;
                         str+="	 </td>	<td   align=\"center\"> "+json.coin;
						 str+="	</td>  	<td  > <a   onclick=\"updatePrice('"+json.id+"')\"   href=\"javascript:void(0)\" >批量设置</a>  "
                         str+="	</td>  	<td  > 	<a href=\"product/updateProduct.html?id="+json.id+"\">编辑</a> | "

                        if(deleted=='0'){
                            str+=" 	<a href=\"DelProductAction?type=1&id="+json.id+"\">停用</a>";
                        }else{
                            str+=" <a href=\"DelProductAction?type=0&id="+json.id+"\">启用</a>";
                        }
                        str+="	<a href=\"admin/product/addOrder.jsp?id="+json.id+"&pid="+json.pid+"\">代客下单</a> | "
						   str+="  </td> </tr>";
					}
					if(data.length == 0){
						alert("未查询到数据，请重新检索！");
					}
				     $("#tr01").after(str);

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