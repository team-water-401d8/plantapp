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
<<<<<<< HEAD
            common_name: $(this).children("h2").text(),
            image_url: $(this).children("img").attr("src")
        }
    );

    console.log( $(this).children("h2").text() );
    console.log( $(this).children("img").attr("src") );
=======

        //need to add id to store here and on the controller

            common_name: $(".common_name").attr("value"),
            image_url: $(".image_url").attr("value")
        }
    );


   // jQuery post request goes to route
   // do all the things
    console.log("----- prevent reload ------")
>>>>>>> 4e87aa583f3b3924f6e151cc9d6db0f9924ee0b9
});

//TODO: We'll need to add the "add to garden button" on the details.html

$(".plant-details").on("click",function(){
    let id = $(this).attr("id");
    window.location.href = `/detail/${id}`
});
