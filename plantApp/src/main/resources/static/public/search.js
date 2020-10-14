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

    $.post("/addPlantToGarden",                 // post data to /addPlantToGarden
        {
            common_name: $(this).children("h2").text(),
            image_url: $(this).children("img").attr("src")
        }
    );

    console.log( $(this).children("h2").text() );
    console.log( $(this).children("img").attr("src") );
});
