$.when($.ready).then(function () {
    console.log("Ready to go with jQuery");

    function saveUser(data) {
        // $.get();

        $.ajax({
            contentType: "application/json",
            dataType: "json",
            type: "PUT",
            url: "/api/users/update",
            data: data
        }).done(function (msg) {
            alert("Data Updated: " + msg);
            location.href="/users/";
        });
    }

    function executeSave(e) {
        e.preventDefault();
        var formData = formHelper($('#formUser').serializeArray());
        console.log(formData);
        saveUser(formData);
    }

    $("#update").click(executeSave);

    function formHelper(unindexed_array) {

        var indexed_array = {};

        $.map(unindexed_array, function (n, i) {
            indexed_array[n['name']] = n['value'];
        });

        return JSON.stringify(indexed_array);
    }
});