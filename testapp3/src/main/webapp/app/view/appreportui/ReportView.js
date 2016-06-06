Ext.define('Testapp3.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Testapp3.view.appreportui.ReportViewController',
	            'Testapp3.view.appreportui.datagrid.DataGridPanel',
	            'Testapp3.view.appreportui.datagrid.DataGridView',
	            'Testapp3.view.appreportui.querycriteria.QueryCriteriaView',
	            'Testapp3.view.appreportui.chart.ChartView',
	            'Testapp3.view.appreportui.datapoint.DataPointView',
	            'Testapp3.view.googlemaps.map.MapPanel',
	            'Testapp3.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
