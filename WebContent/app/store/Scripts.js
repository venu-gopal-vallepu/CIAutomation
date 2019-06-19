Ext.define('Bluesage.TSI.store.Scripts', {
	extend : 'Ext.data.Store',

    fields:['name'],
    data:{'items':[
        { 'name': 'Execute' },
        { 'name': 'History' }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'items'
        }
    }
	
});