Ext.define('Bluesage.TSI.view.login.Login', {
    extend: 'Ext.container.Container',
    alias: 'widget.login-login',

    requires: [
        'Ext.form.Panel',
        'Ext.window.Toast',

        'Bluesage.TSI.view.main.Main',
        'Bluesage.TSI.view.login.LoginController'
    ],

    controller: 'login-login',

    reference: 'loginview',

    layout: {
        type: 'box',
        pack: 'center',
        align: 'middle'
    },

    cls: 'tsi-login',

    items: [{
        xtype: 'form',

        cls: 'tsi-login-form',

        layout: 'anchor',
        defaults: {
            anchor: '100%'
        },

        // The url to authenticate login
        // url: 'http://localhost:8080/ScriptsApp/login',
        url: Utility.api.LOGIN,
        // url: 'http://192.168.1.214:8080/Bluesage.TSI/login',

        title: 'Login',
        defaultButton: 'submit',

        defaultType: 'textfield',
        items: [{
            fieldLabel: 'Username',
            reference: 'username',
            name: 'username',
            value: 'BlueSageDev',
            allowBlank: false,
            listeners: {
                afterrender: 'onUsernameAfterRender'
            }
        }, {
            fieldLabel: 'Password',
            reference: 'password',
            name: 'password',
            value: 'Blu3D3v13',
            inputType: 'password',
            allowBlank: false
        }],

        buttons: [{
            text: 'Reset',
            reference: 'reset',
            handler: 'onFormReset'
        }, {
            text: 'Submit',
            formBind: true,
            disabled: true,
            reference: 'submit',
            handler: 'onFormSubmit'
        }]
    }]
});
