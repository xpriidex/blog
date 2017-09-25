$.when($.ready).then(function () {
    console.log("Ready to go with jQuery");

    function saveUser(data) {
        console.log(data);

        $.ajax({
            contentType: "application/json",
            dataType: "json",
            method: "POST", //metodo que espera el backend
            url: "/api/users/", //url endpoint sprint controller
            data: data //data serializada a json object que espera el backend
        }).done(function (msg) { //se ejecuta cuando responda el backend
            alert("Data Saved: " + msg);
            location.href="/users/";
        });
    }

    function executeSave(e) {
        e.preventDefault();

        console.log($('#formUser').serializeArray());

        var formData = formHelper($('#formUser').serializeArray());

        saveUser(formData);
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