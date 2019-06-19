Ext.define('Bluesage.TSI.view.scripts.ScriptsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.scripts',

    onExecuteBtnClick:function(btn){
        var form  = btn.up('form');
        var formvalues = form.getValues();
        if(formvalues.method == 1){
            Ext.Msg.show({
                    title: 'Script Scheduled',
                    message: 'Script is running',
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.INFO
                });
        }
        form.submit({
            // url:'http://localhost:8080/Bluesage.TSI/NavigationServlet',
            url: Utility.api.SCRIPTEXEC,
            scope:this,
            success:function(form, action){
                var store = Ext.data.StoreManager.lookup('automatedscripts');
                store.reload();
                form.reset();
                return Ext.Msg.show({
                    title: 'Script Scheduled',
                    message: action.result.Data,
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.INFO
                });
            },
            failure:function(form, action){ 
                return Ext.Msg.show({
                    title: 'Script Scheduled',
                    message: action.result.message,//'Request Failed',
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.ERROR
                });
            }
        });
    },
    onDeleteScriptClick:function(grid, rowIndex, colIndex){
        var rec = grid.getStore().getAt(rowIndex);
        var scriptid = rec.get('ScriptId');
        Ext.Ajax.request({
            //url : 'http://localhost:8080/Bluesage.TSI/DeleteScheduleScriptServlet',
            // url: 'http://localhost:8080/Bluesage.TSI/DeleteScheduledScripts',
            url: Utility.api.SCRIPTDEL,
            method:'POST', 
            //timeout:90000000,
            params : {
                scriptId: scriptid,
                scriptRequest: 'delScript'
            },
            scope : this,
            //method to call when the request is successful
            success : function(response){
                var store = Ext.data.StoreManager.lookup('automatedscripts');
                store.reload();
                var text =  Ext.JSON.decode(response.responseText);
                 return Ext.Msg.show({
                  title: 'Script Removed',
                  message:text.Data,//'Order created Successfully',
                  buttons: Ext.Msg.OK,
                  icon: Ext.Msg.INFO
                });
                
            },
            //method to call when the request is a failure
            failure : function(){
                return Ext.Msg.show({
                  title: 'Script Removed',
                  message:'Request Failed',// 'Failed to create Order, please try again',
                  buttons: Ext.Msg.OK,
                  icon: Ext.Msg.ERROR
                });
            }
        }); 
        
    },
    onEditScriptClick:function(grid, rowIndex, colIndex){
        
        var rec = grid.getStore().getAt(rowIndex);
        var data = rec.getData();
        var enddatetime = data.EndDate;
        var etime = Ext.util.Format.date(enddatetime,'Y-m-d g:i A ');
        var endarr = enddatetime.split(' ');
        var enddate = endarr[0];
        var endtime = etime.slice(etime.indexOf(' '))
        var startdatetime = data.StartDate;
        var stime = Ext.util.Format.date(startdatetime,'Y-m-d g:i A ');
        var startarr = startdatetime.split(' ');
        var startdate = startarr[0];
        var starttime = stime.slice(stime.indexOf(' '))
        var form = this.getView();
        //var form = view.down('form');
        form.lookupReference('scriptName').setRawValue(data.ScriptName );
        form.lookupReference('scriptName').setValue(data.ScriptValue+','+data.ScriptName);
        form.lookupReference('ScriptId').setValue(data.ScriptId);
        form.lookupReference('sascheck').setValue(true);
        form.lookupReference('executionCount').setValue(data.executionCount);
        form.lookupReference('startDT').setValue(startdate);
        form.lookupReference('startDTtime').setValue('0'+starttime.trim(' '));
        form.lookupReference('endDT').setValue(enddate);
        form.lookupReference('endDTtime').setValue('0'+endtime.trim(' '));
    }
});
