

function  fycx(numstr){
			 clearOldData();
			 var pageNum ='20';  // 一页显示10个
			 if(numstr=='0'){// 初始化分页显示
				 var  curPage="1";	// 当前页
			 }else{
				 var  curPage=numstr;// 当前页
			 }
		   var phone=$("#phone").val();
           var queryFriend=$("#queryFriend").val();

		   var jsonData={"queryFriend":queryFriend,"phone":phone, "pageNum":pageNum,"curPage":curPage};
			$.ajax({
				url:url+"user/fiendlist.html",
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
                         var friendName=json.friendName==null?'/':json.friendName;
                         var sum=json.sum==null?'0':json.sum;
						 str+="<tr align='center'  bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\"   height=\"22\"  class=\"tr\">";
						 str+="	 <td align=\"center\">  "+(i+1);
						 str+="	</td>  	<td  align=\"center\"> "+friendName ;
						 str+="	 </td>	<td   align=\"center\"> "+json.mobile ;
                         str+="	 </td>	<td   align=\"center\"> "+sum ;
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