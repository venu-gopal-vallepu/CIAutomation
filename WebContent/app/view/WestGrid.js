Ext.define('Bluesage.TSI.view.WestGrid', {
	extend: 'Ext.grid.Panel',
    alias: 'widget.westgrid',
   
    requires : [
        	    'Bluesage.TSI.store.Scripts'            
        	],
    
    columns : [{ text: 'Scripts', dataIndex:'name', width:150, align:'left',padding: 20,border:false, sortable:false, menuDisabled:true }],
    store:'Scripts',
    cls:'westgrid' 
	
});	