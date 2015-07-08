/*global $, SyntaxHighlighter*/

var oTable;


$(document).ready(function () {
	'use strict';

	oTable = $('#entrys_table').dataTable({
		"bStateSave": true,
		"sScrollY": "400px",
		'iDisplayLength': 50,
		"bJQueryUI": true,
		"bProcessing": true,
		"sAjaxSource": "resources/sources/deep.txt",
        "aoColumns": [
            { "mData": "engine" },
            { "mData": "browser" },
            { "mData": "platform.inner" },
            { "mData": "platform.details.0" },
            { "mData": "platform.details.1" }
        ]
    }).yadcf([{column_number : 0},
              {column_number : 1, filter_type: "auto_complete"},
              {column_number : 2, filter_type: "auto_complete"},
              {column_number : 3, filter_type: "range_number_slider", ignore_char: "-"},
              {column_number : 4}
		]);

	SyntaxHighlighter.all();

});