Ext.define('Testapp3.view.databrowsercalendar.DBCalendar', {
	extend : 'Testapp3.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Testapp3.view.databrowsercalendar.DBCalendarController',
	             'Testapp3.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
