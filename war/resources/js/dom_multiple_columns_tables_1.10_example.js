/*global $, SyntaxHighlighter*/


var oTable,
	oTable2;

$(document).ready(function () {
    'use strict';

    oTable = $('#example1').DataTable({
    });
    

    oTable2 = $('#example2').DataTable({
    });

	yadcf.initMultipleColumns(oTable, [{
		column_number: [0, 1], 
		filter_container_id: 'multi-column-filter-01', 
		filter_default_label: 'First table columns 1 and 2!'
	}]);
	
	yadcf.initMultipleTables([oTable, oTable2], [{
		column_number: [2], 
		filter_type: 'multi_select', 
		select_type: 'chosen', 
		filter_container_id: 'multi-table-filter-2', 
		filter_default_label: 'All tables column 3!', 
		select_type_options: {width: '190px'}
	},
	{
		column_number: [3], 
		filter_type: 'select', 
		select_type: 'chosen', 
		filter_container_id: 'multi-table-filter-3', 
		filter_default_label: 'All tables column 4!', 
		select_type_options: {width: '180px'}
	},
	{
		column_number: [4], 
		filter_container_id: 'multi-table-filter-4', 
		filter_default_label: 'All tables column 5!'
	}]);
    SyntaxHighlighter.all();

});