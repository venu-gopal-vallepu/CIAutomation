Ext.define('Bluesage.TSI.view.CenterPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.centerpanel',
	
	requires : [
	    'Bluesage.TSI.store.Suites',
	    'Bluesage.TSI.view.IFrame',
	    'Bluesage.TSI.view.CustomTabPanel'
	],

	width : '100%',
	layout : 'fit',
	items : [{
	    xtype:'customtabpanel',
	    hidden : true
	    
	 
	}],
	tbar : [{
		xtype:'combo',
		fieldLabel: 'Select Script/Suite',
		labelWidth : 200,
	    width : 500,
		store: 'Suites',
		queryMode: 'local',
		displayField: 'name',
		valueField: 'value',
		emptyText:'Select Script',
		editable:false
	},{
		xtype : 'button',
		text : 'Execute',
		itemId : 'executeBtn'
	}]

});