function loadCards()
{
    $.ajax(
        {
           url:'api/top',
           type:'GET',
           datatype: "json",
           success:function(response) {
               document.getElementById("container").innerHTML = "";
               $.each(response, function(key, value) {
                   document.getElementById("container").innerHTML =  document.getElementById("container").innerHTML + value;
               });
           },
           fail:function(response) {
            document.getElementById("card1").innerHTML = "<p>Failed to load Unicorn Data</p>";
            document.getElementById("card2").innerHTML = "<p>Failed to load Unicorn Data</p>";
            document.getElementById("card3").innerHTML = "<p>Failed to load Unicorn Data</p>";
           }
        });
}
