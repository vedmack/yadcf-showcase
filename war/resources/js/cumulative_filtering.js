/*global $, SyntaxHighlighter*/


var oTable;

$(document).ready(function () {
    'use strict';

    
    oTable = $('#example').DataTable();
    
    yadcf.init(oTable,
		[
			{
				column_number : 0,
				filter_type: "multi_select",
				select_type: 'select2'
			}, 
			{
				column_number: 3,
				filter_type: "auto_complete",
				text_data_delimiter: ","
			},
			{
				column_number : 4,
				filter_type: "multi_select",
				select_type: 'select2',
				column_data_type: "html",
				html_data_type: "text",
				filter_default_label: "Select tag"
			}
		],
		{
			cumulative_filtering: true
		}
	);

    SyntaxHighlighter.all();

});