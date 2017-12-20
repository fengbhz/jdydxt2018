
	     
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
				url:url+"hotel/roomlist.html",
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
  debugger;
                        var  id = json.id;
                        var  status =json.status;
						 str+="<tr  align='center' bgcolor=\"#FFFFFF\"   id=\"tr_d\"   rec='"+json.orders+"'  class=\"tr\">";
						 str+="<input type=\"hidden\"  name=\"id\"  id=\"oid\"  value="+json.id+" >";
						 
						 str+="	<td > "+json.orders+" </td> <td align=\"center\">  "+json.hotelName;
						 str+="	</td>  	<td  align=\"center\"> "+json.jiudianKind+"</td><td>" ;
						 if(status=='0'){
                             str+=" <img alt='' width=\"25px\" height=\"25px\" src=\"upload/effective.png\">";
						 }else{
                             str+=" <img alt='' width=\"25px\" height=\"25px\" src=\"upload/invalid.png\">";
						 }


						 str+="	 </td>	<td   align=\"center\"> "+json.submitDate;
						 str+="	</td>  	<td  > 	<a href=\"admin/jiudian/modHotel.jsp?id="+json.id+"\">编辑</a> "


                        if(status=='0'){
                            str+=" 	<a href=\"DelHotelAction?id="+json.id+"&status=1\">停用</a>";
                        }else{
                            str+=" <a href=\"DelHotelAction?id="+json.id+"&status=0\">启用</a>";
                        }
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