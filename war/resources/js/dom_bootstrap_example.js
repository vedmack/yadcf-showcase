/*global $, SyntaxHighlighter*/

var oTable;


$(document).ready(function () {
	'use strict';

	var datepickerDefaults = {
		showTodayButton: true,
		showClear: true
	};

	$('#example').dataTable().yadcf([
		{column_number: 4, filter_type: "range_date", datepicker_type: 'bootstrap-datetimepicker', date_format: 'YYYY-MM-DD', filter_plugin_options: datepickerDefaults}
	]);

	SyntaxHighlighter.all();
});

