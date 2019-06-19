Ext.define('Bluesage.TSI.view.scripts.Scripts', {
    extend: 'Ext.Container',//form.Panel',
    xtype: 'scripts',
    requires: [
        'Ext.form.FieldSet',
        'Ext.window.MessageBox',
        'Bluesage.TSI.view.scripts.ScriptsController',
        //'Bluesage.TSI.view.scripts.TimePickerField'
    ],
    controller: 'scripts',
    width: '100%',
    height:'100%',
    scrollable :true,
    layout: {type:'vbox',align:'stretch'},
    // defaults:{
    //     margin: '20 0 0 0',
    // },
    viewModel:{
        data:{
            checked:true,
        },
        formulas:{
            getCheckedValue:function(get){
                if(get('checked')){
                    return '';
                }
            }
        }
    },
    items: [{
        xtype:'form',
        title: 'Script Deployment',
        defaults: {anchor: '100%',margin:10},
        layout: 'anchor',
        border:true,
        items: [{
            xtype:'combo',
            fieldLabel: 'Select Script',
            store: 'suites',
            queryMode: 'local',
            displayField: 'name',
            valueField: 'value',
            emptyText : 'Select Script',
            name: 'scriptValue',
            allowBlank: false,
            reference:'scriptName'
        },{
            xtype: 'hiddenfield',
            name: 'ScriptId',
            reference:'ScriptId'
            //value: data.ScriptId
        },{
            xtype: 'radiogroup',
            fieldLabel: 'Method ',
            // Arrange radio buttons into two columns, distributed vertically
            columns: 2,
            vertical: true,
            items: [
                { boxLabel: 'Run script now', name: 'method', inputValue: '1',checked: '{checked}',reference : 'rcncheck' },
                { boxLabel: 'Schedule automated scripts', name: 'method', inputValue: '2',reference : 'sascheck',}
            ]
        },{        
            xtype:'fieldcontainer',
            layout:{
                type:'vbox',
               // align:'stretchmax',
               // vertical :false,
            },
            items:[{
                xtype:'fieldcontainer',
                layout:'hbox',
                border:false,
                width:'100%',
                items:[{
                    xtype: 'label',
                    forId: 'scheduled',
                    text: 'Scheduled:',
                    width:80,
                    //columnWidth: 0.1,
                },{
                    xtype:'numberfield',
                    fieldLabel: 'Number of run times', 
                    name: 'executionCount',
                    flex:1,
                    reference:'executionCount',
                    bind : {
                        value:'{getCheckedValue}',
                        disabled : '{!sascheck.checked}'
                    },
                    minValue:1,
                    allowBlank: false
                    //columnWidth: 0.3,
                },{
                    xtype:'tbspacer',
                    width:10,
                },{
                    xtype: 'datefield', 
                    fieldLabel: 'Start date time', 
                    name: 'startDT',
                    flex:1,
                    reference:'startDT',
                    bind : {
                        disabled : '{!sascheck.checked}'
                    },
                    allowBlank: false
                    //columnWidth: 0.3,
                },{
                    xtype:'tbspacer',
                    width:10,
                },{
                    xtype:'combo',
                    store:'timepickerdata',
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'value',
                    name: 'startDTtime',
                    reference:'startDTtime',
                    allowBlank: false,
                    bind : {
                        disabled : '{!sascheck.checked}'
                    },
                }]
            },{
                xtype:'fieldcontainer',
                layout:'hbox',
                border:false,
                width:'100%',
                items:[{
                    xtype:'tbspacer',
                    width:80,
                    //columnWidth: 0.1,
                },{
                    xtype:'tbspacer',
                    flex:1,
                    //columnWidth: 0.3,
                },{
                    xtype:'tbspacer',
                    width:10,
                },{
                    xtype: 'datefield', 
                    fieldLabel: 'End date time', 
                    name: 'endDT',
                    flex:1,
                    reference:'endDT',
                    bind : {
                        disabled : '{!sascheck.checked}'
                    },
                    allowBlank: false
                    //columnWidth: 0.3,
                },{
                    xtype:'tbspacer',
                    width:10,
                },{
                    xtype:'combo',
                    store:'timepickerdata',
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'value',
                    name:'endDTtime',
                    reference:'endDTtime',
                    bind : {
                        disabled : '{!sascheck.checked}'
                    },
                    allowBlank: false
                   // labelWidth:50,
                }]
            }]
        },{
            xtype: 'filefield',
            name: 'photo',
            fieldLabel: 'Data select',
            emptyText:'Select a file',
            msgTarget: 'side',
            buttonText: 'Browse'
        },{
            xtype:'toolbar',
            docked:'bottom',
            padding:'20 0 0 0',
            layout:{
               type:'hbox',
               pack:'center'
            },
            items:[{
                xtype:'button',
                cls:'execute-btn',
                text:'<span style="color:#FFFFFF;font-weight:400;font-size:14px;">Execute</span>',
                //width:100,
                style:'background-color:#5FA2DD;',
                formBind:true,
                bind:{
                    disabled: '{sascheck.checked}'
                   // formBind:'{!sascheck.checked}'
                },
                handler:'onExecuteBtnClick'
            }]
        }],
    }, {
        //xtype:'fieldset',
        xtype:'form',
        border:true,
        // columnWidth: 0.45,
        title: 'Automated Script Schedule',
        //collapsible: true,
        // border:false,
        defaultType: 'textfield',
        defaults: {anchor: '100%'},
        layout: 'anchor',
        items: [{
            xtype: 'grid',
            store:'automatedscripts',
            columns: [
                { text: 'Start Date & Time', dataIndex: 'StartDate',flex:1,xtype: 'datecolumn',   format:'Y-m-d g:i A' },
                { text: 'End Date & Time', dataIndex: 'EndDate', flex:1, xtype: 'datecolumn',   format:'Y-m-d g:i A'},
                { text: 'Script', dataIndex: 'ScriptName',flex: 1.5  },
                { text: 'Added By', dataIndex: 'ScriptValue', flex:1},
                { xtype:'actioncolumn',text: 'Action',flex:0.5,items:[{
                    icon:'resources/images/erro.png',
                    tooltip:'Delete',
                    handler: 'onDeleteScriptClick'
                },{},{
					icon:'resources/images/edit.png',
					tooltip:'Edit',
					handler: 'onEditScriptClick'							
				}]}
            ],
            dockedItems: [{
                xtype: 'pagingtoolbar',
                store: 'automatedscripts', // same store GridPanel is using
                dock: 'bottom',
                displayInfo: true
            }],
            height: 300,
            width: 400,
        }]
    }]
});
