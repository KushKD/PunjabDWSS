$(document).on('click',function() {
     
    $("#search").dataTable( {
        "bProcessing": false,
        "bServerSide": false,
        "sort": "position",
        "sAjaxSource": "strutsPaginationAction",
        "aoColumns": [
            { "mData": "name" },
            { "mData": "position" },
            { "mData": "office" },
            { "mData": "phone" },
            { "mData": "start_date" },
            { "mData": "salary" },
             
        ]
    } );

} );