/*global $, SyntaxHighlighter*/

var oTable;


$(document).ready(function () {
	'use strict';

	oTable = $('#example').dataTable({
		"bStateSave": true
	}).yadcf([
	    {column_number : 0, filter_type: "multi_select", select_type: 'chosen'},
	    {column_number : 1, filter_type: "range_number"},
	    {column_number : 2, select_type: 'chosen', select_type_options: {no_results_text:'Can\'t find ->',search_contains: true}},
	    {column_number : 3, filter_type: "text"},
	    {column_number : 4, select_type: 'chosen', select_type_options : {disable_search:true}, column_data_type: "html", html_data_type: "text", filter_default_label: "Select tag"}]);

	SyntaxHighlighter.all();
});