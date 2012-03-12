
var Application = null;

Ext.Loader.setConfig({
    enabled: true,
    paths: {
        'AM': 'AM'
    }
});


Ext.require('Ext.app.Application');
Ext.require('AM.controller.Users');


Ext.onReady(function() {
    Application = Ext.create('Ext.app.Application', {
        name: 'AM',

        controllers: [
            'Users'
        ],

        launch: function() {
            //include the tests in the test.html head
            jasmine.getEnv().addReporter(new jasmine.TrivialReporter());
            jasmine.getEnv().execute();
        }
    });
});