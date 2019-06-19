Ext.define('Bluesage.TSI.store.AutomatedScripts', {
	extend : 'Ext.data.Store',
	alias: 'store.automatedscripts',
    storeId:'automatedscripts',
    autoLoad:true,
	fields : [ 'EndDate', 'StartDate','ScriptName', 'ScriptValue'],
	proxy : {
		type : 'ajax',
		// url : 'http://localhost:8080/Bluesage.TSI/GetScriptDetailsServlet',
		url: Utility.api.SCRIPTDETAILS,
		reader : {
			type : 'json',
			rootProperty : 'Data'
		}
	},
});