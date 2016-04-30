/*global $, SyntaxHighlighter*/

var oTable;


$(document).ready(function () {
	'use strict';

	oTable = $('.entrys_table').dataTable({
		"processing": true,
		"serverSide": true,
	    "ajax": {
            "url": "entrys_table_server_side_source",
            "type": "POST"
	    },
	    "language": {
	        "infoFiltered": " - filtered from _MAX_ records"
	    }
    }).yadcf([
		{
			column_number: 0
		},
		{
			 column_number: 1,
			 filter_type: "text",
			 filter_delay: 500
		},
		{
			column_number: 2,
			filter_type: "auto_complete"
		},
		{
			column_number: 3,
			filter_type: "range_date",
			date_format: "mm/dd/yyyy",
			filter_delay: 500
		},
		{
			column_number: 4,
			filter_type: "range_number_slider",
			filter_delay: 500
		}
	]);

	yadcf.exFilterColumn(oTable, [[0, "Trident"]]);

	SyntaxHighlighter.all();

});