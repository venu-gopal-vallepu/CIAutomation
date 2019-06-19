/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('Bluesage.TSI.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',

        'Bluesage.TSI.view.main.MainController',
        'Bluesage.TSI.view.main.MainModel',
        // 'Bluesage.TSI.view.main.List',
        'Bluesage.TSI.view.scripts.Scripts',
        'Bluesage.TSI.view.errorlogs.ErrorLogs',
        'Bluesage.TSI.view.uploadscripts.UploadScripts'
    ],

    plugins: [ 
        'viewport' 
    ],

    controller: 'main',
    viewModel: 'main',

    ui: 'navigation',

    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            bind: {
                text: '{name}'
            },
            flex: 0
        },
        style:'background-color:#404040'
        //iconCls: 'fa-th-list'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        },

        
        items: [{
            xtype: 'tbfill'
        }, {
            text: 'Logout',
            closable: false,
            rotation: 0,
            cls: 'tsi-btn-logout',
            textAlign: 'center',
            handler: 'onLogout'
        }]
    },
    tabBarPosition: 'left',
    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left'
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },

    // dockedItems: [
    //     {
    //     //title: 'Settings',
    //     //iconCls: 'fa-cog',
    //     xtype:'toolbar',
    //     dock:'top',
    //     height:89,
    //     border:true,
    // }
    // ],

    items: [{
        title: 'Scripts',
        iconCls: 'x-fa fa-file-text',
        // The following grid shares a store with the classic version's grid as well!
        items: [{
            xtype: 'scripts'
        }]
    }, {
        title: 'Error Logs',
        iconCls: 'x-fa fa-exclamation-triangle',
        items:[{
            xtype:'errorlogs',
            scrollable :true,
        }]
        // bind: {
        //     html: '{loremIpsum}'
        // }
    }, {
        title: 'Upload New Script',
        iconCls: 'x-fa fa-upload',
        items:[{
            xtype:'uploadscripts',
            scrollable :true,
        }]
        // bind: {
        //     html: '{loremIpsum}'
        // }
    }/*, {
        //title: 'Settings',
        //iconCls: 'fa-cog',
        xtype:'toolbar',
        docked:'top',
        height:80,
        bind: {
            html: '{loremIpsum}'
        }
    }*/]
});
