$("#searchButton").click(function() {
    let query = $("#searchBar").val()
    console.log(query)
    if (query) {
            window.location.href = `/search?q=${query}`
    }
});

$(".addPlant").on("submit",function(event){
    event.preventDefault();

    $.post("/addPlantToGarden",
        {

        //need to add id to store here and on the controller

            common_name: $(".common_name").attr("value"),
            image_url: $(".image_url").attr("value")
        }
    );


   // jQuery post request goes to route
   // do all the things
    console.log("----- prevent reload ------")
});

//TODO: We'll need to add the "add to garden button" on the details.html

$(".plant-details").on("click",function(){
    let id = $(this).attr("id");
    window.location.href = `/detail/${id}`
});
