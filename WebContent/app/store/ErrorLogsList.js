Ext.define('Bluesage.TSI.store.ErrorLogsList', {
	extend : 'Ext.data.Store',
	alias: 'store.errorlogslist',
    storeId:'errorlogslist',
    autoLoad:true,
	fields : [ 'ScriptName', 'DateRun','ErrorCount', 'Path'],
	proxy : {
		type : 'ajax',
		// url : 'http://localhost:8080/Bluesage.TSI/HtmlServlet',
		url : Utility.api.ERRORLOGSLIST,
		reader : {
			type : 'json',
			rootProperty : 'Data'
		}
	},
});