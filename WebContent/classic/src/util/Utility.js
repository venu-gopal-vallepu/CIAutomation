Ext.define('Bluesage.TSI.util.Utility', {
	singleton: true,
	
	alternateClassName: ['Utility'],
	
	api: (function() {
		// var hostUrl = "http://localhost:8080/",
		var hostUrl = "/",
			base = "ScriptsApp3.5/";

		return {
			LOGIN: hostUrl + base + 'login', // Login.js
			SCRIPTEXEC: hostUrl + base + 'NavigationServlet', // ScriptsController.js > onExecuteBtnClick
			SCRIPTDEL: hostUrl + base + 'DeleteScheduledScripts', // ScriptsController.js > onDeleteScriptClick
			ERRORLOGSLIST: hostUrl + base + 'HtmlServlet', // ErrorLogsList.js
			SCRIPTDETAILS: hostUrl + base + 'GetScriptDetailsServlet' // AutomatedScripts.js store
		};
	})()
});