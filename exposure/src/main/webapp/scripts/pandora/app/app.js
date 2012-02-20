Ext.Loader.setConfig({
        enabled: true,
        paths: {
            'Ext': '/scripts/extjs/ext-all-dev.js',
            'Pandora': 'scripts/pandora/app'
        }
    });


Ext.require('Pandora.store.Stations');
Ext.require('Pandora.store.RecentSongs');
Ext.require('Pandora.store.SearchResults');

Ext.require('Pandora.model.Station');
Ext.require('Pandora.model.Song');

Ext.require('Pandora.controller.Song');
Ext.require('Pandora.controller.Station');


Ext.require('Pandora.view.NewStation');
Ext.require('Pandora.view.RecentlyPlayedScroller');
Ext.require('Pandora.view.SongControls');
Ext.require('Pandora.view.SongInfo');
Ext.require('Pandora.view.StationsList');
Ext.require('Pandora.view.Viewport');

Ext.application({
    name: 'Pandora',
    
    autoCreateViewport: true,
    
    models: ['Station', 'Song'],    
    stores: ['Stations', 'RecentSongs', 'SearchResults'],
    controllers: ['Station', 'Song']
});