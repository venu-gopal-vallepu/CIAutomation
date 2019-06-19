Ext.define('Bluesage.TSI.store.Suites', {
	extend : 'Ext.data.Store',
	alias: 'store.suites',
    storeId:'suites',
    autoLoad:true,
	fields : [ 'name', 'key' ],
	data : [ 
//	        {"name":"Bluesage - DEV - CRM - NewContact","value":"Con_1,Bluesage - DEV - CRM - NewContact"},
//			{"name":"Bluesage - DEV - CRM - PurchaseQuotes","value":"Quo_1,Bluesage - DEV - CRM - PurchaseQuotes"},
//			{"name":"Bluesage - DEV - CRM - RefinanceQuotes","value":"RQuo_1,Bluesage - DEV - CRM - RefinanceQuotes"},
		//	{"name":"LendingHive - DEV - LHIVE - ApplyOnline","value":"LHive_1,LendingHive - DEV - LHIVE - ApplyOnline"},
	 //   {"name":"Bluesage - DEV - CRM - Create Contact,Quote and complete the loan through funding ","value":"Loa_1,Create Contact,Quote and complete the loan through funding"},
//		    {"name":"PHL - QA - LOS - create a NewLoan and complete the screens through funding","value":"SLos_1,PHL - Stage - LOS - create a NewLoan and complete the screens through funding"},
//		    {"name":"PHL - DEV - LOS - create a NewLoan and complete the screens through funding","value":"PLos_1,PHL - DEV - LOS - create a NewLoan and complete the screens through funding"},
//		    {"name":"PHL - QA - Portal & LOS - Create a PriorApprovalVALoan in CORE and complete the Loan in LOS","value":"ScoreVA_2,PHL - Stage - Portal & LOS - Create a PriorApprovalVALoan in CORE and complete the Loan in LOS"},
//		    {"name":"PHL - QA - Portal & LOS - Create a PriorApprovalUSDALoan in CORE and complete the Loan in LOS","value":"ScoreUSDA_2,PHL - Stage - Portal & LOS - Create a PriorApprovalUSDALoan in CORE and complete the Loan in LOS"},
//		    {"name":"PHL - DEV - Portal & LOS - Create a PriorApprovalFHALoan in CORE and complete the Loan in LOS","value":"ScoreFHA_2,PHL - DEV - Portal & LOS - Create a PriorApprovalFHALoan in CORE and complete the Loan in LOS"},
//		    {"name":"PHL - DEV - Portal & LOS - Create a LockPriorFHALoan in CORE and complete the Loan in LOS","value":"ScoreLockFHA_2,PHL - DEV - Portal & LOS - Create a LockPriorFHALoan in CORE and complete the Loan in LOS"},
//		    {"name":"Bluesage - DEV - POS & LOS - Create a Loan in POS and complete the Loan in LOS","value":"POSLOS_2,Bluesage - DEV - POS & LOS - Create a Loan in POS and complete the Loan in LOS"},
//		    {"name":"SpringEQ - DEV - CRM - create a NewLoan and complete the screens through funding","value":"SCRM_1,SpringCRM - DEV - CRM - create a NewLoan and complete the screens through funding"},
//		    {"name":"SpringEQ - Dev - CRM & LOS - create a NewLoan in CRM and complete the Loan in LOS","value":"SDCRMLOS_2,SpringEQ - Dev - CRM & LOS - create a NewLoan in CRM and complete the Loan in LOS"},
//		    {"name":"SpringEQ - UAT - CRM & LOS - create a NewLoan in CRM and complete the Loan in LOS","value":"SCRMLOS_2,SpringEQ - UAT - CRM & LOS - create a NewLoan in CRM and complete the Loan in LOS"},
//		    {"name":"Homebridge - UAT - CRM & LOS - create a NewLoan in CRM and complete the Loan in LOS","value":"HCRMLOS_2,Homebridge - Dev - CRM & LOS - create a NewLoan in CRM and complete the Loan in LOS"},
		    {"name":"Homebridge - Dev - Portal & LOS - Create a Conventional Loan in Portal and complete the screens through funding","value":"HBDEVPORCONV_2,Homebridge - Dev - Portal & LOS - Create a Conventional Loan in Portal and complete the screens through funding"}
		    /*{"name":"PLOS - PHLCoreFHALoan - Add","value":"fha_4,PHLCoreFHALoan - Add"},
			{"name":"PLOS - PHLCoreConvLoan - Add","value":"conv_4,PLOS - PHLCoreConvLoan - Add"},
			{"name":"PLOS - PHLCoreVALoan - Add","value":"va_4,PLOS - PHLCoreVALoan - Add"},
			{"name":"BDEV - Bluesage_Dev-LOS - Add","value":"Bdev_1,BDEV - Bluesage_Dev-LOS - Add"},
			{"name":"PDEV - Planet_Dev-LOS - Add","value":"Pdev_1,PDEV - Planet_Dev-LOS - Add"},
			{"name":"SLOS - Stage_Dev-LOS - Add","value":"Slos_1,SLOS - Stage_Dev-LOS - Add"},
			{"name":"SCORE - StagePHLCoreLoan - Add","value":"Score_1,SCORE - StagePHLCoreLoan - Add"},*/
			
			
       
	         
	],
	//  autoLoad:true,
	proxy : {
		type : 'memory',//'ajax',
		//  url : 'Display/listScripts',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}

});