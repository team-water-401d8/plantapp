$("#searchButton").click(function() {
    let query = $("#searchBar").val()
    console.log(query)
    if (query) {
            window.location.href = `/search?q=${query}`
    }
})