$.when($.ready).then(function () {
    console.log("Ready to go with jQuery");

    function saveUser(data) {
        // $.get();

        $.ajax({
            contentType: "application/json",
            dataType: "json",
            type: "PUT",
            url: "/api/users/first_name",
            data: data
        }).done(function (msg) {
            alert("Data Saved: " + msg);
        });
    }

    function executeSave(e) {
        e.preventDefault();
        var formData = formHelper($('#formFirstName').serializeArray());
        console.log(formData);
        saveUser(formData);
    }

    $("#updateFirstName").click(executeSave);

    function formHelper(unindexed_array) {

        var indexed_array = {};

        $.map(unindexed_array, function (n, i) {
            indexed_array[n['name']] = n['value'];
        });

        return JSON.stringify(indexed_array);
    }
});