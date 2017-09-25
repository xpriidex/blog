$.when( $.ready ).then(function() {
    console.log("Ready to go with jQuery");

    function saveComment(data){
        console.log(data);
        $.ajax({
                    contextType:"application/json",
                    dataType: "json",
                    method: "POST", //metodo que espera el backend
                    url: "/api/comments/create",//url endpoint sprint controller
                    data: data //data serializada a json object que espera el backend
                }).done(function (msg) { //se ejecuta cuando responda el backend
                    alert("Data Saved: " + msg);

                });
    }

    function executeSave (e){
        e.preventDefault();

        console.log($('#formComment').serializeArray());

        var formComment = formHelper($('#formComment').serializeArray());

        saveComment(formComment);
    }
    $("#save").click(executeSave);

    function formHelper(unindexed_array) {

            var indexed_array = {};
            $.map(unindexed_array, function (n, i) {
                indexed_array[n['name']] = n['value'];
            });

            return JSON.stringify(indexed_array);
        }
});