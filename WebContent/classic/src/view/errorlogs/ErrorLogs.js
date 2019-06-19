Ext.define('Bluesage.TSI.view.errorlogs.ErrorLogs', {
    extend: 'Ext.form.Panel',
    xtype: 'errorlogs',
    requires: [
        'Ext.form.FieldSet',
        'Ext.window.MessageBox',
        'Ext.grid.Panel'
    ],
    bodyStyle: 'padding:5px 5px 0',
    width: '100%',
    height:'100%',
    scrollable :true,
    layout: {type:'vbox', align:'stretch'},
    defaults:{
        margin: '0 5 10',
    },
    items: [{
        xtype:'fieldset',
        title: 'Error Logs',
        //collapsible: true,
        // border:false,
        defaultType: 'textfield',
        defaults: {anchor: '100%'},
        layout: 'anchor',
        items: [{
            xtype: 'grid',
            store:'errorlogslist',
            columns: [
                { text: 'Script', dataIndex: 'scriptName' ,flex: 1},
                { text: 'Date Run', dataIndex: 'date' ,flex: 1},
                { text: 'Total Errors', dataIndex: 'error_count',flex: 1 },
                { text: 'Error Report', dataIndex: 'path',flex: 1,
                renderer: function(value){
                 return '<a href='+'"'+value+'"'+' target="_blank">download</a>';
                }
                }
            ],
            height: 500,
            dockedItems: [{
                xtype: 'pagingtoolbar',
                store: 'errorlogslist', // same store GridPanel is using
                dock: 'bottom',
                displayInfo: true
            }],
           // width: 400,
        }]
    }]
});