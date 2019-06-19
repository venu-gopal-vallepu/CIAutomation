Ext.define('Bluesage.TSI.view.MainContainer', {
	extend: 'Ext.container.Container',
	requires : [
	       'Bluesage.TSI.view.WestGrid',
	       'Bluesage.TSI.view.CenterPanel',
	       'Bluesage.TSI.view.NorthPanel'
	],
	
	height:1000,
	width:'100%',
	autoScroll:true,
	layout : 'border',
	items: [
	        {
	        	xtype: 'northpanel',
	        	region: 'north'
	        },
	        {
	        	xtype:'westgrid',
	        	region:'west',
	        	border:true,
	        	width:150
	        },
	        {
	        	xtype:'centerpanel',
	        	region:'center'
	        }
	        ]

});    
