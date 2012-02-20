Ext.Loader.setConfig({
        enabled: true,
        paths: {
            'Ext': '/scripts/extjs/ext-all-dev.js',
            'Exposure': 'scripts/exposure/app'
        }
    });

Ext.require('Exposure.view.Viewport');

Ext.application({
    name: 'Exposure',
    
    autoCreateViewport: true,
    
    models: [],    
    stores: [],
    controllers: []
});