Ext.define('Exposure.view.Viewport', {
	extend : 'Ext.container.Viewport',
	layout : {
		type : 'fit',
		padding : 0
	},
	defaults : {
		split : true
	},

	requires : [//'Exposure.view.Header'
	//,'Exposure.view.AddressBar',
	//'Exposure.view.Map', 'Exposure.view.ResultsBar',
	//'Exposure.view.ToolsBar'
	],

	initComponent : function() {
		this.items = {
			dockedItems : [{
				dock : 'top',
				//region: 'north',
				xtype : 'toolbar',
				height : 33,
				// contentEl: 'header'
				items : [{
					xtype : 'button',
					text : 'Logout',
					handler : function() {
						location.href = 'logout';
					}
				}]
			}],
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			items : [{
				width : 250,
				xtype : 'panel',
				id : 'west-region',
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				items : []
			}, {
				xtype : 'container',
				flex : 1,
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				items : []
			}]
		};

		this.callParent();
	}
});
