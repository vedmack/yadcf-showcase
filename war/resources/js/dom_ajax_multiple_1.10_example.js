/*global $, SyntaxHighlighter*/


var oTable,
	oTable2;

$(document).ready(function () {
    'use strict';

    
    oTable = $('#example').DataTable({
		//stateSave: true,
		fixedHeader: true
    });
    
    yadcf.init(oTable, [{
        column_number: 0
    }, {
        column_number: 1,
        filter_type: "range_number_slider"
    }, {
        column_number: 2,
        filter_type: "date"
    }, {
        column_number: 3,
        filter_type: "auto_complete",
        text_data_delimiter: ","
    }, {
        column_number: 4,
        column_data_type: "html",
        html_data_type: "text",
        filter_default_label: "Select tag"
    }]);

    oTable2 = $('#entrys_table').DataTable({
    	"responsive": true,
        "processing": true,
        "ajax": "resources/sources/deep.txt",
        "columns": [{
            "data": "engine"
        }, {
            "data": "browser"
        }, {
            "data": "platform.inner"
        }, {
            "data": "platform.details.0"
        }, {
            "data": "platform.details.1"
        }]
    });

    yadcf.init(oTable2, [{
        column_number: 0
    }, {
        column_number: 1,
        filter_type: "text",
        exclude: true,
        exclude_label: '!(not)'
    }, {
        column_number: 2,
        filter_type: "auto_complete"
    }, {
        column_number: 3,
        filter_type: "range_number_slider",
        ignore_char: "-"
    }, {
        column_number: 4
    }]);
    
	yadcf.exFilterColumn(oTable2, [[0, "Misc"]]);
	
	yadcf.initMultipleTables([oTable, oTable2], [{
		filter_container_id: 'multi-table-filter', filter_default_label: 'Filter all tables!'
	}]);
    SyntaxHighlighter.all();

});