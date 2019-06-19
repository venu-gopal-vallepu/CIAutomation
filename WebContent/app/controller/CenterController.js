Ext.define('Bluesage.TSI.controller.CenterController', {
	extend: 'Ext.app.Controller',

	control: {
		"#executeBtn": {
			click: 'onBtnClick'
		}
	},
	refs : [{
		ref : 'customtabpanel',
		selector : 'customtabpanel'
	}],

	onBtnClick : function( btn ) {
     
		var comboValue = btn.up('panel').down('combo').getValue();
		var me = this;
		//if( !Ext.isEmpty( comboValue )){
			/*Ext.Ajax.request({
				method:'GET',
				url:'http://192.168.1.32:9443/Jenkins/JenkinsServlet?action=count&jobName='+comboValue,
				success:function(response){
					var responseObj = Ext.JSON.decode(response.responseText);
					if( responseObj.status == "success"){
						this.latestBuild = responseObj.maxcount;
					}
					
					Ext.data.JsonP.request({
						method:'GET',
						url:'http://192.168.1.214:8080/job/'+comboValue+'/build',
					}, this);
					
					if( !Ext.isEmpty( this.latestBuild )){
						var iframe = Ext.create('Bluesage.TSI.view.IFrame',{
							title : 'Output',
							src : 'http://192.168.1.214:8080/job/'+comboValue+'/'+(this.latestBuild+1)+'/console'
						});
						
						
						var reports = Ext.create('Bluesage.TSI.view.IFrame',{
							title : 'History',
							src : 'http://192.168.1.214:8080/job/'+comboValue+'/ws/XSLT_Reports/output/index.html'
						});
						
					}
					var controller = Bluesage.TSI.getApplication().getController("CenterController");
					controller.getCustomtabpanel().removeAll();
					controller.getCustomtabpanel().add( iframe );
					controller.getCustomtabpanel().add( reports );
					controller.getCustomtabpanel().show();
					
//					var header = document.getElementById("header");
//					header.parentNode.removeChild(header);
				},
				failure:function(response){
					alert('response failure=='+response)
				}
			}, this);*/
		//}
		//create an AJAX request
        Ext.Ajax.request({
            url : 'ScriptRunProcess',
            method:'POST', 
            timeout:90000000,
            params : {
            	comboValue:comboValue
            },
            scope : this,
            //method to call when the request is successful
            success : this.onLoginSuccess,
            //method to call when the request is a failure
            failure : this.onLoginFailure
    }); 
	},
	
	 onLoginFailure : function(comboValue) {
		 alert("failure");
    	
  },

     onLoginSuccess : function(response, opts,comboValue) {
    	// alert("success");
         var src1;
    	 Ext.Ajax.request({
             url : 'ScriptRunProcess',
             method:'POST', 
             params : {
             	report:'report'
             },
             scope : this,
             //method to call when the request is successful
             success : function(response){
            	debugger;
               var appUrl = window.location.href;
            	 var reportPath = 'XSLTReports/output/index.html';
            	// alert("success")
            	 src1 = appUrl+''+reportPath;
            	var reports = Ext.create('Bluesage.TSI.view.IFrame',{
 				title : 'History',
 				src : appUrl+''+reportPath
 									
 			});
             var controller = Bluesage.TSI.getApplication().getController("CenterController");
 			controller.getCustomtabpanel().removeAll();
 			function openWindowReload(link) {
			    var href = link.href;
			    window.open(href,'_src1');
			    setTimeout(function() {
			        document.location.reload(true);
			    },6000);
			}
 		controller.getCustomtabpanel().add( reports );
 		controller.getCustomtabpanel().show();
 		//window.open (src1,"_self");
             },
             //method to call when the request is a failure
             failure : function(response) {
            	 alert('failure1');
             	            }
     });
    	 
     }
     
});