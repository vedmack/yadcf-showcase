/*global $, SyntaxHighlighter*/

var oTable;


$(document).ready(function () {
	'use strict';

	$('#example').dataTable().yadcf([
		{
			column_number: 1, filter_type: "date", datepicker_type: 'bootstrap-datetimepicker', date_format: 'HH:mm'
		},
		{
			column_number: 3, filter_type: "range_date", datepicker_type: 'bootstrap-datetimepicker', date_format: 'HH:mm'
		}
	]);

	SyntaxHighlighter.all();
});

