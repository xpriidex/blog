$.when($.ready).then(function () {
    console.log("Ready to go with jQuery");

    function saveUser(data) {
        $.ajax({
            contentType: "application/json",
            dataType: "json",
            type: "PUT",
            url: "/api/users/update",
            data: data
        }).done(function (msg) {
            if (msg === 1)
            {
                alert("Data Updated: " + msg);
                location.href="/users/";
            }
            else if (msg === -1)
            {
                alert("Error data missing");
            }
            else if (msg === -2)
            {
                alert("Error data missing");
            }
            else if (msg === -3)
            {
                alert("Alias already in use");
            }
            else if (msg === -4)
            {
                alert("Email already in use");
            }
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