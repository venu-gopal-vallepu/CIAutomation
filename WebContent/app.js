/*
 * This file is generated and updated by Sencha Cmd. You can edit this file as
 * needed for your application, but these edits will have to be merged by
 * Sencha Cmd when upgrading.
 */
Ext.application({
    name: 'Bluesage.TSI',

    extend: 'Bluesage.TSI.Application',

    requires: [
        'Bluesage.TSI.util.Utility',
        'Bluesage.TSI.view.login.Login'
        // 'Bluesage.TSI.view.main.Main',
       // 'Bluesage.TSI.view.MainViewPort'
    ],

    // The name of the initial view to create. With the classic toolkit this class
    // will gain a "viewport" plugin if it does not extend Ext.Viewport. With the
    // modern toolkit, the main view will be added to the Viewport.
    //
    mainView: 'Bluesage.TSI.view.login.Login'
    // mainView: 'Bluesage.TSI.view.main.Main'
	//mainView: 'Bluesage.TSI.view.MainViewPort'
    //-------------------------------------------------------------------------
    // Most customizations should be made to Bluesage.TSI.Application. If you need to
    // customize this file, doing so below this section reduces the likelihood
    // of merge conflicts when upgrading to new versions of Sencha Cmd.
    //-------------------------------------------------------------------------
});
