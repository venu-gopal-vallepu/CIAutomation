Ext.define('Bluesage.TSI.view.uploadscripts.UploadScripts', {
    extend: 'Ext.form.Panel',
    xtype: 'uploadscripts',
    requires: [
        'Ext.form.FieldSet',
        'Ext.window.MessageBox',
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
        title: 'Upload New Script',
        defaults: {anchor: '100%'},
        layout: 'anchor',
        items: [{
            xtype:'combo',
            anchor: '50%',
            fieldLabel: 'Select Script',
            store: Ext.create('Ext.data.Store', {
                fields: ['abbr', 'name'],
                data : [
                    {"abbr":"AL", "name":"Alabama"},
                    {"abbr":"AK", "name":"Alaska"},
                    {"abbr":"AZ", "name":"Arizona"}
                    //...
                ]
            }),
            queryMode: 'local',
            displayField: 'name',
            valueField: 'abbr',
            emptyText : 'Select Script',
            name: 'firstName',
            allowBlank: false
        },{
            xtype:'form',
            layout:'anchor',
            defaults:{
                anchor:'100%',
            },
            items:[{
                xtype:'form',
                layout:'hbox',
                // defaults:{
                //     columnWidth:0.2
                // },
                padding:'10 0 0 0',
                style:'background-color:#F6F6F6 !important',
                items: [{
                    xtype:'tbspacer',width:105,
                },{
                    xtype: 'filefield',
                    name: 'photo',
                    //fieldLabel: 'Photo',
                    //labelWidth: 50,
                    //columnWidth:0.75,
                    emptyText:'Select a file',
                    flex:1,
                    msgTarget: 'side',
                    allowBlank: false,
                    //anchor: '100%',
                    buttonText: 'Browse'
                }],
            },{
                xtype:'toolbar',
                docked:'bottom',
                padding:'20 0 0 0',
                layout:{
                   type:'hbox',
                   pack:'center'
                },
                style:'background-color:#F6F6F6;',
                items:[{
                    xtype:'button',
                    text:'<span style="color:#FFFFFF;font-weight:400;font-size:14px;">Upload File</span>',
                    //width:100,
                    style:'background-color:#5FA2DD;',
                    listeners:{
                       click:'onAddButtonClick'
                    }
               },{
                    xtype:'button',
                    text:'<span style="color:#FFFFFF;font-weight:400;font-size:14px;">Cancel</span>',
                   // width:100,
                    style:'background-color:#5FA2DD;',
                    listeners:{
                       click:'onAddButtonClick'
                    }
               }]
            }]
        }]
    }]
});
