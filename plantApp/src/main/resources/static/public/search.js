$("#searchButton").click(function() {
    let query = $("#searchBar").val()
    console.log(query)
    if (query) {
            window.location.href = `/search?q=${query}`
    }
});

$(".addPlant").on("submit",function(event){
    event.preventDefault();                     // prevent page reload
    console.log(event.target.id);
    console.log($(this).children("select").children("option:selected").val())
    $.post("/addPlantToGarden",                 // post data to /addPlantToGarden
        {
            common_name: $(this).children("#commonName").val(),
            image_url: $(this).children("#imageName").val(),
            plantIdFromApi: $(this).children("button").attr("id"),
            gardenId: $(this).children("select").children("option:selected").val()
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
