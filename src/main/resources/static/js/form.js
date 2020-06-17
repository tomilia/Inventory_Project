$( "#search_form" ).on( "submit", function( event ) {
  event.preventDefault();
 $.post('/api/v1/search', $(this).serialize()).done(
    function( data ) {
    $(".compo_result").empty();
        //grid box
    $(".compo_result").append("<div class='container' style='margin-top:10px;padding: 5px 10px;color:#e8e8e8;background:#229922;border-radius:10px;'>"
        +"<h6>Product Name: "+data['member']['p_name']+"</h6>"
        +"<h6>Product Weight: "+data['member']['p_weight']+"</h6>"
        +"</div>");
        //table

        $(".compo_result").append("<table class='table table-striped'></table>");
        var header = "<tr>"+"<th>Id</th>"+"<th>Amount</th>"+"<th>Location</th>"+"</tr>";
        $(".table-striped").append(header);
        $.each(data['prod_loc_list'], function(i, item) {
            $(".table-striped").append("<tr>"
            +"<th>"+item['p_code']
            +"</th>"
            +"<th>"+item['amount']
            +"</th>"+"<th>"+
            item['location']
         +"</th>"+"</tr>");
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