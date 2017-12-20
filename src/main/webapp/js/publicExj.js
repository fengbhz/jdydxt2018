/**
 * @author lk
 */
p_View = function() {
	var appWin = null;
	return {
		init : function() {
		},
		closeWindow : function() {
			appWin.hide();
		},
		showAppInfo : function(url,id, flag) {
			if (!appWin) {
				appWin = new ExtWin(
						{
							width : 800,
							maximizable : true,
							maximized : true,
							modal : true, // 设置遮罩即只能操作最上层
							draggable : true,
							height : 600,
							animateTarget : null,
							layout : 'fit',
							html : "<iframe name='launchWinFrame' id='launchWinFrame' src='' scrolling='no' frameborder='0' width='100%' height='100%' ></iframe>",
							listeners : {
								hide : function() {
									document.getElementById("launchWinFrame").src = '';
								}
							}
						});
			}
			appWin.show();
			document.getElementById("launchWinFrame").src = url+ "?id=" + id+"&status="+flag;
		}
	}
}();
Ext.onReady(p_View.init, p_View);