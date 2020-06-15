$( "#search_form" ).on( "submit", function( event ) {
  event.preventDefault();
 $.post('/api/v1/search', $(this).serialize()).done(
    function( data ) {
        $( ".table-striped" ).empty();
        $(".table-striped").append("<tr>"+"<th>Id</th>"+"<th>Amount</th>"+"<th>Location</th>"+"</tr>");
        $.each(data, function(i, item) {
            $(".table-striped").append("<tr>"+"<th>"+data[i].p_code+"</th>"+"<th>"+data[i].amount+"</th>"+"<th>"+data[i].location+"</th>"+"</tr>");
        });
        //$(".table-striped").append("<tr>"+"<th>"+data['p_code']+"</th>"+"<th>"+data['p_name']+"</th>"+"</tr>");
    });
});
$( "#transfer" ).on( "submit", function( event ) {
  event.preventDefault();
 $.post('/api/v1/transfer', $(this).serialize()).done(
    function( data ) {
    alert(data);
        //$(".table-striped").append("<tr>"+"<th>"+data['p_code']+"</th>"+"<th>"+data['p_name']+"</th>"+"</tr>");
    });
});