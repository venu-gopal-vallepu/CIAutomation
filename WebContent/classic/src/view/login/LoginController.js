Ext.define('Bluesage.TSI.view.login.LoginController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.login-login',

    /**
     * This method focusses the username field after the field is rendered.
     *
     * @param {Ext.form.field.Text} field The username field reference.
     */
    onUsernameAfterRender: function(field) {
        field.focus(false, 500);
    },

    /**
     * This method handles the login.
     * 
     * @param {Ext.button.Button} btn The submit button reference. 
     */
    onFormSubmit: function(btn) {
        var me = this,
            form = btn.up('form').getForm(),
            onAuthSuccess, onAuthFailure;

        // on successful authentication
        onAuthSuccess = function(form, action) {
            if (action.result.success === 'true') {
                me.getView().destroy();
                
                Ext.toast({
                    html: 'Successfully logged in',
                    align: 't'
                });

                // create the main view
                Ext.create('Bluesage.TSI.view.main.Main');
            } else {
                // call failure
                onAuthFailure(form, action);
            }
        };

        // on failed authentication
        onAuthFailure = function(form, action) {
            var passwordFld = form.owner.down('[reference=password]');

            // clearing the password field
            passwordFld.setValue(null);
            passwordFld.focus(false);

            Ext.Msg.alert('Authentication Failed', 'Invalid Username or Password.');
        };

        // authenticate and login if success
        if (form.isValid()) {
            // submit the form
            form.submit({
                success: onAuthSuccess,
                failure: onAuthFailure
            });
        }
    },

    /**
     * This method resets the login form
     * 
     * @param {Ext.button.Button} btn The reset button reference. 
     */
    onFormReset: function(btn) {
        btn.up('form').getForm().reset();
    }
});
