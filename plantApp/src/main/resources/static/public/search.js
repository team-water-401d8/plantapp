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
            common_name: $(".common_name").attr("value"),
            image_url: $(".image_url").attr("value")
        }
    );

   // jQuery post request goes to route
   // do all the things
    console.log("----- prevent reload ------")
});
